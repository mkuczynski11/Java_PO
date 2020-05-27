package life.game.my.solution.files.plants;

import life.game.my.solution.files.Organism;
import life.game.my.solution.files.Plant;
import life.game.my.solution.files.Position;
import life.game.my.solution.files.World;

import java.awt.*;

public class Grass extends Plant
{
    private static final int TRAWA_SILA = 0;

    public Grass(Position position, World world){
        super(TRAWA_SILA,world.define.SYMBOL_TRAWA,position,world);
    }

    @Override
    public String getName(){
        return "Trawa";
    }
    @Override
    public Organism child(Position position){
        return new Grass(position,getWorld());
    }
    @Override
    public Color getColor(){
        return Color.green;
    }
}
