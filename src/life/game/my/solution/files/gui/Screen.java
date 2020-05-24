package life.game.my.solution.files.gui;

import life.game.my.solution.files.Organism;
import life.game.my.solution.files.Position;
import life.game.my.solution.files.World;

import javax.swing.*;
import java.awt.*;

public class Screen {
    private final World world;
    private final JFrame gameframe;
    private final int screenX;
    private final int screenY;
    private JButton[][] board;

    public World getWorld() {return world;}

    public Screen(int x, int y, World world){
        this.world = world;
        gameframe = new JFrame("Life game");
        screenX = x;
        screenY = y;
        this.board = new JButton[screenY][screenX];

        for(int i =0; i < getWorld().getScreenY(); i++){
            for(int j =0; j < getWorld().getScreenY(); j++){
                board[i][j] = new JButton(getWorld().define.SYMBOL_GROUND);
                board[i][j].setBackground(Color.LIGHT_GRAY);
                gameframe.add(board[i][j]);
            }
        }

        gameframe.setLayout(new GridLayout(screenY, screenX));
        gameframe.setSize(30*screenY,30*screenX);
        gameframe.setVisible(true);
    }

    public void addOrganism(Organism organism, Position position){
        Color c = organism.getColor();
        String symbol = organism.getSymbol();
        board[position.getY()][position.getX()].setBackground(c);
        board[position.getY()][position.getX()].setText(symbol);
    }
}
