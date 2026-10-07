package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = DiceRepository()

     val diceState1 = MutableStateFlow(0)
    val diceState2 = MutableStateFlow(0)
    val diceState3 = MutableStateFlow(0)

    val diceStates = MutableStateFlow(Triple(0,0,0))


    val onRoll = MutableStateFlow(false)


    fun roll() {
        viewModelScope.launch {
            onRoll.value = true
            repository.generate(10).collect { value ->
                diceStates.value = value
                diceState1.value = value.first
                diceState2.value = value.second
                diceState3.value = value.third

            }
            onRoll.value = false
        }
    }
}