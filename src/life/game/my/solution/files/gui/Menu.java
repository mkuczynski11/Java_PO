package life.game.my.solution.files.gui;

import life.game.my.solution.files.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel
{
    private JButton bExit;
    private JButton bNextTurn;
    private JButton bSaveGame;
    private JButton bLoadGame;

    public Menu(JFrame frame, GamePanel gamePanel, EventLog eventLog,JButton[][] board, World world){
        setLayout(new GridLayout(4,1));

        this.bExit = new JButton("Exit");
        bExit.setBackground(Color.BLACK);
        bExit.setForeground(Color.white);
        bExit.setLayout(null);
        bExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        this.bNextTurn = new JButton("Tura");
        bNextTurn.setBackground(Color.BLACK);
        bNextTurn.setForeground(Color.white);
        bNextTurn.setLayout(null);
        bNextTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.makeTurn();
                frame.remove(gamePanel);
                frame.add(new GamePanel(world.getScreenY(), world.getScreenX(), world));
            }
        });

        this.bSaveGame = new JButton("Zapisz");
        bSaveGame.setBackground(Color.BLACK);
        bSaveGame.setForeground(Color.white);
        bSaveGame.setLayout(null);
        bSaveGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        this.bLoadGame = new JButton("Zaladuj");
        bLoadGame.setBackground(Color.BLACK);
        bLoadGame.setForeground(Color.white);
        bLoadGame.setLayout(null);
        bLoadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        add(bExit);
        add(bNextTurn);
        add(bSaveGame);
        add(bLoadGame);
    }
}
