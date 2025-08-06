package SCT_SD_01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemperatureConverterGUI extends JFrame {

    private JComboBox<String> fromUnit;
    private JComboBox<String> toUnit;
    private JTextField inputField;
    private JLabel resultLabel;

    public TemperatureConverterGUI() {
        setTitle("Temperature Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        String[] units = { "Celsius", "Fahrenheit", "Kelvin" };

        inputField = new JTextField(10);
        fromUnit = new JComboBox<>(units);
        toUnit = new JComboBox<>(units);
        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel("Result: ");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter Temperature:"));
        panel.add(inputField);
        panel.add(new JLabel("From:"));
        panel.add(fromUnit);
        panel.add(new JLabel("To:"));
        panel.add(toUnit);
        panel.add(convertButton);
        panel.add(resultLabel);

        add(panel);

        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double input = Double.parseDouble(inputField.getText());
                    String from = fromUnit.getSelectedItem().toString();
                    String to = toUnit.getSelectedItem().toString();

                    double result = convertTemperature(input, from, to);
                    resultLabel.setText(String.format("Result: %.2f %s", result, to));
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid input!");
                }
            }
        });
    }

    private double convertTemperature(double value, String from, String to) {
        from = from.toLowerCase();
        to = to.toLowerCase();

        if (from.equals(to)) return value;

        switch (from) {
            case "celsius":
                if (to.equals("fahrenheit")) return (value * 9 / 5) + 32;
                if (to.equals("kelvin")) return value + 273.15;
                break;

            case "fahrenheit":
                if (to.equals("celsius")) return (value - 32) * 5 / 9;
                if (to.equals("kelvin")) return (value - 32) * 5 / 9 + 273.15;
                break;

            case "kelvin":
                if (to.equals("celsius")) return value - 273.15;
                if (to.equals("fahrenheit")) return (value - 273.15) * 9 / 5 + 32;
                break;
        }

        throw new IllegalArgumentException("Invalid temperature scale conversion.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TemperatureConverterGUI().setVisible(true);
        });
    }
}
