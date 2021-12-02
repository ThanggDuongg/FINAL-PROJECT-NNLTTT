package GUI;

import BUS.CustomerBUS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private JLabel Link_Login;

    public RegisterGUI() {
        setContentPane(mainPanel);
        setTitle("Register Form");
        setSize(700, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        Link_Login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
                if (customerBUS.Insert(firstname, lastname, phone, gender, age, address, email, password, retypePassword) == 1) {
                    LoginGUI loginGUI = new LoginGUI();
                }
                else {
                    //
                }
            }
        });
        Link_Login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    LoginGUI loginGUI = new LoginGUI();
                    dispose();
                }
                catch(Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                Link_Login.setForeground(Color.BLUE.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Link_Login.setForeground(new Color(76, 79, 81));
            }
        });
    }
}
