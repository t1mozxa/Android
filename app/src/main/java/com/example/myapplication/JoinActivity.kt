package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class JoinActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        usernameEditText = findViewById(R.id.usernameEditText)
        nextButton = findViewById(R.id.nextButton)

        nextButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            if (username.isNotEmpty()) {
                // Переход на следующую активность
                val intent = Intent(this, QueueActivity::class.java)
                intent.putExtra("username", username)
                startActivity(intent)
            } else {
                // Показать сообщение об ошибке
                Toast.makeText(this, "Введите свое имя", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
