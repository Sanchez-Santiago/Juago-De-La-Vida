package com.mycompany.juego_de_la_vida;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashMap;
import java.util.Random;

public class Tablero extends JPanel {
    private int filas = 50;       // Número de filas en la cuadrícula
    private int columnas = 50;   // Número de columnas en la cuadrícula
    private HashMap<Posicion, Integer> grid = new HashMap<>(); // Matriz que representa la cuadrícula
    private Timer timer;               // Temporizador para avanzar la simulación
    private int zoom = 10;             // Tamaño inicial de cada celda (en píxeles)
    private Game game = new Game();

    public void calcularCuadricula(int ancho, int alto) {
        filas = alto / zoom;
        columnas = ancho / zoom;

        // Ajustar el tamaño preferido del tablero
        setPreferredSize(new Dimension(columnas * zoom, filas * zoom));
        revalidate(); // Actualizar el layout
    }


    public Tablero(int ancho, int alto) {
        
        System.out.println("W: " + ancho + "H: " + alto);
        calcularCuadricula(ancho, alto);
        // Inicializa la cuadrícula con valores aleatorios
        grid = game.inicializarGridRandom(filas, columnas, grid);

        // Configurar el temporizador para avanzar la simulación cada 100 ms
        timer = new Timer(100, e -> {
            if (grid != null) {
                grid = game.siguienteGeneracion(filas, columnas, grid); // Avanzar a la siguiente generación
                repaint(); // Redibujar el tablero
            }
        });
        
        // Agregar soporte para zoom con el scroll del mouse
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getPreciseWheelRotation() < 0) {
                    zoomIn(ancho, alto);
                } else {
                    zoomOut(ancho, alto);
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
    private void zoomIn(int ancho, int alto) {
        zoom = Math.min(zoom + 2, 50); // Limita el zoom máximo
        calcularCuadricula(ancho, alto);
        repaint();
    }

    // Decrementa el nivel de zoom
    private void zoomOut(int ancho, int alto) {
        zoom = Math.max(zoom - 2, 5); // Limita el zoom mínimo
        calcularCuadricula(ancho, alto);
        repaint();
    }

    // Maneja el clic en el tablero para cambiar el estado de la celda
    private void manejarClic(MouseEvent e) {
        // Calcula la posición de la celda según el clic del mouse
        int columna = e.getX() / zoom;
        int fila = e.getY() / zoom;

        // Verifica que el clic esté dentro de los límites del tablero
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
            // Cambia el estado de la celda (0 -> 1 o 1 -> 0)
            Posicion posicion = new Posicion(fila, columna);
            grid.put(posicion, grid.getOrDefault(posicion, 0) == 0 ? 1 : 0);
            repaint(); // Redibuja el tablero
        }
    }

    // Dibuja la cuadrícula en el JPanel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (grid.isEmpty()){
            // Dibuja las celdas
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    Posicion pos = new Posicion(i, j);
                        g.setColor(Color.WHITE); // Celda muerta
                    g.fillRect(j * zoom, i * zoom, zoom, zoom); // Dibuja la celda
                }
            }
        }
        else{
            // Dibuja las celdas
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    Posicion pos = new Posicion(i, j);
                    if (grid.getOrDefault(pos, 0) == 1) {
                        g.setColor(Color.BLACK); // Celda viva
                    } else {
                        g.setColor(Color.WHITE); // Celda muerta
                    }
                    g.fillRect(j * zoom, i * zoom, zoom, zoom); // Dibuja la celda
                }
            }
        }

        

        // Dibuja las líneas de la cuadrícula
        g.setColor(Color.GRAY);
        for (int i = 0; i <= filas; i++) {
            g.drawLine(0, i * zoom, columnas * zoom, i * zoom); // Líneas horizontales
        }
        for (int j = 0; j <= columnas; j++) {
            g.drawLine(j * zoom, 0, j * zoom, filas * zoom); // Líneas verticales
        }
    }

    // Inicia la simulación
    public void iniciar() {
        timer.start();
    }

    // Detiene la simulación
    public void detenerSimulacion() {
        timer.stop();
    }

    // Reinicia la simulación
    public void reiniciarSimulacion() {
        grid = game.inicializarGridRandom(filas, columnas, grid);
        repaint();
    }
    
    public void iniciarSimulacionRandom(){
        grid = game.inicializarGridRandom(filas, columnas, grid);
        repaint();
    }
    public void iniciarSimulacionBlanco() {
        grid = game.inicializarGridBlanco(filas, columnas, grid); // Inicializa el grid vacío
        repaint();
    }


    // Ajusta el tamaño preferido del JPanel según el nivel de zoom
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(columnas * zoom, filas * zoom);
    }
}