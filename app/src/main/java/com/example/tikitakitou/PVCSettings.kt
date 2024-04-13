package com.example.tikitakitou
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity


class PVCSettings: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pvc_settings)

        val btnPVCBackPressed = findViewById<Button>(R.id.btnPVCBack)
        btnPVCBackPressed.setOnClickListener {
            val Intent2 = Intent(this, MainActivity::class.java)
            startActivity(Intent2)
        }
    }
}