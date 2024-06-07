package com.example.myapplication

import android.content.ClipData
import android.content.ClipboardManager
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix

class CreateQueueActivity : AppCompatActivity() {

    private lateinit var queueNameEditText: EditText
    private lateinit var generateLinkButton: Button
    private lateinit var shareIconImageView: ImageView
    private lateinit var qrCodeImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_queue)

        queueNameEditText = findViewById(R.id.queueNameEditText)
        generateLinkButton = findViewById(R.id.generateLinkButton)
        shareIconImageView = findViewById(R.id.shareIconImageView)
        //qrCodeImageView = findViewById(R.id.qrCodeImageView)

        generateLinkButton.setOnClickListener {
            val queueName = queueNameEditText.text.toString()
            if (queueName.isNotEmpty()) {
                val link = generateLink(queueName)
                // Обновляем ссылку и QR-код
                updateLinkAndQRCode(link)
            } else {
                Toast.makeText(this, "Введите название очереди", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun generateLink(queueName: String): String {
        // Логика генерации ссылки на основе названия очереди
        // Например, вы можете использовать URL-шаблон с названием очереди
        return "https://vochered.com/queue/$queueName"
    }

    private fun updateLinkAndQRCode(link: String) {
        // Обновляем ссылку в поле для копирования
        // ...

        // Генерируем QR-код на основе ссылки
        val qrCodeBitmap = generateQRCode(link)
        qrCodeImageView.setImageBitmap(qrCodeBitmap)

        // Обновляем иконку для копирования ссылки
        shareIconImageView.setOnClickListener {
            // Логика копирования ссылки в буфер обмена
            // Например, вы можете использовать метод setClipboardData
            copyLinkToClipboard(link)
            Toast.makeText(this, "Ссылка скопирована!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun generateQRCode(link: String): Bitmap {
        val writer = MultiFormatWriter()
        val bitMatrix: BitMatrix
        try {
            bitMatrix = writer.encode(
                link,
                BarcodeFormat.QR_CODE,
                200,
                200
            )
        } catch (e: IllegalArgumentException) {
            // Обрабатываем исключение, если ссылка слишком длинная
            return Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565)
        }

        val width = bitMatrix.width
        val height = bitMatrix.height
        val pixels = IntArray(width * height)
        for (y in 0 until height) {
            val offset = y * width
            for (x in 0 until width) {
                pixels[offset + x] = if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE
            }
        }

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return bitmap
    }

    private fun copyLinkToClipboard(link: String) {
        // Логика копирования ссылки в буфер обмена
        // Например, вы можете использовать метод setClipboardData
        val clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("link", link)
        clipboardManager.setPrimaryClip(clipData)
    }

}
