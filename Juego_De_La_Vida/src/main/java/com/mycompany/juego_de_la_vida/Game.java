package com.mycompany.juego_de_la_vida;

public class Game {
    private int[][] grid;
    private int filas;
    private int columnas;

    public Game(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.grid = new int[filas][columnas];
    }
    
    // Inicializa la cuadrícula con valores aleatorios (0)
    public int[][] inicializarGridBlanco() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                grid[i][j] = 0; // Todas las celdas están muertas inicialmente
            }
        }
        return grid;
    }

    public int[][] inicializarGridRandom() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                grid[i][j] = Math.random() > 0.5 ? 1 : 0;
            }
        }
        return grid;
    }

    public int[][] getGrid() {
        return grid;
    }

    public int[][] siguienteGeneracion(int[][] grid) {
        int[][] nuevaGrid = new int[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int vivos = contarVecinosVivos(grid, i, j);

                // Aplicar las reglas
                if (grid[i][j] == 1) { // Celda viva
                    nuevaGrid[i][j] = (vivos == 2 || vivos == 3) ? 1 : 0;
                } else { // Celda muerta
                    nuevaGrid[i][j] = (vivos == 3) ? 1 : 0;
                }
            }
        }
        return nuevaGrid;
    }


    private int contarVecinosVivos(int[][] grid, int fila, int columna) {
        int vivos = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue; // Saltar la celda actual
                int nuevaFila = fila + i;
                int nuevaColumna = columna + j;

                if (nuevaFila >= 0 && nuevaFila < grid.length &&
                    nuevaColumna >= 0 && nuevaColumna < grid[0].length) {
                    vivos += grid[nuevaFila][nuevaColumna];
                }
            }
        }
        return vivos;
    }


    public void reiniciar() {
        inicializarGridRandom();
    }
}
