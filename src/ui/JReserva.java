package ui;

import dtc.isw.client.Client;
import dtc.isw.domain.Customer;
import util.JInfoBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class JReserva extends JFrame {

    //Variables
    JComboBox biblioteca;
    JComboBox planta;
    JComboBox mesa;
    JComboBox horaIn;
    JComboBox horaFin;
    JButton salir;

    public void main(String argv[])
    {
        new JReserva("default");
    }

    public JReserva(String usuario)
    {
        super("Reserva");

        //Instanciar variables
        biblioteca = new JComboBox();
        planta = new JComboBox();
        mesa = new JComboBox();
        horaIn = new JComboBox();
        horaFin = new JComboBox();
        salir = new JButton("Salir");
        Font fontTexto = new Font("Arial", Font.BOLD, 12);

        //Paneles
        JPanel pnlNorth = new JPanel();
        JPanel pnlCenter = new JPanel();
        JPanel pnlSouth = new JPanel();

        //Modificacion fuentes
        biblioteca.setFont(fontTexto);
        planta.setFont(fontTexto);
        mesa.setFont(fontTexto);
        horaIn.setFont(fontTexto);
        horaFin.setFont(fontTexto);

        //Norte
        pnlNorth.setLayout(new GridLayout(1, 4));
        pnlNorth.add(biblioteca);
        pnlNorth.add(planta);
        pnlNorth.add(mesa);
        pnlNorth.add(salir);
        this.add(pnlNorth, BorderLayout.NORTH);

        //Sur
        pnlSouth.add(horaIn);
        pnlSouth.add(horaFin);
        this.add(pnlSouth, BorderLayout.SOUTH);

        //Ventana
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //Funciones botones
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                new JOpciones(usuario);
            }
        });
    }
}
