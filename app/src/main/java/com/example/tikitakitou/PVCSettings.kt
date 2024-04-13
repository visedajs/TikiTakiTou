package com.example.tikitakitou
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity


class PVCSettings: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pvc_settings)

        // Poga atpakal uz main menu
        val btnPVCBackPressed = findViewById<Button>(R.id.btnPVCBack)
        btnPVCBackPressed.setOnClickListener {
            val Intent2 = Intent(this, MainActivity::class.java)
            startActivity(Intent2)
        }

        val playerName = findViewById<EditText>(R.id.editTextPVCName)

        val btnPVPPlayGame = findViewById<Button>(R.id.btnPVCPlayGame)
        btnPVPPlayGame.setOnClickListener {
            val one = playerName.text.toString()
            println("first player: $one")
        }
    }
}