package GUI.AdminForms;

import javax.swing.*;

public class AdminRevenueManagement extends JFrame{
    private JTable table_HistoryOrders_Deliverd;
    private JPanel mainPanel;
    private JTextField txt_TotalCost;
    private JButton btn_ExportToPDF;

    public AdminRevenueManagement() {
        setContentPane(mainPanel);
        setTitle("Manage Revenue Form");
        setSize(700, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
