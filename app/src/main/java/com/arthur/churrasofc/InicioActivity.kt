package com.arthur.churrasofc

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InicioActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inicio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnTradicional = findViewById<Button>(R.id.btnTradicional)
        btnTradicional.setOnClickListener {
            val intent = Intent(this, QTD_Activity::class.java)
            startActivity(intent)
        }

        val btnPersonalizado = findViewById<Button>(R.id.btnPersonalizado)
        btnPersonalizado.setOnClickListener {
            val intent = Intent(this, QTD_Activity::class.java)
            startActivity(intent)
        }

        val tvNome = findViewById<TextView>(R.id.tvNome)
        val nome = intent.getStringExtra("NOME")
        tvNome.text = "$nome"
    }
}