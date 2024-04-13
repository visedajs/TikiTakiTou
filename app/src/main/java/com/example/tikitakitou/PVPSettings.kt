package com.example.tikitakitou
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity


class PVPSettings: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pvp_settings)

        val btnPVPBackPressed = findViewById<Button>(R.id.btnPVPBack)
        btnPVPBackPressed.setOnClickListener {
            val Intent1 = Intent(this, MainActivity::class.java)
            startActivity(Intent1)
        }
    }

}