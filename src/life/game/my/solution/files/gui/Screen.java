package life.game.my.solution.files.gui;

import life.game.my.solution.files.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen
{
    public Screen(World world){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                /*try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }*/
                JFrame frame = new JFrame("Life game");
                frame.setLayout(new BorderLayout());
                int x = world.getScreenX();
                int y = world.getScreenY();

                GamePanel gamePanel = new GamePanel(y,x, world);
                frame.getContentPane().add(gamePanel,BorderLayout.CENTER);

                EventLog eventlog = new EventLog(frame);
                frame.getContentPane().add(eventlog, BorderLayout.EAST);

                JPanel menu = new JPanel();
                menu.setLayout(new GridLayout(5,1));
                JButton bExit = new JButton("Exit");
                bExit.setBackground(Color.BLACK);
                bExit.setForeground(Color.white);
                bExit.setLayout(null);
                bExit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });
                JButton bNextTurn = new JButton("Tura");
                bNextTurn.setBackground(Color.BLACK);
                bNextTurn.setForeground(Color.white);
                bNextTurn.setLayout(null);
                bNextTurn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        world.makeTurn();
                        gamePanel.changeBoard(world);
                        eventlog.changeLogs(world);
                    }
                });

                JButton bUseAbility = new JButton("Ability");
                bUseAbility.setBackground(Color.BLACK);
                bUseAbility.setForeground(Color.white);
                bUseAbility.setLayout(null);
                bUseAbility.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        world.setUseAbility(true);
                    }
                });

                JButton bSaveGame = new JButton("Zapisz");
                bSaveGame.setBackground(Color.BLACK);
                bSaveGame.setForeground(Color.white);
                bSaveGame.setLayout(null);
                bSaveGame.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        world.saveGame();
                    }
                });

                JButton bLoadGame = new JButton("Zaladuj");
                bLoadGame.setBackground(Color.BLACK);
                bLoadGame.setForeground(Color.white);
                bLoadGame.setLayout(null);
                bLoadGame.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        world.loadGame();
                    }
                });
                menu.add(bExit);
                menu.add(bNextTurn);
                menu.add(bUseAbility);
                menu.add(bSaveGame);
                menu.add(bLoadGame);
                frame.add(menu, BorderLayout.WEST);

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1920, 1080);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setVisible(true);
            }
        });
    }
}