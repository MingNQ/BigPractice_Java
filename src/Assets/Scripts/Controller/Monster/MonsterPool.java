package Controller.Monster;

import View.GameView;

import java.util.LinkedList;
import java.util.Queue;

public class MonsterPool {
    GameView gp;
    public Queue<MonsterController> pool;

    public MonsterPool(GameView gp) {
        this.gp = gp;
        this.pool = new LinkedList<>();

        for(int i = 0; i < 5; i++) {
            MonsterController monsterController = new MonsterController(gp);
            this.pool.add(monsterController);
        }
        System.out.println("Queue: " + pool);
    }

    public MonsterController getMonster() {
        if (pool.isEmpty()) {
            return new MonsterController(gp);
        } else {
            MonsterController monster = pool.poll();
            return monster;
        }
    }

    public void returnMonster(MonsterController monster) {
        pool.add(monster);
    }
}
