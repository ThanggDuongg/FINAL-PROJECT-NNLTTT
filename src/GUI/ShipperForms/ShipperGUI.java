package GUI.ShipperForms;

import BUS.ShipperBUS;
import DTO.ShipperDTO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;

public class ShipperGUI extends JFrame{
    private JTabbedPane tabShipper;
    private JPanel mainPanel;
    private JButton btn_Update;
    private JTable Table_Orders;
    private JTextField txt_Email;
    private JTextField txt_Password;
    private JTextField txt_Firstname;
    private JTextField txt_Lastname;
    private JTextField txt_Phone;
    private JSpinner sp_Age;
    private JComboBox cb_Gender;
    private JTextField txt_Salary;
    private JPanel pnl_MyAccount;
    private JPanel pnl_Orders;
    private JTextField txt_FullnameCustomer;
    private JTextField txt_AddressCustomer;
    private JTextField txt_PhoneCustomer;
    private JTextField txt_TransportFee;
    private JTextField txt_Total;
    private JTextField txt_DistanceCustomer;
    private JButton btn_Cancel;
    private JButton bnt_Delivered;

    public ShipperGUI() {
        setContentPane(mainPanel);
        setTitle("Shipper Form");
        setSize(700, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        btn_Update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txt_Email.getText().trim();
                String firstname = txt_Firstname.getText().trim();
                String lastname = txt_Lastname.getText().trim();
                String phone = txt_Phone.getText().trim();
                String gender = String.valueOf(cb_Gender.getSelectedItem());
                int age = (Integer) sp_Age.getValue();
                Double salary = Double.parseDouble(txt_Salary.getText().trim());
                String password = txt_Password.getText().trim();
                ShipperBUS shipperBUS = new ShipperBUS();
                if (shipperBUS.updateAccount(firstname, lastname, phone, gender, age, salary, email, password) == 1) {
                    //update successfully
                }
                else {
                    //message
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                ShipperBUS shipperBUS = new ShipperBUS();
                ShipperDTO shipperDTO = shipperBUS.findById();
                txt_Email.setText(shipperDTO.getEmail().trim());
                txt_Firstname.setText(shipperDTO.getFirstname().trim());
                txt_Lastname.setText(shipperDTO.getLastname().trim());
                txt_Phone.setText(shipperDTO.getPhone().trim());
                cb_Gender.setSelectedIndex(shipperBUS.indexGender(shipperDTO.getGender().trim()));
                sp_Age.setValue(shipperDTO.getAge());
                txt_Salary.setText(String.valueOf(shipperDTO.tinhLuong()).trim());
                txt_Password.setText(shipperDTO.getPassword().trim());
            }
        });
    }
}
