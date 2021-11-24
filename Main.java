import java.util.*;

class Main {
  static Random rand; // declare variables
  static ArrayList<String> defaultWords;
  static ArrayList<Character> wordLetters;
  static ArrayList<Character> guessedLetters;
  static String chosenWord;
  static Scanner scan;
  static int strikes;

  public static void initWords() {
    defaultWords = new ArrayList<String>(); // initialize an ArrayList and fill it with words
    defaultWords.add("AaBbCcDd");
    defaultWords.add("two words");
    defaultWords.add("supercalifragilisticexpialidocious");
    defaultWords.add("Coruscant");

    for (int i = 1; i <= defaultWords.size(); i++) {
      boolean invalidChars = false;
      ArrayList<String> invalidCharsAL = new ArrayList<String>();

      String str = defaultWords.get(i - 1).toLowerCase();

      for (int j = 0; j < str.length(); j++) {
        StringBuilder sb = new StringBuilder();
        char c = str.charAt(j);
        if (Character.isLetter(c) || Character.isWhitespace(c)) {
          if (Character.isLowerCase(c) || Character.isWhitespace(c)) {
            // all good
          } else {
            invalidChars = true;
            sb.append(c);
            sb.append(": Uppercase");
            invalidCharsAL.add(sb.toString());
          }
        } else {
          invalidChars = true;
          sb.append(c);
          sb.append(": Not a letter");
          invalidCharsAL.add(sb.toString());
        }
      }

      if (invalidChars == true) {
        System.out.println("Word \"" + str + "\" has been removed from the word list due to invalid characters " + invalidCharsAL);
        defaultWords.remove(i - 1);
      } else {
        defaultWords.set(i - 1, str);
      }
    }

    System.out.println(defaultWords);
  }

  public static void startGame() {
    wordLetters.clear(); // reset letters ArrayList
    guessedLetters.clear();
    chosenWord = defaultWords.get(rand.nextInt(defaultWords.size())); // pick a random word
    System.out.println("All words are lowercase and do not contain numbers or special characters");

    //System.out.println(chosenWord); // print it for debug

    for (int i = 0; i < chosenWord.length(); i++) { // fill ArrayList for letters
      if (chosenWord.charAt(i) == ' ') {
        wordLetters.add(' ');
      } else {
        wordLetters.add('_');
      }
    }

    while (wordLetters.contains('_')) { // while not all letters have been guessed
      String guessStr; // declare some more variables
      char guessChar;
      boolean containsLetter = false;
      int letterCount = 0;

      System.out.println(printWord()); // print current guesses

      System.out.println("Guess a letter"); // prompts guess
      guessStr = scan.nextLine(); // get guess
      if (!guessStr.equals("")) { // checks if the string is not empty
        guessChar = guessStr.charAt(0); // get first letter (the letter to guess)

        if (!guessedLetters.contains(guessChar)) { // checks if the letter has already been guessed

        for (int i = 0; i < chosenWord.length(); i++) { // checks if the letter is in the word and sets it in the ArrayList
          if (guessChar == chosenWord.charAt(i)) {
            wordLetters.set(i, guessChar);
            containsLetter = true;
            letterCount++;
          }
        }

        if (containsLetter == false) { // gives a strike for false guesses
          strikes++;
        }

        System.out.println("You guessed '" + guessChar + "'. It is in the word " + letterCount + " times."); // tells you what you guessed and how many times it was in the word

        printHangman(); // print hangman
        System.out.println(); // blank space
        guessedLetters.add(guessChar); // added guessed letter to array
      } else {
        System.out.println("You already guessed '" + guessChar + "'."); // prints if letter was already guessed
      }
      }
    }

    System.out
        .println("Congrats! You won! The word was \"" + chosenWord + "\". You had " + strikes + " incorrect guesses."); // winning line
  }

  public static void printHangman() { // prints the hangmen (will get designs later)
    System.out.println("Hangman stage " + strikes);
  }

  public static String printWord() { // compiles the letter ArrayList into a string
    StringBuilder sb = new StringBuilder();

    for (char c : wordLetters) {
      sb.append(c);
    }

    return sb.toString();
  }

  public static void main(String[] args) {
    System.out.println(Character.getNumericValue('a')); // debug
    System.out.println(Character.getNumericValue('z'));

    rand = new Random(); // initialize general variables
    wordLetters = new ArrayList<Character>();
    guessedLetters = new ArrayList<Character>();
    scan = new Scanner(System.in);
    strikes = 0;
    initWords(); // initialize word list

    startGame(); // start playing
  }
}