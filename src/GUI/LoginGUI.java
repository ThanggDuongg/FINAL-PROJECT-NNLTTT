package GUI;

import BUS.AdministratorBUS;
import BUS.CustomerBUS;
import BUS.ShipperBUS;
import DTO.CartDTO;
import GUI.AdminForms.AdminGUI;
import GUI.CustomerForms.CustomerGUI;
import GUI.ShipperForms.ShipperGUI;
import Globals.Cart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class LoginGUI extends JFrame{

    private JButton btn_Login;
    private JRadioButton radioBtn_Customer;
    private JRadioButton radioBtn_Shipper;
    private JRadioButton radioBtn_Admin;
    private JPanel mainPanel;
    private JLabel Link_ForgotPassword;
    private JLabel Link_Register;
    private JTextField txt_Email;
    private JTextField txt_Password;


    public LoginGUI() {
        setContentPane(mainPanel);
        setTitle("Login Form");
        setSize(700, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        Link_ForgotPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Link_Register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        Link_ForgotPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    ForgotPasswordGUI forgotPasswordGUI = new ForgotPasswordGUI();
                    dispose();
                }
                catch(Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                Link_ForgotPassword.setForeground(Color.BLUE.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Link_ForgotPassword.setForeground(new Color(76, 79, 81));
            }
        });
        Link_Register.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    RegisterGUI registerGUI = new RegisterGUI();
                    dispose();
                }
                catch(Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                Link_Register.setForeground(Color.BLUE.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Link_Register.setForeground(new Color(76, 79, 81));
            }
        });
        btn_Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txt_Email.getText().trim();
                String password = txt_Password.getText().trim();
                if(radioBtn_Customer.isSelected()) {
                    if (CustomerBUS.checkLogin(email, password) == 1) {
                        CustomerGUI customerGUI = new CustomerGUI();
                        dispose();
                    }
                }
                else if(radioBtn_Shipper.isSelected()) {
                    if (ShipperBUS.checkLogin(email, password) == 1) {
                        ShipperGUI shipperGUI = new ShipperGUI();
                        dispose();
                    }
                }
                else if(radioBtn_Admin.isSelected()) {
                    if (AdministratorBUS.checkLogin(email, password) == 1) {
                        AdminGUI adminGUI = new AdminGUI();
                        dispose();
                    }
                }
            }
        });
    }
}
