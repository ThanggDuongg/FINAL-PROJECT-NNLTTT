package GUI.AdminForms;

import javax.swing.*;

public class AdminAssignmentManagementGUI extends JFrame{
    private JTable table_OrderNotShipper;
    private JPanel mainPanel;
    private JComboBox cb_IdShipper;
    private JTextField txt_FullnameShipepr;
    private JTextField txt_AllOrderInDay;
    private JButton btn_Assignment;

    public AdminAssignmentManagementGUI() {
        setContentPane(mainPanel);
        setTitle("Manage Assignment Form");
        setSize(700, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
