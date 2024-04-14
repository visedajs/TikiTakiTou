package com.example.tikitakitou
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity


class MainGame : ComponentActivity() {

    var currentMove = 1
    var moveCounter = 0
    var firstMove: Int? = 1
    var playerOne: String? = ""
    var playerTwo: String? = ""
    var firstPlayerScore = 0
    var secondPlayerScore = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_game)
        val btnOne = findViewById<TextView>(R.id.one)
        val btnTwo = findViewById<TextView>(R.id.two)
        val btnThree = findViewById<TextView>(R.id.three)
        val btnFour = findViewById<TextView>(R.id.four)
        val btnFive = findViewById<TextView>(R.id.five)
        val btnSix = findViewById<TextView>(R.id.six)
        val btnSeven = findViewById<TextView>(R.id.seven)
        val btnEight = findViewById<TextView>(R.id.eight)
        val btnNine = findViewById<TextView>(R.id.nine)

        val buttons = arrayOf(btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine)

        // nonem laukumam nost visus apzimjumus
        setupGrid(buttons)

        // padara "Play again" pogu neredzamu
        val btnPlayAgain = findViewById<Button>(R.id.btnNextRound)

        // sanem no izvelnes ekrana padoto informaciju
        val extras = intent.extras
        val gameMode = extras?.getString("gameMode")
        playerOne = extras?.getString("firstPlayer")
        playerTwo = extras?.getString("secondPlayer")
        firstMove = extras?.getInt("firstMove")

        // uzstada speletaju vardus ar punktiem
        val firstPlayerName = findViewById<TextView>(R.id.textViewFirstPlayerPoints)
        val secondPlayerName = findViewById<TextView>(R.id.textViewSecondPlayerPoints)
        val textViewWinnerText = findViewById<TextView>(R.id.textViewWinnerText)
        var initialMove = 0

        if (firstMove == 0) {
            initialMove = (1..2).random()
            currentMove = initialMove
            println(currentMove)
        }else{
            // ChatGPT
            // Q: why do i have an error for firstMove?
            // A: because firstMove is nullable, so you need to use the elvis operator (?:) to provide a default value
            currentMove = firstMove ?: 1
        }

        if (currentMove == 2){
            textViewWinnerText.text = "$playerTwo's turn"
        } else {
            textViewWinnerText.text = "$playerOne's turn"
        }

        firstPlayerName.text = playerOne+" score: "+firstPlayerScore
        secondPlayerName.text = playerTwo+" score: "+secondPlayerScore

        println("gameMode: $gameMode playerOne: $playerOne playerTwo: $playerTwo firstMove: $firstMove")



        btnPlayAgain.visibility = Button.INVISIBLE
        btnPlayAgain.setOnClickListener {
            setupGrid(buttons)
            currentMove = initialMove
            if (currentMove == 2){
                textViewWinnerText.text = "$playerTwo's turn"
            } else {
                textViewWinnerText.text = "$playerOne's turn"
            }
        }

        // "Play again" poga
        // "skatas", kuras pogas tiek nospiestas
        for (button in buttons){
            button.setOnClickListener {

                makeMove(button, buttons)
                //https://stackoverflow.com/a/44126291
                button.setClickable(false);
            }
        }

        val btnMainMenu = findViewById<Button>(R.id.btnMainMenu)
        btnMainMenu.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun setupGrid(buttons: Array<TextView>){

        for (button in buttons){
            button.text = ""
            button.setClickable(true)
        }
        val btnPlayAgain = findViewById<Button>(R.id.btnNextRound)
        btnPlayAgain.visibility = Button.INVISIBLE
        moveCounter = 0
        currentMove = firstMove ?: 1
    }

    fun checkIfGameOver(buttons: Array<TextView>) : Boolean{
        val win = false

        if (buttons[0].text == buttons[1].text && buttons[1].text == buttons[2].text && buttons[0].text != ""){
            win == true
            return true
        }
        if (buttons[3].text == buttons[4].text && buttons[4].text == buttons[5].text && buttons[3].text != ""){
            win == true
            return true
        }
        if (buttons[6].text == buttons[7].text && buttons[7].text == buttons[8].text && buttons[6].text != ""){
            win == true
            return true
        }
        if (buttons[0].text == buttons[3].text && buttons[3].text == buttons[6].text && buttons[0].text != ""){
            win == true
            return true
        }
        if (buttons[1].text == buttons[4].text && buttons[4].text == buttons[7].text && buttons[1].text != ""){
            win == true
            return true
        }
        if (buttons[2].text == buttons[5].text && buttons[5].text == buttons[8].text && buttons[2].text != ""){
            win == true
            return true
        }
        if (buttons[0].text == buttons[4].text && buttons[4].text == buttons[8].text && buttons[0].text != ""){
            win == true
            return true
        }
        if (buttons[2].text == buttons[4].text && buttons[4].text == buttons[6].text && buttons[2].text != ""){
            win == true
            return true
        }
        if(moveCounter == 9 && win == false){
            moveCounter = 10
            return true
        }
        return false
    }

    // speletajs izdara gajienu
    fun makeMove(button: TextView, buttons: Array<TextView>){
        val textViewWinnerText = findViewById<TextView>(R.id.textViewWinnerText)
        if (currentMove == 1){
            button.text = "X"
            textViewWinnerText.text = "$playerTwo's turn"
            currentMove = 2
            moveCounter++
        } else {
            button.text = "O"
            textViewWinnerText.text = "$playerOne's turn"
            currentMove = 1
            moveCounter++
        }
        if(checkIfGameOver(buttons)){
            for (button in buttons){
                button.setClickable(false)
            }
            btnPlayAgainVisible()
            val firstPlayerName = findViewById<TextView>(R.id.textViewFirstPlayerPoints)
            val secondPlayerName = findViewById<TextView>(R.id.textViewSecondPlayerPoints)


            if (moveCounter == 10){
                textViewWinnerText.text = "It's a Draw!"
            }else {
                if (currentMove == 2){
                    textViewWinnerText.text = "$playerOne wins!"
                    firstPlayerScore++
                    firstPlayerName.text = playerOne+" score: "+firstPlayerScore
                } else {
                    textViewWinnerText.text = "$playerTwo wins!"
                    secondPlayerScore++
                    secondPlayerName.text = playerTwo+" score: "+secondPlayerScore
                }
            }
        }


    }

    fun btnPlayAgainVisible(){
        val btnPlayAgain = findViewById<Button>(R.id.btnNextRound)
        btnPlayAgain.visibility = Button.VISIBLE

    }


}