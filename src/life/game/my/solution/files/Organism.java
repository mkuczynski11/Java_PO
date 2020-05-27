package life.game.my.solution.files;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

public abstract class Organism
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

    public ArrayList<Position> generateCombinations(Position position){
        ArrayList<Position> combinations = new ArrayList<>();
        int x = position.getX();
        int y = position.getY();
        if(!(x== 0)) combinations.add(new Position(x-1,y));
        if(!(x == getWorld().getScreenX() - 1)) combinations.add(new Position(x+1,y));
        if(!(y == 0)) combinations.add(new Position(x,y-1));
        if(!(y == getWorld().getScreenY() - 1)) combinations.add(new Position(x,y+1));

        return combinations;
    }

    public Position generateRandomPosition(Position position){
        ArrayList<Position> combinations = generateCombinations(position);
        combinations.add(new Position(position.getX(),position.getY()));
        Random r = new Random();
        int choice = r.nextInt(combinations.size());
        return combinations.get(choice);
    }

    public boolean hasAvoided(Organism enemy){
        return false;
    }

    public abstract void action();
    public abstract boolean collision(Organism enemy);
    public abstract String getName();
    public abstract Organism child(Position position);
    public abstract void save(FileWriter fileWriter);
    public abstract Color getColor();

    public int load(String data){
        int count = 0;
        int limit = 4;
        int i = 2;
        while(count < limit){
            char c = data.charAt(i);
            if(c == ' ')i++;
            else
            {
                String number = "";
                while(c >= '0' && c <= '9')
                {
                    number += c;
                    i++;
                    if(i == data.length()) break;
                    c = data.charAt(i);
                }
                if (number != "")
                {
                    int result;
                    result = Integer.parseInt(number);
                    if(count == 0) this.setAge(result);
                    else if (count == 1) this.setStrength(result);
                    else if (count == 2) this.setPosition(result, getPosition().getY());
                    else if (count == 3) this.setPosition(getPosition().getX(), result);
                    count ++;
                }
                i++;
            }
        }
        return i;
    }
}
