package GUI.AdminForms;

import javax.swing.*;

public class AdminAssignmentManagementGUI extends JFrame{
    private JTable table1;
    private JPanel mainPanel;

    public AdminAssignmentManagementGUI() {
        setContentPane(mainPanel);
        setTitle("Manage Assignment Form");
        setSize(700, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
