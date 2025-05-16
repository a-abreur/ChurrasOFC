package com.arthur.churrasofc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class BebFragment : Fragment() {

    val viewModel: AlimentosViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beb, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val checkboxes = listOf(
            Triple(R.id.checkBoxRefri, "Refrigerante", viewModel.selecionadosBeb),
            Triple(R.id.checkBoxCerveja, "Cerveja", viewModel.selecionadosBeb),
            Triple(R.id.checkBoxSuco, "Suco", viewModel.selecionadosBeb)
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