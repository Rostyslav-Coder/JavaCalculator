import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

class ButtonClickListener implements ActionListener {
    private final JTextField display;
    private final CalculatorGUI calculatorGUI;
    private double operand_1 = 0;
    private double operand_2 = 0;
    private double result = 0;
    private String operator = "";

    public ButtonClickListener(JTextField display, CalculatorGUI calculatorGUI) {
        this.display = display;
        this.calculatorGUI = calculatorGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case ButtonLabels.NUM_1 -> display.setText(display.getText() + "1");
            case ButtonLabels.NUM_2 -> display.setText(display.getText() + "2");
            case ButtonLabels.NUM_3 -> display.setText(display.getText() + "3");
            case ButtonLabels.NUM_4 -> display.setText(display.getText() + "4");
            case ButtonLabels.NUM_5 -> display.setText(display.getText() + "5");
            case ButtonLabels.NUM_6 -> display.setText(display.getText() + "6");
            case ButtonLabels.NUM_7 -> display.setText(display.getText() + "7");
            case ButtonLabels.NUM_8 -> display.setText(display.getText() + "8");
            case ButtonLabels.NUM_9 -> display.setText(display.getText() + "9");
            case ButtonLabels.NUM_0 -> display.setText(display.getText() + "0");
            case ButtonLabels.DOT -> {
                if (!display.getText().contains(".")) {
                    display.setText(display.getText() + ".");
                }
            }
            case ButtonLabels.ADD -> {
                operand_1 = Double.parseDouble(display.getText());
                operator = "+";
                display.setText("");
            }
            case ButtonLabels.SUB -> {
                operand_1 = Double.parseDouble(display.getText());
                operator = "-";
                display.setText("");
            }
            case ButtonLabels.MUL -> {
                operand_1 = Double.parseDouble(display.getText());
                operator = "*";
                display.setText("");
            }
            case ButtonLabels.DIV -> {
                operand_1 = Double.parseDouble(display.getText());
                operator = "/";
                display.setText("");
            }
            case ButtonLabels.BC -> {
                String currentText = display.getText();
                if (!currentText.isEmpty()) {
                    display.setText(currentText.substring(0, currentText.length() - 1));
                }
            }
            case ButtonLabels.C -> {
                operand_1 = 0;
                operand_2 = 0;
                result = 0;
                operator = "";
                display.setText("");
            }
            case ButtonLabels.CE -> {
                display.setText("");
            }
            case ButtonLabels.EQ -> {
                if (display.getText().isEmpty()) {
                    return;
                }
                if (operator.isEmpty()) {
                    return;
                }
                operand_2 = Double.parseDouble(display.getText());
                result = switch (operator) {
                    case ButtonLabels.ADD -> operand_1 + operand_2;
                    case ButtonLabels.SUB -> operand_1 - operand_2;
                    case ButtonLabels.MUL -> operand_1 * operand_2;
                    case ButtonLabels.DIV -> {
                        if (operand_2 != 0) {
                            yield operand_1 / operand_2;
                        } else {
                            display.setText("Error");
                            yield 0;
                        }
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + operator);
                };
                display.setText(String.valueOf(formatResult(result)));
                calculatorGUI.addToHistory(formatResult(operand_1) + " " + operator + " " + formatResult(operand_2) + " = " + formatResult(result));
                operator = "";
            }
            case ButtonLabels.SQ -> {
                operand_1 = Double.parseDouble(display.getText());
                result = operand_1 * operand_1;
                display.setText(String.valueOf(formatResult(result)));
                calculatorGUI.addToHistory(operand_1 + " ^ 2 = " + formatResult(result));
            }
            case ButtonLabels.SQRT -> {
                operand_1 = Double.parseDouble(display.getText());
                result = Math.sqrt(operand_1);
                display.setText(String.valueOf(formatResult(result)));
                calculatorGUI.addToHistory("√" + operand_1 + " = " + formatResult(result));
            }
            case ButtonLabels.PERCENT -> {
                operand_1 = Double.parseDouble(display.getText());
                result = operand_1 / 100;
                display.setText(String.valueOf(formatResult(result)));
                calculatorGUI.addToHistory(operand_1 + " % = " + formatResult(result));
            }
            case ButtonLabels.INV -> {
                operand_1 = Double.parseDouble(display.getText());
                if (operand_1 != 0) {
                    result = 1 / operand_1;
                    display.setText(String.valueOf(formatResult(result)));
                    calculatorGUI.addToHistory("1 / " + operand_1 + " = " + formatResult(result));
                } else {
                    display.setText("Error");
                }
            }
            case ButtonLabels.SIGN -> { // Обработка случая изменения знака
                if (!display.getText().isEmpty()) {
                    double value = Double.parseDouble(display.getText());
                    value = -value;
                    display.setText(formatResult(value));
                }
            }
        }
    }

    private String formatResult(double result) {
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            return String.format("%s", result);
        }
    }
}
