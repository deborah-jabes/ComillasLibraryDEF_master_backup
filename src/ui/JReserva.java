package ui;

import dtc.isw.client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class JReserva extends JFrame {

    //Variables
    JComboBox biblioteca;
    JComboBox planta;
    JComboBox mesa;
    JComboBox horaIn;
    JComboBox horaFin;
    JButton salir;
    HashMap<String,Object> h = new HashMap<String,Object>();
    ArrayList<Object> noRepeat = new ArrayList<Object>();

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

        //Configuracion ComboBoxes

        //biblioteca
        biblioteca.addItem("");
        biblioteca.addItem("Calle de Alberto Aguilera 25");

        //planta
        planta.addItem("");
        //planta.addItem("3ª Planta");
        //planta.addItem("4ª Planta");
        //planta.addItem("5ª Planta");

        biblioteca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                noRepeat = new ArrayList<Object>(); // CUANDO PONGAMOS NUEVA BIBLIOTECA, HAY QUE EVITAR QUE SE REPITA LOS VALORES
                Client client = new Client();
                HashMap<String,Object> session = new HashMap<>();
                session.put("table","listaasientos");
                session.put("condicion","ocupado=false AND biblioteca='"+ biblioteca.getSelectedItem().toString()+"'");
                session.put("columna", 2);
                client.enviar("/getColumnInfo",session);
                h = (HashMap<String,Object>) session.get("Respuesta");
                for(Integer i = 0; i< h.size();i++)
                {
                    String s = (String) h.get(i.toString());
                    if(!noRepeat.contains(s))
                    {
                        planta.addItem(s);
                        noRepeat.add(s);
                        System.out.println(noRepeat.contains(h.get(i.toString())));
                    }
                }
            }
        });

        //mesa

        mesa.addItem("");

        planta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = new Client();
                HashMap<String,Object> session = new HashMap<>();
                session.put("table","listaasientos");
                session.put("condicion","ocupado=false AND biblioteca='" + biblioteca.getSelectedItem().toString() + "' AND planta='" + planta.getSelectedItem().toString() + "'");
                session.put("columna", 3);
                client.enviar("/getColumnInfo",session);
                h = (HashMap<String,Object>) session.get("Respuesta");
                for(Integer i = 0; i< h.size();i++)
                {
                    String s = (String) h.get(i.toString());
                    if(!noRepeat.contains(s))
                    {
                        mesa.addItem(s);
                        noRepeat.add(s);
                        System.out.println(noRepeat.contains(h.get(i.toString())));
                    }
                }
            }
        });

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
