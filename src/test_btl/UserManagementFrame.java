// UserManagementFrame.java
package test_btl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserManagementFrame extends JFrame {
    private UserManager userManager;
    private JTable table;
    private DefaultTableModel tableModel;

    public UserManagementFrame() {
        userManager = new UserManager();

        setTitle("Quản lý tài khoản");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new Object[]{"Username", "Role"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        refreshUserList();

        JButton addButton = new JButton("Thêm");
        addButton.addActionListener(e -> addUser());
        JButton removeButton = new JButton("Xóa");
        removeButton.addActionListener(e -> removeUser());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void refreshUserList() {
        tableModel.setRowCount(0);

        List<User> userList = userManager.getAllUsers();
        for (User user : userList) {
            tableModel.addRow(new Object[]{user.getUsername(), user.getRole()});
        }
    }

    private void addUser() {
        String username = JOptionPane.showInputDialog(this, "Nhập tên tài khoản:");
        String password = JOptionPane.showInputDialog(this, "Nhập mật khẩu:");
        String role = JOptionPane.showInputDialog(this, "Nhập quyền (admin/user):");

        if (username != null && password != null && role != null) {
            userManager.addUser(username, password, role);
            refreshUserList();
        }
    }

    private void removeUser() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String username = (String) table.getValueAt(selectedRow, 0);
            userManager.removeUser(username);
            refreshUserList();
        }
    }
}
