package life.game.my.solution.files;

import java.awt.*;

public abstract class Organism implements Creature
{
    private int age;
    private int strength;
    private int iniciative;
    private final String symbol;
    private Position position;
    private final World world;
    private boolean alive;
    private boolean ready;
    private boolean parent;

    public Organism(int strength, int iniciative, String symbol, Position position,
                    World world){
        this.age = 0;
        this.strength = strength;
        this.iniciative = iniciative;
        this.symbol = symbol;
        this.position = new Position(position.getX(),position.getY());
        this.world = world;
        this.alive = true;
        this.ready = true;
        this.parent = false;
    }

    public int getAge() {return age;}
    public int getStrength() {return strength;}
    public int getIniciative() {return iniciative;}
    public String getSymbol() {return symbol;}
    public Position getPosition() {return position;}
    public World getWorld() {return world;}
    public boolean isAlive() {return alive;}
    public boolean isReady() {return ready;}
    public boolean isParent() {return parent;}

    public void setAge(int age){
        this.age = age;
    }
    public void setStrength(int strength){
        this.strength = strength;
    }
    public void setIniciative(int iniciative){
        this.iniciative = iniciative;
    }
    public void setPosition(Position position){
        this.position = position;
    }
    public void setPosition(int x, int y){
        this.position.setX(x);
        this.position.setY(y);
    }
    public void setAlive(boolean alive){
        this.alive = alive;
    }
    public void setReady(boolean ready){
        this.ready = ready;
    }
    public void setParent(boolean parent){
        this.parent = parent;
    }

    @Override
    public void draw(){
        //wypisz symbol
    }
}
