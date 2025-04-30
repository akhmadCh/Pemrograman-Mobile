package com.example.utsmobile_biografimahasiswa

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.utsmobile_biografimahasiswa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragInfo = InfoFragment()
        val mFragmentManager = supportFragmentManager
        val fragment = mFragmentManager.findFragmentByTag(InfoFragment::class.java.simpleName)

        // validasi
        if (fragment !is InfoFragment) {
            mFragmentManager
                .beginTransaction()
                .add(R.id.main, fragInfo, InfoFragment::class.java.simpleName)
                .commit()
        }

    }
}