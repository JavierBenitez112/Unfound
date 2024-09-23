package com.example.unfound.Login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen1() {
    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        //Titulo Unfound encerrado en una box
        Box (){
            Text(
                text = "UNFOUND",
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.align(Alignment.Center).border(10.dp, MaterialTheme.colorScheme.onPrimary)
            )
        }
    }
}

@Composable
@Preview
fun LoginScreenPreview1() {
    LoginScreen1()
}