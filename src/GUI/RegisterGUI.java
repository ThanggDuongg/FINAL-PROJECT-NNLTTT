package GUI;

import BUS.CustomerBUS;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class RegisterGUI extends JFrame{
    private JButton btn_Register;
    private JPanel mainPanel;
    private JTextField txt_Email;
    private JTextField txt_Firstname;
    private JTextField txt_Lastname;
    private JTextField txt_Phone;
    private JTextField txt_Address;
    private JComboBox cb_Gender;
    private JSpinner sp_Age;
    private JTextField txt_Password;
    private JTextField txt_Retypepasswd;
    private JLabel link_login;

    public RegisterGUI() {
        setContentPane(mainPanel);
        setTitle("Register Form");
        setSize(700, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        btn_Register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txt_Email.getText().trim();
                String firstname = txt_Firstname.getText().trim();
                String lastname = txt_Lastname.getText().trim();
                String phone = txt_Phone.getText().trim();
                String address = txt_Address.getText().trim();
                String gender = String.valueOf(cb_Gender.getSelectedItem());
                int age = (Integer) sp_Age.getValue();
                String password = txt_Password.getText().trim();
                String retypePassword = txt_Retypepasswd.getText().trim();
                CustomerBUS customerBUS = new CustomerBUS();
                customerBUS.Insert(firstname, lastname, phone, gender, age, address, email, password, retypePassword);
            }
        });
    }
}
