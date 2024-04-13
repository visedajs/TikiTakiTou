package com.example.tikitakitou

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tikitakitou.ui.theme.TikiTakiTouTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //https://www.youtube.com/watch?v=2gljhNFKimk Saprasts, ka mainit skatus
        val btnPVP = findViewById<Button>(R.id.btnPVP)
        btnPVP.setOnClickListener {
            val intent1 = Intent(this, PVPSettings::class.java)
            startActivity(intent1)
        }

        val btnPVC= findViewById<Button>(R.id.btnPVC)
        btnPVC.setOnClickListener {
            val intent2 = Intent(this, PVCSettings::class.java)
            startActivity(intent2)
        }
    }

}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "caw $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TikiTakiTouTheme {
        Greeting("Android")
    }
}