package life.game.my.solution.files.animals;

import life.game.my.solution.files.Animal;
import life.game.my.solution.files.Organism;
import life.game.my.solution.files.Position;
import life.game.my.solution.files.World;

import java.awt.*;

public class Human extends Animal
{
    private static final int CZLOWIEK_SILA = 5;
    private static final int CZLOWIEK_INICJATYWA = 4;

    public Human(Position position, World world){
        super(CZLOWIEK_SILA, CZLOWIEK_INICJATYWA,world.define.SYMBOL_CZLOWIEK,position, world);
    }

    @Override
    public String getName(){
        return "Czlowiek";
    }
    @Override
    public boolean isSameSpecies(Organism organism){
        return (organism instanceof Human);
    }
    @Override
    public Organism child(Position position) {return new Human(position, getWorld());}
    @Override
    public void save(){

    }
    @Override
    public Color getColor(){
        return Color.ORANGE;
    }
    @Override
    public Position generateRandomPosition(Position position){
        Position p = new Position(position.getX(),position.getY());
        return p;
    }
}
