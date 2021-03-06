package life.game.my.solution.files.animals;

import life.game.my.solution.files.*;

import java.awt.*;

public class Wolf extends Animal
{
    private static final int WILK_SILA = 9;
    private static final int WILK_INICJATYWA = 5;

    public Wolf(Position position, World world){
        super(WILK_SILA, WILK_INICJATYWA,world.define.SYMBOL_WILK,position, world);
    }

    @Override
    public String getName(){
        return "Wilk";
    }
    @Override
    public boolean isSameSpecies(Organism organism){
        return (organism instanceof Wolf);
    }
    @Override
    public Organism child(Position position) {return new Wolf(position, getWorld());}
    @Override
    public Color getColor(){
        return Color.black;
    }

}
