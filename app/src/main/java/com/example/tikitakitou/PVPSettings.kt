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
    // parbaudam, vai ievaditais vards ir tukss un atgriez boolean
    fun checkIfNameIsEmpty(name: String, player: EditText): Boolean {
        if (name.isEmpty()){
            player.error = "Please enter your name"
        }
        return name.isEmpty()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pvp_settings)

        // Poga lai tiktu atpakal uz main menu
        val btnPVPBackPressed = findViewById<Button>(R.id.btnPVPBack)
        btnPVPBackPressed.setOnClickListener {
            val Intent1 = Intent(this, MainActivity::class.java)
            startActivity(Intent1)
        }

        // inicialize mainigos laukiem un pogam
        val firstPlayerName = findViewById<EditText>(R.id.editTextPVPFirstPlayer)
        val secondPlayerName = findViewById<EditText>(R.id.editTextPVPSecondPlayer)
        val btnPVPPlayGame = findViewById<Button>(R.id.btnPVPPlayGame)
        val buttonFirst = findViewById<MaterialButton>(R.id.btnPVPToggleFirst)
        val buttonSecond = findViewById<MaterialButton>(R.id.btnPVPToggleSecond)
        val buttonRandom = findViewById<MaterialButton>(R.id.btnPVPToggleRandom)
        val textViewFirstMoveChoice = findViewById<TextView>(R.id.textViewPVPFirstMoveChoice)

        // poga, kas lauj izveleties, kuram bus pirmais gajiens
        buttonFirst.setOnClickListener {
            val one = firstPlayerName.text.toString()
            val two = secondPlayerName.text.toString()
            // parbauda, vai ir ievaditi abi speletaju vardi, ja nav, tad nevar uzspiest uz pogam
            if(checkIfNameIsEmpty(one, firstPlayerName)){
                return@setOnClickListener
            }
            if(checkIfNameIsEmpty(two, secondPlayerName)){
                return@setOnClickListener
            }
            textViewFirstMoveChoice.setText("Chosen: $one")

        }

        // poga, kas lauj izveleties, kuram bus pirmais gajiens
        buttonSecond.setOnClickListener {
            val one = firstPlayerName.text.toString()
            val two = secondPlayerName.text.toString()
            // parbauda, vai ir ievaditi abi speletaju vardi, ja nav, tad nevar uzspiest uz pogam
            if(checkIfNameIsEmpty(one, firstPlayerName)){
                return@setOnClickListener
            }
            if(checkIfNameIsEmpty(two, secondPlayerName)){
                return@setOnClickListener
            }
            textViewFirstMoveChoice.setText("Chosen: $two")
        }

        // poga, kas izvelesies pirmo gajienu nejausi ()
        buttonRandom.setOnClickListener {
            val one = firstPlayerName.text.toString()
            val two = secondPlayerName.text.toString()
            // parbauda, vai ir ievaditi abi speletaju vardi, ja nav, tad nevar uzspiest uz pogam
            if(checkIfNameIsEmpty(one, firstPlayerName)){
                return@setOnClickListener
            }
            if(checkIfNameIsEmpty(two, secondPlayerName)){
                return@setOnClickListener
            }
            textViewFirstMoveChoice.setText("Chosen: Random")
        }

        // poga, kas sak speli
        btnPVPPlayGame.setOnClickListener {
            val one = firstPlayerName.text.toString()
            val two = secondPlayerName.text.toString()

            // parbauda vai abi speletaju vardi ir ievaditi (jo no sakuma pec noklusejuma pirmais gajiens
            // tiek nejausi izvelets, tapec, ja neviena no tam pogam nav nospiesta, tad seit ari notiek parbaude)
            if(checkIfNameIsEmpty(one, firstPlayerName)){
                return@setOnClickListener
            }
            if(checkIfNameIsEmpty(two, secondPlayerName)){
                return@setOnClickListener
            }
            val intent = Intent(this, MainGame::class.java)

            // https://stackoverflow.com/a/12529603
            // padod uz MainGame speletaju vardus, pirmo gajienu, un speli, kas tiks speleta
            intent.putExtra("firstPlayer", one)
            intent.putExtra("secondPlayer", two)
            intent.putExtra("firstMove", textViewFirstMoveChoice.text.toString())
            intent.putExtra("gameMode", "PVP")
            startActivity(intent)
        }
    }
}