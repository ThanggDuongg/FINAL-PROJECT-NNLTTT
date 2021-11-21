package GUI.CustomerForms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerGUI extends JFrame{
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JButton btn_Update;
    private JTable table_Menu;
    private JList list_Bill;
    private JTable table_HistoryOrders;
    private JTable table_DetailBill;
    private JSpinner sp_Rating;
    private JTextArea text_Review;
    private JPanel pnl_MyAccount;
    private JPanel pnl_Shopping;
    private JPanel pnl_HistoryOrders;
    private JTextField txt_Email;
    private JTextField txt_Password;
    private JTextField txt_Firstname;
    private JTextField txt_Lastname;
    private JTextField txt_Phone;
    private JTextField txt_Address;
    private JSpinner sp_Age;
    private JComboBox cb_Gender;
    private JButton btn_OrderNow;
    private JTextField txt_DateOrder;
    private JTextField txt_FullnameOfShipper;
    private JTextField txt_TotalBill;
    private JButton btn_SubmitReview;

    public CustomerGUI() {
        setContentPane(mainPanel);
        setTitle("Customer Form");
        setSize(700, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        btn_Update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
