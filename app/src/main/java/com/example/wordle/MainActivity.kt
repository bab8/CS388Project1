package com.example.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.wordle.FourLetterWordList.getRandomFourLetterWord

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val guessButton = findViewById<Button>(R.id.GuessButton)
        val guess1 = findViewById<TextView>(R.id.Guess1input)
        val guess2 = findViewById<TextView>(R.id.Guess2input)
        val guess3 = findViewById<TextView>(R.id.Guess3input)
        val guess1Check = findViewById<TextView>(R.id.Guess1Correctness)
        val guess2Check = findViewById<TextView>(R.id.Guess2Correctness)
        val guess3Check = findViewById<TextView>(R.id.Guess3Correctness)
        val correctWord = findViewById<TextView>(R.id.CorrectWord)
        val resetButton = findViewById<Button>(R.id.ResetButton)
        val guessText = findViewById<EditText>(R.id.InputText)
        var counter: Int = 2
        var word : String = getRandomFourLetterWord()

        guessButton.setOnClickListener {
            if(guessText.text.toString().length != 4){
                Toast.makeText(this, "Invalid, Enter a 4 Letter Word", Toast.LENGTH_SHORT).show()
                counter++
            }
            if(checkGuess(guessText.text.toString().uppercase(), word) == "OOOO"){
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
                resetButton.visibility = View.VISIBLE
                guessButton.isEnabled = false
                correctWord.text = word
                correctWord.visibility = View.VISIBLE
            }
            if (counter == 2) {
                guess1.text = guessText.text
                guess1.visibility = View.VISIBLE
                guess1Check.text = checkGuess(guess1.text.toString().uppercase(), word)
                guess1Check.visibility = View.VISIBLE
            }
            else if (counter == 1) {
                guess2.text = guessText.text
                guess2.visibility = View.VISIBLE
                guess2Check.text = checkGuess(guess2.text.toString().uppercase(), word)
                guess2Check.visibility = View.VISIBLE
            }
            else if (counter == 0) {
                guess3.text = guessText.text
                guess3.visibility = View.VISIBLE
                guess3Check.text = checkGuess(guess3.text.toString().uppercase(), word)
                guess3Check.visibility = View.VISIBLE
                resetButton.visibility = View.VISIBLE
                Toast.makeText(this, "Exceeded Number of Guesses", Toast.LENGTH_SHORT).show()
                guessButton.isEnabled = false
                correctWord.text = word
                correctWord.visibility = View.VISIBLE

            }
            guessText.text.clear()
            counter--;
        }

        resetButton.setOnClickListener {
            correctWord.visibility = View.INVISIBLE
            counter = 2
            word = getRandomFourLetterWord()
            correctWord.text = word
            guess1.visibility = View.INVISIBLE
            guess2.visibility = View.INVISIBLE
            guess3.visibility = View.INVISIBLE
            guess1Check.visibility = View.INVISIBLE
            guess2Check.visibility = View.INVISIBLE
            guess3Check.visibility = View.INVISIBLE
            guessButton.isEnabled = true
            resetButton.visibility = View.INVISIBLE
        }
    }
}



/**
 * Parameters / Fields:
 *   wordToGuess : String - the target word the user is trying to guess
 *   guess : String - what the user entered as their guess
 *
 * Returns a String of 'O', '+', and 'X', where:
 *   'O' represents the right letter in the right place
 *   '+' represents the right letter in the wrong place
 *   'X' represents a letter not in the target word
 */
private fun checkGuess(guess: String, wordToGuess: String) : String {
    var result = ""
    for (i in 0..3) {
        result += if (guess[i] == wordToGuess[i]) {
            "O"
        } else if (guess[i] in wordToGuess) {
            "+"
        } else {
            "X"
        }
    }
    return result
}
