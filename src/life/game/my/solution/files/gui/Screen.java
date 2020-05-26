package life.game.my.solution.files.gui;

import life.game.my.solution.files.Organism;
import life.game.my.solution.files.Position;
import life.game.my.solution.files.World;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Screen extends JFrame
{
    private World world;
    private GamePanel gamePanel;
    private Menu menu;
    private EventLog eventlog;
    private JButton[][] board;

    public World getWorld() {
        return world;
    }
    public Screen(World world){
        this.world = world;
        this.setTitle("Life game");
        this.setLayout(new BorderLayout());
        int x = getWorld().getScreenX();
        int y = getWorld().getScreenY();

        gamePanel = new GamePanel(y,x);
        add(gamePanel,BorderLayout.CENTER);

        board = new JButton[y][x];

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                board[i][j] = new JButton(getWorld().define.SYMBOL_GROUND);
                board[i][j].setBackground(Color.LIGHT_GRAY);
                gamePanel.add(board[i][j]);
            }
        }

        menu = new Menu(this);
        add(menu, BorderLayout.WEST);

        eventlog = new EventLog(this);
        add(eventlog, BorderLayout.EAST);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1920, 1080);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
    }

    public void addOrganism(Organism organism, Position position) {
        Color c = organism.getColor();
        String symbol = organism.getSymbol();
        board[position.getY()][position.getX()].setBackground(c);
        board[position.getY()][position.getX()].setText(symbol);
    }

    public void moveOrganism(Position position1, Position position2) {
        Color tmp = board[position1.getY()][position1.getX()].getBackground();
        String tmp_s = board[position1.getY()][position1.getX()].getText();
        board[position1.getY()][position1.getX()].setBackground(board[position2.getY()][position2.getX()].getBackground());
        board[position1.getY()][position1.getX()].setText(board[position2.getY()][position2.getX()].getText());
        board[position2.getY()][position2.getX()].setBackground(tmp);
        board[position2.getY()][position2.getX()].setText(tmp_s);
    }

    public void clearLogs(){
        eventlog.getTextArea().setText("Wydarzenia na planszy podczas trwania tury\n");
    }

    public void addAction(String message) {
        this.eventlog.getTextArea().append(message);
    }
}