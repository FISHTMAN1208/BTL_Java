package test_btl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TaskFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private List<Task> tasks;
    private List<Plan> plans;

    public TaskFrame() {
        tasks = new ArrayList<>();

        setTitle("Task Manager");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Description", "Start Time", "End Time", "Status"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        JButton addButton = new JButton("Thêm công việc");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaskForm taskForm = new TaskForm(TaskFrame.this, null);
                taskForm.setVisible(true);
            }
        });
        buttonPanel.add(addButton);

        JButton searchButton = new JButton("Tìm kiếm");
        JTextField searchField = new JTextField(20);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = searchField.getText();
                searchTasks(keyword);
            }
        });
        buttonPanel.add(searchField);
        buttonPanel.add(searchButton);

        JButton editButton = new JButton("Sửa công việc");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int taskId = (int) tableModel.getValueAt(selectedRow, 0);
                    Task task = findTaskById(taskId);
                    if (task != null) {
                        TaskForm taskForm = new TaskForm(TaskFrame.this, task);
                        taskForm.setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(TaskFrame.this, "Chọn 1 công việc để sửa", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        buttonPanel.add(editButton);

        JButton deleteButton = new JButton("Xóa công việc");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int taskId = (int) tableModel.getValueAt(selectedRow, 0);
                    Task task = findTaskById(taskId);
                    if (task != null) {
                        tasks.remove(task);
                        tableModel.removeRow(selectedRow);
                    }
                } else {
                    JOptionPane.showMessageDialog(TaskFrame.this, "Chọn 1 công việc để xóa.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        buttonPanel.add(deleteButton);
    }

    private Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    private void searchTasks(String keyword) {
        // Xóa dữ liệu cũ
        tableModel.setRowCount(0);

        // Tìm kiếm và hiển thị các công việc phù hợp với từ khóa
        for (Task task : tasks) {
            if (task.getName().contains(keyword) || task.getDescription().contains(keyword)) {
                tableModel.addRow(new Object[]{
                        task.getId(),
                        task.getName(),
                        task.getDescription(),
                        task.getStartTime(),
                        task.getEndTime(),
                        task.getStatus()
                });
            }
        }
    }
    public void addTask(Task task) {
        tasks.add(task);
        tableModel.addRow(new Object[]{
                task.getId(),
                task.getName(),
                task.getDescription(),
                task.getStartTime(),
                task.getEndTime(),
                task.getStatus()
        });
    }

    public void updateTask(Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == task.getId()) {
                tasks.set(i, task);
                tableModel.setValueAt(task.getName(), i, 1);
                tableModel.setValueAt(task.getDescription(), i, 2);
                tableModel.setValueAt(task.getStartTime(), i, 3);
                tableModel.setValueAt(task.getEndTime(), i, 4);
                tableModel.setValueAt(task.getStatus(), i, 5);
                break;
            }
        }
    }
    public void addPlan(Plan plan) {
        plans.add(plan);
        // Thêm hàng mới vào bảng
        tableModel.addRow(new Object[]{
                plan.getId(),
                plan.getName(),
                plan.getDescription(),
                plan.getStartTime(),
                plan.getEndTime(),
                plan.getStatus()
        });
    }

    // Phương thức để cập nhật kế hoạch
    public void updatePlan(Plan plan) {
        for (int i = 0; i < plans.size(); i++) {
            if (plans.get(i).getId() == plan.getId()) {
                plans.set(i, plan);
                // Cập nhật các giá trị trong bảng
                tableModel.setValueAt(plan.getName(), i, 1);
                tableModel.setValueAt(plan.getDescription(), i, 2);
                tableModel.setValueAt(plan.getStartTime(), i, 3);
                tableModel.setValueAt(plan.getEndTime(), i, 4);
                tableModel.setValueAt(plan.getStatus(), i, 5);
                break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TaskFrame().setVisible(true);
            }
        });
    }
}
