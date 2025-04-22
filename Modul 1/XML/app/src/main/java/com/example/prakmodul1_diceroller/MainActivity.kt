package com.example.prakmodul1_diceroller

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.prakmodul1_diceroller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.number1.observe(this) {
            number -> val drawableResource =
                when (number) {
                    0 -> R.drawable.dice_0
                    1 -> R.drawable.dice_1
                    2 -> R.drawable.dice_2
                    3 -> R.drawable.dice_3
                    4 -> R.drawable.dice_4
                    5 -> R.drawable.dice_5
                    6 -> R.drawable.dice_6
                    else -> throw Exception("number must be between 1 and 6")
            }
            binding.diceImage1.setImageResource(drawableResource)
        }
        viewModel.number2.observe(this) {
                number -> val drawableResource =
                when (number) {
                    0 -> R.drawable.dice_0
                    1 -> R.drawable.dice_1
                    2 -> R.drawable.dice_2
                    3 -> R.drawable.dice_3
                    4 -> R.drawable.dice_4
                    5 -> R.drawable.dice_5
                    6 -> R.drawable.dice_6
                    else -> throw Exception("number must be between 1 and 6")
            }
            binding.diceImage2.setImageResource(drawableResource)
        }

        binding.btnRoll.setOnClickListener {
            viewModel.rollDice()
            Toast.makeText(this, "${viewModel.text}", Toast.LENGTH_SHORT).show()
        }

        binding.btnReset.setOnClickListener {
            binding.diceImage1.setImageResource(R.drawable.dice_0)
            binding.diceImage2.setImageResource(R.drawable.dice_0)
        }

    }
}