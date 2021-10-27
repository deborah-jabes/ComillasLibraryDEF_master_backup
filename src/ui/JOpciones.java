package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JOpciones extends JFrame
{
    //Variables
    JButton reservar;
    JLabel titulo;

    public static void main(String argv[])
    {
        new JMenu();
    }

    public JOpciones()
    {
        super("ComillasLibrary: Menu Principal");

        //Instanciar variables
        Font fontTitulo = new Font("Arial",Font.BOLD,20);

        //Paneles
        JPanel pnlNorth = new JPanel();

        //Modificacion fuentes
        //titulo.setFont(fontTitulo);

        //Norte

        //Ventana
        this.setSize(800,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //Funciones botones
        /*login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                dispose();
                new JLogin();
            }
        }); */
    }
}