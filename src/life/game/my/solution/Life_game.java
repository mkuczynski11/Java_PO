package life.game.my.solution;

import java.util.Scanner;
import life.game.my.solution.files.*;

public class Life_game {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Witaj w aplikacji Gra w zycie. Wybierzy wymiary: ");
        System.out.println("Podaj szerokosc planszy");
        int x = scanner.nextInt();
        System.out.println("Podaj wysokosc planszy");
        int y = scanner.nextInt();

        World world = new World(x,y);
    }
}
