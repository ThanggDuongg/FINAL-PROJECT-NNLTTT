package GUI.AdminForms;

import javax.swing.*;

public class AdminMenuManagementGUI extends JFrame{
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTable table1;
    private JButton updateButton1;
    private JRadioButton yesRadioButton;
    private JRadioButton noRadioButton;
    private JTable table2;
    private JTable table3;
    private JTable table4;

    public AdminMenuManagementGUI() {
        setContentPane(mainPanel);
        setTitle("Manage Menu Form");
        setSize(700, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
