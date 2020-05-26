package life.game.my.solution.files.gui;

import life.game.my.solution.files.Position;
import life.game.my.solution.files.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel
{
    private JButton[][] board;

    public GamePanel(int y, int x, World world){
        setLayout(new GridLayout(y,x));
        board = new JButton[y][x];

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                board[i][j] = new JButton(world.getOrganism(new Position(j,i)).getSymbol());
                board[i][j].setBackground(world.getOrganism(new Position(j,i)).getColor());
                add(board[i][j]);
            }
        }

        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0, false),"UP");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0, false),"DOWN");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0, false),"LEFT");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0, false),"RIGHT");

        am.put("UP", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.setCurrentHumanKey(KeyEvent.VK_UP);
            }
        });
        am.put("DOWN", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.setCurrentHumanKey(KeyEvent.VK_DOWN);
            }
        });
        am.put("LEFT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.setCurrentHumanKey(KeyEvent.VK_LEFT);
            }
        });
        am.put("RIGHT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.setCurrentHumanKey(KeyEvent.VK_RIGHT);
            }
        });
    }

    public JButton[][] getBoard() {
        return board;
    }

    public void changeBoard(World world){
        for(int i =0; i <world.getScreenY();i++){
            for(int j =0; j < world.getScreenX(); j++){
                if(board[i][j].getText() != world.getOrganism(new Position(j,i)).getSymbol()){
                    board[i][j].setText(world.getOrganism(new Position(j,i)).getSymbol());
                    board[i][j].setBackground(world.getOrganism(new Position(j,i)).getColor());
                }
            }
        }
    }
}
