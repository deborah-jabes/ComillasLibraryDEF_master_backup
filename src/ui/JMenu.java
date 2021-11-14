package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * JMenu class : displays the first page the user sees when opening the application
 */
public class JMenu extends JFrame
{
    //Variables
    JButton login;
    JButton salir;
    JLabel titulo;
    JLabel info;

    public static int MAXWIDTH = 800;
    public static int MAXHEIGHT = 800;

    public static void main(String argv[])
    {
        new JMenu();
    }

    /**
     * JMenu constructor : builds the Menu window with all its components
     */
    public JMenu()
    {
        super("ComillasLibrary: Menu Principal");

        //Instanciar variables
        login = new JButton("Login");
        salir = new JButton("Salir");
        titulo = new JLabel("Menu Principal", SwingConstants.CENTER);
        info = new JLabel("Bienvenido a la aplicacion ComillasLibrary", SwingConstants.CENTER);
        Font fTitulo = new Font("Arial",Font.BOLD,20);
        Font fTexto = new Font("Arial", Font.PLAIN, 15);

        //Paneles
        JPanel pnlNorth = new JPanel();
        JPanel pnlSouth = new JPanel();
        JPanel pnlCenter = new JPanel();

        //Modificacion fuentes
        titulo.setFont(fTitulo);
        info.setFont(fTexto);

        titulo.setOpaque(true);
        info.setOpaque(true);
        titulo.setBackground(Color.CYAN);
        info.setBackground(Color.CYAN);


        //Norte
        pnlNorth.setLayout(new GridLayout(2,1));
        pnlNorth.add(titulo);
        pnlNorth.add(info);
        this.add(pnlNorth, BorderLayout.NORTH);

        //Centro
        double d = 0.8*MAXHEIGHT;
        int h = (int) d;
        Image logo = new ImageIcon("src/Recursos/LOGO.png").getImage();
        ImageIcon ii = new ImageIcon(logo.getScaledInstance(MAXWIDTH,h,java.awt.Image.SCALE_SMOOTH));
        JLabel im = new JLabel(ii);
        pnlCenter.add(im);
        this.add(pnlCenter, BorderLayout.CENTER);

        //Sur
        pnlSouth.add(login);
        pnlSouth.add(salir);
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

        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });
    }
    }


