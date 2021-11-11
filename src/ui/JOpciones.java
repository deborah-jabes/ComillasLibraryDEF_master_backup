package ui;
import dtc.isw.client.Client;
import util.JInfoBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class JOpciones extends JFrame
{
    //Variables
    JButton reservar;
    JButton cancelar;
    JButton perfil;
    JButton salir;

    public static void main(String argv[])
    {
        new JOpciones("default");
    }

    public JOpciones(String usuario)
    {
        super("ComillasLibrary: Menu Principal");

        //Instanciar variables
        reservar = new JButton("Reserva un asiento");
        cancelar = new JButton("Cancelar la reserva");
        perfil = new JButton("Perfil");
        salir = new JButton("Salir");
        Font fontTitulo = new Font("Arial",Font.BOLD,20);

        //Paneles
        JPanel pnlNorth = new JPanel();
        JPanel pnlSouth = new JPanel();

        //Modificacion fuentes
        //titulo.setFont(fontTitulo);

        //Norte
        pnlNorth.setLayout(new GridLayout(1, 3));
        pnlNorth.add(reservar);
        pnlNorth.add(cancelar);
        pnlNorth.add(perfil);
        this.add(pnlNorth, BorderLayout.NORTH);

        //Sur
        pnlSouth.add(salir);
        this.add(pnlSouth, BorderLayout.SOUTH);

        //Ventana
        this.setSize(800,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //Funciones botones

        reservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = new Client();
                HashMap<String, Object> session = new HashMap<String,Object>();
                session.put("table","reservas");
                session.put("condicion","username = '" + usuario + "'");
                session.put("columna",1);
                client.enviar("/getColumnInfo",session);
                //System.out.println(session.get("Respuesta"));
                HashMap<String,Object> h = (HashMap<String,Object>) session.get("Respuesta");
                if(usuario.equals(h.get("0"))) {
                    JInfoBox.infoBox("Error","Error: Ya tienes una reserva");
                }
                else
                {
                    dispose();
                    new JReserva(usuario);
                }
            }
        });

        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = new Client();
                HashMap<String, Object> session = new HashMap<String,Object>();
                session.put("table","reservas");
                session.put("condicion","username = '" + usuario + "'");
                session.put("columna",1);
                client.enviar("/getColumnInfo",session);
                //System.out.println(session.get("Respuesta"));
                HashMap<String,Object> h = (HashMap<String,Object>) session.get("Respuesta");
                if(usuario.equals(h.get("0"))) {
                    session = new HashMap<String,Object>();
                    session.put("tabla", "reservas");
                    session.put("condicion", "username = '" + usuario + "'");
                    client.enviar("/deleteValue", session);

                    session = new HashMap<String,Object>();
                    session.put("tabla","listaasientos");
                    session.put("valor","ocupado = false");
                    session.put("condicion","username = '" + usuario + "'");
                    client.enviar("/updateColumn",session);

                    session = new HashMap<String,Object>();
                    session.put("tabla","listaasientos");
                    session.put("valor","horain = null");
                    session.put("condicion","username = '" + usuario + "'");
                    client.enviar("/updateColumn",session);

                    session = new HashMap<String,Object>();
                    session.put("tabla","listaasientos");
                    session.put("valor","horafin = null");
                    session.put("condicion","username = '" + usuario + "'");
                    client.enviar("/updateColumn",session);

                    session = new HashMap<String,Object>();
                    session.put("tabla","listaasientos");
                    session.put("valor","username = null");
                    session.put("condicion","username = '" + usuario + "'");
                    client.enviar("/updateColumn",session);

                    JInfoBox.infoBox("Aviso", "Se ha borrado la reserva.");
                }
                else {
                    JInfoBox.infoBox("Error","Error: No has hecho una reserva");
                }
            }
        });

        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                new JLogin();
            }
        });


    }
}