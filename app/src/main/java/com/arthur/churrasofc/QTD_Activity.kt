package com.arthur.churrasofc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QTD_Activity : AppCompatActivity() {
    private var qtdeAdultos = 0
    private var qtdeKids = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_qtd)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.pessoas)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnVoltar = findViewById<Button>(R.id.btnVoltar)
        btnVoltar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }

        val btnAdicionar1 = findViewById<Button>(R.id.btnAdicionar1)
        val btnRemover1 = findViewById<Button>(R.id.btnRemover1)
        val txtQtdePessoas1 = findViewById<TextView>(R.id.txtQtdePessoas1)
        val btnAdicionar2 = findViewById<Button>(R.id.btnAdicionar2)
        val btnRemover2 = findViewById<Button>(R.id.btnRemover2)
        val txtQtdePessoas2 = findViewById<TextView>(R.id.txtQtdePessoas2)
        val btnConfirmar = findViewById<Button>(R.id.btnConfirmar)

        btnAdicionar1.setOnClickListener {
            qtdeAdultos++
            txtQtdePessoas1.text = qtdeAdultos.toString()
        }

        btnRemover1.setOnClickListener {
            if (qtdeAdultos > 0) { // Evita que fique negativo
                qtdeAdultos--
                txtQtdePessoas1.text = qtdeAdultos.toString()
            }
        }

        btnAdicionar2.setOnClickListener {
            qtdeKids++
            txtQtdePessoas2.text = qtdeKids.toString()
        }

        btnRemover2.setOnClickListener {
            if (qtdeKids > 0) { // Evita que fique negativo
                qtdeKids--
                txtQtdePessoas2.text = qtdeKids.toString()
            }
        }

        btnConfirmar.setOnClickListener {
            if (qtdeAdultos == 0 && qtdeKids == 0) {
                Toast.makeText(this, "Adicione pelo menos uma pessoa!", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, FgmtActivity::class.java)
                intent.putExtra("qtdeAdultos", qtdeAdultos)
                intent.putExtra("qtdeKids", qtdeKids)
                startActivity(intent)
            }
        }




    }
}
