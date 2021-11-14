package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JMenu extends JFrame
{
    //Variables
    JButton login;
    JLabel titulo;
    JLabel info;

    public static int MAXWIDTH = 800;
    public static int MAXHEIGHT = 800;

    public static void main(String argv[])
    {
        new JMenu();
    }

    public JMenu()
    {
        super("Menu Principal: ComillasLibrary");

        //Instanciar variables
        login = new JButton("Login");
        titulo = new JLabel("Menu Principal");
        info = new JLabel("Bienvenido a la aplicacion ComillasLibrary");
        Font fontTitulo = new Font("Arial",Font.BOLD,20);

        //Paneles
        JPanel pnlNorth = new JPanel();
        JPanel pnlSouth = new JPanel();
        JPanel pnlCenter = new JPanel();

        //Modificacion fuentes
        titulo.setFont(fontTitulo);

        //Norte
        pnlNorth.setLayout(new GridLayout(2,1));
        pnlNorth.add(titulo);
        pnlNorth.add(info);
        this.add(pnlNorth, BorderLayout.NORTH);

        //Centro
        double d = 0.5*MAXHEIGHT;
        int h = (int) d;
        Image logo = new ImageIcon("./Recursos/LOGO.png").getImage();
        ImageIcon ii = new ImageIcon(logo.getScaledInstance(MAXWIDTH,h,java.awt.Image.SCALE_SMOOTH));
        JLabel im = new JLabel(ii);
        pnlCenter.add(im);
        this.add(pnlCenter, BorderLayout.CENTER);

        //Sur
        pnlSouth.add(login);
        this.add(pnlSouth, BorderLayout.SOUTH);

        //Ventana
        this.setSize(MAXWIDTH,MAXHEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //Funciones botones
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                new JLogin();
            }
        });
    }
    }


