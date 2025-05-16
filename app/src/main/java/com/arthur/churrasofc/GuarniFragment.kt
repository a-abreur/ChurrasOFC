package com.arthur.churrasofc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class GuarniFragment : Fragment() {

    val viewModel: AlimentosViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_guarni, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val checkboxes = listOf(
            Triple(R.id.checkBoxFarofa, "Farofa", viewModel.selecionadosGuarni),
            Triple(R.id.checkBoxArroz, "Arroz", viewModel.selecionadosGuarni),
            Triple(R.id.checkBoxVinagrete, "Vinagrete", viewModel.selecionadosGuarni),
            Triple(R.id.checkBoxBatata, "Batata", viewModel.selecionadosGuarni),
            Triple(R.id.checkBoxFeijao, "Feijão", viewModel.selecionadosGuarni),
            Triple(R.id.checkBoxPao, "Pão", viewModel.selecionadosGuarni)
        )

        checkboxes.forEach { (id, nome, lista) ->
            val checkBox = view.findViewById<CheckBox>(id)
            checkBox.isChecked = nome in lista
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) lista.add(nome)
                else lista.remove(nome)
            }
        }
    }
}