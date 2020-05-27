package life.game.my.solution.files.animals;

import life.game.my.solution.files.Animal;
import life.game.my.solution.files.Organism;
import life.game.my.solution.files.Position;
import life.game.my.solution.files.World;
import life.game.my.solution.files.plants.SosnowskysHogweed;

import java.awt.*;
import java.util.Random;

public class CyberSheep extends Animal
{
    private static final int CYBEROWCA_SILA = 11;
    private static final int CYBEROWCA_INICJATYWA = 4;

    public CyberSheep(Position position, World world){
        super(CYBEROWCA_SILA, CYBEROWCA_INICJATYWA,world.define.SYMBOL_CYBEROWCA,position, world);
    }

    @Override
    public String getName(){
        return "Cyberowca";
    }
    @Override
    public boolean isSameSpecies(Organism organism){
        return (organism instanceof CyberSheep);
    }
    @Override
    public Organism child(Position position) {return new CyberSheep(position, getWorld());}
    @Override
    public Color getColor(){
        return Color.DARK_GRAY;
    }
    @Override
    public Position generateRandomPosition(Position position){
        int y = getWorld().getScreenY();
        int x = getWorld().getScreenX();
        int length = x-1+y-1;
        Organism tmp = null;
        for(int i =0; i < y; i++){
            for(int j =0; j < x; j++){
                Organism o = getWorld().getOrganism(j,i);
                if(o instanceof SosnowskysHogweed && o.isAlive()){
                    int distance_x = getPosition().getX() - o.getPosition().getX();
                    int distance_y = getPosition().getY() - o.getPosition().getY();
                    if(distance_x < 0) distance_x*=-1;
                    if(distance_y < 0) distance_y*=-1;
                    if(distance_x + distance_y < length) {
                        tmp = o;
                        length = distance_x + distance_y;
                    }
                }
            }
        }
        if (tmp == null) return super.generateRandomPosition(position);
        int distance_x = getPosition().getX() - tmp.getPosition().getX();
        int distance_y = getPosition().getY() - tmp.getPosition().getY();
        int n_x = distance_x;
        int n_y = distance_y;
        if(n_x < 0) n_x*=-1;
        if(n_y < 0) n_y*=-1;
        Random r = new Random();
        int choice = r.nextInt(2);
        if(choice == 0){
            if(n_x != 0){
                if(distance_x < 0) return new Position(getPosition().getX() + 1,getPosition().getY());
                if(distance_x > 0) return new Position(getPosition().getX()-1, getPosition().getY());
            }
            else if(n_y != 0){
                if(distance_y < 0) return new Position(getPosition().getX(), getPosition().getY() + 1);
                if(distance_y > 0) return new Position(getPosition().getX(), getPosition().getY() - 1);
            }
        }
        if(choice == 1){
            if(n_y != 0){
                if(distance_y < 0) return new Position(getPosition().getX(), getPosition().getY() + 1);
                if(distance_y > 0) return new Position(getPosition().getX(), getPosition().getY() - 1);
            }
            else if(n_x != 0){
                if(distance_x < 0) return new Position(getPosition().getX() + 1,getPosition().getY());
                if(distance_x > 0) return new Position(getPosition().getX()-1, getPosition().getY());
            }
        }
        return position;
    }
}
