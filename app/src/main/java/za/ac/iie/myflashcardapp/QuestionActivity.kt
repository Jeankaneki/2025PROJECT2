package za.ac.iie.myflashcardapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// QuestionActivity handles the main quiz logic
class QuestionActivity : AppCompatActivity() {

    // Logging tag
    private val TAG = "QuestionActivity"

    // Parallel arrays for questions and answers
    private val questions = arrayOf(
        "Nelson Mandela was the president in 1994.",
        "The Great Wall of China is visible from space.",
        "The Eiffel Tower is in Italy.",
        "World War II ended in 1945.",
        "The Roman Empire fell in 476 AD."
    )
    private val answers = booleanArrayOf(true, false, false, true, true) // Use primitive boolean array

    private var score = 0
    private val userAnswers = BooleanArray(questions.size) // Stores user's responses

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        Log.d(TAG, "QuestionActivity started.")

        // Initialize UI components
        val questionText: TextView = findViewById(R.id.textView3)
        val trueButton: Button = findViewById(R.id.button7)
        val falseButton: Button = findViewById(R.id.button3)

        var i = 0 // Question index counter
        questionText.text = questions[i]

        // True button click handler
        trueButton.setOnClickListener {
            if (i < questions.size) {
                userAnswers[i] = true // Store user's answer
                checkAnswer(true, i)
                i++
                // Update question text using while loop as per original code
                while (i < questions.size) {
                    questionText.text = questions[i]
                    break
                }
                if (i >= questions.size) endQuiz()
            }
        }

        // False button click handler
        falseButton.setOnClickListener {
            if (i < questions.size) {
                userAnswers[i] = false // Store user's answer
                checkAnswer(false, i)
                i++
                while (i < questions.size) {
                    questionText.text = questions[i]
                    break
                }
                if (i >= questions.size) endQuiz()
            }
        }
    }

    // Validate answer against correct answer
    private fun checkAnswer(userAnswer: Boolean, questionIndex: Int) {
        if (userAnswer == answers[questionIndex]) {
            score++
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show()
        }
    }

    // Transition to ScoreActivity with results
    private fun endQuiz() {
        val intent = Intent(this, ScoreActivity::class.java).apply {
            putExtra("SCORE", score)
            putExtra("USER_ANSWERS", userAnswers) // Pass user's answers
            putExtra("CORRECT_ANSWERS", answers) // Pass correct answers
            putStringArrayListExtra("QUESTIONS", ArrayList(questions.toList())) // Pass questions
        }
        startActivity(intent)
        finish()
    }
}