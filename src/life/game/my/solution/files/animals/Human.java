package life.game.my.solution.files.animals;

import life.game.my.solution.files.Animal;
import life.game.my.solution.files.Organism;
import life.game.my.solution.files.Position;
import life.game.my.solution.files.World;

import java.awt.*;
import java.awt.event.KeyEvent;

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
        int code = getWorld().getCurrentHumanKey();
        getWorld().setCurrentHumanKey(-1);
        if(code == KeyEvent.VK_UP){
            if(position.getY() != 0){
                return new Position(position.getX(),position.getY()-1);
            }
        }
        else if (code == KeyEvent.VK_DOWN){
            if(position.getY() != getWorld().getScreenY() - 1) {
                return new Position(position.getX(), position.getY() + 1);
            }
        }
        else if (code == KeyEvent.VK_LEFT){
            if(position.getX() != 0){
                return new Position(position.getX() - 1,position.getY());
            }
        }
        else if (code == KeyEvent.VK_RIGHT){
            if(position.getX() != getWorld().getScreenX() - 1) {
                return new Position(position.getX() + 1 , position.getY());
            }
        }
        return super.generateRandomPosition(position);
    }
}
