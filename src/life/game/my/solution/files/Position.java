package life.game.my.solution.files;

import java.util.Random;

public class Position
{
    private int x;
    private int y;

    public Position(int x, int y){
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
        Random r = new Random();
        int add_x = 0;
        int add_y = 0;

        if(getX() == 0) add_x = r.nextInt(2);
        else if(getX() == screenX - 1) add_x = r.nextInt(2) - 1;
        else add_x = r.nextInt(3) - 1;

        if(getY() == 0) add_y = r.nextInt(2);
        else if(getY() == screenY - 1) add_y = r.nextInt(2) - 1;
        else add_y = r.nextInt(3) - 1;

        setX(getX() + add_x);
        setY(getY() + add_y);
    }
}
