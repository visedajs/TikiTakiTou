package com.example.tikitakitou
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import android.util.Log
import android.widget.TextView
import com.google.android.material.button.MaterialButton

class PVPSettings: ComponentActivity() {
    fun checkIfNameIsEmpty(name: String): Boolean {
        return name.isEmpty()
    }
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

            if(checkIfNameIsEmpty(one)){
                firstPlayerName.error = "Please enter your name"
                return@setOnClickListener
            }
            if(checkIfNameIsEmpty(two)){
                secondPlayerName.error = "Please enter your name"
                return@setOnClickListener
            }
            println("first player: $one and second player: $two")
            val intent = Intent(this, MainGame::class.java)
            startActivity(intent)
        }
        val buttonFirst = findViewById<MaterialButton>(R.id.btnPVPToggleFirst)
        val buttonSecond = findViewById<MaterialButton>(R.id.btnPVPToggleSecond)
        val buttonRandom = findViewById<MaterialButton>(R.id.btnPVPToggleRandom)
        val textViewFirstMoveChoice = findViewById<TextView>(R.id.textViewPVPFirstMoveChoice)

        buttonFirst.setOnClickListener {


            val one = firstPlayerName.text.toString()
            val two = secondPlayerName.text.toString()

            if(checkIfNameIsEmpty(one)){
                firstPlayerName.error = "Please enter your name"
                return@setOnClickListener
            }
            if(checkIfNameIsEmpty(two)){
                secondPlayerName.error = "Please enter your name"
                return@setOnClickListener
            }
            textViewFirstMoveChoice.setText("Chosen: $one")
        }
        buttonSecond.setOnClickListener {
            val one = firstPlayerName.text.toString()
            val two = secondPlayerName.text.toString()
            if(checkIfNameIsEmpty(one)){
                firstPlayerName.error = "Please enter your name"
                return@setOnClickListener
            }
            if(checkIfNameIsEmpty(two)){
                secondPlayerName.error = "Please enter your name"
                return@setOnClickListener
            }
            textViewFirstMoveChoice.setText("Chosen: $two")
        }
        buttonRandom.setOnClickListener {
            val one = firstPlayerName.text.toString()
            val two = secondPlayerName.text.toString()
            if(checkIfNameIsEmpty(one)){
                firstPlayerName.error = "Please enter your name"
                return@setOnClickListener
            }
            if(checkIfNameIsEmpty(two)){
                secondPlayerName.error = "Please enter your name"
                return@setOnClickListener
            }
            textViewFirstMoveChoice.setText("Chosen: Random")
        }


    }

}