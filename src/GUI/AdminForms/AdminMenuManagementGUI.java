package GUI.AdminForms;

import BUS.FoodBUS;
import BUS.BeverageBUS;
import BUS.MenuBeverageBUS;
import BUS.MenuFoodBUS;
import DTO.BeverageDTO;
import DTO.FoodDTO;
import DTO.MenuBeverageDTO;
import DTO.MenuFoodDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class AdminMenuManagementGUI extends JFrame{
    private FoodBUS foodBUS = new FoodBUS();
    private MenuBeverageBUS menuBeverageBUS = new MenuBeverageBUS();
    private BeverageBUS beverageBUS = new BeverageBUS();
    private MenuFoodBUS menuFoodBUS = new MenuFoodBUS();
    private Integer IdGlobal;
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
    private JTextField txt_PriceFood;
    private JTextField txt_QuantityFood;
    private JButton btn_InsertFood;
    private JButton btn_DeleteFood;
    private JTable table_Beverages;
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
    private JButton btn_ResetFoods;
    private JButton btn_ResetBeverage;

    public void loadMenuByDaysInWeek(int daysinweek) {
        DefaultTableModel model = (DefaultTableModel) table_Menu.getModel();
        model.setRowCount(0);
        List<MenuFoodDTO> menuFoodDTOList = MenuFoodBUS.getMenuFoodByDaysInWeek(daysinweek);
        for (MenuFoodDTO item: menuFoodDTOList) {
            FoodDTO foodDTO = this.foodBUS.findById(item.getId_product());
            Integer Id = foodDTO.getId();
            String Name = foodDTO.getName();
            int Quantity = item.getQuantity();
            Float Price = foodDTO.getPrice();
            String Tag = "Food";

            Object[] data = {Id, Name, Quantity, Price, Tag};
            model.addRow(data);
        }

        List<MenuBeverageDTO> menuBeverageDTOList = MenuBeverageBUS.getMenuBeverageByDaysInWeek(daysinweek);
        for (MenuBeverageDTO item:menuBeverageDTOList) {
            BeverageDTO beverageDTO = this.beverageBUS.findById(item.getId_product());
            Integer Id = beverageDTO.getId();
            String Name = beverageDTO.getName();
            int Quantity = item.getQuantity();
            Float Price = beverageDTO.getPrice();
            String Tag = "Beverage";

            Object[] data = {Id, Name, Quantity, Price, Tag};
            model.addRow(data);
        }
    }

    public void loadDataAllBeverages() {
        DefaultTableModel model = (DefaultTableModel) table_AllBeverages.getModel();
        model.setRowCount(0);
        List<BeverageDTO> beverageDTOList = this.beverageBUS.beverageDTOList();
        for (BeverageDTO item:beverageDTOList) {
            Integer Id = item.getId();
            String Name = item.getName();
            String Manufacturer = item.getManufacturer();
            int Quantity = item.getQuantity();
            Float Price = item.getPrice();
            Float AcoholeByVolume = item.getAcoholeByVolume();

            Object[] data = {Id, Name, Manufacturer, Quantity, Price, AcoholeByVolume};
            model.addRow(data);
        }
    }

    public void loadDataAllFoods() {
        DefaultTableModel model = (DefaultTableModel) table_AllFoods.getModel();
        model.setRowCount(0);
        List<FoodDTO> foodDTOList = this.foodBUS.foodDTOList();
        for (FoodDTO item:foodDTOList) {
            Integer Id = item.getId();
            String Name = item.getName();
            Float Price = item.getPrice();
            int Quantity = item.getQuantity();

            Object[] data = {Id, Name, Price, Quantity};
            model.addRow(data);
        }
    }

    public void loadDataFoods() {
        DefaultTableModel model = (DefaultTableModel) table_Foods.getModel();
        model.setRowCount(0);
        List<FoodDTO> foodDTOList = this.foodBUS.foodDTOList();
        for (FoodDTO item:foodDTOList) {
            Integer Id = item.getId();
            String Name = item.getName();
            Float Price = item.getPrice();
            int Quantity = item.getQuantity();

            Object[] data = {Id, Name, Price, Quantity};
            model.addRow(data);
        }
    }

    public void loadDataBeverages() {
        DefaultTableModel model = (DefaultTableModel) table_Beverages.getModel();
        model.setRowCount(0);
        List<BeverageDTO> beverageDTOList = this.beverageBUS.beverageDTOList();
        for (BeverageDTO item:beverageDTOList) {
            Integer Id = item.getId();
            String Name = item.getName();
            String Manufacturer = item.getManufacturer();
            int Quantity = item.getQuantity();
            Float Price = item.getPrice();
            Float AcoholeByVolume = item.getAcoholeByVolume();

            Object[] data = {Id, Name, Manufacturer, Quantity, Price, AcoholeByVolume};
            model.addRow(data);
        }
    }

    private void createTable() {
        table_Foods.getTableHeader().setFont(new Font("Arial", 2, 14));
        table_Foods.setModel(new DefaultTableModel(null, new String[]{"Id", "Name", "Price", "Quantity"}));

        table_Beverages.getTableHeader().setFont(new Font("Arial", 2, 14));
        table_Beverages.setModel(new DefaultTableModel(null, new String[]{"Id", "Name", "Manufacturer", "Quantity", "Price", "ABV"}));

        table_AllFoods.getTableHeader().setFont(new Font("Arial", 2, 14));
        table_AllFoods.setModel(new DefaultTableModel(null, new String[]{"Id", "Name", "Price", "Quantity"}));

        table_AllBeverages.getTableHeader().setFont(new Font("Arial", 2, 14));
        table_AllBeverages.setModel(new DefaultTableModel(null, new String[]{"Id", "Name", "Manufacturer", "Quantity", "Price", "ABV"}));

        table_Menu.getTableHeader().setFont(new Font("Arial", 2, 14));
        table_Menu.setModel(new DefaultTableModel(null, new String[]{"Id", "Name", "Quantity", "Price", "Tag"}));
    }

    public AdminMenuManagementGUI() {
        createTable();
        setContentPane(mainPanel);
        setTitle("Manage Menu Form");
        setSize(700, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                loadDataBeverages();
                loadMenuByDaysInWeek(1);
            }
        });
        tabbedPane2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadDataBeverages();
            }
        });
        table_Beverages.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = table_Beverages.getSelectedRow();
                TableModel tableModel = table_Beverages.getModel();
                BeverageDTO beverageDTO = beverageBUS.findById(Integer.parseInt(tableModel.getValueAt(index, 0).toString().trim()));
                IdGlobal = Integer.parseInt(tableModel.getValueAt(index, 0).toString().trim());
                txt_NameBeverage.setText(beverageDTO.getName().trim());
                txt_NameManufaturerBeverage.setText(beverageDTO.getManufacturer().trim());
                txt_QuantityBeverage.setText(String.valueOf(beverageDTO.getQuantity()));
                txt_PriceBeverage.setText(String.valueOf(beverageDTO.getPrice()));
                float ABV = beverageDTO.getAcoholeByVolume();
                if (ABV != 0) {
                    radio_BtnYes.setSelected(true);
                }
                else {
                    radio_BtnNo.setSelected(true);
                }
                txt_AcoholeByVolBeverage.setText(String.valueOf(beverageDTO.getAcoholeByVolume()));
            }
        });
        table_Foods.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = table_Foods.getSelectedRow();
                TableModel tableModel = table_Foods.getModel();
                FoodDTO foodDTO = foodBUS.findById(Integer.parseInt(tableModel.getValueAt(index, 0).toString().trim()));
                IdGlobal = Integer.parseInt(tableModel.getValueAt(index, 0).toString().trim());
                txt_NameFood.setText(foodDTO.getName().trim());
                txt_PriceFood.setText(String.valueOf(foodDTO.getPrice()).trim());
                txt_QuantityFood.setText(String.valueOf(foodDTO.getQuantity()).trim());
            }
        });
        tabbedPane2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadDataFoods();
            }
        });
        btn_ResetBeverage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = "";
                txt_NameBeverage.setText(temp);
                txt_NameManufaturerBeverage.setText(temp);
                txt_QuantityBeverage.setText("1");
                txt_PriceBeverage.setText(temp);
                radio_BtnNo.setSelected(true);
                txt_AcoholeByVolBeverage.setText("0");
            }
        });
        btn_ResetFoods.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = "";
                txt_NameFood.setText(temp);
                txt_PriceFood.setText(temp);
                txt_QuantityFood.setText("1");
            }
        });
        btn_InsertFood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txt_NameFood.getText().trim();
                float price = Float.parseFloat(txt_PriceFood.getText().trim());
                int quantity = Integer.parseInt(txt_QuantityFood.getText().trim());
                if (foodBUS.insert(name, price, quantity) == 1) {
                    //sc
                    loadDataFoods();
                }
                else {
                    //usc
                }
            }
        });
        btn_InsertBeverage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txt_NameBeverage.getText().trim();
                String manufacturer = txt_NameManufaturerBeverage.getText().trim();
                int quantity = Integer.parseInt(txt_QuantityBeverage.getText().trim());
                float price = Float.parseFloat(txt_PriceBeverage.getText().trim());
                float ABV = Float.parseFloat(txt_AcoholeByVolBeverage.getText().trim());

                if (beverageBUS.insert(name, manufacturer, quantity, price, ABV) == 1) {
                    //sc
                    loadDataBeverages();
                }
                else {
                    //usc
                }
            }
        });
        btn_UpdateBeverage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txt_NameBeverage.getText().trim();
                String manufacturer = txt_NameManufaturerBeverage.getText().trim();
                int quantity = Integer.parseInt(txt_QuantityBeverage.getText().trim());
                float price = Float.parseFloat(txt_PriceBeverage.getText().trim());
                float ABV = Float.parseFloat(txt_AcoholeByVolBeverage.getText().trim());

                if (beverageBUS.update(IdGlobal, name, manufacturer, quantity, price, ABV) == 1) {
                    //sc
                    loadDataBeverages();
                }
                else {
                    //usc
                }
            }
        });
        btn_UpdateFood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txt_NameFood.getText().trim();
                float price = Float.parseFloat(txt_PriceFood.getText().trim());
                int quantity = Integer.parseInt(txt_QuantityFood.getText().trim());

                if (foodBUS.update(IdGlobal, name, price, quantity) == 1) {
                    //sc
                    loadDataFoods();
                }
                else {
                    //usc
                }
            }
        });
        btn_DeleteFood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (foodBUS.delete(IdGlobal)) {
                    //sc
                    loadDataFoods();
                    String temp = "";
                    txt_NameFood.setText(temp);
                    txt_PriceFood.setText(temp);
                    txt_QuantityFood.setText("1");
                }
                else {
                    //sc
                }
            }
        });
        btn_DeleteBeverage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (beverageBUS.delete(IdGlobal)) {
                    //sc
                    loadDataBeverages();
                    String temp = "";
                    txt_NameBeverage.setText(temp);
                    txt_NameManufaturerBeverage.setText(temp);
                    txt_QuantityBeverage.setText("1");
                    txt_PriceBeverage.setText(temp);
                    radio_BtnNo.setSelected(true);
                    txt_AcoholeByVolBeverage.setText("0");
                }
                else {
                    //usc
                }
            }
        });
        tabbedPane1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadDataAllBeverages();
                loadDataAllFoods();
            }
        });
        cb_DaysInWeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexCB = cb_DaysInWeek.getSelectedIndex() + 1;
                loadMenuByDaysInWeek(indexCB);
            }
        });
        btn_InsertFoodToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = table_AllFoods.getSelectedRow();
                TableModel tableModel = table_AllFoods.getModel();
                int daysinweek = cb_DaysInWeek.getSelectedIndex() + 1;
                FoodDTO foodDTO = foodBUS.findById(Integer.parseInt(tableModel.getValueAt(index,0).toString().trim()));
                if (foodDTO.getQuantity() == 0) {
                    //
                }
                else {
                    if (foodBUS.update(foodDTO.getId(), foodDTO.getName(), foodDTO.getPrice(), foodDTO.getQuantity() - 1) == 1) {
                        //check this food existed in menu, days in week
                        menuFoodBUS.handleInsertFormListFoodToMenu(foodDTO.getId(), daysinweek);
                        loadMenuByDaysInWeek(daysinweek);
                        loadDataAllFoods();
                    }
                    else {
                        //
                    }
                }
            }
        });
        btn_InsertBeverageToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = table_AllBeverages.getSelectedRow();
                TableModel tableModel = table_AllBeverages.getModel();
                int daysinweek = cb_DaysInWeek.getSelectedIndex() + 1;
                BeverageDTO beverageDTO = beverageBUS.findById(Integer.parseInt(tableModel.getValueAt(index,0).toString().trim()));
                if (beverageDTO.getQuantity() == 0) {
                    //
                }
                else {
                    if (beverageBUS.update(beverageDTO.getId(), beverageDTO.getName(), beverageDTO.getManufacturer(),
                            beverageDTO.getQuantity() - 1, beverageDTO.getPrice(), beverageDTO.getAcoholeByVolume()) == 1) {
                        menuBeverageBUS.handleInsertFormListFoodToMenu(beverageDTO.getId(), daysinweek);
                        loadDataAllBeverages();
                        loadMenuByDaysInWeek(daysinweek);
                    }
                    else {

                    }
                }
            }
        });
        btn_RemoveFromMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = table_Menu.getSelectedRow();
                TableModel tableModel = table_Menu.getModel();
                int daysinweek = cb_DaysInWeek.getSelectedIndex() + 1;
                String tag = tableModel.getValueAt(index,4).toString().trim();
                int id_product = Integer.parseInt(tableModel.getValueAt(index, 0).toString().trim());
                if (tag.equals("Food")) {
                    MenuFoodBUS.removeFromMenu(id_product, daysinweek);
                }
                else {
                    MenuBeverageBUS.removeFromMenu(id_product, daysinweek);
                }
                loadMenuByDaysInWeek(daysinweek);
            }
        });
    }
}
