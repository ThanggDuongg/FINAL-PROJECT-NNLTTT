package GUI.AdminForms;

import BUS.CustomerBUS;
import BUS.OrderBUS;
import BUS.ShipperBUS;
import BUS.StatusOfShipperBUS;
import DTO.CustomerDTO;
import DTO.OrderDTO;
import DTO.ShipperDTO;
import DTO.StatusOfShipperDTO;
import GUI.LoginGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;

public class AdminAssignmentManagementGUI extends JFrame{
    private CustomerBUS customerBUS = new CustomerBUS();
    private ShipperBUS shipperBUS = new ShipperBUS();
    private StatusOfShipperBUS statusOfShipperBUS = new StatusOfShipperBUS();
    private OrderBUS orderBUS = new OrderBUS();
    private JTable table_OrderNotShipper;
    private JPanel mainPanel;
    private JComboBox cb_IdShipper;
    private JTextField txt_FullnameShipepr;
    private JTextField txt_AllOrderInDay;
    private JButton btn_Assignment;
    private JButton return_BT;

    private void loadDataCombobox() {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cb_IdShipper.getModel();
        cb_IdShipper.removeAllItems();
        for (StatusOfShipperDTO statusOfShipperDTO:StatusOfShipperBUS.getAllNotStatus()) {
            comboBoxModel.addElement(statusOfShipperDTO.getIdShipper());
        }
        cb_IdShipper.setModel(comboBoxModel);
    }

    private void loadTableOrderNotShipper() {
        DefaultTableModel model = (DefaultTableModel) table_OrderNotShipper.getModel();
        model.setRowCount(0);
        for (OrderDTO item: OrderBUS.getAllOrder_noShipper()) {
            Integer Id = item.getId();
            Timestamp dateOrder = item.getDateOrder();
            int quantity = item.getQuantity();

            //boolean status = item.isStatus();
            CustomerDTO customerDTO = this.customerBUS.findById(item.getIdCustomer());
            String nameCustomer = customerDTO.getFirstname() + " " + customerDTO.getLastname();
            double distance = customerDTO.getDistance();
            float total = item.getTotal();
            Object[] data = {Id, dateOrder, quantity, total, nameCustomer, distance};
            model.addRow(data);
        }
    }

    private void createTable() {
        table_OrderNotShipper.getTableHeader().setFont(new Font("Arial", 2, 14));
        table_OrderNotShipper.setModel(new DefaultTableModel(null, new String[]{"Id", "Date Order", "Quantity", "Total Price", "Name Customer", "Distance"}));
    }

    public AdminAssignmentManagementGUI() {
        createTable();
        setContentPane(mainPanel);
        setTitle("Manage Assignment Form");
        setMinimumSize(new Dimension(930, 490));
        setMaximumSize(new Dimension(930, 490));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                loadDataCombobox();
                loadTableOrderNotShipper();
            }
        });
        cb_IdShipper.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer IdShipper = (Integer) cb_IdShipper.getSelectedItem();
                ShipperDTO shipperDTO = shipperBUS.findById(IdShipper);
                String fullname = shipperDTO.getFirstname() + " " + shipperDTO.getLastname();
                txt_FullnameShipepr.setText(fullname.trim());
                int numOrderOfShipper = OrderBUS.numOfOrdersByShipper_Today(IdShipper);
                txt_AllOrderInDay.setText(String.valueOf(numOrderOfShipper));
            }
        });
        btn_Assignment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = table_OrderNotShipper.getSelectedRow();
                index = index == -1 ? 0 : index;
                TableModel tableModel = table_OrderNotShipper.getModel();
                Integer IdOrder = Integer.parseInt(tableModel.getValueAt(index, 0).toString().trim());
                Integer IdShipper = Integer.parseInt(cb_IdShipper.getSelectedItem().toString().trim());
                StatusOfShipperDTO statusOfShipperDTO = new StatusOfShipperDTO(IdShipper, true);
                OrderDTO orderDTO = orderBUS.findById(IdOrder);
                orderDTO.setIdShipper(IdShipper);
                if (statusOfShipperBUS.changeStatusShipper(statusOfShipperDTO)) {
                    orderBUS.updateShipperForOrder(orderDTO);
                    loadDataCombobox();
                    loadTableOrderNotShipper();
                }
            }
        });
        return_BT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AdminGUI adminGUI = new AdminGUI();
                    dispose();
                }
                catch(Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        AdminAssignmentManagementGUI adminAssignmentManagementGUI = new AdminAssignmentManagementGUI();
    }
}
