package life.game.my.solution.files.animals;

import life.game.my.solution.files.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Antelope extends Animal
{
    private static final int ANTYLOPA_SILA = 4;
    private static final int ANTYLOPA_INICJATYWA = 4;
    private static final int ANTYLOPA_RANGE = 2;

    public Antelope(Position position, World world){
        super(ANTYLOPA_SILA, ANTYLOPA_INICJATYWA,world.define.SYMBOL_ANTYLOPA,position, world);
        this.setMovementRange(ANTYLOPA_RANGE);
    }

    @Override
    public String getName(){
        return "Antylopa";
    }
    @Override
    public boolean isSameSpecies(Organism organism){
        return (organism instanceof Antelope);
    }
    @Override
    public Organism child(Position position) {return new Antelope(position, getWorld());}
    @Override
    public void save(){

    }
    @Override
    public Color getColor(){
        return Color.blue;
    }
    @Override
    public boolean collision(Organism enemy){
        Random r = new Random();
        if(r.nextInt(101) < 50){
            ArrayList<Position> combinations = generateCombinations(getPosition());
            while(true)
            {
                if(combinations.size() == 0) break;
                int choice = r.nextInt(combinations.size());
                Organism tmp = getWorld().getOrganism(combinations.get(choice));
                if(tmp instanceof Ground || !(tmp.isAlive())){
                    getWorld().moveOrganism(getPosition(),combinations.get(choice));
                    getWorld().getLogs().addLog(getWorld().getCommentator().announceRunaway(enemy,this));
                    return true;
                }
                combinations.remove(choice);
            }
        }
        return super.collision(enemy);
    }
}
