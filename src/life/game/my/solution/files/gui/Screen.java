package life.game.my.solution.files.gui;

import life.game.my.solution.files.Organism;
import life.game.my.solution.files.Position;
import life.game.my.solution.files.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;

public class Screen implements ActionListener
{
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    private static final int MENU_Y =10;
    private static final int MENU_WIDTH =190;
    private static final int MENU_X = WIDTH-MENU_WIDTH-10;
    private static final int MENU_HEIGHT =HEIGHT - 2*MENU_Y;
    private static final int GAMEPANEL_WIDTH = 780 ;
    private static final int GAMEPANEL_HEIGHT = 980 ;
    private World world;
    private JFrame gameframe;
    private JPanel gamepanel;
    private JPanel menu;
    private JButton bExit;
    private JButton bNextTurn;
    private JButton bSaveGame;
    private JButton bLoadGame;
    private JButton[][] board;

    public World getWorld() {return world;}

    public Screen(World world) {
        this.world = world;
        gameframe = new JFrame("Life game");
        int x = world.getScreenX();
        int y = world.getScreenY();

        int button_width = GAMEPANEL_WIDTH/x;
        int button_height = GAMEPANEL_HEIGHT/y;

        this.gamepanel = new JPanel();
        gamepanel.setLayout(new GridLayout(y,x));
        gamepanel.setBounds(10,10,GAMEPANEL_WIDTH, GAMEPANEL_HEIGHT);
        this.board = new JButton[y][x];

        for(int i =0; i < getWorld().getScreenY(); i++){
            for(int j =0; j < getWorld().getScreenY(); j++){
                board[i][j] = new JButton(getWorld().define.SYMBOL_GROUND);
                board[i][j].setBackground(Color.LIGHT_GRAY);
                board[i][j].setBounds(10+(button_width*j),10+(button_height*i),button_width,button_height);
                gamepanel.add(board[i][j]);
            }
        }

        this.menu = new JPanel();
        menu.setLayout(null);
        menu.setBounds(MENU_X,MENU_Y,MENU_WIDTH,MENU_HEIGHT);
        this.bExit = new JButton("Exit");
        bExit.setBackground(Color.BLACK);
        bExit.setForeground(Color.white);
        bExit.setBounds(MENU_X+10,MENU_Y,MENU_WIDTH-20, (MENU_HEIGHT/4)-10);
        bExit.addActionListener(this);
        menu.add(bExit);
        this.bNextTurn = new JButton("Tura");
        bNextTurn.setBackground(Color.BLACK);
        bNextTurn.setForeground(Color.white);
        bNextTurn.setBounds(MENU_X+10,MENU_Y+(MENU_HEIGHT/4),MENU_WIDTH-20,(MENU_HEIGHT/4)-10);
        bNextTurn.addActionListener(this);
        menu.add(bNextTurn);
        this.bSaveGame = new JButton("Zapisz");
        bSaveGame.setBackground(Color.BLACK);
        bSaveGame.setBounds(MENU_X+10,MENU_Y+2*(MENU_HEIGHT/4),MENU_WIDTH-20,(MENU_HEIGHT/4)-10);
        bSaveGame.addActionListener(this);
        menu.add(bSaveGame);
        this.bLoadGame = new JButton("Zaladuj");
        bLoadGame.setBackground(Color.BLACK);
        bLoadGame.setBounds(MENU_X+10,MENU_Y+3*(MENU_HEIGHT/4),MENU_WIDTH-20,(MENU_HEIGHT/4));
        bLoadGame.addActionListener(this);
        menu.add(bLoadGame);

        gameframe.add(gamepanel);
        gameframe.add(menu);
        gameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameframe.setSize(WIDTH,HEIGHT);
        gameframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameframe.setBackground(Color.yellow);
        gameframe.setVisible(true);
    }

    public void addOrganism(Organism organism, Position position){
        Color c = organism.getColor();
        String symbol = organism.getSymbol();
        board[position.getY()][position.getX()].setBackground(c);
        board[position.getY()][position.getX()].setText(symbol);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == bExit){
            gameframe.dispose();
        }
        else if(source == bNextTurn){
            this.world.makeTurn();
        }
        else if(source == bLoadGame){

        }
        else if(source == bSaveGame){

        }
    }

    public void moveOrganism(Position position1, Position position2){
        Color tmp = board[position1.getY()][position1.getX()].getBackground();
        String tmp_s = board[position1.getY()][position1.getX()].getText();
        board[position1.getY()][position1.getX()].setBackground(board[position2.getY()][position2.getX()].getBackground());
        board[position1.getY()][position1.getX()].setText(board[position2.getY()][position2.getX()].getText());
        board[position2.getY()][position2.getX()].setBackground(tmp);
        board[position2.getY()][position2.getX()].setText(tmp_s);
    }
}
