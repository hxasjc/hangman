import java.util.*;

class Main {
  static Random rand;
  static ArrayList<String> defaultWords;
  static ArrayList<Character> wordLetters;
  static String chosenWord;
  static Scanner scan;
  static int strikes;

  public static void initWords() {
    defaultWords = new ArrayList<String>();
    defaultWords.add("Hi");
    defaultWords.add("Word");
    defaultWords.add("Idk");
    defaultWords.add("Cool");
    defaultWords.add("AaBbCcDd");

    for (int i = 1; i <= defaultWords.size(); i++) {
      String str = defaultWords.get(i - 1).toLowerCase();
      defaultWords.set(i - 1, str);
    }
  }

  public static void startGame() {
    wordLetters.clear();
    chosenWord = defaultWords.get(rand.nextInt(defaultWords.size()));

    System.out.println(chosenWord);

    for (int i = 0; i < chosenWord.length(); i++) {
      wordLetters.add('_');
    }

    while (wordLetters.contains('_')) {
      System.out.println(wordLetters);
      String guessStr;
      char guessChar;
      boolean containsLetter = false;

      System.out.println("Guess a letter");
      guessStr = scan.nextLine();
      guessChar = guessStr.charAt(0);

      System.out.println("Guessed character: " + guessChar);

      for (int i = 0; i < chosenWord.length(); i++) {
        if (guessChar == chosenWord.charAt(i)) {
          wordLetters.set(i, guessChar);
          containsLetter = true;
        }
      }

      if (containsLetter == false) {
        strikes++;
      }
    }
  }

  public static void main(String[] args) {
    rand = new Random();
    wordLetters = new ArrayList<Character>();
    scan = new Scanner(System.in);
    strikes = 0;
    initWords();

    startGame();

    System.out.println(wordLetters);
}
}