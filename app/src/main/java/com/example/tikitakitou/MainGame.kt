package com.example.tikitakitou
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity


class MainGame : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_game)
        // sanem no izvelnes ekrana padoto informaciju
        val extras = intent.extras
        val gameMode = extras?.getString("gameMode")
        val playerOne = extras?.getString("firstPlayer")
        val playerTwo = extras?.getString("secondPlayer")
        val firstMove = extras?.getString("firstMove")

        // uzstada speletaju vardus ar punktiem
        val firstPlayerName = findViewById<TextView>(R.id.textViewFirstPlayerPoints)
        val secondPlayerName = findViewById<TextView>(R.id.textViewSecondPlayerPoints)
        firstPlayerName.text = playerOne+" score: 0"
        secondPlayerName.text = playerTwo+" score: 0"

        println("gameMode: $gameMode playerOne: $playerOne playerTwo: $playerTwo firstMove: $firstMove")



        val btnMainMenu = findViewById<Button>(R.id.btnMainMenu)
        btnMainMenu.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val btnOne = findViewById<TextView>(R.id.one)
        btnOne.setOnClickListener {
            if (btnOne.text == "") {
                btnOne.text = "X"
            } else {
                btnOne.text = ""
            }
        }
    }


}