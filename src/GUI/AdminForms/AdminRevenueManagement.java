package GUI.AdminForms;

import BUS.AdministratorBUS;
import BUS.CustomerBUS;
import BUS.OrderBUS;
import BUS.ShipperBUS;
import DTO.AdministratorDTO;
import DTO.CustomerDTO;
import DTO.OrderDTO;
import DTO.ShipperDTO;
import Globals.Globals;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AdminRevenueManagement extends JFrame{
    private AdministratorBUS administratorBUS = new AdministratorBUS();
    private CustomerBUS customerBUS = new CustomerBUS();
    private ShipperBUS shipperBUS = new ShipperBUS();
    private JTable table_HistoryOrders_Deliverd;
    private JPanel mainPanel;
    private JTextField txt_TotalCost;
    private JButton btn_ExportPDF;
    private JComboBox cb_Sort;
    private JPanel pnl_Chart;
    private JButton return_BT;
    private JButton btn_ChangeChart;
    private int state = 2;

    private void loadHistoryOrder_Delivered(int index) {
        DefaultTableModel model = (DefaultTableModel) table_HistoryOrders_Deliverd.getModel();
        model.setRowCount(0);
        for (OrderDTO item: OrderBUS.getAllOrdersWithStatusTrue(index)) {
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
        table_HistoryOrders_Deliverd.getTableHeader().setFont(new Font("Arial", 2, 14));
        table_HistoryOrders_Deliverd.setModel(new DefaultTableModel(null, new String[]{"Id", "Date Order", "Quantity", "Total Price", "Name Customer", "Distance"}));
    }

    private static XYDataset createDataset() {
        double Array[] = new double[200];
        double Array2[] = new double[200];
        List<OrderDTO> orderDTOList = OrderBUS.getAllOrderOfShipper_StatusFalse(0);
        for (int i = 0; i < orderDTOList.size(); i++) {
            Array[i] = orderDTOList.get(i).getTotal();
            Array2[i] = Math.round(orderDTOList.get(i).getTotal());
            System.out.println(Array[i] + "-" + Array2[i]);
        }
        DefaultXYDataset ds = new DefaultXYDataset();

        double[][] data = { Array, Array2 };

        ds.addSeries("series1", data);

        return ds;
    }

    private int dayOfMonth(Timestamp timestamp) {
        LocalDate currentdate = timestamp.toLocalDateTime().toLocalDate();
        return currentdate.getDayOfMonth();
    }

    public void drawChart() {
        List<OrderDTO> orderDTOList = OrderBUS.getAllOrdersWithStatusTrue(0);
        JFreeChart chart = null;
        if (state == 1) {
            XYSeries series = new XYSeries("total price / quantity");
            System.out.println(orderDTOList.size());
            for (int i = 0; i < orderDTOList.size(); i++)
                series.add(orderDTOList.get(i).getQuantity(), orderDTOList.get(i).getTotal());
            XYSeriesCollection dataset = new XYSeriesCollection(series);
            chart = ChartFactory.createXYLineChart("Revenue Line Chart", "Quantity", "Total", dataset, PlotOrientation.HORIZONTAL, true, true, true);
        }
        else if (state == 2) {
            XYSeries series = new XYSeries("total price / day of month");
            System.out.println(orderDTOList.size());
            for (int i = 0; i < orderDTOList.size(); i++)
                series.add(dayOfMonth(orderDTOList.get(i).getDateOrder()), orderDTOList.get(i).getTotal());
            XYSeriesCollection dataset = new XYSeriesCollection(series);
            chart = ChartFactory.createXYLineChart("Revenue Line Chart", "Day of month", "Total", dataset, PlotOrientation.HORIZONTAL, true, true, true);
        }

        ChartPanel chartpanel = new ChartPanel(chart);
        chartpanel.setDomainZoomable(true);

        this.pnl_Chart.setLayout(new BorderLayout());
        this.pnl_Chart.add(chartpanel, BorderLayout.CENTER);
    }

    public AdminRevenueManagement() {
        drawChart();
        createTable();
        setContentPane(mainPanel);
        setTitle("Manage Revenue Form");
        setMinimumSize(new Dimension(800, 430));
        setMaximumSize(new Dimension(800, 430));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                loadHistoryOrder_Delivered(0);

                float total = 0;
                for (OrderDTO item: OrderBUS.getAllOrdersWithStatusTrue(0)) {
                    total += item.getTotal();
                }
                txt_TotalCost.setText(String.valueOf(total));
            }
        });

        cb_Sort.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = cb_Sort.getSelectedIndex();
                loadHistoryOrder_Delivered(index);
            }
        });
//        btn_ExportExcel.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
//                    XSSFSheet xssfSheet = xssfWorkbook.createSheet("Ngôn ngữ lập trình tiên tiến");
//
//                    XSSFRow xssfRow = null;
//                    Cell cell = null;
//
//                    xssfRow = xssfSheet.createRow(3);
//                    cell = xssfRow.createCell(0, CellType.NUMERIC);
//                    cell.setCellValue("ID Order");
//
//                    cell = xssfRow.createCell(1, CellType.STRING);
//                    cell.setCellValue("Date Order");
//
//                    cell = xssfRow.createCell(2, CellType.NUMERIC);
//                    cell.setCellValue("Quantity");
//
//                    cell = xssfRow.createCell(3, CellType.STRING);
//                    cell.setCellValue("Name Customer");
//
//                    cell = xssfRow.createCell(4, CellType.STRING);
//                    cell.setCellValue("Email Customer");
//
//                    cell = xssfRow.createCell(5, CellType.STRING);
//                    cell.setCellValue("Name Shipper");
//
//                    cell = xssfRow.createCell(6, CellType.STRING);
//                    cell.setCellValue("Phone Shipper");
//
//                    List<OrderDTO> orderDTOList = OrderBUS.getAllOrdersWithStatusTrue(0);
//                    int i = 0;
//                    for (OrderDTO orderDTO : orderDTOList) {
//                        CustomerDTO customerDTO = customerBUS.findById(orderDTO.getIdCustomer());
//                        ShipperDTO shipperDTO = shipperBUS.findById(orderDTO.getIdShipper());
//
//                        xssfRow = xssfSheet.createRow(4 + i);
//
//                        cell = xssfRow.createCell(0, CellType.NUMERIC);
//                        cell.setCellValue(orderDTO.getId());
//
//                        cell = xssfRow.createCell(1, CellType.STRING);
//                        cell.setCellValue(orderDTO.getDateOrder());
//
//                        cell = xssfRow.createCell(2, CellType.NUMERIC);
//                        cell.setCellValue(orderDTO.getQuantity());
//
//                        String fullnameCustomer = customerDTO.getFirstname() + " " + customerDTO.getLastname();
//                        cell = xssfRow.createCell(3, CellType.STRING);
//                        cell.setCellValue(fullnameCustomer);
//
//                        cell = xssfRow.createCell(4, CellType.STRING);
//                        cell.setCellValue(customerDTO.getEmail());
//
//                        String fullnameShipper = shipperDTO.getFirstname() + " " + shipperDTO.getLastname();
//                        cell = xssfRow.createCell(5, CellType.STRING);
//                        cell.setCellValue(fullnameShipper);
//
//                        cell = xssfRow.createCell(6, CellType.STRING);
//                        cell.setCellValue(shipperDTO.getPhone());
//
//                        i++;
//                    }
//
//                    File file = new File(".\\test.xlsx");
//                    FileOutputStream fileOutputStream = new FileOutputStream(file);
//                    xssfWorkbook.write(fileOutputStream);
//                    fileOutputStream.close();
//                }
//                catch (FileNotFoundException fileNotFoundException) {
//                    fileNotFoundException.printStackTrace();
//                }
//                catch (IOException ioException) {
//                    ioException.printStackTrace();
//                }
//            }
//        });
        btn_ExportPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdministratorDTO administratorDTO = administratorBUS.findById(Globals.getGlobalAdministratorId());
                try {
                    OutputStream file = new FileOutputStream(new File(".\\RevenueManagement" + administratorDTO.getFirstname() + ".pdf"));
                    Document document = new Document();
                    PdfWriter.getInstance(document, file);
                    PdfPTable table=new PdfPTable(6);

                    PdfPCell cell = new PdfPCell (new Paragraph ("Revenue Management"));

                    cell.setColspan (6);
                    cell.setHorizontalAlignment (Element.ALIGN_CENTER);
                    cell.setPadding (10.0f);
                    cell.setBackgroundColor (new BaseColor (140, 221, 8));

                    table.addCell(cell);

                    table.addCell("ID Order");
                    table.addCell("Date Order");
                    table.addCell("Quantity");
                    table.addCell("Fullname Customer");
                    table.addCell("Email Customer");
                    table.addCell("Fullname Shipper");
                    table.setSpacingBefore(30.0f);
                    table.setSpacingAfter(30.0f);

                    List<OrderDTO> orderDTOList = OrderBUS.getAllOrdersWithStatusTrue(0);
                    for (OrderDTO orderDTO : orderDTOList) {
                        CustomerDTO customerDTO = customerBUS.findById(orderDTO.getIdCustomer());
                        ShipperDTO shipperDTO = shipperBUS.findById(orderDTO.getIdShipper());

                        table.addCell(orderDTO.getId().toString().trim());
                        table.addCell(orderDTO.getDateOrder().toString().trim());
                        table.addCell(String.valueOf(orderDTO.getQuantity()).trim());
                        String fullnameCustomer = customerDTO.getFirstname() + " " + customerDTO.getLastname();
                        table.addCell(fullnameCustomer.trim());
                        table.addCell(customerDTO.getEmail().trim());
                        String fullnameShipper = shipperDTO.getFirstname() + " " + shipperDTO.getLastname();
                        table.addCell(fullnameShipper.trim());
                    }

                    //Text formating in PDF
                    String total = txt_TotalCost.getText().trim();
                    Chunk chunk = new Chunk("Total Current: " + total);
                    chunk.setUnderline(+1f,-2f);//1st co-ordinate is for line width,2nd is space between

                    String adminFullname = administratorDTO.getFirstname() + " " + administratorDTO.getLastname();
                    Chunk chunk1 = new Chunk("Admin: " + adminFullname);
                    //Now Insert Every Thing Into PDF Document
                    document.open();//PDF document opened........

                    document.add(Chunk.NEWLINE);

                    document.add(new Paragraph("Ngon ngu lap trinh tien tien"));
                    document.add(new Paragraph("Document Generated On - " + java.util.Calendar.getInstance().getTime()));

                    document.add(table);

                    document.add(chunk);

                    document.add(Chunk.NEWLINE);

                    document.add(chunk1);

                    document.newPage();

                    document.close();

                    file.close();

                    System.out.println("Pdf created successfully..");

                } catch (Exception ex) {
                    ex.printStackTrace();
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
        btn_ChangeChart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                if (state == 1) {
//                    state = 2;
//                    drawChart();
//                    new AdminRevenueManagement();
//                }
//                else if (state == 2) {
//                    state = 3;
//                    drawChart();
//                }
//                else {
//                    state = 1;
//                    drawChart();
//                }
            }
        });
    }

    public static void main(String[] args) {
        AdminRevenueManagement adminRevenueManagement = new AdminRevenueManagement();
    }
}
