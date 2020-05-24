package life.game.my.solution.files;

import java.util.Random;

public abstract class Animal extends Organism
{
    private final int BASE_RANGE = 1;
    private final int BASE_CHANCE = 100;
    private int movementRange;
    private double movementChanse;

    public Animal(int strength, int iniciative, String symbol, Position position,
                  World world){
        super(strength, iniciative, symbol, position, world);
        this.movementRange = BASE_RANGE;
        this.movementChanse = BASE_CHANCE;
    }
    public Animal(int age, int strength, int iniciative, String symbol, Position position, World world){
        this(strength,iniciative,symbol,position,world);
        this.setAge(age);
    }

    public int getMovementRange() {return movementRange;}
    public double getMovementChanse() {return movementChanse;}

    public void setMovementRange(int movementRange){
        this.movementRange= movementRange;
    }
    public void setMovementChanse(double movementChanse){
        this.movementChanse = movementChanse;
    }

    private boolean isAbleToMove(){
        Random r = new Random();
        if(r.nextDouble() < movementChanse) {return true;}
        else return false;
    }

    private boolean move(){
        Position heading = new Position(getPosition().getX(), getPosition().getY());
        heading.generateRandomPosition(getWorld().getScreenX(), getWorld().getScreenY());
        if(heading.getX() == getPosition().getX() && heading.getY() == getPosition().getY()){
            //oglos przesuniecie w miejscu
            return true;
        }
        Organism tmp = getWorld().getOrganism(heading);
        if(tmp instanceof Ground || !tmp.isAlive())
        {
            //oglos przesuniecie
            getWorld().moveOrganism(getPosition(), heading);
            return true;
        }
        else if(isSameSpecies(tmp) && tmp.isAlive())
        {
            if(!isParent() && !tmp.isParent()){
                //oglos rozmnozenie
                breed(tmp);
                return false;
            }
            return true;
        }
        else if (tmp.isAlive())
        {
            if(tmp.collision(this)){
                //oglos przesuniecie
                getWorld().moveOrganism(getPosition(), heading);
            }
            return false;
        }
        return true;
    }

    private void breed(Organism lover){
        //rozmnazanie
    }

    public abstract boolean isSameSpecies(Organism organism);
    @Override
    public void action(){
        if(isAbleToMove() && isReady())
        {
            int i = 0;
            while(i < movementRange)
            {
                if(!move()) break;
                i++;
            }
        }
    }

    @Override
    public boolean collision(Organism enemy){
        if(getStrength() > enemy.getStrength())
        {
            enemy.setAlive(false);
            enemy.setReady(false);
            getWorld().addToKill(enemy);
            //oglos zabojstwo
            return false;
        }
        else
        {
            setAlive(false);
            setReady(false);
            getWorld().addToKill(this);
            //oglos zabojstwo
            return true;
        }
    }
}
