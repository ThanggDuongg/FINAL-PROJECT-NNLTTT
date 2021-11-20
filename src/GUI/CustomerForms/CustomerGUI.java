package GUI.CustomerForms;

import javax.swing.*;

public class CustomerGUI extends JFrame{
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JButton updateButton;
    private JTable table1;
    private JList list1;
    private JTable table2;
    private JTable table3;
    private JSpinner spinner1;
    private JTextArea textArea1;

    public CustomerGUI() {
        setContentPane(mainPanel);
        setTitle("Customer Form");
        setSize(700, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
