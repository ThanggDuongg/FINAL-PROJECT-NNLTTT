package GUI.AdminForms;

import javax.swing.*;

public class AdminAccountsManagementGUI extends JFrame{
    public JTabbedPane tabbedPane1;
    public JPanel mainPanel;
    private JButton btn_UpdateAdmin;
    private JTabbedPane tabbedPane2;
    private JTable table_Customer;
    private JTable table_Shipper;
    private JButton btn_Update;
    public JPanel panelAccountManagement;
    private JTextField txt_Email;
    private JTextField txt_Password;
    private JTextField txt_Firstname;
    private JTextField txt_Last;
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

    public AdminAccountsManagementGUI() {
        setContentPane(mainPanel);
        setTitle("Manage Account Form");
        setSize(700, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
