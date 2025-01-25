package com.mycompany.juego_de_la_vida;

import javax.swing.*;

public class Ventana extends JFrame {
    public Ventana() {
        setTitle("Juego de la Vida");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Tablero tablero = new Tablero();
        MenuSuperior menuSuperior = new MenuSuperior(tablero);
        setJMenuBar(menuSuperior.getMenuBar());

        JScrollPane scrollPane = new JScrollPane(tablero);
        add(scrollPane);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
