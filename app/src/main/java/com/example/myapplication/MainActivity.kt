package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InscriptionScreen()
        }
    }
}


fun getImage(value: Int)  =
    when (value) {
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.dice_1
    }

@Composable
fun InscriptionScreen(
    viewModel: MainViewModel = viewModel()
) {
    val diceState1 by viewModel.diceState1.collectAsStateWithLifecycle()
    val diceState2 by viewModel.diceState2.collectAsStateWithLifecycle()
    val diceState3 by viewModel.diceState3.collectAsStateWithLifecycle()
    val onRoll by viewModel.onRoll.collectAsStateWithLifecycle()
    val diceStates by viewModel.diceStates.collectAsStateWithLifecycle()

    val total = diceStates.toList().sum()





    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Générez votre numéro de passage",
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 40.dp)
        )

        Row {

            diceStates.toList().forEach {
                Image(
                    painter = painterResource(id = getImage(it)),
                    contentDescription = "Dé",
                    modifier = Modifier.size(100.dp)
                )
            }

        }

        Box(
            modifier = Modifier.height(60.dp),
            contentAlignment = Alignment.Center
        ) {
            if (onRoll) {
                CircularProgressIndicator()
            } else if (total > 0) {
                Text(text = total.toString(), fontSize = 36.sp)
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            Button(
                onClick = { viewModel.roll() },
                modifier = Modifier.alpha(if (onRoll) 0f else 1f)
            ) {
                Text(text = if (total == 0) "Roll" else "Reroll")
            }

            OutlinedButton(onClick = { /* Action Retour */ }) {
                Text("Retour")
            }
        }
    }
}