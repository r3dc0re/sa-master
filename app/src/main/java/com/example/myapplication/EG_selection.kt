package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_eg_selection.*

class EG_selection : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eg_selection)

        my_event_btn.setOnClickListener {
            val intent = Intent(this,my_event::class.java)
            startActivity(intent)
        }

        my_good_btn.setOnClickListener {
            val intent = Intent(this,my_good::class.java)
            startActivity(intent)
        }

        EG_back.setOnClickListener {
            val intent = Intent(this,main_page::class.java)
            finish()
        }
    }
}

