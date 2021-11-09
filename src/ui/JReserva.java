package ui;

import dtc.isw.client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class JReserva extends JFrame {

    //Constantes
    public static ArrayList<String> HORAS = new ArrayList<String>(Arrays.asList("08:00","08:30","09:00","09:30","10:00","10:30","11:00","11:30","12:00","12:30","13:00","13:30","14:00","14:30","15:00"));

    //Variables
    JComboBox biblioteca;
    JComboBox planta;
    JComboBox horaIn;
    JComboBox horaFin;
    JButton buscar;
    JButton salir;
    JLabel b;
    JLabel p;
    JLabel hi;
    JLabel hf;
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
        horaIn = new JComboBox();
        horaFin = new JComboBox();
        buscar = new JButton("Buscar Mesas");
        salir = new JButton("Volver a Opciones");
        b = new JLabel("Biblioteca:");
        p = new JLabel("Plantas Libres:");
        hi = new JLabel("Horario Inicial:");
        hf = new JLabel("Horario Final:");
        Font fontTexto = new Font("Arial", Font.BOLD, 12);

        //Paneles
        JPanel pnlCenter = new JPanel();
        JPanel pnlSouth = new JPanel();

        //Modificacion fuentes
        biblioteca.setFont(fontTexto);
        planta.setFont(fontTexto);
        horaIn.setFont(fontTexto);
        horaFin.setFont(fontTexto);

        //Configuracion ComboBoxes
        //Valores nulos
        biblioteca.addItem("");
        planta.addItem("");
        horaIn.addItem("");
        horaFin.addItem("");

        //biblioteca
        biblioteca.addItem("Calle de Alberto Aguilera 25");
        biblioteca.addItem("Otro");

        //planta
        //planta.addItem("3ª Planta");
        //planta.addItem("4ª Planta");
        //planta.addItem("5ª Planta");

        biblioteca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Previous options eliminated and replaced
                switch(planta.getItemCount())
                {
                    case 1:
                        System.out.println("No hay valores guardados");
                        break;

                    default:
                        planta.removeAllItems();
                        planta.addItem("");
                        break;
                }

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

        //HoraIn
        for(int i = 0; i<HORAS.size()-1;i++)
        {
            horaIn.addItem(HORAS.get(i));
        }

        //HoraFin

        horaIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switch(horaFin.getItemCount()) {
                    case 1:
                        System.out.println("No hay valores guardados");
                        break;

                    default:
                        horaFin.removeAllItems();
                        horaFin.addItem("");
                        break;
                }

                int index = HORAS.indexOf(horaIn.getSelectedItem());
                if(index < HORAS.size()-4)
                {
                    for(int j = 1; j < 5; j++) // Siempre 4 opciones
                    {
                        horaFin.addItem(HORAS.get(index+j));
                    }
                }
                else
                {
                    for(int j = index+1; j < HORAS.size();j++)
                    {
                        System.out.println(j);
                        horaFin.addItem(HORAS.get(j));
                    }

                }
            }
        });

        //Centro
        pnlCenter.setLayout(new GridLayout(4, 2));
        pnlCenter.add(b);
        pnlCenter.add(biblioteca);
        pnlCenter.add(p);
        pnlCenter.add(planta);
        pnlCenter.add(hi);
        pnlCenter.add(horaIn);
        pnlCenter.add(hf);
        pnlCenter.add(horaFin);
        this.add(pnlCenter, BorderLayout.CENTER);

        //Sur
        pnlSouth.add(buscar);
        pnlSouth.add(salir);
        this.add(pnlSouth, BorderLayout.SOUTH);

        //Ventana
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);




        //Funciones botones
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                ArrayList<String> valores = new ArrayList<String>();
                valores.add((String) biblioteca.getSelectedItem());
                valores.add((String) planta.getSelectedItem());
                valores.add((String) horaIn.getSelectedItem());
                valores.add((String) horaFin.getSelectedItem());
                new JReserva2(usuario,valores);
            }
        });

        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new JOpciones(usuario);
            }
        });
    }
}
