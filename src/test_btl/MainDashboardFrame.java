package test_btl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainDashboardFrame extends JFrame {
    private User currentUser;

    public MainDashboardFrame(User currentUser) {
        this.currentUser = currentUser;

        setTitle("Main Dashboard");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        JButton manageTasksButton = new JButton("Quản lý công việc");
        manageTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Chuyển sang trang quản lý công việc
                TaskFrame taskFrame = new TaskFrame();
                taskFrame.setVisible(true);
            }
        });
        add(manageTasksButton);

        JButton managePlansButton = new JButton("Quản lý kế hoạch");
        managePlansButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Chuyển sang trang quản lý kế hoạch
                PlanFrame planFrame = new PlanFrame();
                planFrame.setVisible(true);
            }
        });
        add(managePlansButton);

        JButton manageUsersButton = new JButton("Quản lý tài khoản");
        manageUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Chuyển sang trang quản lý tài khoản
                UserManagementFrame userManagementFrame = new UserManagementFrame();
                userManagementFrame.setVisible(true);
            }
        });
        add(manageUsersButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainDashboardFrame(null).setVisible(true); // Truyền null như một giá trị tạm thời
            }
        });
    }
}
