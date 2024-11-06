package AO2_MARINIO_CRISTIAN.juego;

import AO2_MARINIO_CRISTIAN.inputOuput.Ingreso;

public class DemoJuego {
    public static void main(String[] args) { 
        int cantidadJugadores = Ingreso.datoEntero("Ingrese cantidad de jugadores", "Bienvenido", 1);
        Juego juego = new Juego(cantidadJugadores);
    }
}
