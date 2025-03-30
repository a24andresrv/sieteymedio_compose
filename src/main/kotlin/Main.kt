import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.Alignment


@Composable
@Preview
fun Juego() {
    val estadoJuego = remember { EstadoJuego() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = estadoJuego.mensaje)
        Row {
            Row {
                Button(onClick = { estadoJuego.pedirCarta() }) {
                    Text("Pedir Carta")
                }

                Button(onClick = { estadoJuego.plantarse() }) {
                    Text("Plantarse")
                }
            }

        }
        Text(text = "Cartas Jugador: ${estadoJuego.cartasJugador.joinToString { it.toString() }}")
        Text(text = "Cartas Banca: ${if (estadoJuego.turnoFinalizado) estadoJuego.cartasBanca.joinToString { it.toString() } else "Aún no terminó su turno"}")
    }

}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        MaterialTheme {
            Juego()
        }
    }
}
