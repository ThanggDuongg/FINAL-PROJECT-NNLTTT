package GUI.AdminForms;

import GUI.LoginGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminGUI extends JFrame{

    private JPanel mainPanel;
    private JButton btn_ManageAccount;
    private JButton btn_ManageAssignment;
    private JButton btn_ManageMenu;
    private JButton btn_ManageRevenue;
    private JButton logout_BT;

    public AdminGUI() {
        setContentPane(mainPanel);
        setTitle("Login Form");
        setSize(700, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        btn_ManageAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminAccountsManagementGUI accountsManagementGUI = new AdminAccountsManagementGUI();
                dispose();
            }
        });
        btn_ManageAssignment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminAssignmentManagementGUI adminAssignmentManagementGUI = new AdminAssignmentManagementGUI();
                dispose();
            }
        });
        btn_ManageRevenue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminRevenueManagement adminRevenueManagement = new AdminRevenueManagement();
                dispose();
            }
        });
        btn_ManageMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminMenuManagementGUI adminMenuManagementGUI = new AdminMenuManagementGUI();
                dispose();
            }
        });
        logout_BT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LoginGUI loginGUI = new LoginGUI();
                    dispose();
                }
                catch(Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        AdminGUI adminGUI = new AdminGUI();
    }
}
