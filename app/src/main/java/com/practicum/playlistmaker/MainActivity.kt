package com.practicum.playlistmaker

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val search = findViewById<Button>(R.id.search)
        search.setOnClickListener {
            Toast.makeText(this@MainActivity, "Нажали на ПОИСК!", Toast.LENGTH_SHORT).show()
        }

        val library = findViewById<Button>(R.id.library)
        val buttonClickListener: View.OnClickListener = object : View.OnClickListener {
            override fun onClick(v: View?) {
                Toast.makeText(this@MainActivity, "Нажали на МЕДИАТЕКА!", Toast.LENGTH_SHORT).show()
            }
        }
        library.setOnClickListener(buttonClickListener)

        val settings = findViewById<Button>(R.id.settings)
        settings.setOnClickListener {
            Toast.makeText(this@MainActivity, "Нажали на НАСТРОЙКИ!", Toast.LENGTH_SHORT).show()
        }
    }
}