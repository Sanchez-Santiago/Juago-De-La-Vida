package com.mycompany.juego_de_la_vida;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Tablero extends JPanel {
    private final int FILAS = 50;       // Número de filas en la cuadrícula
    private final int COLUMNAS = 50;   // Número de columnas en la cuadrícula
    private int[][] grid;              // Matriz que representa la cuadrícula
    private Timer timer;               // Temporizador para avanzar la simulación
    private int zoom = 10;             // Tamaño inicial de cada celda (en píxeles)
    private Game game = new Game(FILAS, COLUMNAS);
    
    public Tablero() {
        grid = new int[FILAS][COLUMNAS];
        grid = game.inicializarGridRandom();// Inicializa la cuadrícula con valores aleatorios

        // Configurar el temporizador para avanzar la simulación cada 100 ms
        timer = new Timer(100, e -> {
            grid = game.siguienteGeneracion(grid); // Avanzar a la siguiente generación
            repaint(); // Redibujar el tablero
        });

        // Agregar soporte para zoom con el scroll del mouse
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getPreciseWheelRotation() < 0) {
                    zoomIn();
                } else {
                    zoomOut();
                }
            }
        });

        // Agregar MouseListener para registrar clics
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                manejarClic(e);
            }
        });
    }

    // Incrementa el nivel de zoom
    private void zoomIn() {
        zoom = Math.min(zoom + 2, 50); // Limita el zoom máximo
        repaint();
    }

    // Decrementa el nivel de zoom
    private void zoomOut() {
        zoom = Math.max(zoom - 2, 5); // Limita el zoom mínimo
        repaint();
    }

    // Maneja el clic en el tablero para cambiar el estado de la celda
    private void manejarClic(MouseEvent e) {
        // Calcula la posición de la celda según el clic del mouse
        int columna = e.getX() / zoom;
        int fila = e.getY() / zoom;

        // Verifica que el clic esté dentro de los límites del tablero
        if (fila >= 0 && fila < FILAS && columna >= 0 && columna < COLUMNAS) {
            // Cambia el estado de la celda (0 -> 1 o 1 -> 0)
            grid[fila][columna] = grid[fila][columna] == 0 ? 1 : 0;
            repaint(); // Redibuja el tablero
        }
    }

    // Dibuja la cuadrícula en el JPanel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibuja las celdas
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (grid[i][j] == 1) {
                    g.setColor(Color.BLACK); // Celda viva
                } else {
                    g.setColor(Color.WHITE); // Celda muerta
                }
                g.fillRect(j * zoom, i * zoom, zoom, zoom); // Dibuja la celda
            }
        }

        // Dibuja las líneas de la cuadrícula
        g.setColor(Color.GRAY);
        for (int i = 0; i <= FILAS; i++) {
            g.drawLine(0, i * zoom, COLUMNAS * zoom, i * zoom); // Líneas horizontales
        }
        for (int j = 0; j <= COLUMNAS; j++) {
            g.drawLine(j * zoom, 0, j * zoom, FILAS * zoom); // Líneas verticales
        }
    }

    // Inicia la simulación
    
    public void iniciar() {
        timer.start();
    }
    public void iniciarSimulacionBlanco() {
        grid = game.inicializarGridBlanco();
        repaint();
    }
    
    public void iniciarSimulacionRandom() {
        grid = game.inicializarGridRandom();
        repaint();
    }

    // Detiene la simulación
    public void detenerSimulacion() {
        timer.stop();
    }

    // Reinicia la simulación
    public void reiniciarSimulacion() {
        grid = game.inicializarGridRandom();
        repaint();
    }

    // Ajusta el tamaño preferido del JPanel según el nivel de zoom
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COLUMNAS * zoom, FILAS * zoom);
    }
}
