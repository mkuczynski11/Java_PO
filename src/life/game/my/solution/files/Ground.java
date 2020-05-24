package life.game.my.solution.files;

import life.game.my.solution.files.animals.Antelope;
import life.game.my.solution.files.animals.Wolf;

import java.awt.*;

public class Ground extends Organism
{
    public Ground(Position position, World world){
        super(0,0, "g",position,world);
        setAlive(false);
        setReady(false);
    }
    public Ground(int age, int strength, Position position, World world){
        this(position,world);
    }

    @Override
    public void action(){

    }
    @Override
    public String getName(){
        return "Ground";
    }
    @Override
    public Organism copy(Position position){
        return new Ground(position, getWorld());
    }
    @Override
    public Organism child(Position position) {return new Ground(position, getWorld());}
    @Override
    public void save(){

    }
    @Override
    public boolean collision(Organism enemy){
        return true;
    }
    @Override
    public Color getColor(){
        return Color.lightGray;
    }
}
