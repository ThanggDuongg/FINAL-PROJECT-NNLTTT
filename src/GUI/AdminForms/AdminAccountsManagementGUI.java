package GUI.AdminForms;

import javax.swing.*;

public class AdminAccountsManagementGUI extends JFrame{
    public JTabbedPane tabbedPane1;
    public JPanel mainPanel;
    private JRadioButton customerRadioButton;
    private JRadioButton administratorRadioButton;
    private JRadioButton shipperRadioButton;
    private JButton updateButton;
    private JTabbedPane tabbedPane2;
    private JTable table1;
    private JTable table2;
    private JButton updateButton1;
    public JPanel panelAccountManagement;

    public AdminAccountsManagementGUI() {
        setContentPane(mainPanel);
        setTitle("Manage Account Form");
        setSize(700, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
