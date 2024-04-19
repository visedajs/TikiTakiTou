package com.example.tikitakitou
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity


class MainGame : ComponentActivity() {

    // inicialize mainigos, kuriem tiek pieskirtas sakotnejas noklusejuma vertibas, ar kuriem notiks
    // darbosanas ari arpus onCreate metodes un kuru vertibas tiks mainitas
    var currentMove = 1
    var initialMove = 0
    var moveCounter = 0
    var firstMove: Int? = 1
    var playerOne: String? = ""
    var playerTwo: String? = ""
    var firstPlayerScore = 0
    var secondPlayerScore = 0
    var gameMode: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_game)

        // inicialize mainigos, kuriem tiek pieskirtas vertibas no 9 lauciniem, kurus izvelesies
        // speletaji speles gaita
        val btnOne = findViewById<TextView>(R.id.one)
        val btnTwo = findViewById<TextView>(R.id.two)
        val btnThree = findViewById<TextView>(R.id.three)
        val btnFour = findViewById<TextView>(R.id.four)
        val btnFive = findViewById<TextView>(R.id.five)
        val btnSix = findViewById<TextView>(R.id.six)
        val btnSeven = findViewById<TextView>(R.id.seven)
        val btnEight = findViewById<TextView>(R.id.eight)
        val btnNine = findViewById<TextView>(R.id.nine)

        // ievieto speles laucinus masiva, lai nebutu jaraksta garie teksti
        val buttons = arrayOf(btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine)

        // notira speles laukumu, ja nu tur kaut kas ir palicis
        setupGrid(buttons)

        // padara "Play again" pogu neredzamu, kamer spele nav izspeleta
        val btnPlayAgain = findViewById<Button>(R.id.btnNextRound)
        btnPlayAgain.visibility = Button.INVISIBLE

        // sanem no izvelnes ekrana padoto informaciju
        val extras = intent.extras
        gameMode = extras?.getString("gameMode")
        playerOne = extras?.getString("firstPlayer")
        playerTwo = extras?.getString("secondPlayer")
        firstMove = extras?.getInt("firstMove")

        // uzstada speletaju vardus ar punktiem
        val firstPlayerName = findViewById<TextView>(R.id.textViewFirstPlayerPoints)
        val secondPlayerName = findViewById<TextView>(R.id.textViewSecondPlayerPoints)
        val textViewWinnerText = findViewById<TextView>(R.id.textViewWinnerText)

        // parbauda, kuram speletajam ir javeic pirmais gajiens
        if (firstMove == 0) {
            // https://stackoverflow.com/a/45687695
            // izvelas nejausi, kurs speletajs bus pirmais, ja tika izveleta "Random" poga
            initialMove = (1..2).random()
            currentMove = initialMove
        }else{
            // citadak tiek izvelets pirmais speletajs, kas padots no ieprieksejas aktivitates
            // izveles ekrana

            // ChatGPT
            // Q: why do i have an error for firstMove?
            // A: because firstMove is nullable, so you need to use the elvis operator (?:) to provide a default value
            currentMove = firstMove ?: 1

            // initialMove mainigais vajadzigs, lai zinatu, kurs bija pirmais, kas gaja, lai
            // nakamajas speles zinatu, kuram speletajam ir jaizdara pirmais gajiens
            initialMove = firstMove ?: 1
        }

        // parada, kuram speletajam ir jaizdara pirmais gajiens
        if (currentMove == 2){
            textViewWinnerText.text = "$playerTwo's turn"
        } else {
            textViewWinnerText.text = "$playerOne's turn"
        }

        // parada speletaju punktu skaitu sakumam
        firstPlayerName.text = playerOne+" score: "+firstPlayerScore
        secondPlayerName.text = playerTwo+" score: "+secondPlayerScore

        //println("gameMode: $gameMode, playerOne: $playerOne, playerTwo: $playerTwo, firstMove: $firstMove")

        // kad spele beidzas un speletaji grib spelet velreiz, tiek uzspiesta "Play again" poga
        // un tiek atkal notirits speles laukums un ja speles veids ir PVC, tad ari tiek parbaudits,
        // vai pirmais gajiens ir jaizdara datoram un ja ir, tad to izdara
        btnPlayAgain.setOnClickListener {
            setupGrid(buttons)
            currentMove = initialMove
            if (currentMove == 2){
                textViewWinnerText.text = "$playerTwo's turn"
                if (gameMode == "PVC"){
                    val random : Int = computerMove(buttons)
                    makeMove(buttons[random], buttons)
                }
            } else {
                textViewWinnerText.text = "$playerOne's turn"
            }
        }

        // ja speles veids ir PVC un pirmais gajiens ir jaizdara datoram un si ir pirma spele, tad
        // dators izdara gajienu pirmais
        if (gameMode == "PVC" && initialMove == 2){
            val random : Int = computerMove(buttons)
            makeMove(buttons[random], buttons)
        }

        // "skatas", kuras pogas tiek nospiestas
        for (button in buttons){
            button.setOnClickListener {

                // kad tiek nospiesta kada no pogam, tad tiek izsaukta funkcija, kas izdara gajienu
                makeMove(button, buttons)

                //https://stackoverflow.com/a/44126291
                // nospiesto pogu padara par vairs nenospiezamu, lai speletajs nevar uzspiest uz tas
                // divreiz, ta, iespejams, mainot laucina vertibu no "X" uz "O" un otradi
                button.setClickable(false);
            }
        }

        // poga, kas atgriez uz galveno izvelnes ekranu
        val btnMainMenu = findViewById<Button>(R.id.btnMainMenu)
        btnMainMenu.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    // funkcija, kas notira speles laukumu un padara visus laucinus par spejamiem
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

    // funkcija, kas parbauda, vai spele ir beigusies, kad kads no speletajiem ir uzvarejis vai ir neizskirts
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
        if(moveCounter == 9 && !win){
            moveCounter = 10
            return true
        }
        return false
    }

    // logika cilveku-speletaju gajienu veiksanai
    fun makeMove(button: TextView, buttons: Array<TextView>){
        val textViewWinnerText = findViewById<TextView>(R.id.textViewWinnerText)

        // parbauda, kura speletaja gajiens ir jaizdara, izmanina tas pogas vertibu, gajienu un cik
        // gajienu ir izdarits
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

        // parbauda, vai spele ir beigusies, ja ir, tad padara visus laucinus par nespejamiem un
        // parada uzvaras tekstu
        if(checkIfGameOver(buttons)){
            for (button in buttons){
                button.setClickable(false)
            }
            btnPlayAgainVisible()
            val firstPlayerName = findViewById<TextView>(R.id.textViewFirstPlayerPoints)
            val secondPlayerName = findViewById<TextView>(R.id.textViewSecondPlayerPoints)

            // parada uzvaras tekstu atkariba no ta, kas vinne un ari tam pieskaita punktus
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
        } else{
            // ja spele nav beigusies, tad parbauda, vai spele ir PVC un ja ir, tad dators izdara
            // savu gajienu
            if (currentMove == 2 && gameMode=="PVC"){
                val random : Int = computerMove(buttons)
                makeMove(buttons[random], buttons)
            }
        }
    }

    // funkcija, kas padara "Play again" pogu redzamu
    fun btnPlayAgainVisible(){
        val btnPlayAgain = findViewById<Button>(R.id.btnNextRound)
        btnPlayAgain.visibility = Button.VISIBLE

    }

    // funkcija, kas izdara datora gajienu, kur tas pec nejausibas izvelas, kura laucina vertibu
    // mainit un atgriez to laucina numuru
    fun computerMove(buttons: Array<TextView>) : Int{
        var random: Int
        while(true) {
            random = (0..8).random()
            if (buttons[random].text == "") {
                buttons[random].setClickable(false)
                break
            }
        }
        return random
    }
}