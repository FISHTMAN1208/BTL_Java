package test_btl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PlanFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private List<Plan> plans;

    public PlanFrame() {
        plans = new ArrayList<>();

        setTitle("Quản lý kế hoạch");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new Object[]{"Mã công việc", "Tên công việc", "Mô tả", "Bắt đầu", "Kết thúc", "Trạng thái"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        JButton addButton = new JButton("Thêm kế hoạch");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlanForm planForm = new PlanForm(PlanFrame.this, null);
                planForm.setVisible(true);
            }
        });
        buttonPanel.add(addButton);

        JButton editButton = new JButton("Sửa kế hoạch");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    // Ensure that the ID column value is correctly cast to an Integer
                    int planId = (Integer) tableModel.getValueAt(selectedRow, 0);
                    Plan plan = findPlanById(planId);
                    if (plan != null) {
                        PlanForm planForm = new PlanForm(PlanFrame.this, plan);
                        planForm.setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(PlanFrame.this, "Hãy chọn 1 kế hoạch để chỉnh sửa.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        buttonPanel.add(editButton);

        JButton deleteButton = new JButton("Xóa kế hoạch");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    // Ensure that the ID column value is correctly cast to an Integer
                    int planId = (Integer) tableModel.getValueAt(selectedRow, 0);
                    Plan plan = findPlanById(planId);
                    if (plan != null) {
                        plans.remove(plan);
                        tableModel.removeRow(selectedRow);
                    }
                } else {
                    JOptionPane.showMessageDialog(PlanFrame.this, "Hãy chọn 1 kế hoạch để xóa", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        buttonPanel.add(deleteButton);
    }

    private Plan findPlanById(int id) {
        for (Plan plan : plans) {
            if (plan.getId() == id) {
                return plan;
            }
        }
        return null;
    }

    public void addPlan(Plan plan) {
        plans.add(plan);
        Object[] rowData = {plan.getId(), plan.getName(), plan.getDescription(), plan.getStartTime(), plan.getEndTime(), plan.getStatus()};
        tableModel.addRow(rowData);
    }

    public void updatePlan(Plan plan) {
        for (int i = 0; i < plans.size(); i++) {
            if (plans.get(i).getId() == plan.getId()) {
                plans.set(i, plan);
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
                new PlanFrame().setVisible(true);
            }
        });
    }
}
