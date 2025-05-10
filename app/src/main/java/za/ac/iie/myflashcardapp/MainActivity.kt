package za.ac.iie.myflashcardapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

// MainActivity serves as the welcome screen of the app
class MainActivity : AppCompatActivity() {

    // Tag for logging
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "App launched, displaying Welcome screen.")

        // Find the Start button and set a click listener
        val startButton: Button = findViewById(R.id.button)
        startButton.setOnClickListener {
            Log.d(TAG, "Start button clicked, transitioning to QuestionActivity.")
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
        }
    }
}