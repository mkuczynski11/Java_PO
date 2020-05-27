package life.game.my.solution.files.animals;

import life.game.my.solution.files.*;

import java.awt.*;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.Random;

public class Fox extends Animal
{
    private static final int LIS_SILA = 3;
    private static final int LIS_INICJATYWA = 7;

    public Fox(Position position, World world){
        super(LIS_SILA, LIS_INICJATYWA,world.define.SYMBOL_LIS,position, world);
    }

    @Override
    public String getName(){
        return "Lis";
    }
    @Override
    public boolean isSameSpecies(Organism organism){
        return (organism instanceof Fox);
    }
    @Override
    public Organism child(Position position) {return new Fox(position, getWorld());}
    @Override
    public void save(){

    }
    @Override
    public Color getColor(){
        return Color.orange;
    }
    @Override
    public Position generateRandomPosition(Position position){
        ArrayList<Position> combinations = generateCombinations(position);
        combinations.add(new Position(position.getX(),position.getY()));
        Random r = new Random();
        while(true)
        {
            if(combinations.size() == 0) break;
            int choice = r.nextInt(combinations.size());
            Organism tmp = getWorld().getOrganism(combinations.get(choice));
            if(tmp instanceof Ground || tmp instanceof Fox || tmp.getStrength() < getStrength()){
                return combinations.get(choice);
            }
            combinations.remove(choice);
        }
        return position;
    }
}
