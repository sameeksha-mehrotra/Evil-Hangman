/*  Student information for assignment:


 *
 *  On my honor, Sameeksha Mehrotra, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name: Sameeksha Mehrotra
 *  email address: sm85495@utexas.edu
 *  UTEID: sm85495
 *  Section 5 digit ID: 50750
 *  Grader name: Leul Dagnachew
 *  Number of slip days used on this assignment: 0
 */

// add imports as necessary

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Manages the details of EvilHangman. This class keeps
 * tracks of the possible words from a dictionary during
 * rounds of hangman, based on guesses so far.
 *
 */
public class HangmanManager {

    // instance variables / fields
    private Set<String> originalWords;
    private boolean debug;
    private int wordLength;
    private int numGuesses;
    private ArrayList<Character> guessed;
    private HangmanDifficulty difficulty;
    private Set<String> activeWords;
    private String pattern;

    /**
     * Create a new HangmanManager from the provided set of words and phrases.
     * pre: words != null, words.size() > 0
     *
     * @param words A set with the words for this instance of Hangman.
     * @param debugOn true if we should print out debugging to System.out.
     */
    public HangmanManager(Set<String> words, boolean debugOn) {
        // Checks pre-conditions and throws exception if conditions aren't met
        if(words == null || words.size() <= 0) {
            throw new IllegalArgumentException("words cannot be null and its size must"
                    + " be greater than 0.");
        }
        
        originalWords = new HashSet<>(words);
        this.debug = debugOn;
    }

    /**
     * Create a new HangmanManager from the provided set of words and phrases.
     * Debugging is off.
     * pre: words != null, words.size() > 0
     *
     * @param words A set with the words for this instance of Hangman.
     */
    public HangmanManager(Set<String> words) {
        // Checks pre-conditions and throws exception if conditions aren't met
        if(words == null || words.size() <= 0) {
            throw new IllegalArgumentException("words cannot be null and its size must"
                    + " be greater than 0.");
        }
        
        originalWords = new HashSet<>(words);
        debug = false;
    }


    /**
     * Get the number of words in this HangmanManager of the given length.
     * pre: none
     *
     * @param length The given length to check.
     * @return the number of words in the original Dictionary with the given
     *         length
     */
    public int numWords(int length) {
        int wordCount = 0;
        
        // Iterates through original word lest to return number of words
        // that have the given length
        Iterator<String> iterator = originalWords.iterator();
        
        while(iterator.hasNext()) {
            String w = iterator.next();
            
            if(w.length() == length) {
                wordCount++;
            }
        }
        
        return wordCount;
    }


    /**
     * Get for a new round of Hangman. Think of a round as a complete game of
     * Hangman.
     *
     * @param wordLen the length of the word to pick this time.
     *                numWords(wordLen) > 0
     * @param numGuesses the number of wrong guesses before the player loses
     *                   the round. numGuesses >= 1
     * @param diff The difficulty for this round.
     */
    public void prepForRound(int wordLen, int numGuesses, HangmanDifficulty diff) {
        // Checks pre-conditions and throws exception if conditions aren't met
        if(!(numWords(wordLen) > 0) && !(numGuesses>=1)) {
            throw new IllegalArgumentException("the number of words that have this "
                    + "word length must be greater than 0. the number of wrong guesses"
                    + " must also be greater than or equal to 1.");
        }
        
        // Reset all variables for new round
        wordLength = wordLen;
        difficulty = diff;
        this.numGuesses = numGuesses;
        guessed = new ArrayList<>();
        
        // Iterates through original dictionary called words and adds words that have tbe 
        // user length to the activeWords list
        activeWords = new HashSet<>();
         
        Iterator<String> iterator = originalWords.iterator();
        
        while(iterator.hasNext()) {
            String w = iterator.next();
            
            if(w.length() == wordLength) {
                activeWords.add(w);
            }
        }
        
        // Get defualt pattern that is equal to given wordLength
        pattern = "";
        for(int i = 0; i<wordLength; i++) {
            pattern += "-";
        }
    }


    /**
     * The number of words still possible (live) based on the guesses so far.
     * Guesses will eliminate possible words.
     *
     * @return the number of words that are still possibilities based on the
     * original dictionary and the guesses so far.
     */
    public int numWordsCurrent() {
        return activeWords.size();
    }


    /**
     * Get the number of wrong guesses the user has left in this round (game)
     * of Hangman.
     *
     * @return the number of wrong guesses the user has left in this round
     *         (game) of Hangman.
     */
    public int getGuessesLeft() {
        return numGuesses;
    }


    /**
     * Return a String that contains the letters the user has guessed so far
     * during this round. The characters in the String are in alphabetical
     * order. The String is in the form [let1, let2, let3, ... letN].
     * For example: [a, c, e, s, t, z]
     *
     * @return a String that contains the letters the user has guessed so far
     *         during this round.
     */
    public String getGuessesMade() {
        // Sort alphabetically
        Collections.sort(guessed);
        return guessed.toString();
    }


    /**
     * Check the status of a character.
     *
     * @param guess The characater to check.
     * @return true if guess has been used or guessed this round of Hangman,
     *         false otherwise.
     */
    public boolean alreadyGuessed(char guess) { 
        
        for(int i = 0; i< guessed.size(); i++) {
            
            if(guessed.get(i) == guess) {
                return true;
            }
        }
        return false;
    }


    /**
     * Get the current pattern. The pattern contains '-''s for unrevealed (or
     * guessed) characters and the actual character for "correctly guessed"
     * characters.
     *
     * @return the current pattern.
     */
    public String getPattern() {
        return pattern;
    }
    
    
    /**
     * Helper method that updates the string pattern of the game. The pattern updates 
     * depending on if the word includes the user's guess.
     *
     *@param String pattern used to iterate and char guess used to check for each letter
     * @return the updated pattern.
     */
    private String updatePattern(String pattern, char guess) {
        String updatedPattern = "";
        
        for(int i = 0; i < pattern.length(); i++) {
            
            if(pattern.charAt(i) == guess) {
                updatedPattern += guess;
            } else {
                updatedPattern += this.pattern.charAt(i);
            }
        }
        
        return updatedPattern;
    }


    /**
     * Update the game status (pattern, wrong guesses, word list), based on
     * the given guess.
     *
     * @param guess pre: !alreadyGuessed(ch), the current guessed character
     * @return return a tree map with the resulting patterns and the number of
     *         words in each of the new patterns. The return value is for
     *         testing and debugging purposes.
     */
    public TreeMap<String, Integer> makeGuess(char guess) {
        // Checks pre-conditions and throws exception if conditions aren't met
        if(alreadyGuessed(guess)) {
            throw new IllegalStateException();
        }
        
        guessed.add(guess);
        
        Map<String, ArrayList<String>> families = new HashMap<String, ArrayList<String>>();
        
        // Updates the families map
        families = createFamilies(families, guess);

        // Goes through families and gets the number of words in each pattern. Then,
        // puts the pattern and number of words with that pattern in a new map
        TreeMap<String, Integer> familyCount =  new TreeMap<>();
       
        for (Map.Entry<String, ArrayList<String>> entry : families.entrySet()) {
            familyCount.put(entry.getKey(), entry.getValue().size());
        }
        
        getPatternOfDifficulty(difficulty, familyCount);
        
        activeWords.clear();
        
        for(String word: families.get(pattern)) {
            activeWords.add(word);
        }
        
        if(pattern.indexOf(guess) == -1) {
            numGuesses--;
        }
        
        if(debug) {
            System.out.println();
            System.out.println("DEBUGGING: " + "the number of guesses left: " + numGuesses);
        }
        
        return familyCount;
    }

    /**
     * Helper method to update the families map with the patterns and guess given by the user.
     *
     *@param Map<String, ArrayList<String>> families to iterate through and char guess to find
     * pattern for each family
     * 
     * @return return the updated families map.
     */
    private Map<String, ArrayList<String>> createFamilies 
        (Map<String, ArrayList<String>> families, char guess) {
        
        // Iterates through the activeWords list and adds words to specific families
        // grouped by pattern
        Iterator<String> iterator = activeWords.iterator();
        
        while(iterator.hasNext()) {
            String currentWord = iterator.next();
            String currentPattern = updatePattern(currentWord, guess);
            
            if(families.containsKey(currentPattern)) {
                families.get(currentPattern).add(currentWord);
            } else {
                ArrayList<String> currentWordOfList = new ArrayList<>();
                currentWordOfList.add(currentWord);
                families.put(currentPattern, currentWordOfList);
            }
        }
        return families;
    }
    
    /**
     * Helper method to get specified family based on user difficulty
     * 
     * @param HangmanDifficulty difficulty used to find hardest pattern based off that. 
     * TreeMap<String, Integer> familyCount used to loop through and get specified values
     * and key for pattern
     * 
     * @return the hardest pattern based on given difficulty
     */
    private void getPatternOfDifficulty
        (HangmanDifficulty difficulty, TreeMap<String, Integer> familyCount) {
        
        final int MEDIUM_DIFF_PATTERN = 4;
        final int EASY_DIFF_PATTERN = 2;
        
        String secretPattern = "";
        
        ArrayList<CompareClass> sortedPatterns = new ArrayList<CompareClass>();
        
        for(Map.Entry<String, Integer> entry : familyCount.entrySet()) {
            sortedPatterns.add(new CompareClass(entry.getValue(), entry.getKey()));
        }
        
        Collections.sort(sortedPatterns);
        
        boolean isMedium = (difficulty == HangmanDifficulty.MEDIUM 
                && guessed.size() % MEDIUM_DIFF_PATTERN == 0);
        boolean isEasy = (difficulty == HangmanDifficulty.EASY 
                && guessed.size() % EASY_DIFF_PATTERN == 0);
        
        // Based on difficulty, get hardest (or second hardest) pattern
        if((isMedium || isEasy) && sortedPatterns.size() > 1) 
        {
            secretPattern = sortedPatterns.get(1).getSecretPattern();
        }  else {
            secretPattern = sortedPatterns.get(0).getSecretPattern();
        }
        
        pattern = secretPattern;
        
        if(debug) {
            System.out.println();
            
            System.out.println("DEBUGGING: " + "Picking hardest list.");
            System.out.println("DEBUGGING: New pattern is: " + pattern + ". " + "New family has "
                    + familyCount.get(pattern) + " words.");
            
            System.out.println();
        }
        
    }
    
    /**
     * Implements Comparable to compare two objects using overroded compareTo method
     */
    private class CompareClass implements Comparable<CompareClass> {
        
        // Instance vars for this class
        private int numOfWords;
        private String wordPattern;
        
        // Constructor that creates a new CompareClass object with given parameters
        public CompareClass (int numOfWords, String wordPattern) {
            this.numOfWords = numOfWords;
            this.wordPattern = wordPattern;     
        }
        
        public int getNumOfWords() {
            return numOfWords;
        }
        
        
        public String getSecretPattern() {
            return wordPattern;
        }
        
        // Overrides the compareTo method in Object.
        // Returns the object based on thE conditions.
        @Override
        public int compareTo(CompareClass other) {
            if(numOfWords != other.getNumOfWords()) {
                return other.getNumOfWords() - numOfWords;
            } 
            
            if(getCharsInPattern(wordPattern) != getCharsInPattern(other.getSecretPattern())) {
                return getCharsInPattern(wordPattern) - getCharsInPattern(other.getSecretPattern());
            } else {
                return wordPattern.compareTo(other.getSecretPattern());
            }
        }
    }
    
    
    /**
     * Helper method to get number of characters in the pattern that aren't dashes (revealed)
     * 
     * @param String wordPattern that is the pattern used to iterate through
     * @return the number of revelaed characters in given wordPattern
     */
    private int getCharsInPattern(String wordPattern) {
        int dashCount = 0;
        
        for(int i = 0; i < wordPattern.length(); i++) {
            if(wordPattern.charAt(i) == '-') {
                dashCount++;
            }
        }
        
        return wordPattern.length() - dashCount;
    }

    /**
     * Return the secret word this HangmanManager finally ended up picking for
     * this round. If there are multiple possible words left one is selected
     * at random.
     * pre: numWordsCurrent() > 0
     *
     * @return return the secret word the manager picked.
     */
    public String getSecretWord() {
        // Checks pre-conditions and throws exception if conditions aren't met
        if(!(numWordsCurrent() > 0)) {
            throw new IllegalArgumentException("current list of words must be greater than 0.");
        }
       
       String secretWord = "";
       Iterator<String> iterator = activeWords.iterator();
       if (iterator.hasNext()) {
           secretWord = iterator.next();
       }
       return secretWord;
    }
}