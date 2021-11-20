package GUI;

import javax.swing.*;

public class ForgotPasswordGUI extends JFrame{
    private JButton confirmChangesButton;
    private JPanel mainPanel;

    public ForgotPasswordGUI() {
        setContentPane(mainPanel);
        setTitle("Forgot Password Form");
        setSize(700, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
