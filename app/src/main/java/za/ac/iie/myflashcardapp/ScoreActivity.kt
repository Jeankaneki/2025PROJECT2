package za.ac.iie.myflashcardapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {

    // Logging tag
    private val TAG = "ScoreActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        Log.d(TAG, "ScoreActivity started.")

        // Initialize UI components
        val scoreText: TextView = findViewById(R.id.textView7)
        val reviewButton: Button = findViewById(R.id.button5)
        val exitButton: Button = findViewById(R.id.button6)
        val feedbackText: TextView = findViewById(R.id.textView6)

        // Retrieve data from QuestionActivity
        val score = intent.getIntExtra("SCORE", 0)
        scoreText.text = "Your Score: $score"

        // Display feedback based on score
        feedbackText.text = if (score >= 3) "Great job! Keep it up!" else "Keep practicing!"

        // Review button click handler
        reviewButton.setOnClickListener {
            Intent(this, ReviewActivity::class.java).apply {
                // Forward all data to ReviewActivity
                putExtra("USER_ANSWERS", intent.getBooleanArrayExtra("USER_ANSWERS"))
                putExtra("CORRECT_ANSWERS", intent.getBooleanArrayExtra("CORRECT_ANSWERS"))
                putStringArrayListExtra("QUESTIONS", intent.getStringArrayListExtra("QUESTIONS"))
                startActivity(this)
            }
        }

        // Exit button click handler
        exitButton.setOnClickListener {
            finishAffinity() // Close the app
        }
    }
}