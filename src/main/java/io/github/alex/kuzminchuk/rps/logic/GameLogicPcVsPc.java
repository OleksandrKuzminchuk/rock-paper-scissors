package io.github.alex.kuzminchuk.rps.logic;

import io.github.alex.kuzminchuk.rps.Field;
import io.github.alex.kuzminchuk.rps.GameLogic;

import java.util.Random;
import java.util.Scanner;

public class GameLogicPcVsPc implements GameLogic {

    private final Field field = new Field();
    private final Scanner scanner;
    private final Random random = new Random();

    public GameLogicPcVsPc(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void start() {
        System.out.println("Mode: Computer vs Computer");
        System.out.println("Press Enter to play next round. Type 'exit' to return to menu.");

        while (true) {
            System.out.print("Next round (Enter/exit): ");
            String cmd = scanner.nextLine().trim().toLowerCase();
            if (isExit(cmd)) return;

            String pc1 = randomMove();
            String pc2 = randomMove();

            field.setFigure1(pc1);
            field.setFigure2(pc2);

            printRound();
        }
    }

    private boolean isExit(String s) {
        return "exit".equals(s) || "quit".equals(s) || "q".equals(s) || "0".equals(s);
    }

    private String randomMove() {
        int x = random.nextInt(3);
        if (x == 0) return "rock";
        if (x == 1) return "paper";
        return "scissors";
    }

    private void printRound() {
        String a = field.getFigure1();
        String b = field.getFigure2();

        System.out.println("Computer 1" + ": " + a);
        System.out.println("Computer 2" + ": " + b);
        System.out.println("Result: " + resultText(a, b));
        System.out.println();
    }

    private String resultText(String a, String b) {
        if (a.equals(b)) return "DRAW";
        if (wins(a, b)) return "PLAYER_1_WINS";
        return "PLAYER_2_WINS";
    }

    private boolean wins(String a, String b) {
        return ("rock".equals(a) && "scissors".equals(b))
                || ("paper".equals(a) && "rock".equals(b))
                || ("scissors".equals(a) && "paper".equals(b));
    }
}
