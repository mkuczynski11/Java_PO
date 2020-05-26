package life.game.my.solution.files.gui;

import javax.swing.*;
import java.awt.*;

public class EventLog extends JPanel
{
    private JTextArea textArea;
    private Screen screen;

    public EventLog(Screen screen){
        this.screen = screen;
        setLayout(new BorderLayout());
        textArea = new JTextArea();
        textArea.setLayout(null);
        add(textArea);
        textArea.append("Wydarzenia na planszy podczas trwania tury\n");
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}
