package life.game.my.solution.files;

import java.awt.*;

public interface Creature
{
    void action();
    boolean collision(Organism enemy);
    String getName();
    Organism child(Position position);
    void save();
    Color getColor();
}
