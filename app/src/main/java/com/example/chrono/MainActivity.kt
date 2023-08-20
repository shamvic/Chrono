package com.example.chrono
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var totalSeconds = 0
    private var running = false
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.start_button)
        val stopButton = findViewById<Button>(R.id.stop_button)
        val resetButton = findViewById<Button>(R.id.reset_button)
        val timerTextView = findViewById<TextView>(R.id.timer_text)

        handler = Handler()

        startButton.setOnClickListener {
            running = true
            handler.postDelayed(timerRunnable, 1000)
        }

        stopButton.setOnClickListener {
            running = false
            handler.removeCallbacks(timerRunnable)
        }

        resetButton.setOnClickListener {
            running = false
            totalSeconds = 0
            updateTimerText(timerTextView)
        }
    }

    private val timerRunnable = object : Runnable {
        override fun run() {
            val timerTextView = findViewById<TextView>(R.id.timer_text)
            totalSeconds++
            updateTimerText(timerTextView)
            handler.postDelayed(this, 1000)
        }
    }

    private fun updateTimerText(timerTextView: TextView) {
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        val formattedTime = String.format("%02d:%02d", minutes, seconds)
        timerTextView.text = formattedTime
    }
}
