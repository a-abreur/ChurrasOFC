package com.arthur.churrasofc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class CarnesFragment : Fragment() {

      val viewModel: AlimentosViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_carnes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val checkboxes = listOf(
            Triple(R.id.checkBoxPorco, "Porco", viewModel.selecionadosCarnes),
            Triple(R.id.checkBoxVaca, "Vaca", viewModel.selecionadosCarnes),
            Triple(R.id.checkBoxLinguica, "Linguiça", viewModel.selecionadosCarnes),
            Triple(R.id.checkBoxCoxa, "Coxa", viewModel.selecionadosCarnes)
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
