package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QueueActivity : AppCompatActivity() {
    //extView.text = userName

    private lateinit var queueTextView: TextView
    private lateinit var positionTextView: TextView
    private lateinit var connectButton: Button

    //private lateinit var adapter: QueueAdapter

    private var currentPosition = 1

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queue)

        queueTextView = findViewById(R.id.queueTextView)
        //positionTextView = findViewById(R.id.positionTextView)
        connectButton = findViewById(R.id.nextButton)

        updateQueuePosition()

        connectButton.setOnClickListener {
            currentPosition++
            updateQueuePosition()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateQueuePosition() {
       // queueTextView.text = "Очередь: $currentPosition"
        positionTextView.text = "Вы в очереди: $currentPosition"
    }
}