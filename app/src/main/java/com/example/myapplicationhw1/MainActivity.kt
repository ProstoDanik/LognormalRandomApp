package com.example.myapplicationhw1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.exp
import kotlin.math.sqrt
import java.util.*

class MainActivity : AppCompatActivity() {
    private var savedNumber: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val label = findViewById<TextView>(R.id.random_number_result)
        val userData1: EditText = findViewById(R.id.mean_val)
        val userData2: EditText = findViewById(R.id.variance_value)
        val button: Button = findViewById(R.id.get_random_num)

        savedInstanceState?.let {
            savedNumber = it.getString("saved_number")
            label.text = savedNumber ?: "Результат будет здесь"
        }

        button.setOnClickListener {
            val mu = userData1.text.toString().toDoubleOrNull() ?: 0.0
            val sigmasquare = userData2.text.toString().toDoubleOrNull() ?: 1.0
            val sigma = sqrt(sigmasquare)

            val random = Random()
            val normalRandom = mu + sigma * random.nextGaussian()
            val lognormalRandom = exp(normalRandom)

            savedNumber = lognormalRandom.toString()
            label.text = savedNumber
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("saved_number", savedNumber)
    }
}