package project_files;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RobertsonMultiplier {
    private JTextField inputMultiplicand;
    private JTextField inputMultiplier;
    private JButton multiplyButton;
    private JTable resultTable;
    private JPanel mainPanel;
    private MultiplicationTableModel tableModel;

    public RobertsonMultiplier() {
        initComponents();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void initComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 2));

        inputMultiplicand = new JTextField(10);
        inputMultiplier = new JTextField(10);
        multiplyButton = new JButton("Solve");
        setupResultTable();

        mainPanel.add(new JLabel("Multiplicando:"));
        mainPanel.add(inputMultiplicand);
        mainPanel.add(new JLabel("Multiplicador:"));
        mainPanel.add(inputMultiplier);
        mainPanel.add(new JLabel(""));
        mainPanel.add(multiplyButton);
        mainPanel.add(new JScrollPane(resultTable));

        multiplyButton.addActionListener(e -> {
            try {
                int multiplicand = Integer.parseInt(inputMultiplicand.getText());
                int multiplier = Integer.parseInt(inputMultiplier.getText());
                MultiplicationLogic.performMultiplication(multiplicand, multiplier, tableModel);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, ingrese números enteros válidos.");
            }
        });
    }

    private void setupResultTable() {
        tableModel = new MultiplicationTableModel();
        resultTable = new JTable(tableModel);
    }
}
