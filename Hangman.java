import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        // Array of words for the game
        String[] words = {"programming", "java", "python", "hangman", "developer", "algorithm", "software", "computer", "network", "artificial"};
        String word = words[random.nextInt(words.length)]; // Randomly select a word

        char[] guessedWord = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            guessedWord[i] = '_';
        }

        StringBuilder incorrectGuesses = new StringBuilder();
        int maxAttempts = 6;
        int attempts = 0;

        while (attempts < maxAttempts) {
            System.out.println("\nCurrent word: " + String.valueOf(guessedWord));
            System.out.println("Incorrect guesses: " + incorrectGuesses);
            System.out.println("You have " + (maxAttempts - attempts) + " attempts left.");
            System.out.print("Guess a letter: ");
            char guess = sc.next().toLowerCase().charAt(0);

            // Check if the guess has already been made
            if (incorrectGuesses.toString().contains(String.valueOf(guess)) || String.valueOf(guessedWord).contains(String.valueOf(guess))) {
                System.out.println("You've already guessed that letter.");
                continue;
            }

            boolean correctGuess = false;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess) {
                    guessedWord[i] = guess;
                    correctGuess = true;
                }
            }

            if (!correctGuess) {
                incorrectGuesses.append(guess).append(" ");
                attempts++;
            }

            // Check if the user has guessed the entire word
            if (String.valueOf(guessedWord).equals(word)) {
                System.out.println("\nCongratulations! You've guessed the word: " + word);
                break;
            }
        }

        if (attempts == maxAttempts) {
            System.out.println("\nGame Over! The word was: " + word);
        }

        sc.close();
    }
}
