package com.mycompany.juego_de_la_vida;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MenuSuperior {
    private JMenuBar menuBar;

    public MenuSuperior(Tablero tablero) {
        menuBar = new JMenuBar();
        crearMenu(tablero);
    }

    private void crearMenu(Tablero tablero) {
        JMenu archivoMenu = new JMenu("Archivo");
        agregarItemsArchivo(archivoMenu, tablero);

        JMenu ayudaMenu = new JMenu("Ayuda");
        agregarItemsAyuda(ayudaMenu);

        menuBar.add(archivoMenu);
        menuBar.add(ayudaMenu);
    }

    private void agregarItemsArchivo(JMenu archivoMenu, Tablero tablero) {
        JMenuItem iniciarItem = new JMenuItem("Iniciar");
        JMenuItem iniciorrandomItem = new JMenuItem("Tablero en random");
        JMenuItem iniciarBlancoItem = new JMenuItem("Tablero en blanco");
        JMenuItem detenerItem = new JMenuItem("Detener");
        JMenuItem reiniciarItem = new JMenuItem("Reiniciar");

        // Asignar la acción correcta a cada ítem
        iniciarItem.addActionListener(e -> tablero.iniciar());
        iniciorrandomItem.addActionListener(e -> tablero.iniciarSimulacionRandom());
        iniciarBlancoItem.addActionListener(e -> tablero.iniciarSimulacionBlanco());
        detenerItem.addActionListener(e -> tablero.detenerSimulacion());
        reiniciarItem.addActionListener(e -> tablero.reiniciarSimulacion());

        archivoMenu.add(iniciarItem);
        archivoMenu.add(iniciorrandomItem);
        archivoMenu.add(iniciarBlancoItem);
        archivoMenu.add(detenerItem);
        archivoMenu.add(reiniciarItem);
    }


    private void agregarItemsAyuda(JMenu ayudaMenu) {
        JMenuItem acercaDeItem = new JMenuItem("Acerca de");
        acercaDeItem.addActionListener(e -> JOptionPane.showMessageDialog(null, "Juego de la Vida\nVersión 1.0", "Acerca de", JOptionPane.INFORMATION_MESSAGE));
        ayudaMenu.add(acercaDeItem);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
