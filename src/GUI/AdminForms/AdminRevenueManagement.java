package GUI.AdminForms;

import javax.swing.*;

public class AdminRevenueManagement extends JFrame{
    private JTable table1;
    private JPanel mainPanel;

    public AdminRevenueManagement() {
        setContentPane(mainPanel);
        setTitle("Manage Revenue Form");
        setSize(700, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
