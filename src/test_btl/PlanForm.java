package test_btl;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class PlanForm extends JDialog {
    private JTextField nameField;
    private JTextField descriptionField;
    private JDateChooser startTimeField;
    private JDateChooser endTimeField;
    private JButton saveButton;

    private PlanFrame planFrame;
    private Plan plan;

    public PlanForm(PlanFrame planFrame, Plan plan) {
        this.planFrame = planFrame;
        this.plan = plan;

        setTitle(plan == null ? "Add Plan" : "Edit Plan");
        setSize(400, 400);
        setLayout(null);
        setLocationRelativeTo(planFrame);

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

        JLabel startTimeLabel = new JLabel("Thời gian bắt đầu:");
        startTimeLabel.setBounds(20, 100, 80, 25);
        add(startTimeLabel);

        startTimeField = new JDateChooser();
        startTimeField.setBounds(100, 100, 260, 25);
        add(startTimeField);

        JLabel endTimeLabel = new JLabel("Thời gian kết thúc");
        endTimeLabel.setBounds(20, 140, 80, 25);
        add(endTimeLabel);

        endTimeField = new JDateChooser();
        endTimeField.setBounds(100, 140, 260, 25);
        add(endTimeField);

        saveButton = new JButton(plan == null ? "Add" : "Save");
        saveButton.setBounds(150, 180, 100, 25);
        add(saveButton);

        if (plan != null) {
            nameField.setText(plan.getName());
            descriptionField.setText(plan.getDescription());
            startTimeField.setDate(convertToDateViaInstant(plan.getStartTime()));
            endTimeField.setDate(convertToDateViaInstant(plan.getEndTime()));
        }

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (plan == null) {
                    Plan newPlan = new Plan(
                            nameField.getText(),
                            descriptionField.getText(),
                            convertToLocalDateTimeViaInstant(startTimeField.getDate()),
                            convertToLocalDateTimeViaInstant(endTimeField.getDate())
                    );
                    planFrame.addPlan(newPlan);
                } else {
                    plan.setName(nameField.getText());
                    plan.setDescription(descriptionField.getText());
                    plan.setStartTime(convertToLocalDateTimeViaInstant(startTimeField.getDate()));
                    plan.setEndTime(convertToLocalDateTimeViaInstant(endTimeField.getDate()));
                    planFrame.updatePlan(plan);
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
                new PlanForm(null, null).setVisible(true);
            }
        });
    }
}
