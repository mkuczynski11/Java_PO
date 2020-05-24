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

    public void generateRandomPosition(int screenX, int screenY){
        ArrayList<Position> arr = new ArrayList<Position>();
        if(!(x == 0)) arr.add(new Position(-1,0));
        if(!(x == screenX -1)) arr.add(new Position(1,0));
        if(!(y == 0)) arr.add(new Position(0,-1));
        if(!(y == screenY -1)) arr.add(new Position(0,1));
        arr.add(new Position(0,0));
        int choice = r.nextInt(arr.size());
        this.x += arr.get(choice).getX();
        this.y += arr.get(choice).getY();
    }
}
