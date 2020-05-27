package life.game.my.solution.files.gui;

import life.game.my.solution.files.Position;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameButton extends JButton
{
    private final int row;
    private final int col;

    public GameButton(String t, int col ,int row){
        super(t);
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
