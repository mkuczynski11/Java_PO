package life.game.my.solution.files;

import java.util.ArrayList;
import java.util.Random;

import life.game.my.solution.files.animals.*;
import life.game.my.solution.files.gui.Screen;

public class World {
    private final int screenX;
    private final int screenY;
    private int turn;
    private ArrayList<Organism> organisms;
    private ArrayList<Organism> toKill;
    private Organism[][] board;
    private Screen screen;
    public DEFINE define;

    public World(){
        this(20,20);
    }

    public World(int screenX, int screenY){
        this.screenX = screenX;
        this.screenY = screenY;
        this.turn = 0;
        this.organisms = new ArrayList<>();
        this.toKill = new ArrayList<>();
        this.board = new Organism[screenY][screenX];
        for(int i =0; i < screenY; i++){
            for(int j =0;j<screenX; j++){
                board[i][j] = new Ground(new Position(j,i),this);
            }
        }
        this.screen = new Screen(this);
        this.define = new DEFINE();
        fillUpTheWorld();
    }

    private void fillUpTheWorld(){
       // fillUpHelper(define.CZLOWIEK_AMOUNT, define.SYMBOL_CZLOWIEK);
        fillUpHelper(define.WILK_AMOUNT, define.SYMBOL_WILK);
        fillUpHelper(define.ZOLW_AMOUNT, define.SYMBOL_ZOLW);
        fillUpHelper(define.OWCA_AMOUNT, define.SYMBOL_OWCA);
        fillUpHelper(define.LIS_AMOUNT, define.SYMBOL_LIS);
        fillUpHelper(define.ANTYLOPA_AMOUNT, define.SYMBOL_ANTYLOPA);
        /*fillUpHelper(define.TRAWA_AMOUNT, define.SYMBOL_TRAWA);
        fillUpHelper(define.MLECZ_AMOUNT, define.SYMBOL_MLECZ);
        fillUpHelper(define.GUARANA_AMOUNT, define.SYMBOL_GUARANA);
        fillUpHelper(define.WILCZEJAGODY_AMOUNT, define.SYMBOL_WIKLCZEJAGODY);
        fillUpHelper(define.BARSZCZSOSNOWSKIEGO_AMOUNT, define.SYMBOL_BARSZCZSOSNOWSKIEGO);*/
        //posortuj
    }
    private void fillUpHelper(int amount, String symbol){
        int i = 0;
        Random r = new Random();
        while(i < amount)
        {
            Organism tmp;
            int x, y;
            while(true){
                x = r.nextInt(getScreenX());
                y = r.nextInt(getScreenY());
                tmp = board[y][x];
                if(tmp instanceof Ground) break;
            }
            Position p = new Position(x,y);
            if(symbol == define.SYMBOL_GROUND){board[y][x] = new Ground(p,this);}
            //else if(symbol == define.SYMBOL_CZLOWIEK){}
            else if(symbol == define.SYMBOL_WILK){board[y][x] = new Wolf(p, this);}
            else if(symbol == define.SYMBOL_ZOLW){board[y][x] = new Turtle(p,this);}
            else if(symbol == define.SYMBOL_OWCA){board[y][x] = new Sheep(p,this); }
            else if(symbol == define.SYMBOL_LIS){board[y][x] = new Fox(p,this);}
            else if(symbol == define.SYMBOL_ANTYLOPA){board[y][x] = new Antelope(p,this); }
            /*else if(symbol == define.SYMBOL_TRAWA){}
            else if(symbol == define.SYMBOL_MLECZ){}
            else if(symbol == define.SYMBOL_GUARANA){}
            else if(symbol == define.SYMBOL_WIKLCZEJAGODY){}
            else if(symbol == define.SYMBOL_BARSZCZSOSNOWSKIEGO){}*/
            i++;
            organisms.add(board[p.getY()][p.getX()]);
            screen.addOrganism(board[p.getY()][p.getX()],p);
        }
    }

    public int getScreenX() {return screenX;}
    public int getScreenY() {return screenY;}
    public int getTurn() {return turn;}

    private void setTurn(int turn){
        this.turn = turn;
    }

    public void makeTurn(){
        for(Organism o : organisms)
        {
            o.action();
            o.setAge(o.getAge()+1);
        }
        //addOrganisms();
        killOrganisms();
        //fixOrganisms();
        //sort
        setTurn(getTurn()+1);
    }

    public Organism getOrganism(Position position){
        return board[position.getY()][position.getX()];
    }
    public Organism getOrganism(int x, int y){
        return getOrganism((new Position(x,y)));
    }

    public void moveOrganism(Position from, Position to){
        Organism tmp_from = board[from.getY()][from.getX()];
        Organism tmp_to = board[to.getY()][to.getX()];
        board[from.getY()][from.getX()] = tmp_to;
        board[to.getY()][to.getX()] = tmp_from;
        int moves = 0;
        for(Organism o: organisms)
        {
            if(o.getPosition().getX() == from.getX() && o.getPosition().getY() == from.getY())
            {
                o.setPosition(to);
                moves++;
            }
            else if(o.getPosition().getX() == to.getX() && o.getPosition().getY() == to.getY())
            {
                o.setPosition(from);
                moves++;
            }
            if (moves == 2) break;
        }
        screen.moveOrganism(from,to);
    }

    public void addToKill(Organism organism){
        if(!(organism instanceof Ground)){
            toKill.add(organism);
        }
    }

    private void killOrganisms(){
        while(true)
        {
            if(toKill.size() == 0) break;
            killHelper(toKill.get(0));
            toKill.remove(0);
        }
    }
    private void killHelper(Organism organism){
        int i =0;
        while(i < organisms.size())
        {
            if(organisms.get(i).getPosition().getX() == organism.getPosition().getX()
             && organisms.get(i).getPosition().getY() == organism.getPosition().getY())
            {
                Organism tmp = new Ground(organism.getPosition(), organism.getWorld());
                board[organism.getPosition().getY()][organism.getPosition().getX()] = tmp;
                organisms.remove(i);
                screen.addOrganism(tmp,tmp.getPosition());
                break;
            }
            i++;
        }
    }

}
