package life.game.my.solution.files.gui;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel
{
    public GamePanel(int y, int x){
        setLayout(new GridLayout(y,x));
    }
}
