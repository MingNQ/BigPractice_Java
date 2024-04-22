package Controller.Projectile;

import Model.Laser;
import View.GamePanel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class LaserController extends ProjectileController {
    public final double PI = Math.PI;
    public double angle; // in radian
    public Rectangle2D rectangle;

    // Position that is center of image and will at after rotate
    double backX;
    double backY;
    double recBackX;
    double recBackY;

    public LaserController(GamePanel gp) {
        super(gp);
        this.gp = gp;
        this.rd = new Random();
        this.projectile = new Laser();

        setDefaultValue();
        projectile.getImage();
    }

    // Set angle will rotate
    public void setAngle() {
        double argX = projectile.targetPosX - projectile.posX;
        double argY = projectile.targetPosY - projectile.posY;

        angle = Math.atan2(argY, argX) - PI/2;
    }

    // Set where target distance for projectile move to
    public void setTargetDistance() {
        super.setTargetDistance();
    }

    // Set default value when call
    public void setDefaultValue() {
        spawn();
        projectile.state = "idle"; // idle that do nothing
        setAngle();
    }

    public void setHitBox() {
        double projectileRecW = projectile.recW * 2;
        double projectileRecH = projectile.imageHeight;
//        projectile.hitbox = new Rectangle2D.Double(projectileRecPosX, projectileRecPosY, projectileRecW, projectileRecH);
//        rectangle = new Rectangle2D.Double(projectileRecPosX, projectileRecPosY, projectileRecW, projectileRecH);

        recBackX = -(projectile.recW) * Math.cos(angle);
        recBackY = -(projectile.recW) * Math.sin(angle);

        projectile.hitbox = new Rectangle2D.Double(projectile.posX + recBackX, projectile.posY + recBackY, projectileRecW, projectileRecH);
    }

    // Spawn from random side
    public void spawn() {
        // Random side
        projectile.spawnSide = rd.nextInt(4);
        projectile.targetPosX = gp.playerController.player.posX;
        projectile.targetPosY = gp.playerController.player.posY;

        switch (projectile.spawnSide) {
            case 0: // Spawn from up
                projectile.direction = "down";
                projectile.posX = rd.nextDouble(gp.screenWidth);
                projectile.posY = -1;
                break;
            case 1: // Spawn from down
                projectile.direction = "up";
                projectile.posX = rd.nextInt(gp.screenWidth);
                projectile.posY = gp.screenHeight;
                break;
            case 2: // Spawn from left
                projectile.direction = "right";
                projectile.posX = -1;
                projectile.posY = rd.nextInt(gp.screenHeight);
                break;
            case 3: // Spawn from right
                projectile.direction = "left";
                projectile.posX = gp.screenWidth;
                projectile.posY = rd.nextInt(gp.screenHeight);
                break;
        }
    }

    public void update() {
        if (gp.playerController.player.posY > 450)
            projectile.state = "fire";
        else {
            setHitBox();
            projectile.state = "idle";
        }

        projectile.setAnimations();

//        System.out.println("Rectangle: " + rectangle.getX() + " " + rectangle.getY());
//        System.out.println("Project: " + projectile.posX + " " + projectile.posY);
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        image = projectile.drawAnimation();

        AffineTransform at = new AffineTransform();
        AffineTransform backup = g2.getTransform();
        // Rotate image
        backX = -(image.getWidth()/2) * Math.cos(angle);
        backY = -(image.getWidth()/2) * Math.sin(angle);
        at.translate(projectile.posX + backX, projectile.posY + backY);
        at.rotate(angle);

        g2.drawImage(image, at, null);

//        at.translate(projectile.posX + backX, projectile.posY + backY);
//        g2.setTransform(at);
        g2.rotate(angle, projectile.posX + recBackX, projectile.posY + recBackY);
        g2.fill(projectile.hitbox);

        g2.setTransform(backup);
    }

    public Point2D[] getVertices(Rectangle2D rect, double angle) {
        AffineTransform at = AffineTransform.getRotateInstance(angle, rect.getCenterX(), rect.getCenterY());
        Point2D[] vertices = new Point2D[4];
        vertices[0] = new Point2D.Double(rect.getMinX(), rect.getMinY());
        vertices[1] = new Point2D.Double(rect.getMaxX(), rect.getMinY());
        vertices[2] = new Point2D.Double(rect.getMaxX(), rect.getMaxY());
        vertices[3] = new Point2D.Double(rect.getMinX(), rect.getMaxY());
        for (Point2D vertex : vertices) {
            at.transform(vertex, vertex);
        }
        return vertices;
    }

    public boolean satCollision(Rectangle2D rect1, double angle1, Rectangle2D rect2, double angle2) {
        // Lấy các đỉnh của hai hình chữ nhật
        Point2D[] vertices1 = getVertices(rect1, angle1);
        Point2D[] vertices2 = getVertices(rect2, angle2);

        // Kiểm tra va chạm trên tất cả các trục của hai hình chữ nhật
        for (int i = 0; i < 4; i++) {
            double axisX = vertices1[i].getX() - vertices1[(i + 1) % 4].getX();
            double axisY = vertices1[i].getY() - vertices1[(i + 1) % 4].getY();

            // Tính toán chiều dài của trục
            double axisLength = Math.sqrt(axisX * axisX + axisY * axisY);

            // Chuẩn hóa trục
            axisX /= axisLength;
            axisY /= axisLength;

            // Tính toán chiều dài chiếu của hai hình chữ nhật trên trục
            double min1 = Double.POSITIVE_INFINITY, max1 = Double.NEGATIVE_INFINITY;
            double min2 = Double.POSITIVE_INFINITY, max2 = Double.NEGATIVE_INFINITY;

            for (Point2D vertex : vertices1) {
                double projection = vertex.getX() * axisX + vertex.getY() * axisY;
                min1 = Math.min(min1, projection);
                max1 = Math.max(max1, projection);
            }

            for (Point2D vertex : vertices2) {
                double projection = vertex.getX() * axisX + vertex.getY() * axisY;
                min2 = Math.min(min2, projection);
                max2 = Math.max(max2, projection);
            }

            // Nếu có một trục mà hai hình chữ nhật không giao nhau, thì không có va chạm
            if (max1 < min2 || max2 < min1) {
                return false;
            }
        }

        // Nếu không tìm thấy trục nào mà hai hình chữ nhật không giao nhau, thì có va chạm
        return true;
    }
}
