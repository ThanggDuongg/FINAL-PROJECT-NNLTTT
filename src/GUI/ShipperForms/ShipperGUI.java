package GUI.ShipperForms;

import javax.swing.*;

public class ShipperGUI extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;
    private JRadioButton customerRadioButton;
    private JRadioButton administratorRadioButton;
    private JRadioButton shipperRadioButton;
    private JButton updateButton;
    private JTable table1;

    public ShipperGUI() {
        setContentPane(mainPanel);
        setTitle("Shipper Form");
        setSize(700, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
