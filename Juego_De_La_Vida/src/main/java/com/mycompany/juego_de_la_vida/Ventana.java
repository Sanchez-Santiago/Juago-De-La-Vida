package com.mycompany.juego_de_la_vida;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;

public class Ventana extends JFrame {
    int ancho = 800; // Valor inicial
    int alto = 600;  // Valor inicial

    public Ventana() {
        setTitle("Juego de la Vida");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Crear la instancia de Tablero después de que la ventana haya sido inicializada
        Tablero tablero = new Tablero(ancho, alto); // Usar valores iniciales
        MenuSuperior menuSuperior = new MenuSuperior(tablero);
        setJMenuBar(menuSuperior.getMenuBar());

        JScrollPane scrollPane = new JScrollPane(tablero);
        add(scrollPane);

        setSize(ancho, alto); // Usar los valores iniciales de ancho y alto
        setLocationRelativeTo(null);
        setVisible(true);

        // Agregar un ComponentListener para detectar cambios en el tamaño
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension newSize = getSize(); // Obtiene el nuevo tamaño
                ancho = newSize.width;
                alto = newSize.height;

                // Redibujar el tablero con el nuevo tamaño
                tablero.calcularCuadricula(ancho, alto);
                tablero.repaint(); // Forzar actualización del tablero
            }
        });
    }
}
