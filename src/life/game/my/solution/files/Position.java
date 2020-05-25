package life.game.my.solution.files;

import java.util.ArrayList;
import java.util.Random;

public class Position
{
    private Random r;
    private int x;
    private int y;

    public Position(int x, int y){
        this.r = new Random();
        this.x = x;
        this.y = y;
    }

    public int getX(){return this.x;}
    public int getY(){return this.y;}

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }
}
