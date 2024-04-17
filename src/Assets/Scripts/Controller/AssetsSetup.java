package Controller;

import Controller.Monster.MonsterPool;
import View.GameView;

public class AssetsSetup {
    GameView gp;
    MonsterPool monsterPool;

    public AssetsSetup(GameView gp) {
        this.gp = gp;
        monsterPool = new MonsterPool(gp);
    }

    public void setUpMonster() {
        for (int i = 0; i < 5; i++) {
            gp.monsterList.add(monsterPool.getMonster());
        }
    }
}
