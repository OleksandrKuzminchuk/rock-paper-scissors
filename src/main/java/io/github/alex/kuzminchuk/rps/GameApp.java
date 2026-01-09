package io.github.alex.kuzminchuk.rps;

import io.github.alex.kuzminchuk.rps.logic.GameLogicPcVsPc;
import io.github.alex.kuzminchuk.rps.logic.GameLogicUserVsPc;
import io.github.alex.kuzminchuk.rps.logic.GameLogicUserVsUser;

import java.util.Scanner;

public class GameApp {

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Rock Paper Scissors ===");
            System.out.println("1) Human vs Computer");
            System.out.println("2) Human vs Human");
            System.out.println("3) Computer vs Computer");
            System.out.println("0) Exit");
            System.out.print("Choose mode: ");

            String choice = scanner.nextLine().trim();

            if ("0".equals(choice)) {
                System.out.println("Bye!");
                return;
            }

            GameLogic game = createGame(choice, scanner);
            if (game == null) {
                System.out.println("Unknown option. Choose 1/2/3 or 0.");
                continue;
            }

            game.start();
            System.out.println();
        }
    }

    private GameLogic createGame(String choice, Scanner scanner) {
        if ("1".equals(choice)) return new GameLogicUserVsPc(scanner);
        if ("2".equals(choice)) return new GameLogicUserVsUser(scanner);
        if ("3".equals(choice)) return new GameLogicPcVsPc(scanner);
        return null;
    }
}
