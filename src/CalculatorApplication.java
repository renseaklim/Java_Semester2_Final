import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApplication extends JFrame {
    private JTextField firstNumberField;
    private JTextField secondNumberField;
    private JTextField resultField;

    public CalculatorApplication() {
        // Set up the frame
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Create and add components
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("First Number:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        firstNumberField = new JTextField();
        add(firstNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Second Number:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        secondNumberField = new JTextField();
        add(secondNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Result:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        resultField = new JTextField();
        resultField.setEditable(false);
        add(resultField, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 3;
        JButton addButton = new JButton("+");
        addButton.addActionListener(new OperationActionListener("+"));
        add(addButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        JButton subtractButton = new JButton("-");
        subtractButton.addActionListener(new OperationActionListener("-"));
        add(subtractButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JButton multiplyButton = new JButton("*");
        multiplyButton.addActionListener(new OperationActionListener("*"));
        add(multiplyButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        JButton divideButton = new JButton("/");
        divideButton.addActionListener(new OperationActionListener("/"));
        add(divideButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        JButton moduloButton = new JButton("%");
        moduloButton.addActionListener(new OperationActionListener("%"));
        add(moduloButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            firstNumberField.setText("");
            secondNumberField.setText("");
            resultField.setText("");
        });
        add(clearButton, gbc);

        // Center the frame on the screen
        setLocationRelativeTo(null);
    }

    private class OperationActionListener implements ActionListener {
        private String operation;

        public OperationActionListener(String operation) {
            this.operation = operation;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double firstNumber = Double.parseDouble(firstNumberField.getText());
                double secondNumber = Double.parseDouble(secondNumberField.getText());
                double result = 0;

                switch (operation) {
                    case "+":
                        result = firstNumber + secondNumber;
                        break;
                    case "-":
                        result = firstNumber - secondNumber;
                        break;
                    case "*":
                        result = firstNumber * secondNumber;
                        break;
                    case "/":
                        if (secondNumber != 0) {
                            result = firstNumber / secondNumber;
                        } else {
                            JOptionPane.showMessageDialog(null, "Cannot divide by zero", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        break;
                    case "%":
                        result = firstNumber % secondNumber;
                        break;
                }
                resultField.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorApplication calculator = new CalculatorApplication();
            calculator.setVisible(true);
        });
    }
}
