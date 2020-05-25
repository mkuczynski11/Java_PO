package life.game.my.solution.files.plants;

import life.game.my.solution.files.Organism;
import life.game.my.solution.files.Plant;
import life.game.my.solution.files.Position;
import life.game.my.solution.files.World;

import java.awt.*;

public class Guarana extends Plant
{
    private static final int GUARANA_SILA = 0;

    public Guarana(Position position, World world){
        super(GUARANA_SILA,world.define.SYMBOL_GUARANA,position,world);
    }

    @Override
    public String getName(){
        return "Guarana";
    }
    @Override
    public Organism child(Position position){
        return new Guarana(position,getWorld());
    }
    @Override
    public void save(){

    }
    @Override
    public Color getColor(){
        return Color.red;
    }
    @Override
    public boolean collision(Organism enemy){
        enemy.setStrength(enemy.getStrength()+3);
        getWorld().getScreen().addAction(getWorld().getCommentator().announceStrengthRise(enemy));
        return super.collision(enemy);
    }
}
