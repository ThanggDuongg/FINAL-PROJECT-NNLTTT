package GUI.CustomerForms;

import BUS.BUS;
import BUS.*;
import BUS.MenuFoodBUS;
import BUS.MenuBeverageBUS;
import BUS.CustomerBUS;
import DTO.*;
import GUI.LoginGUI;
import Globals.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class CustomerGUI extends JFrame{
    Calendar calendar = Calendar.getInstance();
    private CustomerBUS customerBUS = new CustomerBUS();
    private int day = calendar.get(Calendar.DAY_OF_WEEK);
    private FoodBUS foodBUS = new FoodBUS();
    private BeverageBUS beverageBUS = new BeverageBUS();
    private MenuFoodBUS menuFoodBUS = new MenuFoodBUS();
    private MenuBeverageBUS menuBeverageBUS = new MenuBeverageBUS();
    private ShipperBUS shipperBUS = new ShipperBUS();
    private OrderBUS orderBUS = new OrderBUS();
    private ReviewBUS reviewBUS = new ReviewBUS();
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JButton btn_Update;
    private JTable table_Menu;
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
    private JTextField txt_DateOrder;
    private JTextField txt_FullnameOfShipper;
    private JTextField txt_TotalBill;
    private JButton btn_SubmitReview;
    private JTextField txt_Distance;
    private JTable table_Order;
    private JButton btn_singleRight;
    private JButton btn_singleLeft;
    private JTextField txt_Total;
    private JButton btn_orderNow;
    private JTable table_AllReview;
    private JTextField txt_CustomerReview;
    private JTextField txt_ShipperReview;
    private JSpinner sp_RatingReview;
    private JTextArea text_ReviewReview;
    private JButton logout_BT;

    public void loadAllReviewOrder() {
        DefaultTableModel model = (DefaultTableModel) table_AllReview.getModel();
        model.setRowCount(0);
        for (ReviewDTO item:reviewBUS.getAll()) {
            Integer IdOrder = item.getIdOrder();
            OrderDTO orderDTO = orderBUS.findById(IdOrder);
            Timestamp dateOrder = orderDTO.getDateOrder();
            Integer IdCustomer = item.getIdCustomer();
            int rating = item.getRating();
            Object[] data = {IdOrder, dateOrder, rating};
            model.addRow(data);
        }
    }

    public void resetReview() {
        sp_Rating.setValue(1);
        text_Review.setText("No information");
    }

    public void loadDetailOrder(Integer IdOrder) {
        DefaultTableModel model = (DefaultTableModel) table_DetailBill.getModel();
        model.setRowCount(0);
        for (DetailFoodOrderDTO detailFoodOrderDTO:DetailFoodOrderBUS.getAllDetailByOrderId(IdOrder)) {
            Integer Id = detailFoodOrderDTO.getIdProduct();
            FoodDTO foodDTO = this.foodBUS.findById(Id);
            String Name = foodDTO.getName();
            int quantity = detailFoodOrderDTO.getQuantity();
            float price = foodDTO.getPrice() * quantity;
            String Tag = "Food";

            Object[] data = {Id, Name, quantity, price, Tag};
            model.addRow(data);
        }

        for (DetailBeverageOrderDTO detailBeverageOrderDTO:DetailBeverageOrderBUS.getAllDetailByOrderId(IdOrder)) {
            Integer Id = detailBeverageOrderDTO.getIdProduct();
            BeverageDTO beverageDTO = this.beverageBUS.findById(Id);
            String Name = beverageDTO.getName();
            int quantity = detailBeverageOrderDTO.getQuantity();
            float price = beverageDTO.getPrice() * quantity;
            String Tag = "Beverage";

            Object[] data = {Id, Name, quantity, price, Tag};
            model.addRow(data);
        }
    }

    public void loadHistoryOrder() {
        DefaultTableModel model = (DefaultTableModel) table_HistoryOrders.getModel();
        model.setRowCount(0);
        for (OrderDTO item:OrderBUS.getAllOrderByIdCustomer()) {
            Integer Id = item.getId();
            Timestamp dateOrder = item.getDateOrder();
            int quantity = item.getQuantity();
            float total = item.getTotal();
            boolean status = item.isStatus();
            Object[] data = {Id, dateOrder, quantity, total, status};
            model.addRow(data);
        }
    }

    public void loadTotal() {
        this.txt_Total.setText("0 VND");
        if (table_Order.getRowCount() > 0) {
            float total = 0;
            for (int i = 0; i < table_Order.getRowCount(); i++) {
                TableModel tableModel = table_Order.getModel();
                total += Float.parseFloat(tableModel.getValueAt(i, 3).toString().trim());
            }
            String totalString = String.valueOf(total);
            this.txt_Total.setText(totalString);
        }
    }

    public void loadOrder() {
        DefaultTableModel model = (DefaultTableModel) table_Order.getModel();
        model.setRowCount(0);
        List<CartDTO> cartDTOList = Cart.getOrdersList();
        for (CartDTO cartDTO:cartDTOList) {
            Integer Id;
            String Name = "";
            int Quantity;
            Float Price;
            if (cartDTO.getTag().equals("Food")) {
                FoodDTO foodDTO = this.foodBUS.findById(cartDTO.getProductId());
                Id = foodDTO.getId();
                Name = foodDTO.getName();
                Quantity = cartDTO.getQuantity();
                Price = foodDTO.getPrice() * Quantity;
            }
            else {
                BeverageDTO beverageDTO = this.beverageBUS.findById(cartDTO.getProductId());
                Id = beverageDTO.getId();
                Name = beverageDTO.getName();
                Quantity = cartDTO.getQuantity();
                Price = beverageDTO.getPrice() * Quantity;
            }
            Object[] data = {Id, Name, Quantity, Price, cartDTO.getTag()};
            model.addRow(data);
        }
    }

    public void loadMenuByDaysInWeek(int daysinweek) {
        DefaultTableModel model = (DefaultTableModel) table_Menu.getModel();
        model.setRowCount(0);
        List<MenuFoodDTO> menuFoodDTOList = MenuFoodBUS.getMenuFoodByDaysInWeek(daysinweek);
        for (MenuFoodDTO item : menuFoodDTOList) {
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

    private void createTable() {
        table_Menu.getTableHeader().setFont(new Font("Arial", 2, 14));
        table_Menu.setModel(new DefaultTableModel(null, new String[]{"Id", "Name", "Quantity", "Price", "Tag"}));

        table_Order.getTableHeader().setFont(new Font("Arial", 2, 14));
        table_Order.setModel(new DefaultTableModel(null, new String[]{"Id", "Name", "Quantity", "Price", "Tag"}));

        table_HistoryOrders.getTableHeader().setFont(new Font("Arial", 2, 14));
        table_HistoryOrders.setModel(new DefaultTableModel(null, new String[]{"Id", "Date Order", "Quantity", "Total Price", "Status"}));

        table_DetailBill.getTableHeader().setFont(new Font("Arial", 2, 14));
        table_DetailBill.setModel(new DefaultTableModel(null, new String[]{"Id", "Name", "Quantity", "Price", "Tag"}));

        table_AllReview.getTableHeader().setFont(new Font("Arial", 2, 14));
        table_AllReview.setModel(new DefaultTableModel(null, new String[]{"Id Order", "Date Order", "Rating"}));
    }

    private void createLimitSpinner() {
        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(1.0, 0.0, 5.0, 1.0);
        sp_Rating.setModel(spinnerNumberModel);
    }

    private void setTextArea() {
        text_ReviewReview.setMaximumSize(new Dimension(120, 200));
        text_ReviewReview.setMinimumSize(new Dimension(120, 200));
    }

    public CustomerGUI() {
        createTable();
        setTextArea();
        createLimitSpinner();
        setContentPane(mainPanel);
        setTitle("Customer Form");
        setMinimumSize(new Dimension(765, 430));
        setMaximumSize(new Dimension(765, 430));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        btn_Update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txt_Email.getText().trim();
                String firstname = txt_Firstname.getText().trim();
                String lastname = txt_Lastname.getText().trim();
                String phone = txt_Phone.getText().trim();
                String gender = String.valueOf(cb_Gender.getSelectedItem());
                int age = (Integer) sp_Age.getValue();
                String address = txt_Address.getText().trim();
                Double distance = Double.parseDouble(txt_Distance.getText().trim());
                String password = txt_Password.getText().trim();

                CustomerBUS customerBUS = new CustomerBUS();
                customerBUS.updateAccount(Globals.getGlobalCustomerId(), firstname, lastname, phone, gender, age, address,email,password, distance);
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                CustomerBUS customerBUS = new CustomerBUS();
                CustomerDTO customerDTO = customerBUS.findById(Globals.getGlobalCustomerId());
                txt_Email.setText(customerDTO.getEmail().trim());
                txt_Firstname.setText(customerDTO.getFirstname().trim());
                txt_Lastname.setText(customerDTO.getLastname().trim());
                txt_Phone.setText(customerDTO.getPhone().trim());
                cb_Gender.setSelectedIndex(BUS.indexGender(customerDTO.getGender().trim()));
                txt_Address.setText(customerDTO.getAddress().trim());
                txt_Distance.setText(String.valueOf(customerDTO.getDistance()).trim());
                sp_Age.setValue(customerDTO.getAge());
                txt_Password.setText(customerDTO.getPassword().trim());

                loadMenuByDaysInWeek(day);
                loadTotal();
                loadHistoryOrder();
                loadAllReviewOrder();
            }
        });
        btn_singleRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if (table_Menu.getRowCount() > 0) {
                   int index = table_Menu.getSelectedRow();
                   index = index == -1 ? 0 : index;
                   TableModel tableModel = table_Menu.getModel();
                   Integer productId = Integer.parseInt(tableModel.getValueAt(index, 0).toString().trim());

                   String tag = tableModel.getValueAt(index, 4).toString().trim();
                   int quantityInMenu = Integer.parseInt(tableModel.getValueAt(index, 2).toString().trim());
                   if (quantityInMenu > 0) {
                       if (tag.equals("Food")) {
                           int quantity = MenuFoodBUS.getByDaysAndId(productId, day).getQuantity() - 1;
                           if (menuFoodBUS.update(productId, day, quantity)) {
                               CartBUS.addCart(productId, tag);
                               loadMenuByDaysInWeek(day);
                               loadOrder();
                           }
                       }
                       else {
                           BeverageDTO beverageDTO = beverageBUS.findById(productId);
                           CustomerDTO customerDTO = customerBUS.findById(Globals.getGlobalCustomerId());

                           if (customerDTO.getAge() >= 18) {
                               int quantity = MenuBeverageBUS.getByDaysAndId(productId, day).getQuantity() - 1;
                               if (menuBeverageBUS.update(productId, day, quantity)) {
                                   CartBUS.addCart(productId, tag);
                                   loadMenuByDaysInWeek(day);
                                   loadOrder();
                               }
                           }
                           else {
                               if (beverageDTO.getAcoholeByVolume() > 0) {
                                   JOptionPane.showMessageDialog(mainPanel, "You aren't old enough to buy acohol drink");
                               }
                               else {
                                   int quantity = MenuBeverageBUS.getByDaysAndId(productId, day).getQuantity() - 1;
                                   if (menuBeverageBUS.update(productId, day, quantity)) {
                                       CartBUS.addCart(productId, tag);
                                       loadMenuByDaysInWeek(day);
                                       loadOrder();
                                   }
                               }
                           }
                       }
                   }
                   loadTotal();
               }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                CartBUS cartBUS = new CartBUS();
                cartBUS.returnMenu(day);
            }
        });
        btn_singleLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if (table_Order.getRowCount() > 0) {
                   int index = table_Order.getSelectedRow();
                   index = index == -1 ? 0 : index;
                   TableModel tableModel = table_Order.getModel();
                   Integer productId = Integer.parseInt(tableModel.getValueAt(index, 0).toString().trim());
                   String tag = tableModel.getValueAt(index, 4).toString().trim();
                   int quantityInOrder = Integer.parseInt(tableModel.getValueAt(index, 2).toString().trim());
                   if (quantityInOrder > 0) {
                       if (tag.equals("Food")) {
                           int quantity = MenuFoodBUS.getByDaysAndId(productId, day).getQuantity() +  quantityInOrder;
                           if (menuFoodBUS.update(productId, day, quantity)) {
                               CartBUS.removeCart(productId);
                               loadMenuByDaysInWeek(day);
                               loadOrder();
                           }
                       }
                       else {
                           int quantity = MenuBeverageBUS.getByDaysAndId(productId, day).getQuantity() +  quantityInOrder;
                           if (menuBeverageBUS.update(productId, day, quantity)) {
                               CartBUS.removeCart(productId);
                               loadMenuByDaysInWeek(day);
                               loadOrder();
                           }
                       }
                   }
                   loadTotal();
               }
            }
        });
        btn_orderNow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table_Order.getRowCount() > 0) {
                    int quantityOrders = table_Order.getRowCount();
                    int columns = table_Order.getColumnCount();
                    float total = Float.parseFloat(txt_Total.getText().trim());
                    List<OrderFakeDTO> orderFakeDTOList = new ArrayList<>();

                    for(int row = 0; row < quantityOrders; row++) {
                        OrderFakeDTO orderFakeDTO = new OrderFakeDTO();
                        for(int column = 0; column < columns; column++) {
                            if (column == 0) {
                                orderFakeDTO.setProductId(Integer.parseInt(table_Order.getValueAt(row, column).toString().trim()));
                            }
                            else if (column == 2) {
                                orderFakeDTO.setQuantity(Integer.parseInt(table_Order.getValueAt(row, column).toString().trim()));
                            }
                            else if (column == 4) {
                                orderFakeDTO.setTag(table_Order.getValueAt(row, column).toString().trim());
                            }
                        }
                        orderFakeDTOList.add(orderFakeDTO);
                    }
                    CustomerDTO customerDTO = customerBUS.findById(Globals.getGlobalCustomerId());
                    total = total * customerDTO.getDistance().floatValue();
                    orderBUS.insert(orderFakeDTOList, total);
                    Cart.getOrdersList().clear();
                    loadHistoryOrder();
                    loadOrder();
                }
            }
        });
        table_HistoryOrders.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            resetReview();
            int index = table_HistoryOrders.getSelectedRow();
            TableModel tableModel = table_HistoryOrders.getModel();
            Integer orderId = Integer.parseInt(tableModel.getValueAt(index, 0).toString().trim());
            Timestamp dateOrder = Timestamp.valueOf(tableModel.getValueAt(index, 1).toString().trim());
            txt_DateOrder.setText(new  SimpleDateFormat("yyyy - MM - dd").format(dateOrder));
            txt_TotalBill.setText(tableModel.getValueAt(index, 3).toString().trim());
            OrderDTO orderDTO = orderBUS.findById(orderId);
            ShipperDTO shipperDTO = null;
            if (orderDTO.getIdShipper() != 0) {
                shipperDTO = shipperBUS.findById(orderDTO.getIdShipper());
            }
//                String fullnameShipper = shipperDTO != null ? shipperDTO.getFirstname() + " " + shipperDTO.getLastname() : "No information";
            String fullnameShipper = "No information";
            if (shipperDTO != null) {
                fullnameShipper = shipperDTO.getFirstname() + " " + shipperDTO.getLastname();
            }
            txt_FullnameOfShipper.setText(fullnameShipper);
            loadDetailOrder(orderId);

            ReviewDTO reviewDTO = ReviewBUS.getByCustomerAndOrder(orderId, Globals.getGlobalCustomerId());
            System.out.println(reviewDTO.getId());
            boolean status = Boolean.valueOf(tableModel.getValueAt(index, 4).toString().trim());

            if (reviewDTO.getId() != null) {
                sp_Rating.setValue(reviewDTO.getRating());
                text_Review.setText(reviewDTO.getComment().trim());
                btn_SubmitReview.setEnabled(false);
            }
            else {
                if (status == false) {
                    btn_SubmitReview.setEnabled(false);
                }
                else {
                    btn_SubmitReview.setEnabled(true);
                }
            }
            }
        });
        btn_SubmitReview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = table_HistoryOrders.getSelectedRow();
                TableModel tableModel = table_HistoryOrders.getModel();
                Integer orderId = Integer.parseInt(tableModel.getValueAt(index, 0).toString().trim());
                float rating_Float = Float.parseFloat(sp_Rating.getValue().toString().trim());
                int rating = (int) rating_Float;
                String comment = text_Review.getText().toString().trim();
                if (reviewBUS.insert(new ReviewDTO(rating, comment, Globals.getGlobalCustomerId(), orderId))) {

                }
                else {

                }
            }
        });
        table_AllReview.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sp_RatingReview.setValue(0);
                text_ReviewReview.setText("No information");
                txt_ShipperReview.setText("No information");
                txt_CustomerReview.setText("No information");
                if (table_AllReview.getRowCount() != 0) {
                    int index = table_AllReview.getSelectedRow();
                    index = index == -1 ? 0 : index;
                    TableModel tableModel = table_AllReview.getModel();

                    Integer orderId = Integer.parseInt(tableModel.getValueAt(index, 0).toString().trim());
                    OrderDTO orderDTO = orderBUS.findById(orderId);
                    CustomerDTO customerDTO = customerBUS.findById(orderDTO.getIdCustomer());
                    String cusFullname = customerDTO.getFirstname() + " " + customerDTO.getLastname();
                    txt_CustomerReview.setText(cusFullname);

                    ShipperDTO shipperDTO = shipperBUS.findById(orderDTO.getIdShipper());
                    if (shipperDTO.getID() != null) {
                        String shipFullname = shipperDTO.getFirstname() + " " + shipperDTO.getLastname();
                        txt_ShipperReview.setText(shipFullname);
                    }

                    ReviewDTO reviewDTO = ReviewBUS.getByCustomerAndOrder(orderId, orderDTO.getIdCustomer());
                    if (reviewDTO.getId() != null) {
                        sp_RatingReview.setValue(reviewDTO.getRating());
                        text_ReviewReview.setText(reviewDTO.getComment().trim());
                    }
                }
            }
        });
        logout_BT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LoginGUI loginGUI = new LoginGUI();
                    dispose();
                }
                catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
    }
}
