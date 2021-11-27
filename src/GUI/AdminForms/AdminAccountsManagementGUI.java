package GUI.AdminForms;

import BUS.AdministratorBUS;
import BUS.CustomerBUS;
import BUS.ShipperBUS;
import BUS.BUS;
import DTO.AdministratorDTO;
import DTO.CustomerDTO;
import DTO.ShipperDTO;
import Globals.Globals;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class AdminAccountsManagementGUI extends JFrame{
    private CustomerBUS customerBUS = new CustomerBUS();
    private ShipperBUS shipperBUS = new ShipperBUS();
    private AdministratorBUS administratorBUS = new AdministratorBUS();
    public JTabbedPane tabbedPane1;
    public JPanel mainPanel;
    private JButton btn_UpdateAdmin;
    private JTabbedPane tabbedPane2;
    private JTable table_Customers;
    private JTable table_Shippers;
    private JButton btn_Update;
    public JPanel panelAccountManagement;
    private JTextField txt_Email;
    private JTextField txt_Password;
    private JTextField txt_Firstname;
    private JTextField txt_Lastname;
    private JTextField txt_Phone;
    private JTextField txtAddress;
    private JComboBox cb_Gender;
    private JTextField txt_Salary;
    private JSpinner sp_AgeAdmin;
    private JTextField txt_EmailAdmin;
    private JTextField txt_PasswdAdmin;
    private JTextField txt_FirstnameAdmin;
    private JTextField txt_LastnameAdmin;
    private JTextField txt_PhoneAdmin;
    private JComboBox cb_GenderAdmin;
    private JSpinner sp_Age;
    private JComboBox cb_Role;
    private JButton btn_Insert;
    private JButton btn_Delete;
    private JButton btn_Reset;
    private JTextField txt_Distance;
    private JTable table_Admins;

    public void loadDataAdmins() {
        DefaultTableModel model = (DefaultTableModel) table_Admins.getModel();
        model.setRowCount(0);
        List<AdministratorDTO> administratorDTOList = this.administratorBUS.administratorDTOList();
        for (AdministratorDTO item:administratorDTOList) {
            String Email = item.getEmail();
            String Firstname = item.getFirstname();
            String Lastname = item.getLastname();
            String Phone = item.getPhone();

            Object[] data = {Email, Firstname, Lastname, Phone};
            model.addRow(data);
        }
    }

    public void loadDataShippers() {
        DefaultTableModel model = (DefaultTableModel) table_Shippers.getModel();
        model.setRowCount(0);
        List<ShipperDTO> shipperDTOList = this.shipperBUS.shipperDTOList();
        for (ShipperDTO item:shipperDTOList) {
            String Email = item.getEmail();
            String Firstname = item.getFirstname();
            String Lastname = item.getLastname();
            String Phone = item.getPhone();

            Object[] data = {Email, Firstname, Lastname, Phone};
            model.addRow(data);
        }
    }

    public void loadDataCustomers() {
        DefaultTableModel model = (DefaultTableModel) table_Customers.getModel();
        model.setRowCount(0);
        List<CustomerDTO> customerDTOArrayList = this.customerBUS.customerDTOArrayList();
        for (CustomerDTO item:customerDTOArrayList) {
            String Email = item.getEmail();
            String Firstname = item.getFirstname();
            String Lastname = item.getLastname();
            String Phone = item.getPhone();

            Object[] data = null;
            model.addRow(new Object[] {Email, Firstname, Lastname, Phone});
        }
        System.out.println(model.getDataVector().size());
    }

    private void createTable() {
        table_Customers.getTableHeader().setFont(new Font("Arial", 2, 14));
        table_Customers.setModel(new DefaultTableModel(null, new String[]{"Email", "First name", "Last name", "Phone number"}));

        table_Shippers.getTableHeader().setFont(new Font("Arial", 2, 14));
        table_Shippers.setModel(new DefaultTableModel(null, new String[]{"Email", "First name", "Last name", "Phone number"}));

        table_Admins.getTableHeader().setFont(new Font("Arial", 2, 14));
        table_Admins.setModel(new DefaultTableModel(null, new String[]{"Email", "First name", "Last name", "Phone number"}));
    }

    public AdminAccountsManagementGUI() {
        createTable();
        setContentPane(mainPanel);
        setTitle("Manage Account Form");
        setSize(700, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        btn_UpdateAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txt_EmailAdmin.getText().trim();
                String firstname = txt_FirstnameAdmin.getText().trim();
                String lastname = txt_LastnameAdmin.getText().trim();
                String phone = txt_PhoneAdmin.getText().trim();
                String gender = String.valueOf(cb_GenderAdmin.getSelectedItem());
                int age = (Integer) sp_AgeAdmin.getValue();
                String password = txt_PasswdAdmin.getText().trim();

                AdministratorBUS administratorBUS = new AdministratorBUS();
                if (administratorBUS.updateAccount(Globals.getGlobalAdministratorId(), firstname, lastname, phone, gender, age, email, password) == 1) {
                    //success
                }
                else {
                    //
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                AdministratorBUS administratorBUS = new AdministratorBUS();
                AdministratorDTO administratorDTO = administratorBUS.findById(Globals.getGlobalAdministratorId());

                txt_EmailAdmin.setText(administratorDTO.getEmail().trim());
                txt_FirstnameAdmin.setText(administratorDTO.getFirstname().trim());
                txt_LastnameAdmin.setText(administratorDTO.getLastname().trim());
                txt_PhoneAdmin.setText(administratorDTO.getPhone().trim());
                cb_GenderAdmin.setSelectedIndex(BUS.indexGender(administratorDTO.getGender().trim()));
                sp_AgeAdmin.setValue(administratorDTO.getAge());
                txt_PasswdAdmin.setText(administratorDTO.getPassword().trim());
                loadDataAdmins();
            }
        });
        tabbedPane2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadDataCustomers();
                if (txt_Salary.isEditable()) {
                    txt_Salary.setEditable(false);
                }
                if (!txtAddress.isEditable()) {
                    txt_Distance.setEditable(true);
                    txtAddress.setEditable(true);
                }
            }
        });
        tabbedPane2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadDataShippers();
                if (txt_Distance.isEditable()) {
                    txt_Distance.setEditable(false);
                    txtAddress.setEditable(false);
                }

                if (!txt_Salary.isEditable()) {
                    txt_Salary.setEditable(true);
                }
            }
        });
        table_Customers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = table_Customers.getSelectedRow();
                TableModel tableModel = table_Customers.getModel();
                CustomerDTO customerDTO = CustomerBUS.findByEmail(tableModel.getValueAt(index, 0).toString().trim());
                txt_Email.setText(customerDTO.getEmail().trim());
                txt_Password.setText(customerDTO.getPassword().trim());
                txt_Firstname.setText(customerDTO.getFirstname().trim());
                txt_Lastname.setText(customerDTO.getLastname().trim());
                txt_Phone.setText(customerDTO.getPhone().trim());
                txtAddress.setText(customerDTO.getAddress().trim());
                txt_Distance.setText(String.valueOf(customerDTO.getDistance()).trim());
                txt_Salary.setText("No information");
                cb_Gender.setSelectedIndex(BUS.indexGender(customerDTO.getGender()));
                sp_Age.setValue(customerDTO.getAge());
                cb_Role.setSelectedIndex(1);

                if (txt_Salary.isEditable()) {
                    txt_Salary.setEditable(false);
                }
                if (!txtAddress.isEditable()) {
                    txt_Distance.setEditable(true);
                    txtAddress.setEditable(true);
                }
            }
        });
        btn_Reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = "";
                txt_Email.setText(temp);
                txt_Password.setText(temp);
                txt_Firstname.setText(temp);
                txt_Lastname.setText(temp);
                txt_Phone.setText(temp);
                txtAddress.setText(temp);
                txt_Distance.setText(temp);
                txt_Salary.setText(temp);
                cb_Gender.setSelectedIndex(0);
                sp_Age.setValue(0);
                cb_Role.setSelectedIndex(0);
            }
        });
        table_Shippers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = table_Shippers.getSelectedRow();
                TableModel tableModel = table_Shippers.getModel();
                ShipperDTO shipperDTO = ShipperBUS.findByEmail(tableModel.getValueAt(index, 0).toString().trim());
                txt_Email.setText(shipperDTO.getEmail().trim());
                txt_Password.setText(shipperDTO.getPassword().trim());
                txt_Firstname.setText(shipperDTO.getFirstname().trim());
                txt_Lastname.setText(shipperDTO.getLastname().trim());
                txt_Phone.setText(shipperDTO.getPhone().trim());
                txtAddress.setText("No information");
                txt_Distance.setText("No information");
                txt_Salary.setText(String.valueOf(shipperDTO.getSalary()).trim());
                cb_Gender.setSelectedIndex(BUS.indexGender(shipperDTO.getGender()));
                sp_Age.setValue(shipperDTO.getAge());
                cb_Role.setSelectedIndex(2);

                if (txt_Distance.isEditable()) {
                    txt_Distance.setEditable(false);
                    txtAddress.setEditable(false);
                }

                if (!txt_Salary.isEditable()) {
                    txt_Salary.setEditable(true);
                }
            }
        });
        btn_Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txt_Email.getText().trim();
                int indexRole = cb_Role.getSelectedIndex();
                if (indexRole == 1) {
                    // form check sure to delete =>
                    if (CustomerBUS.deleteByEmail(email) == 1) {
                        //sc
                        loadDataCustomers();
                        String temp = "";
                        txt_Email.setText(temp);
                        txt_Password.setText(temp);
                        txt_Firstname.setText(temp);
                        txt_Lastname.setText(temp);
                        txt_Phone.setText(temp);
                        txtAddress.setText(temp);
                        txt_Distance.setText(temp);
                        txt_Salary.setText(temp);
                        cb_Gender.setSelectedIndex(0);
                        sp_Age.setValue(0);
                        cb_Role.setSelectedIndex(0);
                    }
                    else {
                        //unsc
                    }
                }
                else if (indexRole == 2) {
                    if (ShipperBUS.deleteByEmail(email) == 1) {
                        //sc
                        loadDataShippers();
                        String temp = "";
                        txt_Email.setText(temp);
                        txt_Password.setText(temp);
                        txt_Firstname.setText(temp);
                        txt_Lastname.setText(temp);
                        txt_Phone.setText(temp);
                        txtAddress.setText(temp);
                        txt_Distance.setText(temp);
                        txt_Salary.setText(temp);
                        cb_Gender.setSelectedIndex(0);
                        sp_Age.setValue(0);
                        cb_Role.setSelectedIndex(0);
                    }
                    else {
                        //unsc
                    }
                }
                else {
                    if (AdministratorBUS.deleteByEmail(email) == 1) {
                        //sc
                        loadDataAdmins();
                        String temp = "";
                        txt_Email.setText(temp);
                        txt_Password.setText(temp);
                        txt_Firstname.setText(temp);
                        txt_Lastname.setText(temp);
                        txt_Phone.setText(temp);
                        txtAddress.setText(temp);
                        txt_Distance.setText(temp);
                        txt_Salary.setText(temp);
                        cb_Gender.setSelectedIndex(0);
                        sp_Age.setValue(0);
                        cb_Role.setSelectedIndex(0);
                    }
                    else {
                        //unsc
                    }
                }
            }
        });
        btn_Update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexRole = cb_Role.getSelectedIndex() + 1;
                String email = txt_Email.getText().trim();
                String firstname = txt_Firstname.getText().trim();
                String lastname = txt_Lastname.getText().trim();
                String phone = txt_Phone.getText().trim();
                String gender = cb_Gender.getSelectedItem().toString().trim();
                int age = Integer.parseInt(sp_Age.getValue().toString());
                String password = txt_Password.getText().trim();
                if (indexRole == 2) { //Customer
                    String address = txtAddress.getText().trim();
                    double distance = Double.parseDouble(txt_Distance.getText().trim());
                    CustomerBUS customerBUS = new CustomerBUS();
                    Integer Id = CustomerBUS.findByEmail(email).getID();
                    if (customerBUS.updateAccount(Id, firstname, lastname, phone, gender, age, address, email, password, distance) == 1) {
                        //sc
                        loadDataCustomers();
                    }
                    else {
                        //usc
                    }
                }
                else if (indexRole == 3) {
                    double salary = Double.parseDouble(txt_Salary.getText().trim());
                    ShipperBUS shipperBUS = new ShipperBUS();
                    Integer Id = ShipperBUS.findByEmail(email).getID();
                    if (shipperBUS.updateAccount(Id, firstname, lastname, phone, gender, age, salary, email, password) == 1) {
                        //sc
                        loadDataShippers();
                    }
                    else {
                        //unsc
                    }
                }
                else {
                    Integer Id = AdministratorBUS.findByEmail(email).getID();
                    if (administratorBUS.updateAccount(Id, firstname, lastname, phone, gender, age, email, password) == 1) {
                        //sc
                        loadDataAdmins();
                    }
                    else {
                        //unsc
                    }
                }
            }
        });
        btn_Insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexRole = cb_Role.getSelectedIndex() + 1;
                String email = txt_Email.getText().trim();
                String firstname = txt_Firstname.getText().trim();
                String lastname = txt_Lastname.getText().trim();
                String phone = txt_Phone.getText().trim();
                String gender = cb_Gender.getSelectedItem().toString().trim();
                int age = Integer.parseInt(sp_Age.getValue().toString());
                String password = txt_Password.getText().trim();
                if (indexRole == 2) { //Customer
                    String address = txtAddress.getText().trim();
                    CustomerBUS customerBUS = new CustomerBUS();
                    Integer Id = CustomerBUS.findByEmail(email).getID();
                    if (customerBUS.insertAccount(firstname, lastname, phone, gender, age, address, email, password) == 1) {
                        //sc
                        loadDataCustomers();
                    }
                    else {
                        //usc
                    }
                }
                else if (indexRole == 3) {
                    double salary = Double.parseDouble(txt_Salary.getText().trim());
                    ShipperBUS shipperBUS = new ShipperBUS();
                    Integer Id = ShipperBUS.findByEmail(email).getID();
                    if (shipperBUS.insertAccount(firstname, lastname, phone, gender, age, email, password, salary) == 1) {
                        //sc
                        loadDataShippers();
                    }
                    else {
                        //unsc
                    }
                }
                else {
                    Integer Id = AdministratorBUS.findByEmail(email).getID();
                    if (administratorBUS.insertAccount(firstname, lastname, phone, gender, age, email, password) == 1) {
                        //sc
                        loadDataAdmins();
                    }
                    else {
                        //unsc
                    }
                }
            }
        });
        tabbedPane2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadDataAdmins();
                txt_Salary.setEditable(false);
                txt_Distance.setEditable(false);
                txtAddress.setEditable(false);
            }
        });
        table_Admins.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = table_Admins.getSelectedRow();
                TableModel tableModel = table_Admins.getModel();
                AdministratorDTO administratorDTO = AdministratorBUS.findByEmail(tableModel.getValueAt(index, 0).toString().trim());
                txt_Email.setText(administratorDTO.getEmail().trim());
                txt_Password.setText(administratorDTO.getPassword().trim());
                txt_Firstname.setText(administratorDTO.getFirstname().trim());
                txt_Lastname.setText(administratorDTO.getLastname().trim());
                txt_Phone.setText(administratorDTO.getPhone().trim());
                txtAddress.setText("No information");
                txt_Distance.setText("No information");
                txt_Salary.setText("No information");
                cb_Gender.setSelectedIndex(BUS.indexGender(administratorDTO.getGender()));
                sp_Age.setValue(administratorDTO.getAge());
                cb_Role.setSelectedIndex(0);

                txt_Salary.setEditable(false);
                txt_Distance.setEditable(false);
                txtAddress.setEditable(false);
            }
        });
    }
}
