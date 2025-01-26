package com.mycompany.juego_de_la_vida;

import java.util.HashMap;
import java.util.Random;

public class Game {

    public Game() {
    }
    
    // Inicializa la cuadrícula con valores aleatorios (0)
//    public int[][] inicializarGridBlanco(int filas, int columnas) {
//        grid = new int[filas][columnas]; // Inicializa la matriz antes de usarla
//        for (int i = 0; i < filas; i++) {
//            for (int j = 0; j < columnas; j++) {
//                grid[i][j] = 0; // Todas las celdas están muertas inicialmente
//            }
//        }
//        return grid;
//    }

    public HashMap inicializarGridRandom(int filas, int columnas, HashMap<Posicion, Integer> grid) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Random aleatorio = new Random();
                int celula = aleatorio.nextInt(2);
                if (celula == 1){
                    grid.put(new Posicion(i,j), 1); 
                }
            }
        }
        return grid;
    }


//    public int[][] getGrid() {
//        return grid;
//    }

    // Método para obtener la siguiente generación
    public HashMap<Posicion, Integer> siguienteGeneracion(int filas, int columnas, HashMap<Posicion, Integer> grid) {
        if (grid == null || grid.isEmpty()) {
            throw new IllegalArgumentException("La cuadrícula está vacía o no ha sido inicializada.");
        }

        // Crear el HashMap para la nueva generación
        HashMap<Posicion, Integer> nuevaGrid = new HashMap<>();

        // Iterar sobre cada posición en la cuadrícula
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Posicion posicionActual = new Posicion(i, j);
                int vivos = contarVecinosVivos(grid, i, j);

                // Obtener el estado de la celda actual (viva o muerta)
                int estadoActual = grid.getOrDefault(posicionActual, 0); // 0 si no está en el mapa

                // Aplicar las reglas del juego
                if (estadoActual == 1) { // Celda viva
                    nuevaGrid.put(posicionActual, (vivos == 2 || vivos == 3) ? 1 : 0);
                } else { // Celda muerta
                    nuevaGrid.put(posicionActual, (vivos == 3) ? 1 : 0);
                }
            }
        }

        return nuevaGrid;
    }

    // Método para contar los vecinos vivos
    private int contarVecinosVivos(HashMap<Posicion, Integer> grid, int x, int y) {
        int vivos = 0;

        // Verificar los 8 vecinos alrededor de la celda (x, y)
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                // No contar la celda central
                if (dx == 0 && dy == 0) continue;

                Posicion vecino = new Posicion(x + dx, y + dy);
                if (grid.getOrDefault(vecino, 0) == 1) { // Si la celda está viva
                    vivos++;
                }
            }
        }
        return vivos;
    }


//    public void reiniciar(int fila, int columna) {
//        inicializarGridRandom(fila, columna);
//    }
}
