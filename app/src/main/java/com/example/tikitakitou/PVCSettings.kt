package com.example.tikitakitou
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.google.android.material.button.MaterialButton




class PVCSettings: ComponentActivity() {
    fun checkIfNameIsEmpty(name: String): Boolean {
        return name.isEmpty()
    }
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


        val buttonFirst = findViewById<MaterialButton>(R.id.btnPVCToggleFirst)
        val buttonSecond = findViewById<MaterialButton>(R.id.btnPVCToggleSecond)
        val buttonRandom = findViewById<MaterialButton>(R.id.btnPVCToggleRandom)
        val textViewFirstMoveChoice = findViewById<TextView>(R.id.textViewPVCFirstMoveChoice)

        // pogas, kas lauj izveleties, kuram bus pirmais gajiens
        buttonFirst.setOnClickListener {
            val one = playerName.text.toString()
            if(checkIfNameIsEmpty(one)){
                playerName.error = "Please enter your name"
                return@setOnClickListener
            }

            textViewFirstMoveChoice.setText("Chosen: $one")
        }
        buttonSecond.setOnClickListener {
            val one = playerName.text.toString()
            if(checkIfNameIsEmpty(one)){
                playerName.error = "Please enter your name"
                return@setOnClickListener
            }
            textViewFirstMoveChoice.setText("Chosen: Computer")
        }
        buttonRandom.setOnClickListener {
            val one = playerName.text.toString()
            if(checkIfNameIsEmpty(one)){
                playerName.error = "Please enter your name"
                return@setOnClickListener
            }
            textViewFirstMoveChoice.setText("Chosen: Random")
        }
        // poga, kas sak speli
        btnPVPPlayGame.setOnClickListener {

            //parbauda, vai speletaja vards ir ievadits
            val one = playerName.text.toString()
            if (one.isEmpty()) {
                playerName.error = "Please enter your name"
                return@setOnClickListener
            }

            val intent = Intent(this, MainGame::class.java)
            // https://stackoverflow.com/a/12529603
            // padod speletaju vardu uz MainGame
            intent.putExtra("firstPlayer", one)
            intent.putExtra("secondPlayer", "Computer")
            intent.putExtra("firstMove", textViewFirstMoveChoice.text.toString())
            intent.putExtra("gameMode", "PVC")
            startActivity(intent)

        }
    }


}