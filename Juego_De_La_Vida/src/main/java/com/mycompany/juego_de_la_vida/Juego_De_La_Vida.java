package com.mycompany.juego_de_la_vida;

import javax.swing.SwingUtilities;


public class Juego_De_La_Vida {

    public static void main(String[] args) {
        // Crear la ventana en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> new Ventana());
    }
}
