# Evil-Hangman

<b>Overview:</b>
<br/>
This project implements Evil Hangman, a deceptive twist on the classic Hangman game in Java. Unlike traditional Hangman where the computer selects a word upfront, Evil Hangman dynamically adjusts the possible word list based on the player's guesses, delaying the choice of a secret word as long as possible to maximize difficulty. The computer "cheats" by always selecting the largest word family that fits the current pattern, making it harder for the player to win.

This was developed as part of a programming assignment to demonstrate proficiency in data structures, algorithm design, and object-oriented programming. Key learning outcomes include using maps, lists, and sets to manage game state efficiently.

------------------------------------------------------------------------------------------------------------------------

<b>Features:</b>
<br/>
<ul>
  <li>Dynamic Word Selection: Maintains a list of possible words from a dictionary and narrows it down based on guesses, choosing the hardest word family according to tie-breaking rules (largest size, fewest revealed characters, lexicographical order)</li>
  <li>Difficulty Levels: Supports Easy, Medium, and Hard modes, which alter how word families are selected (e.g., alternating between hardest and second-hardest in Easy mode)</li>
  <li>Debugging Mode: Optional debug output to track word families, patterns, and active word lists during gameplay.
Game State Management: Tracks guesses, remaining attempts, current pattern, and reveals the secret word only when necessary (or randomly if multiple options remain)</li>
  <li>Game State Management: Tracks guesses, remaining attempts, current pattern, and reveals the secret word only when necessary (or randomly if multiple options remain)</li>
  <li>Dictionary Support: Works with provided dictionaries (e.g., smallDictionary.txt for testing, dictionary.txt for full gameplay)</li>
</ul>

------------------------------------------------------------------------------------------------------------------------

<b>Technologies Used:</b>
<br/>
Language: Java (utilizing standard library classes like TreeMap, HashMap, ArrayList, Set, and StringBuilder).
Data Structures:

Map<String, ArrayList<String>> for mapping patterns to word families.
Set<String> for the original dictionary and guessed letters.
ArrayList<String> for active word lists.

Algorithms: Pattern generation, family partitioning based on guesses, tie-breaking logic for difficulty selection, and random word picking.

------------------------------------------------------------------------------------------------------------------------

<b>How to Run:</b>
<br/>
Prerequisites: Java Development Kit (JDK) installed (version 8 or higher recommended).

Clone the Repository:
git clone https://github.com/yourusername/evil-hangman.git
cd evil-hangman

Compile the Code:
javac HangmanManager.java HangmanMain.java HangmanDifficulty.java

Run the Game:
java HangmanMain

The game defaults to smallDictionary.txt for testing. Modify HangmanMain.java to use dictionary.txt for a larger word set. Follow on-screen prompts to select word length, guesses allowed, difficulty, and play the game.

Example gameplay output (matches provided sample logs):
Debug mode shows word family breakdowns for each guess. Non-debug mode provides a clean Hangman interface.

------------------------------------------------------------------------------------------------------------------------

<b>Sample Gameplay:</b>
<br/>
Welcome to Evil Hangman!

What length word do you want to use? 5
How many wrong answers allowed? 4
What difficulty level do you want? h

Remaining guesses: 4
Guessed: 
Current: - - - - -

Your guess? e

------------------------------------------------------------------------------------------------------------------------

<b>Implementation Highlights:</b>
<br/>
Core Logic in makeGuess(char guess): Partitions active words into families based on the guess, selects the appropriate family per difficulty, updates the pattern, and handles wrong guesses.

Prep for Rounds: Resets game state for new rounds without recreating the manager object.

Edge Cases Handled: Ensures no duplicate guesses, validates word lengths, and throws exceptions for invalid states.

Scalability: Designed to easily adapt to changes in difficulty patterns (e.g., via comparators for sorting families).

This project showcases strong problem-solving skills in implementing complex algorithms with efficient data structures, making it a great addition to a technical portfolio.

------------------------------------------------------------------------------------------------------------------------

<b>Credits:</b>
<br/>
Assignment provided by Professor Mike Scott and Professor Amrita Kaur. Developed individually as per assignment guidelines.
