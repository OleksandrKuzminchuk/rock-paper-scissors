package io.github.alex.kuzminchuk.rps.logic;

import io.github.alex.kuzminchuk.rps.Field;
import io.github.alex.kuzminchuk.rps.GameLogic;

import java.util.Scanner;

public class GameLogicUserVsUser implements GameLogic {

    private final Field field = new Field();
    private final Scanner scanner;

    public GameLogicUserVsUser(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void start() {
        System.out.println("Mode: Human vs Human");
        System.out.println("Type: rock/paper/scissors (or 1/2/3). Type 'exit' to return to menu.");

        while (true) {
            String p1 = readMove("Player 1 move: ");
            if (p1 == null) return;

            String p2 = readMove("Player 2 move: ");
            if (p2 == null) return;

            field.setFigure1(p1);
            field.setFigure2(p2);

            printRound();
        }
    }

    private String readMove(String prompt) {
        while (true) {
            System.out.print(prompt);
            String raw = scanner.nextLine().trim().toLowerCase();

            if (isExit(raw)) return null;

            String move = normalizeMove(raw);
            if (move != null) return move;

            System.out.println("Invalid input. Use rock/paper/scissors or 1/2/3. Or 'exit'.");
        }
    }

    private boolean isExit(String s) {
        return "exit".equals(s) || "quit".equals(s) || "q".equals(s) || "0".equals(s);
    }

    private String normalizeMove(String s) {
        if ("1".equals(s) || "r".equals(s) || "rock".equals(s) || "камень".equals(s)) return "rock";
        if ("2".equals(s) || "p".equals(s) || "paper".equals(s) || "бумага".equals(s)) return "paper";
        if ("3".equals(s) || "s".equals(s) || "scissors".equals(s) || "ножницы".equals(s)) return "scissors";
        return null;
    }

    private void printRound() {
        String a = field.getFigure1();
        String b = field.getFigure2();

        System.out.println("Player 1" + ": " + a);
        System.out.println("Player 2" + ": " + b);
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
