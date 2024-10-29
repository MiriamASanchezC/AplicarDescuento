package com.example.examendescuento

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.examendescuento.ui.theme.ExamenDescuentoTheme

val LightPink = Color(0xFFFFC0CB)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExamenDescuentoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorScheme.background
                ) {
                    AñosPerrunos()
                }
            }
        }
    }
}

@Composable
fun AñosPerrunos() {
    PosicionPantalla(titulo = "Calculadora Descuento")
}

@Composable
private fun PosicionPantalla(titulo: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        var precio by remember { mutableStateOf("") }
        var descuento by remember { mutableStateOf("") }
        var resultadoDescuento by remember { mutableStateOf("") }
        var resultadoTotal by remember { mutableStateOf("") }

        Text(
            text = titulo,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive
        )

        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text("Precio") }
        )

        OutlinedTextField(
            value = descuento,
            onValueChange = { descuento = it },
            label = { Text("%Descuento") }
        )

        ElevatedButton(
            onClick = {
                val precioDouble = precio.toDoubleOrNull() ?: 0.0
                val descuentoInt = descuento.toIntOrNull() ?: 0
                val res = (precioDouble * descuentoInt) / 100
                resultadoDescuento = res.toString()
                val res2 = precioDouble - res
                resultadoTotal = res2.toString()
            },
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = LightPink,
                contentColor = Color.Black
            )
        ) {
            Text("Calcular")
        }

        ElevatedButton(onClick = {
            resultadoDescuento = ""
            precio = ""
            descuento = ""
            resultadoTotal = ""
        }, colors = ButtonDefaults.elevatedButtonColors(
            containerColor = LightPink,
            contentColor = Color.Black
        )) {
            Text(text = "Borrar")
        }

        OutlinedTextField(
            value = resultadoDescuento,
            readOnly = true,
            onValueChange = {},
            label = { Text("Descuento") }
        )
        OutlinedTextField(
            value = resultadoTotal,
            readOnly = true,
            onValueChange = {},
            label = { Text("Total") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExamenDescuentoTheme {
        AñosPerrunos()
    }
}
