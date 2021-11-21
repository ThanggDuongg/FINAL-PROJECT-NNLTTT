package GUI.AdminForms;

import javax.swing.*;

public class AdminMenuManagementGUI extends JFrame{
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTable table_Foods;
    private JButton btn_UpdateFood;
    private JRadioButton radio_BtnYes;
    private JRadioButton radio_BtnNo;
    private JTable table_Menu;
    private JTable table_AllFoods;
    private JTable table_AllBeverages;
    private JPanel pnl_Foods;
    private JTextField txt_NameFood;
    private JTextField txt_Price;
    private JTextField txt_Quantity;
    private JButton btn_InsertFood;
    private JButton btn_DeleteFood;
    private JTable table_Beverage;
    private JTextField txt_NameBeverage;
    private JTextField txt_NameManufaturerBeverage;
    private JTextField txt_QuantityBeverage;
    private JTextField txt_AcoholeByVolBeverage;
    private JTextField txt_PriceBeverage;
    private JButton btn_InsertBeverage;
    private JButton btn_UpdateBeverage;
    private JButton btn_DeleteBeverage;
    private JComboBox cb_DaysInWeek;
    private JButton btn_RemoveFromMenu;
    private JButton btn_InsertFoodToMenu;
    private JButton btn_InsertBeverageToMenu;

    public AdminMenuManagementGUI() {
        setContentPane(mainPanel);
        setTitle("Manage Menu Form");
        setSize(700, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
