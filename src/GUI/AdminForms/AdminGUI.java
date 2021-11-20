package GUI.AdminForms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminGUI extends JFrame{

    private JPanel mainPanel;
    private JButton btnManageAccount;
    private JButton btnManageAssignment;
    private JButton btnManageMenu;
    private JButton btnManageRevenue;
    private JPanel menuPanel;

    public AdminGUI() {
        setContentPane(mainPanel);
        setTitle("Login Form");
        setSize(700, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        btnManageAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminAccountsManagementGUI accountsManagementGUI = new AdminAccountsManagementGUI();
            }
        });
        btnManageAssignment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminAssignmentManagementGUI adminAssignmentManagementGUI = new AdminAssignmentManagementGUI();
            }
        });
        btnManageRevenue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminRevenueManagement adminRevenueManagement = new AdminRevenueManagement();
            }
        });
        btnManageMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminMenuManagementGUI adminMenuManagementGUI = new AdminMenuManagementGUI();
            }
        });
    }

}
