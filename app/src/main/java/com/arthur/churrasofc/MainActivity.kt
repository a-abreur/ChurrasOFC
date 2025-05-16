package com.arthur.churrasofc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            val intent = Intent(this, InicioActivity::class.java)
            startActivity(intent)
        }

        val etNome = findViewById<TextInputEditText>(R.id.etNome)

        btnLogin.setOnClickListener{
            val nome = etNome.text.toString()
            val intent = Intent(this, InicioActivity::class.java)
            intent.putExtra("NOME", nome.uppercase())
            startActivity(intent)

            val textoDigitado = etNome.text.toString()
            val senhaCorreta = "lista123"
            if (textoDigitado == senhaCorreta) {
                // Abre a nova Activity
                val intent = Intent(this, ItensActivity::class.java)
                startActivity(intent)}

    }



    }
}