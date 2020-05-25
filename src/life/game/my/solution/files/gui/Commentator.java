package life.game.my.solution.files.gui;

import life.game.my.solution.files.Organism;
import life.game.my.solution.files.Position;

public class Commentator
{
    public Commentator(){}
    public String announceMove(Organism organism, Position from, Position to){
        return organism.getName()+" poruszył się z "+from.getX()+" "+from.getY()+" na "+to.getX()+" "+to.getY()+"\n";
    }
    public String announceKill(Organism killer, Organism victim){
        return killer.getName()+" na pozycji "+killer.getPosition().getX()+" "+killer.getPosition().getY()+" zabija "+victim.getName()+" na pozycji"+victim.getPosition().getX()+" "+victim.getPosition().getY()+"\n";
    }
    public String announceBreed(Organism first, Organism second,Organism child){
        return first.getName()+" na pozycji "+first.getPosition().getX()+" "+first.getPosition().getY()+" tworzy potomka z "+second.getName()+" na pozycji"+second.getPosition().getX()+" "+second.getPosition().getY()+
                " na pozycji "+child.getPosition().getX()+" "+child.getPosition().getY()+"\n";
    }
}
