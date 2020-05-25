package life.game.my.solution.files.plants;

import life.game.my.solution.files.Organism;
import life.game.my.solution.files.Plant;
import life.game.my.solution.files.Position;
import life.game.my.solution.files.World;

import java.awt.*;

public class DeadlyNightshade extends Plant
{
    private static final int WILCZEJAGDOY_SILA = 99;

    public DeadlyNightshade(Position position, World world){
        super(WILCZEJAGDOY_SILA,world.define.SYMBOL_WIKLCZEJAGODY,position,world);
    }

    @Override
    public String getName(){
        return "WilczeJagody";
    }
    @Override
    public Organism child(Position position){
        return new DeadlyNightshade(position,getWorld());
    }
    @Override
    public void save(){

    }
    @Override
    public Color getColor(){
        return Color.DARK_GRAY;
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
}
