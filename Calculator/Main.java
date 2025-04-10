import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        new CalculatorFrame();
    }
}

class CalculatorFrame extends JFrame implements ActionListener {
    JTextField inputField;
    double num1 = 0, num2 = 0, result = 0;
    String operator = "";

    CalculatorFrame() {
        setTitle("Simple Calculator");
        setSize(350, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inputField = new JTextField();
        inputField.setEditable(false);
        inputField.setFont(new Font("Arial", Font.BOLD, 24));
        add(inputField, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9')) {
            inputField.setText(inputField.getText() + command);
        } else if (command.equals("C")) {
            inputField.setText("");
            num1 = num2 = result = 0;
            operator = "";
        } else if (command.equals("=")) {
            try {
                num2 = Double.parseDouble(inputField.getText());

                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/": result = num2 != 0 ? num1 / num2 : 0; break; // could be handled better
                }

                inputField.setText("" + result);
            } catch (Exception ex) {
                inputField.setText("Error");
            }
        } else {
            try {
                num1 = Double.parseDouble(inputField.getText());
                operator = command;
                inputField.setText("");
            } catch (Exception ex) {
                inputField.setText("Err");
            }
        }
    }
}
