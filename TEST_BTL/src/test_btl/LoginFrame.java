package test_btl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;
    private UserManager userManager;
    private User currentUser;

    public LoginFrame() {
        userManager = new UserManager();

        setTitle("Đăng nhập");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel usernameLabel = new JLabel("Tên tài khoản:");
        usernameLabel.setBounds(50, 30, 100, 25);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 30, 160, 25);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Mật khẩu:");
        passwordLabel.setBounds(50, 70, 100, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 70, 160, 25);
        add(passwordField);

        loginButton = new JButton("Đăng nhập");
        loginButton.setBounds(50, 110, 80, 25);
        add(loginButton);

        cancelButton = new JButton("Hủy bỏ");
        cancelButton.setBounds(140, 110, 80, 25);
        add(cancelButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (userManager.authenticate(username, password)) {
                    currentUser = userManager.getUser(username);
                    dispose();
                    MainDashboardFrame mainPageFrame = new MainDashboardFrame(currentUser); 
                    mainPageFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Sai tài khoản", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
}
