package com.arthur.churrasofc

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class TempFragment : Fragment() {

    val viewModel: AlimentosViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_temp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val checkboxes = listOf(
            Triple(R.id.checkBoxSal, "Sal", viewModel.selecionadosTemp),
            Triple(R.id.checkBoxBBQ, "BBQ", viewModel.selecionadosTemp),
            Triple(R.id.checkBoxKTC, "Ketchup", viewModel.selecionadosTemp),
            Triple(R.id.checkBoxAlho, "Alho", viewModel.selecionadosTemp)
        )

        checkboxes.forEach { (id, nome, lista) ->
            val checkBox = view.findViewById<CheckBox>(id)
            checkBox.isChecked = nome in lista
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) lista.add(nome)
                else lista.remove(nome)
            }
        }

        val buttonRelatorio = view.findViewById<Button>(R.id.buttonRelatorio)
        buttonRelatorio.setOnClickListener {
            val qtdeAdultos = (activity as? FgmtActivity)?.qtdeAdultos ?: 0
            val qtdeKids = (activity as? FgmtActivity)?.qtdeKids ?: 0

            val intent = Intent(requireContext(), RelatorioActivity::class.java)
            intent.putExtra("qtdeAdultos", qtdeAdultos)
            intent.putExtra("qtdeKids", qtdeKids)
            intent.putExtra("carnes_selecionadas", viewModel.selecionadosCarnes.joinToString(", "))
            intent.putExtra("guarnições_selecionadas", viewModel.selecionadosGuarni.joinToString(", "))
            intent.putExtra("bebidas_selecionadas", viewModel.selecionadosBeb.joinToString(", "))
            intent.putExtra("temperos_selecionados", viewModel.selecionadosTemp.joinToString(", "))

            startActivity(intent)
        }
    }
}
