import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Card Dealing Program!");

        initializeDeck();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Press Enter to deal four cards or 'q' to quit");
            String userInput = scanner.nextLine();

            if (userInput.equals("q")) {
                System.out.println("Thanks for playing! Goodbye!");
                recordDealtCards();
                break;
            }

            dealCards();
            recordDealtCards();
        }

        scanner.close();
    }


    private static List<String> deck = new ArrayList<>();
    private static List<String> dealtCards = new ArrayList<>();

    public static void initializeDeck() {
        String[] suits = {"S", "H", "D", "C"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (String suit : suits) {
            for (String rank : ranks) {
                String card = rank + suit;
                deck.add(card);
            }
        }
    }

    public static void dealCards() {
        if (deck.isEmpty()) {
            System.out.println("Sorry, the deck is empty");
            return;
        }

        Random rand = new Random();

        for (int i = 0; i < 4; i++) {
            int randomIndex = rand.nextInt(deck.size());
            String card = deck.get(randomIndex);
            System.out.println("Card " + (i + 1) + ": " + card);
            dealtCards.add(card);
            deck.remove(randomIndex);
        }
    }

    public static void recordDealtCards() {
        if (dealtCards.isEmpty()) {
            return;
        }

        try (PrintWriter outFile = new PrintWriter(new FileWriter("CardsDealt.txt", true))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date currentTime = new Date();
            outFile.println(dateFormat.format(currentTime));

            for (String card : dealtCards) {
                outFile.print(card + ", ");
            }
            outFile.println();
        } catch (IOException e) {
            System.err.println("Error opening file");
        }
    }

}