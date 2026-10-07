package com.example.myapplication

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.milliseconds

class DiceRepository {
    fun generate(n: Int): Flow<Triple<Int,Int,Int>> = flow {
        repeat(n) {
            emit(Triple((1..6).random(),(1..6).random(),(1..6).random()))
            delay(200.milliseconds)
        }
    }
}