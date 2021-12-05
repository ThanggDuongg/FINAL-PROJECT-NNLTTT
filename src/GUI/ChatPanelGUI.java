package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatPanelGUI extends JFrame implements Runnable {
    private JTextArea text_TextAllMess;
    private JButton btn_Send;
    private JTextArea text_TextInput;
    private JPanel mainPanel;

    private Socket socket = null;
    private String Sender;
    private String Receiver;
    private BufferedReader bufferedReader = null;
    private DataOutputStream dataOutputStream = null;

    @Override
    public void run() {
        while (true) {
            try {
                if (socket != null) {
                    String msg = "";
                    while ((msg = bufferedReader.readLine()) != null) {
                        // Nếu có tin nhắn đến thì ghi vào lịch sử
                        text_TextAllMess.append("\n" + Receiver + ": " + msg);
                    }
                }
            } catch (Exception e) {
                // Do not change this because it spawn try-catch many time while running thread!
            }
        }
    }

    public ChatPanelGUI(Socket s, String sender, String receiver) {
        setContentPane(mainPanel);
        setTitle("Customer Form");
        setMinimumSize(new Dimension(765, 430));
        setMaximumSize(new Dimension(765, 430));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                try {
                    //Khởi tạo các Components cho giao diện đồ họa
                    //initComponents();

                    //Truyền socket(Socket này sẽ được tạo sau)
                    socket = s;

                    //Nhận tên người gửi và người nhận
                    Sender = sender;
                    Receiver = receiver;

                    //Tạo các bộ đệm để gửi và nhận tin nhắn
                    bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
                }
                catch (Exception ex) {
                    System.out.println("Error while create Main Panel");
                }
            }
        });


        btn_Send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (text_TextInput.getText().isEmpty()) return;
                try {
                    // Ghi dữ liệu từ ô nhập tin nhắn vào "bộ đệm của của socket"
                    // để sau này chúng ta có thể lấy dữ liệu này từ server
                    dataOutputStream.writeBytes(text_TextInput.getText());
                    // Xuống Dòng + Xóa bộ đệm
                    dataOutputStream.write(13);
                    dataOutputStream.write(10);
                    dataOutputStream.flush();

                    // Ghi dữ liệu vào textArea ở phía trên
                    text_TextAllMess.append("\n" + sender + ": " + text_TextInput.getText());

                    // Xóa hết tin nhắn tại ô nhập tin nhắn
                    text_TextInput.setText("");
                }
                catch (Exception ex) {
                    System.out.println("Error while sendding messeger");
                }
            }
        });
    }
}
