package com.arthur.churrasofc

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RelatorioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_relatorio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recebe os dados da Intent
        val carnes = intent.getStringExtra("carnes_selecionadas") ?: "Nenhuma carne selecionada"
        val bebidas = intent.getStringExtra("bebidas_selecionadas") ?: "Nenhuma bebida selecionada"
        val temperos = intent.getStringExtra("temperos_selecionados") ?: "Nenhum tempero selecionado"
        val guarnições = intent.getStringExtra("guarnições_selecionadas") ?: "Nenhum tempero selecionado"


        // Monta o relatório completo
        val relatorioCompleto = """
            CARNES SELECIONADAS:
            $carnes
            
            GUARNIÇÕES SELECIONADAS:
            $guarnições
            
            BEBIDAS SELECIONADAS:
            $bebidas
            
            TEMPEROS SELECIONADOS:
            $temperos
        """.trimIndent()

        // Exibe no TextView
        val textViewRelatorio = findViewById<TextView>(R.id.textViewRelatorio)
        textViewRelatorio.text = relatorioCompleto
    }
}