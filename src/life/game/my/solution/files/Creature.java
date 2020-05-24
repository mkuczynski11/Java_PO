package life.game.my.solution.files;

import java.awt.*;

public interface Creature
{
    void action();
    boolean collision(Organism enemy);
    String getName();
    Organism copy(Position position);
    void draw();
    void save();
    Color getColor();
}
