package life.game.my.solution.files.animals;

import life.game.my.solution.files.Animal;
import life.game.my.solution.files.Organism;
import life.game.my.solution.files.Position;
import life.game.my.solution.files.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Human extends Animal
{
    private static final int CZLOWIEK_SILA = 5;
    private static final int CZLOWIEK_INICJATYWA = 4;
    private int cooldown;
    private int turnsLeft;

    public Human(Position position, World world){
        super(CZLOWIEK_SILA, CZLOWIEK_INICJATYWA,world.define.SYMBOL_CZLOWIEK,position, world);
        this.cooldown = 5;
        this.turnsLeft = 0;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getTurnsLeft() {
        return turnsLeft;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public void setTurnsLeft(int turnsLeft) {
        this.turnsLeft = turnsLeft;
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
    @Override
    public void action(){
        if(isAlive() && isReady()) {
            if (getWorld().isUseAbility()) {
                if (getCooldown() == 0 && getTurnsLeft() == 0) {
                    setTurnsLeft(5);
                }
            }
            if (getTurnsLeft() > 0) {
                ability();
                setTurnsLeft(getTurnsLeft() - 1);
                if (getTurnsLeft() == 0) setCooldown(5);
            }
            if (getCooldown() > 0) {
                setCooldown(getCooldown() - 1);
            }
        }
        getWorld().setUseAbility(false);
        super.action();
    }

    private void ability(){
        ArrayList<Position> combinations = generateCombinations(getPosition());
        while(true)
        {
            if(combinations.size() == 0 ) break;
            Organism tmp = getWorld().getOrganism(combinations.get(0));
            if(tmp.isAlive()){
                tmp.setAlive(false);
                tmp.setReady(false);
                getWorld().addToKill(tmp);
                getWorld().getLogs().addLog(getWorld().getCommentator().announceKill(this,tmp));
            }
            combinations.remove(0);
        }
    }
}
