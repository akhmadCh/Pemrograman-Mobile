package com.example.tippycoba

import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.tippycoba.databinding.ActivityMainBinding
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding.btnCalculate.setOnClickListener {
            val costService = binding.etCost.text.toString().toDoubleOrNull() ?: 0.0

            if (costService <= 0.00) {
                Toast.makeText(this, "Masukkan Jumlah Biaya yang Valid!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val roundTip = binding.roundUpSwitch.isChecked
            val selectedRadioTipPercent = binding.tipRadioGroup.checkedRadioButtonId

            val eachTip = when (selectedRadioTipPercent) {
                R.id.tip20 -> 0.20
                R.id.tip18 -> 0.18
                else -> 0.15
            }

            viewModel.calculateTip(costService, eachTip, roundTip)
        }

        viewModel.tipAmount.observe(this) {
                tip -> binding.tvTotalAmount.text = String.format("Tip Amount: %.2f", tip)
        }

    }
}