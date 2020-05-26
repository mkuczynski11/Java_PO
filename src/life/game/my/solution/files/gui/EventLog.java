package life.game.my.solution.files.gui;

import life.game.my.solution.files.World;

import javax.swing.*;
import java.awt.*;

public class EventLog extends JPanel
{
    private JTextArea textArea;
    private JFrame frame;

    public EventLog(JFrame frame){
        this.frame = frame;
        setLayout(new BorderLayout());
        textArea = new JTextArea();
        textArea.setLayout(null);
        add(textArea);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void changeLogs(World world){
        textArea.setText(world.getLogs().getLogs());
        world.getLogs().clearLogs();
    }
}
