package life.game.my.solution.files.plants;

import life.game.my.solution.files.Organism;
import life.game.my.solution.files.Plant;
import life.game.my.solution.files.Position;
import life.game.my.solution.files.World;

import java.awt.*;

public class Milt extends Plant
{
    private static final int MLECZ_SILA = 0;
    private static final int MLECZ_TIMES = 3;

    public Milt(Position position, World world){
        super(MLECZ_SILA,world.define.SYMBOL_MLECZ,position,world);
        this.setSeedTimes(MLECZ_TIMES);
    }

    @Override
    public String getName(){
        return "Mlecz";
    }
    @Override
    public Organism child(Position position){
        return new Milt(position,getWorld());
    }
    @Override
    public void save(){

    }
    @Override
    public Color getColor(){
        return Color.yellow;
    }
}
