import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import recursos.Carta

class EstadoJuego {
    val motor = Motor()
    var cartasJugador by mutableStateOf(mutableListOf<Carta>())
    var cartasBanca by mutableStateOf(mutableListOf<Carta>())
    var mensaje by mutableStateOf("Bienvenido a Siete y Media")
    var turnoFinalizado by mutableStateOf(false)

    fun pedirCarta() {
        if (!turnoFinalizado) {
            val nuevaCarta = motor.baraja.darCartas(1)[0]
            motor.insertarCartaEnArray(motor.cartasJugador, nuevaCarta)
            cartasJugador.add(nuevaCarta)
            val valor = motor.valorCartas(motor.cartasJugador)
            mensaje = "Tu puntuación: $valor"
            if (valor > 7.5) {
                mensaje = "Te pasaste. Gana la banca."
                turnoFinalizado = true
            }
        }
    }

    fun plantarse() {
        turnoFinalizado = true
        motor.jugarbanca()
        cartasBanca = motor.cartasBanca.filterNotNull().toMutableList()
        val valorBanca = motor.valorCartas(motor.cartasBanca)
        val valorJugador = motor.valorCartas(motor.cartasJugador)
        mensaje = if (valorBanca > 7.5 || valorJugador > valorBanca) {
            "¡Ganaste!"
        } else {
            "Gana la banca"
        }
    }
}