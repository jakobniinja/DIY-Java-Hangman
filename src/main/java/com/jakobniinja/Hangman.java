package com.jakobniinja;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Hangman extends MyWindow {

  private String phrase;

  private String clue;

  private String wrongLetters;

  public static final String FILENAME = "src/main/resources/phrases.txt";

  public Hangman() {

    ArrayList phrases = new ArrayList();
    Random rand = new Random();

    try {
      BufferedReader in = new BufferedReader(new FileReader(FILENAME));
      String s = in.readLine();
      while (s != null) {
        phrases.add(s);
        s = in.readLine();
      }
      in.close();
      int numberOfPhrases = phrases.size();

      boolean again = true;
      while (again) {
        int pick = rand.nextInt(numberOfPhrases);
        phrase = phrases.get(pick).toString().toUpperCase();
        blankOutClue();

        wrongLetters = "";
        printPuzzle();

        while (clue.contains("-") && wrongLetters.length() < 10) {
          String guess = promptforString("Guess a letter");
          guess = guess.toUpperCase();

          if (guess.length() != 1) {
            print("Your guess must contain only 1 letter. Guess");

          } else if (clue.contains(guess) || wrongLetters.contains(guess)) {
            print("You already guessed " + guess + ". Guess Again.");
          } else {
            boolean found = false;

            int index = phrase.indexOf(guess);
            while (index > -1) {
              found = true;
              clue = MyStringMethods.replaceStringAt(clue, index, guess);
              index = phrase.indexOf(guess, index + 1);
            }

            if (!found) {
              wrongLetters += guess;
            }
            printPuzzle();
          }
        }
        if (!clue.contains("-")) {
          print("Congratulations! You guessed it before you were hanged");
        } else {
          print("You lose! " + phrase);
        }
        again = promptForYesNo("Do you want to play again?");
      }
      System.exit(0);

    } catch (FileNotFoundException e) {
      throw new RuntimeException("Could not find file: " + FILENAME, e);
    } catch (IOException e) {
      throw new RuntimeException("IOException", e);
    }
  }

  private void blankOutClue() {
    clue = "";
    for (int i = 0; i < phrase.length(); i++) {
      if (phrase.charAt(i) == ' ') {
        clue += ' ';
      } else {
        clue += '-';
      }
    }
  }

  private void printPuzzle() {
    print("____");

    switch (wrongLetters.length()) {
      case 0:
        print("     ____     ");
        print("     |/        ");
        print("     |          ");
        print("     |           ");
        print("     |         ");
        print("     |           ");
        print("     |           ");
        print("     | " + wrongLetters);
        break;

      case 1:
        print("     _________");
        print("     |/        ");
        print("     |          ");
        print("     |           ");
        print("     |         ");
        print("     |           ");
        print("     |           ");
        print("     | " + wrongLetters);
        break;

      case 2:
        print("     _________");
        print("     |/       |");
        print("     |          ");
        print("     |           ");
        print("     |         ");
        print("     |           ");
        print("     |           ");
        print("     | " + wrongLetters);
        break;

      case 3:
        print("     _________");
        print("     |/       |");
        print("     |       (  ");
        print("     |           ");
        print("     |         ");
        print("     |           ");
        print("     |           ");
        print("     | " + wrongLetters);
        break;

      case 4:
        print("     _________");
        print("     |/       |");
        print("     |       (_)");
        print("     |           ");
        print("     |         ");
        print("     |           ");
        print("     |           ");
        print("     | " + wrongLetters);
        break;

      case 5:
        print("     _________");
        print("     |/       |");
        print("     |       (_)");
        print("     |       \\  ");
        print("     |         ");
        print("     |           ");
        print("     |           ");
        print("     | " + wrongLetters);
        break;

      case 6:
        print("     _________");
        print("     |/       |");
        print("     |       (_)");
        print("     |       \\| ");
        print("     |         ");
        print("     |           ");
        print("     |           ");
        print("     | " + wrongLetters);
        break;

      case 7:
        print("     _________");
        print("     |/       |");
        print("     |       (_)");
        print("     |       \\|/");
        print("     |         ");
        print("     |           ");
        print("     |           ");
        print("     | " + wrongLetters);

        break;

      case 8:
        print("     _________");
        print("     |/       |");
        print("     |       (_)");
        print("     |       \\|/");
        print("     |        |");
        print("     |           ");
        print("     |           ");
        print("     | " + wrongLetters);

        break;

      case 9:
        print("     _________");
        print("     |/       |");
        print("     |       (_)");
        print("     |       \\|/");
        print("     |        |");
        print("     |       /   ");
        print("     |           ");
        print("     | " + wrongLetters);
        break;

      case 10:
        print("     _________");
        print("     |/       |");
        print("     |       (_)");
        print("     |       \\|/");
        print("     |        |");
        print("     |       / \\");
        print("     |           ");
        print("     | " + wrongLetters);
        break;
    }

    print("    _|___         " + clue);
  }

  public static void main(String[] args) {
    new Hangman();
  }
}
