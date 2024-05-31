package com.example.repo8_cardsgame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HelloAplication {
    //Deck setup: Four sets of cards from 2 to Ace
    static final String[] valuesCards = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    static final String[] sticks = {"\u2660", "\u2764", "\u2663", "\u2666"};
    static List<String> cards = new ArrayList<>();
    static Random random = new Random();

    public static void main(String[] args) {
        initializeDeck();
        menu();
    }

    static void initializeDeck() {
        for (String valor : valuesCards) {
            for (int i = 0; i < 4; i++) {
                cards.add(valor);
            }
        }
        Collections.shuffle(cards);
    }

    static void distribute() {
        List<String> cardsDealt = new ArrayList<>();
        List<List<List<String>>> matrixLetters = new ArrayList<>();

        for (int Round = 1; Round
<= 5; Round++) {
            System.out.println("\nRound " + Round + ":");
            List<List<String>> roundsCards = new ArrayList<>();

            for (int player = 1; player <= 3; player++) {
                System.out.println("\t\tPlayer " + player + ":");
                List<String> handPlayer = new ArrayList<>();
                List<String> AssignedLetters = new ArrayList<>();

                // Repartir cartas iniciales
                for (int i = 0; i < 3; i++) {
                    String[] letterSuit = distributeLetter(cardsDealt);
                    handPlayer.add(letterSuit[0] + letterSuit[1]);
                    AssignedLetters.add(letterSuit[0] + letterSuit[1]);
                }

                roundsCards.add(AssignedLetters);
                System.out.println("Initial letters: " + String.join(", ", handPlayer));
            }

            matrixLetters.add(roundsCards);
        }

        // Imprimir la matriz de cartas
        System.out.println("\nAssigned Card Matrix:");
        for (int i = 0; i < matrixLetters.size(); i++) {
            System.out.println("\n\tRound " + (i + 1) + ":");
            List<List<String>> round = matrixLetters.get(i);
            for (int j = 0; j < round.size(); j++) {
                System.out.println("Player " + (j + 1) + ": " + String.join(", ", round.get(j)));
            }
        }
    }

    static String[] distributeLetter(List<String> cardsDealt) {
        while (true) {
            String letter = cards.get(random.nextInt(cards.size()));
            String stick = sticks[random.nextInt(sticks.length)];
            String letterSuit = letter + stick;
            if (!cardsDealt.contains(letterSuit)) {
                cardsDealt.add(letterSuit);
                cards.remove(letter);
                return new String[]{letter, stick};
            }
        }
    }

    static void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean keep = true;

        while (keep) {
            System.out.println("\n1. Play Blackjack");
            System.out.println("2. Go out");
            System.out.print("Enter the option you want: ");
            int option = scanner.nextInt();
            scanner.nextLine(); //salto de línea

            if (option == 1) {
                distribute();
            } else if (option == 2) {
                System.out.println("See you later");
                keep = false;
            } else {
                System.out.println("Please enter a valid option");
            }
        }

        scanner.close();
    }
}