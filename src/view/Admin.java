package view;

import DAO.AdminDAO;
import VO.AdminVO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Admin extends JFrame {
    private String cargo, username, password;
    private AdminVO ad;
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    JLabel l[] = new JLabel[5];
    JComboBox cb[] = new JComboBox[5];
    JPanel p[] = new JPanel[8];
    JTextField tf[]=new JTextField[5];

    String cargoDe[] = {"Chefe", "Gerente", "Faxineiro", "Director(a)", "Gestor",
        "Advogado", "Chefe", "Tecnico"};
    String nomeDe[] = {"A", "B", "C"};
    //Default
    Font Butoes_Font, label_Font, ComboBox_Font;
    Color blue, white, orange, gray;
    GridBagConstraints gbc = new GridBagConstraints();

    public Admin() {
        setTitle("Cadastro || S.G.R.H");
        setLocation(250, 100);
        setSize(900, 700);
        setExtendedState(MAXIMIZED_BOTH);

        //Layout
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);

        //Incializando Variaveis
        Butoes_Font = new Font("SansSerif", Font.BOLD, 13);
        label_Font = new Font("SansSerif", Font.BOLD, 15);
        ComboBox_Font = new Font("SansSerif", Font.BOLD, 12);
        blue = new Color(44, 62, 80);
        white = new Color(255, 255, 255);
        gray = new Color(149, 156, 147);
        orange = new Color(245, 139, 31);

        //Image
        JLabel img = new JLabel("", new ImageIcon("src/images/d.jpg"), JLabel.CENTER);

        gbc.gridwidth = 0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(img, gbc);

        //Imagem 2
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel img2 = new JLabel("", new ImageIcon("src/images/m.jpg"), JLabel.LEFT);

        gbc.gridwidth = 0;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(img2, gbc);

        admin();

        butoes();

        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void admin() {
        //l[0] = new JLabel("Ano de Formacao:");
        l[1] = new JLabel("   Cargo:");
        l[2] = new JLabel("Nome da Empresa:");
        l[3] = new JLabel("Username");
        l[4] = new JLabel("Password");

        //Label Propriedade
        for (int i = 1; i <= 4; i++) {
            l[i].setFont(label_Font);
            l[i].setForeground(blue);
            l[i].setBackground(white);
        }

        //Linha 1
        p[1] = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        //p[1].add(l[0]);

        cb[0] = new JComboBox(cargoDe);
        cb[0].setFont(ComboBox_Font);
        cb[0].setForeground(blue);
        cb[0].setBackground(white);
        cb[0].setPreferredSize(new Dimension(180, 20));
       // p[1].add(cb[0]);

        p[1].add(l[1]);

        cb[1] = new JComboBox(cargoDe);
        cb[1].setFont(ComboBox_Font);
        cb[1].setForeground(blue);
        cb[1].setBackground(white);
        cb[1].setPreferredSize(new Dimension(180, 20));
        p[1].add(cb[1]);

        p[1].setBackground(white);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(p[1], gbc);
        
        
        //Imagem 2
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel img2 = new JLabel("", new ImageIcon("src/images/p.png"), JLabel.LEFT);

        gbc.gridwidth = 0;
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(img2, gbc);
        
        //Linha 2
            tf[0] = new JTextField("Username", 16);
        
        p[2] = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        p[2].add(l[3]);
        p[2].add(tf[0]);
        tf[0].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(tf[0].getText().equalsIgnoreCase("Username")){
                    tf[0].setText("");
                    tf[0].setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(tf[0].getText().equals("")){
                    tf[0].setText("Username");
                    tf[0].setForeground(Color.GRAY);
                }
            }
        });
        
        p[2].setBackground(white);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(p[2], gbc);
        
          //Linha 3
        tf[1] = new JTextField("Password", 16);
        p[3] = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        p[3].add(l[4]);
        p[3].add(tf[1]);
        tf[1].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(tf[1].getText().equalsIgnoreCase("Password")){
                    tf[1].setText("");
                    tf[1].setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(tf[1].getText().equals("")){
                    tf[1].setText("Password");
                    tf[1].setForeground(Color.GRAY);
                }
            }
        });
        
        p[3].setBackground(white);
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(p[3], gbc);
    }

    private void butoes() {
        //Butoes
        p[6] = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 25));
        JButton anterior = new JButton("Anterior");
        anterior.setBackground(orange);
        anterior.setPreferredSize(new Dimension(120, 32));
        anterior.setFont(new Font("Sans Serif", Font.BOLD, 14));
        anterior.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setVisible(false);
                Experiencia ex = new Experiencia();
            }
        });

        JButton proximo = new JButton("Finalizar");
        proximo.setBackground(blue);
        proximo.setForeground(white);
        proximo.setPreferredSize(new Dimension(120, 32));
        proximo.setFont(new Font("Sans Serif", Font.BOLD, 14));
        proximo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cadastro4();
            }
        });
        p[6].add(anterior);
        p[6].add(proximo);

        p[6].setBackground(white);
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 0;
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(p[6], gbc);
    }
    public void cadastro4(){
        cargo = cb[1].getSelectedItem().toString();
        username = tf[0].getText();
        password = tf[1].getText();
        ad = new AdminVO(cargo, username, password);
        AdminDAO daoad = new AdminDAO();
        daoad.inserir(ad);
        setVisible(false);
        MenuPrincipal mn = new MenuPrincipal();
        JOptionPane.showMessageDialog(null, " Cadastrado Com Sucesso! ");
    }
}