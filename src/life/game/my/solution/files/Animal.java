package life.game.my.solution.files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public abstract class Animal extends Organism
{
    private final int BASE_RANGE = 1;
    private final int BASE_CHANCE = 100;
    private int movementRange;
    private int movementChanse;

    public Animal(int strength, int iniciative, String symbol, Position position,
                  World world){
        super(strength, iniciative, symbol, position, world);
        this.movementRange = BASE_RANGE;
        this.movementChanse = BASE_CHANCE;
    }

    public int getMovementRange() {return movementRange;}
    public int getMovementChanse() {return movementChanse;}

    public void setMovementRange(int movementRange){
        this.movementRange= movementRange;
    }
    public void setMovementChanse(int movementChanse){
        this.movementChanse = movementChanse;
    }

    private boolean isAbleToMove(){
        Random r = new Random();
        boolean is = r.nextInt(101) < movementChanse;
        return is;
    }

    private boolean move(){
        Position heading = this.generateRandomPosition(this.getPosition());
        if(heading.getX() == getPosition().getX() && heading.getY() == getPosition().getY()){
            return true;
        }
        Organism tmp = getWorld().getOrganism(heading);
        if(tmp instanceof Ground || !tmp.isAlive())
        {
            getWorld().getLogs().addLog(getWorld().getCommentator().announceMove(this,this.getPosition(),heading));
            getWorld().moveOrganism(getPosition(), heading);
            return true;
        }
        else if(isSameSpecies(tmp) && tmp.isAlive())
        {
            if(!isParent() && !tmp.isParent()){
                breed(tmp);
                return false;
            }
            return true;
        }
        else if (tmp.isAlive())
        {
            if(tmp.collision(this)){
                getWorld().moveOrganism(getPosition(), heading);
            }
            return false;
        }
        return true;
    }

    private void breed(Organism lover){
        List<Position> combinations = this.generateCombinations(lover.getPosition());
        Random r = new Random();
        while(true)
        {
            if(combinations.size() == 0) break;
            int choice = r.nextInt(combinations.size());
            Position p = combinations.get(choice);
            Organism tmp = getWorld().getOrganism(p);
            if(tmp instanceof Ground || !(tmp.isAlive()) && (p.getX() != getPosition().getX() && p.getY() != getPosition().getY()))
            {
                Organism child = this.child(p);
                child.setReady(false);
                child.setParent(true);
                getWorld().addToAdd(child);
                getWorld().addToFix(this);
                getWorld().addToFix(lover);
                getWorld().getLogs().addLog(getWorld().getCommentator().announceBreed(this,lover,child));
                return;
            }
            combinations.remove(choice);
        }
    }

    public abstract boolean isSameSpecies(Organism organism);
    @Override
    public void action(){
        if(isAbleToMove() && isReady() && isAlive())
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
        if(hasAvoided(enemy)){
            getWorld().getLogs().addLog(getWorld().getCommentator().announceAvoid(enemy,this));
            return false;
        }
        if(getStrength() > enemy.getStrength())
        {
            enemy.setAlive(false);
            enemy.setReady(false);
            getWorld().addToKill(enemy);
            getWorld().getLogs().addLog(getWorld().getCommentator().announceKill(this, enemy));
            return false;
        }
        else
        {
            this.setAlive(false);
            this.setReady(false);
            getWorld().addToKill(this);
            getWorld().getLogs().addLog(getWorld().getCommentator().announceKill(enemy, this));
            return true;
        }
    }
    @Override
    public void save(FileWriter fileWriter) {
        try{
            fileWriter.write(getSymbol()+" "+getAge()+" "+getStrength()+" "+getPosition().getX()+" "+getPosition().getY()+"\n");
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
