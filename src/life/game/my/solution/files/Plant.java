package life.game.my.solution.files;


import java.util.List;
import java.util.Random;

public abstract class Plant extends Organism
{
    private final int BASE_TIMES = 1;
    private final int BASE_CHANCE = 3;
    private int seedTimes;
    private int seedChance;

    public Plant(int strength, String symbol, Position position,
                 World world){
        super(strength,0,symbol,position,world);
        this.seedChance = BASE_CHANCE;
        this.seedTimes = BASE_TIMES;
    }

    public int getSeedChance() {
        return seedChance;
    }

    public int getSeedTimes() {
        return seedTimes;
    }

    public void setSeedChance(int seedChance) {
        this.seedChance = seedChance;
    }

    public void setSeedTimes(int seedTimes) {
        this.seedTimes = seedTimes;
    }

    @Override
    public void action(){
        if(isReady())
        {
            int i = 0;
            while(i < seedTimes)
            {
                if(isAbleToSeed())
                {
                    seed();
                    return;
                }
                i++;
            }
        }
    }

    private boolean isAbleToSeed(){
        Random r = new Random();
        return (r.nextInt(101) < seedChance);
    }

    private void seed(){
        List<Position> combinations = this.generateCombinations(getPosition());
        Random r = new Random();
        while(true)
        {
            if(combinations.size() == 0)break;
            int choice = r.nextInt(combinations.size());
            Position p = combinations.get(choice);
            Organism tmp = getWorld().getOrganism(p);
            if(tmp instanceof Ground || !tmp.isAlive())
            {
                Organism child = this.child(p);
                child.setReady(false);
                child.setParent(true);
                getWorld().addToAdd(child);
                getWorld().addToFix(this);
                getWorld().getScreen().addAction(getWorld().getCommentator().announceSeed(this,child));
                return;
            }
            combinations.remove(choice);
        }
    }

    @Override
    public boolean collision(Organism enemy){
        if(getStrength() > enemy.getStrength())
        {
            enemy.setAlive(false);
            enemy.setReady(false);
            getWorld().addToKill(enemy);
            getWorld().getScreen().addAction(getWorld().getCommentator().announceKill(this, enemy));
            return false;
        }
        else
        {
            setAlive(false);
            setReady(false);
            getWorld().addToKill(this);
            /////////////////////////////////////////////////////getWorld().getScreen().addAction(getWorld().getCommentator().announceConsume(enemy, this));
            return true;
        }
    }
}
