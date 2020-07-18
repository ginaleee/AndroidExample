package com.example.example

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUi()
    }

    private fun setUi() {
        findViewById<Button>(R.id.address_button).setOnClickListener {
            val intent = Intent(this, AddressActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.time_button).setOnClickListener {
            val intent = Intent(this, TimeActivity::class.java)
            startActivity(intent)
        }
    }
}
