package com.arthur.churrasofc

import androidx.lifecycle.ViewModel

class AlimentosViewModel: ViewModel() {

    val selecionadosCarnes = mutableSetOf<String>()
    val selecionadosGuarni = mutableSetOf<String>()
    val selecionadosBeb = mutableSetOf<String>()
    val selecionadosTemp = mutableSetOf<String>()

}