package com.arthur.churrasofc

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RelatorioActivity : AppCompatActivity() {

    // Quantidades padrão por pessoa (em gramas ou ml)
    private val alimentoSolidoAdulto = 600.0  // g
    private val alimentoSolidoCrianca = 400.0 // g
    private val bebidaAdulto = 600.0           // ml
    private val bebidaCrianca = 400.0          // ml
    private val cervejaAdulto = 600.0          // ml (somente adulto)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_relatorio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recebendo quantidades de pessoas
        val qtdAdultos = intent.getIntExtra("qtdeAdultos", 0)
        val qtdCriancas = intent.getIntExtra("qtdeKids", 0)

        // Recebe os dados das strings e converte para listas (espera-se que estejam separadas por vírgula)
        val carnes = intent.getStringExtra("carnes_selecionadas")?.split(",")?.map { it.trim() } ?: emptyList()
        val guarnicoes = intent.getStringExtra("guarnições_selecionadas")?.split(",")?.map { it.trim() } ?: emptyList()
        val bebidas = intent.getStringExtra("bebidas_selecionadas")?.split(",")?.map { it.trim() } ?: emptyList()
        val temperos = intent.getStringExtra("temperos_selecionados")?.split(",")?.map { it.trim() } ?: emptyList()

        // Calcula as quantidades necessárias para carnes e guarnições
        val carnesQuant = calcularCarnes(carnes, qtdAdultos, qtdCriancas)
        val guarnicoesQuant = calcularGuarnicoes(guarnicoes, qtdAdultos, qtdCriancas)
        val bebidasQuant = calcularBebidas(bebidas, qtdAdultos, qtdCriancas)
        val temperosQuant = calcularTemperos(temperos, qtdAdultos, qtdCriancas)

        // Monta o relatório formatado
        val relatorioCompleto = buildString {
            append("RELATÓRIO DO CHURRASCO\n\n")
            append("Quantidade de adultos: $qtdAdultos\n")
            append("Quantidade de crianças: $qtdCriancas\n\n")

            append("CARNES:\n")
            if (carnesQuant.isEmpty()) append("Nenhuma carne selecionada\n") else
                carnesQuant.forEach { (item, quant) ->
                    val texto = if (quant >= 1000)
                        "${"%.1f".format(quant / 1000)} kg"
                    else
                        "${"%.0f".format(quant)} g"
                    append("- $item: $texto\n")
                }
            append("\n")

            append("GUARNIÇÕES:\n")
            if (guarnicoesQuant.isEmpty()) append("Nenhuma guarnição selecionada\n") else
                guarnicoesQuant.forEach { (item, quant) ->
                    val texto = if (quant >= 1000)
                        "${"%.1f".format(quant / 1000)} kg"
                    else
                        "${"%.0f".format(quant)} g"
                    append("- $item: $texto\n")
                }
            append("\n")

            append("BEBIDAS:\n")
            if (bebidasQuant.isEmpty()) append("Nenhuma bebida selecionada\n") else
                bebidasQuant.forEach { (item, quant) ->
                    val texto = if (quant >= 1000)
                        "${"%.1f".format(quant / 1000)} L"
                    else
                        "${"%.0f".format(quant)} ml"
                    append("- $item: $texto\n")
                }
            append("\n")

            append("TEMPEROS E CONDIMENTOS:\n")
            if (temperosQuant.isEmpty()) append("Nenhum tempero selecionado\n") else
                temperosQuant.forEach { (item, quant) ->
                    append("- $item: $quant\n")
                }
        }



        // Exibe no TextView
        val textViewRelatorio = findViewById<TextView>(R.id.textViewRelatorio)
        textViewRelatorio.text = relatorioCompleto
    }

    private fun calcularCarnes(carnes: List<String>, qtdAdultos: Int, qtdCriancas: Int): Map<String, Double> {
        if (carnes.isEmpty()) return emptyMap()
        // Total de alimento sólido por pessoa
        val totalAlimentoSolido = (alimentoSolidoAdulto * qtdAdultos) + (alimentoSolidoCrianca * qtdCriancas)
        // Carnes representam 70% desse total
        val totalCarnes = totalAlimentoSolido * 0.7
        // Dividir igualmente entre os itens selecionados
        val qtdPorCarne = totalCarnes / carnes.size
        return carnes.associateWith { qtdPorCarne }
    }

    private fun calcularGuarnicoes(guarnicoes: List<String>, qtdAdultos: Int, qtdCriancas: Int): Map<String, Double> {
        if (guarnicoes.isEmpty()) return emptyMap()
        val totalAlimentoSolido = (alimentoSolidoAdulto * qtdAdultos) + (alimentoSolidoCrianca * qtdCriancas)
        val totalGuarnicoes = totalAlimentoSolido * 0.3
        val qtdPorGuarnicao = totalGuarnicoes / guarnicoes.size
        return guarnicoes.associateWith { qtdPorGuarnicao }
    }

    private fun calcularBebidas(bebidas: List<String>, qtdAdultos: Int, qtdCriancas: Int): Map<String, Double> {
        if (bebidas.isEmpty()) return emptyMap()

        val resultado = mutableMapOf<String, Double>()
        val qtdBebidaAdultos = bebidaAdulto * qtdAdultos
        val qtdBebidaCriancas = bebidaCrianca * qtdCriancas

        // Separar bebidas alcoólicas e não alcoólicas
        val naoAlcoolicas = bebidas.filter { it.lowercase() != "cerveja" }
        val qtdNaoAlcoolicas = naoAlcoolicas.size

        bebidas.forEach { bebida ->
            when (bebida.lowercase()) {
                "cerveja" -> {
                    resultado[bebida] = cervejaAdulto * qtdAdultos // só adultos
                }
                "refrigerante", "suco" -> {
                    val total = qtdBebidaAdultos + qtdBebidaCriancas
                    resultado[bebida] = total / qtdNaoAlcoolicas
                }
                else -> {
                    val total = qtdBebidaAdultos + qtdBebidaCriancas
                    resultado[bebida] = total / qtdNaoAlcoolicas
                }
            }
        }

        return resultado
    }


    private fun calcularTemperos(temperos: List<String>, qtdAdultos: Int, qtdCriancas: Int): Map<String, String> {
        if (temperos.isEmpty()) return emptyMap()
        val totalPessoas = qtdAdultos + qtdCriancas
        val resultado = mutableMapOf<String, String>()

        temperos.forEach { tempero ->
            when (tempero.lowercase()) {
                "sal" -> resultado[tempero] = "${totalPessoas * 10} g"       // 10g por pessoa
                "alho" -> resultado[tempero] = "${(totalPessoas / 2).coerceAtLeast(1)} dentes" // 1 dente a cada 2 pessoas, mínimo 1
                "bbq" -> resultado[tempero] = "${totalPessoas * 15} ml"  // 15ml por pessoa
                "ketchup" -> resultado[tempero] = "${totalPessoas * 10} ml"         // 10ml por pessoa
                else -> resultado[tempero] = "Quantidade padrão"
            }
        }

        return resultado
    }
}
