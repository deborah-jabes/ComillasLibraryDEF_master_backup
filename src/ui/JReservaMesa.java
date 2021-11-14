package ui;

import dtc.isw.client.Client;
import util.JInfoBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Displays the page where the user can choose a table for his reservation
 */
public class JReservaMesa extends JFrame {
    //Variables
    JComboBox mesa;
    JButton salir;
    JButton reservar;
    JLabel m;

    HashMap<String,Object> h;
    ArrayList<Object> noRepeat = new ArrayList<Object>();

    public static int MAXWIDTH = 800;
    public static int MAXHEIGHT = 800;

    public void main(String argv[])
    {
        new JReservaMesa("default",null);
    }

    // valores(0) = biblioteca
    // valores(1) = planta
    // valores(2) = horain;
    // valores(3) = horafin;

    /**
     * Contructor of JReservaMesa class
     * Initializes variables and describe the page and its elements
     * @param usuario The current user
     * @param valores Table with library and level for the reservation
     */
    public JReservaMesa(String usuario, ArrayList<String> valores)
    {
        super("ComillasLibrary: Reservacion (Opciones)");

        //Instanciar variables
        mesa = new JComboBox();
        salir = new JButton("Volver Atras");
        reservar = new JButton("Reservar el asiento");
        m = new JLabel("Mesa:");
        Font fontTexto = new Font("Arial", Font.BOLD, 12);

        //Paneles
        JPanel pnlNorth = new JPanel();
        JPanel pnlCenter = new JPanel();
        JPanel pnlSouth = new JPanel();

        //Modificacion fuentes
        mesa.setFont(fontTexto);

        //Configuracion ComboBoxes
        //Valores nulos
        mesa.addItem("");

        //mesa

        Client client = new Client();
        HashMap<String,Object> session = new HashMap<>();
        session.put("table","listaasientos");
        session.put("condicion","biblioteca ='" + valores.get(0) + "' AND planta = '" + valores.get(1) + "' AND ocupado = false");
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


        //Norte
        //pnlNorth.setLayout(new GridLayout(1, 2));
        pnlNorth.add(m);
        pnlNorth.add(mesa);
        this.add(pnlNorth, BorderLayout.NORTH);

        //Centro
        double d = 0.8*MAXHEIGHT;
        int h = (int) d;
        Image logo = new ImageIcon("src/Recursos/Bibliotecas/"+valores.get(0)+"/"+valores.get(1)+".png").getImage();
        ImageIcon ii = new ImageIcon(logo.getScaledInstance(MAXWIDTH,h,java.awt.Image.SCALE_SMOOTH));
        JLabel im = new JLabel(ii);
        pnlCenter.add(im);
        this.add(pnlCenter, BorderLayout.CENTER);

        //Sur
        pnlSouth.add(salir);
        pnlSouth.add(reservar);
        this.add(pnlSouth, BorderLayout.SOUTH);

        //Ventana
        this.setSize(MAXWIDTH, MAXHEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //Funciones botones
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                new JReservaInformacion(usuario);
            }
        });

        reservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mesa.getSelectedItem().equals(""))
                {
                    JInfoBox.infoBox("Error","Error: No has elegido una mesa");
                }
                else {
                    Client client = new Client();
                    HashMap<String, Object> session;

                    //insertar valor en tabla reservas
                    session = new HashMap<String, Object>();
                    System.out.println(usuario);
                    session.put("tabla", "reservas");
                    session.put("valor", "'" + usuario + "' , '" + valores.get(2) + "' , '" + valores.get(3) + "'");
                    client.enviar("/insertColumn", session);


                    //actualizar tabla listaasientos
                    session = new HashMap<String, Object>();
                    session.put("tabla", "listaasientos");
                    session.put("valor", "ocupado = true");
                    session.put("condicion", "biblioteca = '" + valores.get(0) + "' AND planta = '" + valores.get(1) + "'" + " AND mesa = '" + mesa.getSelectedItem() + "'");
                    client.enviar("/updateColumn", session);

                    session = new HashMap<String, Object>();
                    session.put("tabla", "listaasientos");
                    session.put("valor", "horain = '" + valores.get(2) + "'");
                    session.put("condicion", "biblioteca = '" + valores.get(0) + "' AND planta = '" + valores.get(1) + "'" + " AND mesa = '" + mesa.getSelectedItem() + "'");
                    client.enviar("/updateColumn", session);

                    session = new HashMap<String, Object>();
                    session.put("tabla", "listaasientos");
                    session.put("valor", "horafin = '" + valores.get(3) + "'");
                    session.put("condicion", "biblioteca = '" + valores.get(0) + "' AND planta = '" + valores.get(1) + "'" + " AND mesa = '" + mesa.getSelectedItem() + "'");
                    client.enviar("/updateColumn", session);

                    session = new HashMap<String, Object>();
                    session.put("tabla", "listaasientos");
                    session.put("valor", "username = '" + usuario + "'");
                    session.put("condicion", "biblioteca = '" + valores.get(0) + "' AND planta = '" + valores.get(1) + "'" + " AND mesa = '" + mesa.getSelectedItem() + "'");
                    client.enviar("/updateColumn", session);

                    dispose();
                    new JOpciones(usuario);
                }
            }
        });
    }
}
