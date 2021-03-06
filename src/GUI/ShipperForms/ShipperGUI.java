package GUI.ShipperForms;

import BUS.*;
import BUS.ShipperBUS;
import DTO.*;
import GUI.LoginGUI;
import Globals.Globals;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;

public class ShipperGUI extends JFrame{
    private CustomerBUS customerBUS = new CustomerBUS();
    private FoodBUS foodBUS = new FoodBUS();
    private BeverageBUS beverageBUS = new BeverageBUS();
    private OrderBUS orderBUS = new OrderBUS();
    private StatusOfShipperBUS statusOfShipperBUS = new StatusOfShipperBUS();
    private JTabbedPane tabShipper;
    private JPanel mainPanel;
    private JButton btn_Update;
    private JTable table_Orders;
    private JTextField txt_Email;
    private JTextField txt_Password;
    private JTextField txt_Firstname;
    private JTextField txt_Lastname;
    private JTextField txt_Phone;
    private JSpinner sp_Age;
    private JComboBox cb_Gender;
    private JTextField txt_Salary;
    private JPanel pnl_MyAccount;
    private JPanel pnl_Orders;
    private JTextField txt_FullnameCustomer;
    private JTextField txt_AddressCustomer;
    private JTextField txt_PhoneCustomer;
    private JTextField txt_TransportFee;
    private JTextField txt_Total;
    private JTextField txt_DistanceCustomer;
    private JButton btn_Cancel;
    private JButton bnt_Delivered;
    private JButton logout_BT;
    private JTable table_OrderHistory;
    private JTextArea text_ReviewHistory;
    private JTextField txt_DateOderHistory;
    private JSpinner sp_RatingHistory;
    private JTextField txt_CustomerHistory;
    private JTextField txt_TotalHistory;

    public void loadOrderHistory(){
        DefaultTableModel model = (DefaultTableModel) table_OrderHistory.getModel();
        model.setRowCount(0);
        for (OrderDTO item: OrderBUS.getAllByIdShipperHistory()) {
            Integer Id = item.getId();
            Timestamp dateOrder = item.getDateOrder();
            int quantity = item.getQuantity();

            //boolean status = item.isStatus();
            CustomerDTO customerDTO = this.customerBUS.findById(item.getIdCustomer());
            //String nameCustomer = customerDTO.getFirstname() + " " + customerDTO.getLastname();
            double distance = customerDTO.getDistance();
            float total = item.getTotal();
            Object[] data = {Id, dateOrder, quantity, total, item.getIdCustomer(), distance};
            model.addRow(data);
        }
    }

    public void loadOrderWithStatusFalse() {
        DefaultTableModel model = (DefaultTableModel) table_Orders.getModel();
        model.setRowCount(0);
        for (OrderDTO item: OrderBUS.getAllOrderOfShipper_StatusFalse(Globals.getGlobalShipperId())) {
            Integer Id = item.getId();
            Timestamp dateOrder = item.getDateOrder();
            int quantity = item.getQuantity();

            //boolean status = item.isStatus();
            CustomerDTO customerDTO = this.customerBUS.findById(item.getIdCustomer());
            //String nameCustomer = customerDTO.getFirstname() + " " + customerDTO.getLastname();
            double distance = customerDTO.getDistance();
            float total = item.getTotal();
            Object[] data = {Id, dateOrder, quantity, total, item.getIdCustomer(), distance};
            model.addRow(data);
        }
    }

    public void createTable() {
        table_Orders.getTableHeader().setFont(new Font("Arial", 2, 14));
        table_Orders.setModel(new DefaultTableModel(null, new String[]{"Id", "Date Order", "Quantity", "Total Price", "Id Customer", "Distance"}));

        table_OrderHistory.getTableHeader().setFont(new Font("Arial", 2, 14));
        table_OrderHistory.setModel(new DefaultTableModel(null, new String[]{"Id", "Date Order", "Quantity", "Total Price", "Id Customer", "Distance"}));
    }

    public ShipperGUI() {
        createTable();
        setContentPane(mainPanel);
        setTitle("Shipper Form");
        setMinimumSize(new Dimension(765, 400));
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
                Double salary = Double.parseDouble(txt_Salary.getText().trim());
                String password = txt_Password.getText().trim();
                ShipperBUS shipperBUS = new ShipperBUS();
                if (shipperBUS.updateAccount(Globals.getGlobalShipperId(), firstname, lastname, phone, gender, age, salary, email, password) == 1) {
                    //update successfully
                }
                else {
                    //message
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                ShipperBUS shipperBUS = new ShipperBUS();
                ShipperDTO shipperDTO = shipperBUS.findById(Globals.getGlobalShipperId());
                txt_Email.setText(shipperDTO.getEmail().trim());
                txt_Firstname.setText(shipperDTO.getFirstname().trim());
                txt_Lastname.setText(shipperDTO.getLastname().trim());
                txt_Phone.setText(shipperDTO.getPhone().trim());
                cb_Gender.setSelectedIndex(BUS.indexGender(shipperDTO.getGender().trim()));
                sp_Age.setValue(shipperDTO.getAge());
                txt_Salary.setText(String.valueOf(shipperDTO.tinhLuong()).trim());
                txt_Password.setText(shipperDTO.getPassword().trim());

                //loadOrderWithStatusFalse();
            }
        });
        table_Orders.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = table_Orders.getSelectedRow();
                index = index == -1 ? 0 : index;
                TableModel tableModel = table_Orders.getModel();
                Integer IdOrder = Integer.parseInt(tableModel.getValueAt(index, 0).toString().trim());
                Integer IdCustomer = Integer.parseInt(tableModel.getValueAt(index, 4).toString().trim());
                String total = tableModel.getValueAt(index, 3).toString().trim();
                CustomerDTO customerDTO = customerBUS.findById(IdCustomer);
                String fullnameCustomer = customerDTO.getFirstname() + " " + customerDTO.getLastname();
                txt_FullnameCustomer.setText(fullnameCustomer);
                txt_AddressCustomer.setText(customerDTO.getAddress());
                txt_PhoneCustomer.setText(customerDTO.getPhone());
                txt_DistanceCustomer.setText(String.valueOf(customerDTO.getDistance()));

                float realCost = orderBUS.realCost(IdOrder);
                float transportFee = Float.parseFloat(total.trim()) - realCost;
                txt_TransportFee.setText(String.valueOf(transportFee));
                txt_Total.setText(total);


            }
        });
        bnt_Delivered.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusOfShipperBUS.changeStatusShipper(new StatusOfShipperDTO(Globals.getGlobalShipperId(), false));
                int index = table_Orders.getSelectedRow();
                index = index == -1 ? 0 : index;
                TableModel tableModel = table_Orders.getModel();
                Integer IdOrder = Integer.parseInt(tableModel.getValueAt(index, 0).toString().trim());
                OrderDTO orderDTO = orderBUS.findById(IdOrder);
                orderDTO.setStatus(true);
                orderBUS.updateShipperForOrder(orderDTO);
                loadOrderWithStatusFalse();
            }
        });
        btn_Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusOfShipperBUS.changeStatusShipper(new StatusOfShipperDTO(Globals.getGlobalShipperId(), false));
                int index = table_Orders.getSelectedRow();
                index = index == -1 ? 0 : index;
                TableModel tableModel = table_Orders.getModel();
                Integer IdOrder = Integer.parseInt(tableModel.getValueAt(index, 0).toString().trim());
                OrderDTO orderDTO = orderBUS.findById(IdOrder);
                orderDTO.setIdShipper(null);
                orderBUS.updateShipperForOrder(orderDTO);
                loadOrderWithStatusFalse();
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
        tabShipper.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (tabShipper.getSelectedIndex() == 1) {
                    loadOrderWithStatusFalse();
                }
                else if (tabShipper.getSelectedIndex() == 2) {
                    loadOrderHistory();
                }
            }
        });
        table_OrderHistory.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = table_OrderHistory.getSelectedRow();
                index = index == -1 ? 0 : index;
                TableModel tableModel = table_OrderHistory.getModel();

                Integer orderId = Integer.parseInt(tableModel.getValueAt(index, 0).toString().trim());
                OrderDTO orderDTO = orderBUS.findById(orderId);
                txt_DateOderHistory.setText(orderDTO.getDateOrder().toString().trim());

                CustomerDTO customerDTO = customerBUS.findById(orderDTO.getIdCustomer());
                String fullnameCustomer = customerDTO.getFirstname() + " " + customerDTO.getLastname();
                txt_CustomerHistory.setText(fullnameCustomer);

                txt_TotalHistory.setText(String.valueOf(orderDTO.getTotal()));

                ReviewDTO reviewDTO = ReviewBUS.getByCustomerAndOrder(orderId, orderDTO.getIdCustomer());
                if (reviewDTO.getId() != null) {
                    text_ReviewHistory.setText(reviewDTO.getComment().trim());
                    sp_RatingHistory.setValue(reviewDTO.getRating());
                }
                else {
                    text_ReviewHistory.setText("No information");
                    sp_RatingHistory.setValue(1);
                }
            }
        });
    }
}
