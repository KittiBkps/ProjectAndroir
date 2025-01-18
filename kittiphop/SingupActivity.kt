package com.cs.kittiphop

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cs.kittiphop.databinding.ActivitySingupBinding

class SingupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingupBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.singupButton.setOnClickListener {
            val singupUsername = binding.singupUsername.text.toString()
            val singupPassword = binding.singupPassword.text.toString()
            singupDatabase(singupUsername, singupPassword)
        }

        binding.loginRedirect.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun singupDatabase(username: String, password: String){
        val insertedRowID = databaseHelper.insertUser(username, password)
        if (insertedRowID != -1L) {
            Toast.makeText(this, "Singup successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Singup failed", Toast.LENGTH_SHORT).show()
        }
    }


}