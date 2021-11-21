package GUI.AdminForms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminGUI extends JFrame{

    private JPanel mainPanel;
    private JButton btn_ManageAccount;
    private JButton btn_ManageAssignment;
    private JButton btn_ManageMenu;
    private JButton btn_ManageRevenue;
    private JPanel menuPanel;

    public AdminGUI() {
        setContentPane(mainPanel);
        setTitle("Login Form");
        setSize(700, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        btn_ManageAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminAccountsManagementGUI accountsManagementGUI = new AdminAccountsManagementGUI();
            }
        });
        btn_ManageAssignment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminAssignmentManagementGUI adminAssignmentManagementGUI = new AdminAssignmentManagementGUI();
            }
        });
        btn_ManageRevenue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminRevenueManagement adminRevenueManagement = new AdminRevenueManagement();
            }
        });
        btn_ManageMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminMenuManagementGUI adminMenuManagementGUI = new AdminMenuManagementGUI();
            }
        });
    }

}
