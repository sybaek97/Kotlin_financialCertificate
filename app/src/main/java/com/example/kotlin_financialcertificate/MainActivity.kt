package com.example.kotlin_financialcertificate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_financialcertificate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view=this
    }

    fun opneShuffle(){
        startActivity(Intent(this,PinActivity::class.java))
    }
    fun openVerifyOTP(){
        startActivity(Intent(this,IdentityInputActivicy::class.java))
    }

}