package Controller.Tool;

import java.io.*;
import java.util.Scanner;

public class DataStorage {
    File file;
    public DataStorage() {
        this.file = new File("./src/Assets/Sprites/SaveGame/data.txt");
    }

    public void saveScore(double highestScore) {
        try {
            PrintWriter printWriter = new PrintWriter(file);

            printWriter.println(highestScore);
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double readScore() {
        String s = "";
        try {
            Scanner sc = new Scanner(file);

            s = sc.nextLine();

            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Double.parseDouble(s);
    }

    public static void main(String[] args) {
        DataStorage data = new DataStorage();

        System.out.println(data.readScore());
    }
}
