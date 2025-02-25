package com.example.calculadora_imc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.slider.Slider
import java.util.Locale
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    lateinit var heightTextView: TextView
    lateinit var heightSlider: Slider

    lateinit var weightTextView: TextView
    lateinit var weightAddButton: Button
    lateinit var weightMinusButton: Button

    lateinit var calculateButton: Button
    lateinit var resultTextView: TextView

    lateinit var descriptionTextView: TextView

    var height = 170.0f
    var weight = 75.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        heightTextView = findViewById(R.id.heightTextView)
        heightSlider = findViewById(R.id.heightSlider)

        weightTextView = findViewById(R.id.weightTextView)
        weightAddButton = findViewById(R.id.weightAddButton)
        weightMinusButton = findViewById(R.id.weightMinusButton)

        calculateButton = findViewById(R.id.calculateButton)
        resultTextView = findViewById(R.id.resultTextView)
        descriptionTextView = findViewById(R.id.descriptionTextView)

        heightSlider.addOnChangeListener { slider, value, fromUser ->
            height = value
            heightTextView.text = "${height.toInt()}"
        }

        weightAddButton.setOnClickListener {
            weight ++
            weightTextView.text = "${weight.toInt()}"
        }

        weightMinusButton.setOnClickListener {
            weight --
            weightTextView.text = "${weight.toInt()}"
        }

        calculateButton.setOnClickListener {
            val result = weight / (height / 100).pow(2)
            descriptionTextView.text = "Con una altura de $height cm y un peso de $weight cm \ntu masa corporal es de:"
            resultTextView.text = String.format(Locale.getDefault(), "%.2f", result)
            heightTextView.setText("")
            weightTextView.setText("")
        }
    }
}

//// calculateButton.setOnClickListener({
//            val height = heightEditText.text.toString().toFloat()
//            val weight = weightEditText.text.toString().toFloat()
//            val result = weight / (height / 100).pow(2)
//
//            resultTextView.text = "$result"
//
//            descriptionTextView.text = "Con una altura de $height cm y un peso de $weight cm \ntu masa corporal es de:"
//
//            heightEditText.setText("")
//            weightEditText.setText("")
//        })
//    }
//}
