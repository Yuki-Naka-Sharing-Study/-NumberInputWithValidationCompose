package com.example.numberinputwithvalidation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.numberinputwithvalidation.ui.theme.NumberInputWithValidationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NumberInputWithValidationTheme {
                NumberInputWithValidation()
            }
        }
    }
}

@Composable
fun NumberInputWithValidation() {
    var input by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = input,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) { // 数字のみ許可
                    input = newValue
                    val number = newValue.toIntOrNull()
                    if (number != null && number >= 850) {
                        errorMessage = "851以上は入力できません。"
                    } else {
                        errorMessage = ""
                    }
                }
            },
            label = { Text("数値を入力してください") }
        )
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = androidx.compose.ui.graphics.Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNumberInputWithValidation() {
    NumberInputWithValidation()
}