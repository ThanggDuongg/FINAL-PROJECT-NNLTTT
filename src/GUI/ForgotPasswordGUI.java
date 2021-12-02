package GUI;

import BUS.CustomerBUS;
import GUI.AdminForms.AdminGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;

public class ForgotPasswordGUI extends JFrame{
    private JButton btn_Confirm;
    private JPanel mainPanel;
    private JTextField txt_Email;
    private JTextField txt_NewPasswd;
    private JTextField txt_RetypeNewPasswd;
    private JButton return_BT;

    public ForgotPasswordGUI() {
        setContentPane(mainPanel);
        setTitle("Forgot Password Form");
        setMinimumSize(new Dimension(450, 250));
        setMaximumSize(new Dimension(450, 250));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        btn_Confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txt_Email.getText().trim();
                String new_password = txt_NewPasswd.getText().trim();
                String retype_new_password = txt_RetypeNewPasswd.getText().trim();
                CustomerBUS customerBUS = new CustomerBUS();
                customerBUS.forgotPassword(email, new_password, retype_new_password);
            }
        });
        return_BT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LoginGUI loginGUI = new LoginGUI();
                    dispose();
                }
                catch(Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
    }
}
