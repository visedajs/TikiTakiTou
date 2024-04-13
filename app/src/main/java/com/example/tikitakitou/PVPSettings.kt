package com.example.tikitakitou
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import android.util.Log

class PVPSettings: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pvp_settings)

        // Poga atpakal uz main menu
        val btnPVPBackPressed = findViewById<Button>(R.id.btnPVPBack)
        btnPVPBackPressed.setOnClickListener {
            val Intent1 = Intent(this, MainActivity::class.java)
            startActivity(Intent1)
        }

        val firstPlayerName = findViewById<EditText>(R.id.editTextPVPFirstPlayer)
        val secondPlayerName = findViewById<EditText>(R.id.editTextPVPSecondPlayer)

        val btnPVPPlayGame = findViewById<Button>(R.id.btnPVPPlayGame)
        btnPVPPlayGame.setOnClickListener {
            val one = firstPlayerName.text.toString()
            val two = secondPlayerName.text.toString()
            println("first player: $one and second player: $two")
        }
    }

}