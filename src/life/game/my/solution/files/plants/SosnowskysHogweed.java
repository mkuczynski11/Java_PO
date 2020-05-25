package life.game.my.solution.files.plants;

import life.game.my.solution.files.*;

import java.awt.*;
import java.util.ArrayList;

public class SosnowskysHogweed extends Plant
{
    private static final int BARSZCZSOSNOWSKIEGO_SILA = 0;

    public SosnowskysHogweed(Position position, World world){
        super(BARSZCZSOSNOWSKIEGO_SILA,world.define.SYMBOL_BARSZCZSOSNOWSKIEGO,position,world);
    }

    @Override
    public String getName(){
        return "BarszczSosnowkiego";
    }
    @Override
    public Organism child(Position position){
        return new SosnowskysHogweed(position,getWorld());
    }
    @Override
    public void save(){

    }
    @Override
    public Color getColor(){
        return Color.CYAN;
    }
    @Override
    public boolean collision(Organism enemy){
        enemy.setAlive(false);
        enemy.setReady(false);
        getWorld().addToKill(enemy);
        getWorld().getScreen().addAction(getWorld().getCommentator().announceConsume(enemy, this));
        setAlive(false);
        setReady(false);
        getWorld().addToKill(this);
        return false;
    }
    @Override
    public void action(){
        if(isAlive() && isReady()) {
            ArrayList<Position> combinations = generateCombinations(getPosition());
            while (true) {
                if (combinations.size() == 0) break;
                Organism tmp = getWorld().getOrganism(combinations.get(0));
                if (tmp instanceof Animal) {
                    tmp.setAlive(false);
                    tmp.setReady(false);
                    getWorld().addToKill(tmp);
                    getWorld().getScreen().addAction(getWorld().getCommentator().announceKill(this, tmp));
                }
                combinations.remove(0);
            }
            super.action();
        }
    }
}
