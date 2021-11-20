package GUI;

import javax.swing.*;

public class RegisterGUI extends JFrame{
    private JButton registerButton;
    private JPanel mainPanel;

    public RegisterGUI() {
        setContentPane(mainPanel);
        setTitle("Register Form");
        setSize(700, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
