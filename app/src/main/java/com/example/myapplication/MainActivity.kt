package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    class MainActivity : AppCompatActivity() {
        @SuppressLint("MissingInflatedId")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            val buttonNext: Button = findViewById(R.id.buttonNext)
            buttonNext.setOnClickListener {
                val intent = Intent(this, activity_create_queue::class.java)
                startActivity(intent)
            }
        }
    }

    private lateinit var usernameEditText: EditText
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usernameEditText = findViewById(R.id.usernameEditText)
        nextButton = findViewById(R.id.nextButton)

        nextButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            if (username.isNotEmpty()) {
                val intent = Intent(this, CreateQueueActivity::class.java)
                intent.putExtra("key", username)
                startActivity(intent)
            } else {
                // Показать сообщение об ошибке
                Toast.makeText(this, "Пожалуйста, введите свое имя", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

