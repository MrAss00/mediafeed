package com.example.mediafeed

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.tvStudent).text =
            "Student Name: ${getString(R.string.student_name)}"

        // ===================================================================
        // TODO 7: Implement button click navigation
        // Hint:
        //   - Image View button: use Intent(this, ImageFeedActivity::class.java)
        //     then call startActivity(intent)
        //   - Video View button: similar, navigate to VideoActivity
        // ===================================================================
        findViewById<Button>(R.id.btnImage).setOnClickListener {
            val intent = Intent(this, ImageFeedActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.btnVideo).setOnClickListener {
            val intent = Intent(this, VideoActivity::class.java)
            startActivity(intent)
        }
    }
}