package GUI;

import GUI.AdminForms.AdminGUI;
import GUI.AdminForms.AdminMenuManagementGUI;
import GUI.CustomerForms.CustomerGUI;
import GUI.ShipperForms.ShipperGUI;
import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginGUI extends JFrame{

    private JButton loginButton;
    private JRadioButton customerRadioButton;
    private JRadioButton shipperRadioButton;
    private JRadioButton administratorRadioButton;
    private JPanel mainPanel;
    private JLabel lbforgotPassWord;
    private JLabel lbNewAccount;


    public LoginGUI() {
        setContentPane(mainPanel);
        setTitle("Login Form");
        setSize(700, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        lbforgotPassWord.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lbNewAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        lbforgotPassWord.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    ForgotPasswordGUI forgotPasswordGUI = new ForgotPasswordGUI();
                }
                catch(Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                lbforgotPassWord.setForeground(Color.BLUE.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbforgotPassWord.setForeground(new Color(76, 79, 81));
            }
        });
        lbNewAccount.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    RegisterGUI registerGUI = new RegisterGUI();
                }
                catch(Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                lbNewAccount.setForeground(Color.BLUE.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbNewAccount.setForeground(new Color(76, 79, 81));
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(customerRadioButton.isSelected()) {
                    CustomerGUI customerGUI = new CustomerGUI();
                    dispose();
                }
                else if(shipperRadioButton.isSelected()) {
                    ShipperGUI shipperGUI = new ShipperGUI();
                    dispose();
                }
                else if(administratorRadioButton.isSelected()) {
                    AdminGUI adminGUI = new AdminGUI();
                    dispose();
                }
            }
        });
    }
}
