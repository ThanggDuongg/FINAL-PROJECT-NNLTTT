package GUI;

import BUS.CustomerBUS;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class ForgotPasswordGUI extends JFrame{
    private JButton btn_Confirm;
    private JPanel mainPanel;
    private JTextField txt_Email;
    private JTextField txt_NewPasswd;
    private JTextField txt_RetypeNewPasswd;

    public ForgotPasswordGUI() {
        setContentPane(mainPanel);
        setTitle("Forgot Password Form");
        setSize(700, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
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
    }
}
