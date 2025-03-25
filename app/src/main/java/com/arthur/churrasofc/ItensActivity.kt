package com.arthur.churrasofc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ItensActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_itens)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.itens)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnVoltar2 = findViewById<Button>(R.id.btnVoltar2)
        btnVoltar2.setOnClickListener {
            val intent = Intent(this, QTD_Activity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }

        // Loop para associar cada CheckBox ao seu respectivo TextView
        for (i in 1..20) {
            val checkBoxId = resources.getIdentifier("CB$i", "id", packageName)
            val textViewId = resources.getIdentifier("TX$i", "id", packageName)

            val checkBox = findViewById<CheckBox>(checkBoxId)
            val textView = findViewById<TextView>(textViewId)


            for (i in 1..20) {
                val constraintId = resources.getIdentifier("caixa$i", "id", packageName)
                val checkBoxId = resources.getIdentifier("CB$i", "id", packageName)

                val constraint = findViewById<ConstraintLayout>(constraintId)
                val checkBox = findViewById<CheckBox>(checkBoxId)

                constraint?.setOnClickListener {
                    checkBox?.toggle()
                }
            }


        }
    }
}