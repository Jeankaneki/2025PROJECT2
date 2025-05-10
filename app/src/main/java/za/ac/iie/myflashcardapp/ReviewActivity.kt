package za.ac.iie.myflashcardapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// ReviewActivity displays all questions along with their correct answers
class ReviewActivity : AppCompatActivity() {

    // Logging tag
    private val TAG = "ReviewActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        Log.d(TAG, "ReviewActivity started.")

        // Initialize UI components
        val reviewText: TextView = findViewById(R.id.textView7)
        val backButton: Button = findViewById(R.id.button7)

        // Retrieve data from ScoreActivity
        val questions = intent.getStringArrayListExtra("QUESTIONS") ?: arrayListOf()
        val userAnswers = intent.getBooleanArrayExtra("USER_ANSWERS") ?: booleanArrayOf()
        val correctAnswers = intent.getBooleanArrayExtra("CORRECT_ANSWERS") ?: booleanArrayOf()

        // Build review content
        val reviewContent = buildString {
            questions.forEachIndexed { index, question ->
                append("Q${index + 1}: $question\n")
                // Safely display user's answer
                append("Your answer: ${userAnswers.getOrNull(index)?.let { if(it) "True" else "False" } ?: "N/A"}\n")
                // Safely display correct answer
                append("Correct answer: ${correctAnswers.getOrNull(index)?.let { if(it) "True" else "False" } ?: "N/A"}\n\n")
            }
        }

        reviewText.text = reviewContent

        // Back button click handler
        backButton.setOnClickListener {
            finish() // Return to previous activity
        }
    }
}