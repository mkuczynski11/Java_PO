package life.game.my.solution.files.gui;

import life.game.my.solution.files.Organism;
import life.game.my.solution.files.Position;
import life.game.my.solution.files.World;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen implements ActionListener
{
    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1020;
    private static final int SPACE = 10;
    private static final int MENU_Y = SPACE;
    private static final int MENU_X = 800;
    private static final int MENU_WIDTH = 200 - 1*SPACE;
    private static final int MENU_HEIGHT = HEIGHT - 2*SPACE;
    private static final int GAMEPANEL_X= SPACE;
    private static final int GAMEPANEL_Y= SPACE;
    private static final int GAMEPANEL_WIDTH = 800-2*SPACE ;
    private static final int GAMEPANEL_HEIGHT = HEIGHT - 2*SPACE;
    private static final int EVENTLOG_X =1000;
    private static final int EVENTLOG_Y =SPACE;
    private static final int EVENTLOG_WIDTH = WIDTH-EVENTLOG_X-1*SPACE;
    private static final int EVENTLOG_HEIGHT =HEIGHT-2*SPACE;
    private World world;
    private JFrame gameframe;
    private JPanel gamepanel;
    private JPanel menu;
    private JPanel eventlog;
    private JTextField textField;
    private JButton bExit;
    private JButton bNextTurn;
    private JButton bSaveGame;
    private JButton bLoadGame;
    private JButton[][] board;

    public World getWorld() {return world;}

    public Screen(World world) {
        this.world = world;
        gameframe = new JFrame("Life game");
        gameframe.setLayout(new BorderLayout());
        int x = world.getScreenX();
        int y = world.getScreenY();

        int button_width = GAMEPANEL_WIDTH/x;
        int button_height = GAMEPANEL_HEIGHT/y;

        this.gamepanel = new JPanel();
        gameframe.add(gamepanel, BorderLayout.CENTER);
        gamepanel.setLayout(new GridLayout(y,x));
        gamepanel.setBounds(GAMEPANEL_X,GAMEPANEL_Y,GAMEPANEL_WIDTH, GAMEPANEL_HEIGHT);
        this.board = new JButton[y][x];

        for(int i =0; i < getWorld().getScreenY(); i++){
            for(int j =0; j < getWorld().getScreenY(); j++){
                board[i][j] = new JButton(getWorld().define.SYMBOL_GROUND);
                board[i][j].setBackground(Color.LIGHT_GRAY);
                board[i][j].setBounds(SPACE+(button_width*j),SPACE+(button_height*i),button_width,button_height);
                gamepanel.add(board[i][j]);
            }
        }

        this.menu = new JPanel();
        menu.setLayout(new GridLayout(4,1));

        this.bExit = new JButton("Exit");
        bExit.setBackground(Color.BLACK);
        bExit.setForeground(Color.white);
        bExit.setLayout(null);
        //bExit.setBounds(MENU_X+SPACE,MENU_Y,MENU_WIDTH-2*SPACE, (MENU_HEIGHT/4)-SPACE);
        bExit.addActionListener(this);
        this.bNextTurn = new JButton("Tura");
        bNextTurn.setBackground(Color.BLACK);
        bNextTurn.setForeground(Color.white);
        bNextTurn.setLayout(null);
        //bNextTurn.setBounds(MENU_X+SPACE,MENU_Y+(MENU_HEIGHT/4),MENU_WIDTH-2*SPACE,(MENU_HEIGHT/4)-SPACE);
        bNextTurn.addActionListener(this);
        this.bSaveGame = new JButton("Zapisz");
        bSaveGame.setBackground(Color.BLACK);
        bSaveGame.setForeground(Color.white);
        bSaveGame.setLayout(null);
        //bSaveGame.setBounds(MENU_X+SPACE,MENU_Y+2*(MENU_HEIGHT/4),MENU_WIDTH-2*SPACE,(MENU_HEIGHT/4)-SPACE);
        bSaveGame.addActionListener(this);
        this.bLoadGame = new JButton("Zaladuj");
        bLoadGame.setBackground(Color.BLACK);
        bLoadGame.setForeground(Color.white);
        bLoadGame.setLayout(null);
        //bLoadGame.setBounds(MENU_X+SPACE,MENU_Y+3*(MENU_HEIGHT/4),MENU_WIDTH-2*SPACE,(MENU_HEIGHT/4));
        bLoadGame.addActionListener(this);

        menu.add(bExit);
        menu.add(bNextTurn);
        menu.add(bSaveGame);
        menu.add(bLoadGame);

        gameframe.add(menu, BorderLayout.WEST);

        /*this.eventlog = new JPanel();
        eventlog.setLayout(null);
        eventlog.setBounds(EVENTLOG_X,EVENTLOG_Y,EVENTLOG_WIDTH,EVENTLOG_HEIGHT);
        textField = new JTextField();
        textField.setLayout(null);
        textField.setBounds(EVENTLOG_X+SPACE,EVENTLOG_Y,EVENTLOG_WIDTH-2*SPACE,EVENTLOG_HEIGHT);
*/
        gameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameframe.setSize(1920,1080);
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
