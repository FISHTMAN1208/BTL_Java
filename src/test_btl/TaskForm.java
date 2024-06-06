// TaskForm.java
package test_btl;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class TaskForm extends JDialog {
    private JTextField nameField;
    private JTextField descriptionField;
    private JDateChooser startTimeField;
    private JDateChooser endTimeField;
    private JButton saveButton;

    private TaskFrame taskFrame;
    private Task task;

    public TaskForm(TaskFrame taskFrame, Task task) {
        this.taskFrame = taskFrame;
        this.task = task;

        setTitle(task == null ? "Add Task" : "Edit Task");
        setSize(400, 400);
        setLayout(null);
        setLocationRelativeTo(taskFrame);

        JLabel nameLabel = new JLabel("Tên:");
        nameLabel.setBounds(20, 20, 80, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 20, 260, 25);
        add(nameField);

        JLabel descriptionLabel = new JLabel("Mô tả:");
        descriptionLabel.setBounds(20, 60, 80, 25);
        add(descriptionLabel);

        descriptionField = new JTextField();
        descriptionField.setBounds(100, 60, 260, 25);
        add(descriptionField);

        JLabel startTimeLabel = new JLabel("Bắt đầu:");
        startTimeLabel.setBounds(20, 100, 80, 25);
        add(startTimeLabel);

        startTimeField = new JDateChooser();
        startTimeField.setBounds(100, 100, 260, 25);
        add(startTimeField);

        JLabel endTimeLabel = new JLabel("Kết thúc:");
        endTimeLabel.setBounds(20, 140, 80, 25);
        add(endTimeLabel);

        endTimeField = new JDateChooser();
        endTimeField.setBounds(100, 140, 260, 25);
        add(endTimeField);

        saveButton = new JButton(task == null ? "Add" : "Save");
        saveButton.setBounds(150, 180, 100, 25);
        add(saveButton);

        if (task != null) {
            nameField.setText(task.getName());
            descriptionField.setText(task.getDescription());
            startTimeField.setDate(convertToDateViaInstant(task.getStartTime()));
            endTimeField.setDate(convertToDateViaInstant(task.getEndTime()));
        }

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (task == null) {
                    Task newTask = new Task(
                            nameField.getText(),
                            descriptionField.getText(),
                            convertToLocalDateTimeViaInstant(startTimeField.getDate()),
                            convertToLocalDateTimeViaInstant(endTimeField.getDate())
                    );
                    taskFrame.addTask(newTask);
                } else {
                    task.setName(nameField.getText());
                    task.setDescription(descriptionField.getText());
                    task.setStartTime(convertToLocalDateTimeViaInstant(startTimeField.getDate()));
                    task.setEndTime(convertToLocalDateTimeViaInstant(endTimeField.getDate()));
                    taskFrame.updateTask(task);
                }
                dispose();
            }
        });
    }

    public Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
    }

    public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // TaskForm.java (continued)
                new TaskForm(null, null).setVisible(true);
            }
        });
    }
}

            
