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
    public String announceAvoid(Organism attacker, Organism defender){
        return defender.getName()+" na pozycji "+defender.getPosition().getX()+" "+defender.getPosition().getY()+" odbija atak "+attacker.getName()+" na pozycji"+attacker.getPosition().getX()+" "+attacker.getPosition().getY()+"\n";
    }
    public String announceRunaway(Organism attacker, Organism defender){
        return defender.getName()+" na pozycji "+defender.getPosition().getX()+" "+defender.getPosition().getY()+" ucieka przed "+attacker.getName()+" na pozycji"+attacker.getPosition().getX()+" "+attacker.getPosition().getY()+"\n";
    }
    public String announceSeed(Organism parent, Organism child){
        return parent.getName()+" na pozycji "+parent.getPosition().getX()+" "+parent.getPosition().getY()+" tworzy potmka na pozycji "+child.getPosition().getX()+" "+child.getPosition().getY()+"\n";
    }
    public String announceConsume(Organism animal, Organism plant){
        return animal.getName()+" na pozycji "+animal.getPosition().getX()+" "+animal.getPosition().getY()+" zjada "+plant.getName()+" na pozycji"+plant.getPosition().getX()+" "+plant.getPosition().getY()+"\n";
    }
    public String announceStrengthRise(Organism organism){
        return organism.getName()+" zwieksza sila o 3 i tera ma "+organism.getStrength()+"sily\n";
    }

}
