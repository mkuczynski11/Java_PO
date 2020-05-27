package life.game.my.solution.files;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;

import life.game.my.solution.files.animals.*;
import life.game.my.solution.files.gui.*;
import life.game.my.solution.files.plants.*;

import java.io.File;
import java.io.IOException;


public class World {
    private int screenX;
    private int screenY;
    private int turn;
    private List<Organism> organisms;
    private List<Organism> toKill;
    private List<Organism> toAdd;
    private List<Organism> toFix;
    private Organism[][] board;
    private Screen screen;
    private Commentator commentator;
    public DEFINE define;
    private Log logs;
    private int currentHumanKey;
    private boolean useAbility;

    public World(){
        this(20,20);
    }

    public World(int screenX, int screenY){
        this.screenX = screenX;
        this.screenY = screenY;
        this.turn = 0;
        this.organisms = new ArrayList<>();
        this.toKill = new ArrayList<>();
        this.toAdd = new ArrayList<>();
        this.toFix = new ArrayList<>();
        this.board = new Organism[screenY][screenX];
        for(int i =0; i < screenY; i++){
            for(int j =0;j<screenX; j++){
                board[i][j] = new Ground(new Position(j,i),this);
            }
        }
        this.screen = new Screen(this);
        this.define = new DEFINE();
        this.commentator = new Commentator();
        this.logs = new Log();
        this.currentHumanKey = 0;
    }

    public void launch(){
        fillUpTheWorld();
    }

    private void fillUpTheWorld(){
        fillUpHelper(define.CZLOWIEK_AMOUNT, define.SYMBOL_CZLOWIEK);
        fillUpHelper(define.WILK_AMOUNT, define.SYMBOL_WILK);
        fillUpHelper(define.ZOLW_AMOUNT, define.SYMBOL_ZOLW);
        fillUpHelper(define.OWCA_AMOUNT, define.SYMBOL_OWCA);
        fillUpHelper(define.LIS_AMOUNT, define.SYMBOL_LIS);
        fillUpHelper(define.ANTYLOPA_AMOUNT, define.SYMBOL_ANTYLOPA);
        fillUpHelper(define.CYBEROWCA_AMOUNT, define.SYMBOL_CYBEROWCA);
        fillUpHelper(define.TRAWA_AMOUNT, define.SYMBOL_TRAWA);
        fillUpHelper(define.MLECZ_AMOUNT, define.SYMBOL_MLECZ);
        fillUpHelper(define.GUARANA_AMOUNT, define.SYMBOL_GUARANA);
        fillUpHelper(define.WILCZEJAGODY_AMOUNT, define.SYMBOL_WIKLCZEJAGODY);
        fillUpHelper(define.BARSZCZSOSNOWSKIEGO_AMOUNT, define.SYMBOL_BARSZCZSOSNOWSKIEGO);
        Collections.sort(organisms, new CustomComparator());
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
            else if(symbol == define.SYMBOL_CZLOWIEK){board[y][x] = new Human(p,this);}
            else if(symbol == define.SYMBOL_WILK){board[y][x] = new Wolf(p, this);}
            else if(symbol == define.SYMBOL_ZOLW){board[y][x] = new Turtle(p,this);}
            else if(symbol == define.SYMBOL_OWCA){board[y][x] = new Sheep(p,this); }
            else if(symbol == define.SYMBOL_LIS){board[y][x] = new Fox(p,this);}
            else if(symbol == define.SYMBOL_ANTYLOPA){board[y][x] = new Antelope(p,this); }
            else if(symbol == define.SYMBOL_CYBEROWCA){board[y][x] = new CyberSheep(p,this); }
            else if(symbol == define.SYMBOL_TRAWA){board[y][x] = new Grass(p,this);
            }
            else if(symbol == define.SYMBOL_MLECZ){board[y][x] = new Milt(p,this);
            }
            else if(symbol == define.SYMBOL_GUARANA){board[y][x] = new Guarana(p,this);
            }
            else if(symbol == define.SYMBOL_WIKLCZEJAGODY){board[y][x] = new DeadlyNightshade(p,this);
            }
            else if(symbol == define.SYMBOL_BARSZCZSOSNOWSKIEGO){board[y][x] = new SosnowskysHogweed(p, this);
            }
            i++;
            organisms.add(board[p.getY()][p.getX()]);
        }
    }

    public int getScreenX() {return screenX;}
    public int getScreenY() {return screenY;}
    public int getTurn() {return turn;}
    public Commentator getCommentator() {return commentator;}
    public Screen getScreen() {return screen;}

    public int getCurrentHumanKey() {
        return currentHumanKey;
    }

    public Log getLogs() {
        return logs;
    }

    public boolean isUseAbility() {
        return useAbility;
    }

    private void setTurn(int turn){
        this.turn = turn;
    }

    public void setCurrentHumanKey(int currentHumanKey) {
        this.currentHumanKey = currentHumanKey;
    }

    public void setUseAbility(boolean useAbility) {
        this.useAbility = useAbility;
    }

    public void setScreenX(int screenX) {
        this.screenX = screenX;
    }

    public void setScreenY(int screenY) {
        this.screenY = screenY;
    }

    public void makeTurn(){
        for(Organism o : organisms)
        {
            o.action();
            o.setAge(o.getAge()+1);
        }
        addOrganisms();
        killOrganisms();
        fixOrganisms();
        Collections.sort(organisms, new CustomComparator());
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
        for(Organism o: organisms)
        {
            if(o.getPosition().getX() == from.getX() && o.getPosition().getY() == from.getY())
            {
                o.setPosition(to);
            }
            else if(o.getPosition().getX() == to.getX() && o.getPosition().getY() == to.getY())
            {
                o.setPosition(from);
            }
        }
        for(Organism o : toAdd){
            if(o.getPosition().getX() == from.getX() && o.getPosition().getY() == from.getY())
            {
                o.setPosition(to);
            }
            else if(o.getPosition().getX() == to.getX() && o.getPosition().getY() == to.getY())
            {
                o.setPosition(from);
            }
        }
        for(Organism o : toKill){
            if(o.getPosition().getX() == from.getX() && o.getPosition().getY() == from.getY())
            {
                o.setPosition(to);
            }
            else if(o.getPosition().getX() == to.getX() && o.getPosition().getY() == to.getY())
            {
                o.setPosition(from);
            }
        }
        for(Organism o : toFix){
            if(o.getPosition().getX() == from.getX() && o.getPosition().getY() == from.getY())
            {
                o.setPosition(to);
            }
            else if(o.getPosition().getX() == to.getX() && o.getPosition().getY() == to.getY())
            {
                o.setPosition(from);
            }
        }
        board[from.getY()][from.getX()].setPosition(from);
        board[to.getY()][to.getX()].setPosition(to);
    }

    public void addToKill(Organism organism){
        if(!(organism instanceof Ground)){
            toKill.add(organism);
        }
    }

    private void killOrganisms(){
        int x = toKill.size();
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
                organisms.remove(i);
                Organism tmp = new Ground(organism.getPosition(), organism.getWorld());
                board[organism.getPosition().getY()][organism.getPosition().getX()] = tmp;
                break;
            }
            i++;
        }
    }

    public void addToAdd(Organism organism){
        if(!(organism instanceof Ground)){
            toAdd.add(organism);
            board[organism.getPosition().getY()][organism.getPosition().getX()] = organism;
        }
    }

    private void addOrganisms(){
        while(true)
        {
            if(toAdd.size() == 0) break;
            addHelper(toAdd.get(0));
            toAdd.remove(0);
        }
    }

    private void addHelper(Organism organism){
        organism.setParent(false);
        organism.setReady(true);
        organisms.add(organism);
    }

    public void addToFix(Organism organism){
        if(!(organism instanceof Ground)){
            toFix.add(organism);
        }
    }

    private void fixOrganisms(){
        while(true)
        {
            if(toFix.size() == 0) break;
            fixHelper(toFix.get(0));
            toFix.remove(0);
        }
    }

    private void fixHelper(Organism organism){
        int i =0;
        while(i < organisms.size())
        {
            if(organisms.get(i).getPosition().getX() == organism.getPosition().getX()
                    && organisms.get(i).getPosition().getY() == organism.getPosition().getY())
            {
                organisms.get(i).setParent(false);
                break;
            }
            i++;
        }
    }

    public void saveGame(){
        try{
            File file = new File("zapis.txt");
            if(file.createNewFile()){
                System.out.println("File created: " + file.getName());
            }
            else{
                System.out.println("File already exists.");
            }
            FileWriter fileWriter = new FileWriter(file, false);
            fileWriter.write("S "+getScreenX()+" "+ getScreenY()+" "+getTurn()+"\n");
            for(Organism o : organisms){
                o.save(fileWriter);
            }
            fileWriter.close();
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void loadGame(){
        this.organisms = new ArrayList<>();
        this.toKill = new ArrayList<>();
        this.toAdd = new ArrayList<>();
        this.toFix = new ArrayList<>();

        try{
            File file = new File("zapis.txt");
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                char c = data.charAt(0);
                Position p = new Position(0,0);
                Organism tmp = null;
                if(c == "S".charAt(0)) { loadWorld(data); }
                else if(c == define.SYMBOL_GROUND.charAt(0)){tmp = new Ground(p,this);}
                else if(c == define.SYMBOL_CZLOWIEK.charAt(0)){tmp = new Human(p,this);}
                else if(c == define.SYMBOL_WILK.charAt(0)){tmp = new Wolf(p, this);}
                else if(c == define.SYMBOL_ZOLW.charAt(0)){tmp = new Turtle(p,this);}
                else if(c == define.SYMBOL_OWCA.charAt(0)){tmp = new Sheep(p,this); }
                else if(c == define.SYMBOL_LIS.charAt(0)){tmp = new Fox(p,this);}
                else if(c == define.SYMBOL_ANTYLOPA.charAt(0)){tmp = new Antelope(p,this); }
                else if(c == define.SYMBOL_CYBEROWCA.charAt(0)){tmp = new CyberSheep(p,this); }
                else if(c == define.SYMBOL_TRAWA.charAt(0)){tmp = new Grass(p,this);
                }
                else if(c == define.SYMBOL_MLECZ.charAt(0)){tmp = new Milt(p,this);
                }
                else if(c == define.SYMBOL_GUARANA.charAt(0)){tmp = new Guarana(p,this);
                }
                else if(c == define.SYMBOL_WIKLCZEJAGODY.charAt(0)){tmp = new DeadlyNightshade(p,this);
                }
                else if(c == define.SYMBOL_BARSZCZSOSNOWSKIEGO.charAt(0)){tmp = new SosnowskysHogweed(p, this);
                }
                if(c != "S".charAt(0)) {
                    tmp.load(data);
                    board[tmp.getPosition().getY()][tmp.getPosition().getX()] = tmp;
                    organisms.add(tmp);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        this.screen = new Screen(this);
    }

    private void loadWorld(String data){
        int count = 0;
        int limit = 3;
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
                    if( i == data.length()) break;
                    c = data.charAt(i);
                }
                if (number != "")
                {
                    int result;
                    result = Integer.parseInt(number);
                    if(count == 0) this.setScreenX(result);
                    else if (count == 1) this.setScreenY(result);
                    else if (count == 2) this.setTurn(result);
                    count++;
                }
                i++;
            }
        }
        this.board = new Organism[screenY][screenX];
        for(int k=0; k < screenY; k++){
            for(int j =0;j<screenX; j++){
                board[k][j] = new Ground(new Position(j,k),this);
            }
        }
    }
    public void addOrganism(Position position, String name){
        if(name == "Antylopa") board[position.getY()][position.getX()] = new Antelope(position, this);
        else if(name == "Cyberowca") board[position.getY()][position.getX()] = new CyberSheep(position, this);
        else if(name == "Lis") board[position.getY()][position.getX()] = new Fox(position, this);
        else if(name == "Owca") board[position.getY()][position.getX()] = new Sheep(position, this);
        else if(name == "Zolw") board[position.getY()][position.getX()] = new Turtle(position, this);
        else if(name == "Wilk") board[position.getY()][position.getX()] = new Wolf(position, this);
        else if(name == "Wilczejagody") board[position.getY()][position.getX()] = new DeadlyNightshade(position, this);
        else if(name == "Trawa") board[position.getY()][position.getX()] = new Grass(position, this);
        else if(name == "Guarana") board[position.getY()][position.getX()] = new Guarana(position, this);
        else if(name == "Mlecz") board[position.getY()][position.getX()] = new Milt(position, this);
        else if(name == "BarszczSosnowskiego") board[position.getY()][position.getX()] = new SosnowskysHogweed(position, this);
        organisms.add(board[position.getY()][position.getX()]);
    }
}
