package AO2_MARINIO_CRISTIAN.juego;

import java.time.Instant;

import AO2_MARINIO_CRISTIAN.inputOuput.Ingreso;
import AO2_MARINIO_CRISTIAN.inputOuput.Salida;

public class Juego {
    private Jugador[] jugadores;
    private int[] numerosAAdivinar;

    public Juego(int cantidadJugadores) {
        this.jugadores = obtenerArrayDeJugadores(cantidadJugadores);
        this.numerosAAdivinar = new int[cantidadJugadores];
        cargarJugador();
        iniciarJuego();
        mostrarResultados();
    }

    private Jugador[] obtenerArrayDeJugadores(int cantidadJugadores) {
        Jugador[] jugadores = new Jugador[cantidadJugadores];
        for (int i = 0; i < cantidadJugadores; i++) {
            Jugador jugador = new Jugador();
            jugadores[i] = jugador;
        }
        return jugadores;
    }

    private void mostrarResultados() {
        for (int i = 0; i < jugadores.length; i++) {
            System.out.println("El jugador "+jugadores[i].getNombre() + " ha tardado " +
            +jugadores[i].getTiempoJugado() + " segundos.");
        }
    }

    private void iniciarJuego() {
        int cantRondas = 0;
        while (this.jugadoresEnJuego() != 0) {
            realizarRonda(cantRondas);
            cantRondas++;
        }
    }

    private void realizarRonda(int cantidadRondas) {
        for (int i = 0; i < jugadores.length; i++) {
            if (!jugadores[i].yaAdivino()) {
                realizarIntento(i, cantidadRondas);
            }
        }
    }

    private void realizarIntento(int indJugador, int ronda) {
        if(ronda == 0){
            Instant tiempoIniciado = Instant.now();
            jugadores[indJugador].setTiempoInicio(tiempoIniciado);
            System.out.println(jugadores[indJugador].getTiempoInicio());
        }
        int numeroIngresado = Ingreso.datoEntero("Ingrese numero a adivinar",
                jugadores[indJugador].getNombre(), 1);
        

        if (jugadorHaAdivinado(indJugador, numeroIngresado)) {
            jugadorAcerto(indJugador);
        } else {
            jugadorFallo(indJugador, numeroIngresado);
        }
    }

    private boolean jugadorHaAdivinado(int indJugador, int numeroIngresado) {
        return this.numerosAAdivinar[indJugador] == numeroIngresado;
    }

    private void jugadorFallo(int i, int numeroIngresado) {
        if (numeroIngresado > numerosAAdivinar[i]) {
            Salida.mMensaje("Demasiado alto", "Error");
        } else {
            Salida.mMensaje("Demasiado bajo", "Error");
        }
        jugadores[i].actualizarIntentos();
    }

    private void jugadorAcerto(int i) {
        jugadores[i].setAdivino(true);
        Instant tiempoFinal = Instant.now();
        jugadores[i].setTiempoFinal(tiempoFinal);
        Salida.mMensaje(jugadores[i].getNombre() + " Ha adivinado", "Felicitaciones!");
    }

    private int jugadoresEnJuego() {
        int jEnJuego = 0;
        for (int i = 0; i < jugadores.length; i++) {
            if (!jugadores[i].yaAdivino()) {
                jEnJuego++;
            }
        }
        return jEnJuego;
    }

    private void cargarJugador() {
        for (int i = 0; i < jugadores.length; i++) {
            String nombre = Ingreso.datoString("Ingrese nombre del jugador", " Jugador " + 
            (i + 1), 1);
            int numero = (int) (Math.random() * 100 + 1);
            System.out.println(numero);
            this.numerosAAdivinar[i] = numero;
            jugadores[i].setNombre(nombre);
        }
    }
}
