package org.example;

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
            case Button.NUM_1 -> display.setText(display.getText() + "1");
            case Button.NUM_2 -> display.setText(display.getText() + "2");
            case Button.NUM_3 -> display.setText(display.getText() + "3");
            case Button.NUM_4 -> display.setText(display.getText() + "4");
            case Button.NUM_5 -> display.setText(display.getText() + "5");
            case Button.NUM_6 -> display.setText(display.getText() + "6");
            case Button.NUM_7 -> display.setText(display.getText() + "7");
            case Button.NUM_8 -> display.setText(display.getText() + "8");
            case Button.NUM_9 -> display.setText(display.getText() + "9");
            case Button.NUM_0 -> display.setText(display.getText() + "0");
            case Button.DOT -> {
                if (!display.getText().contains(".")) {
                    display.setText(display.getText() + ".");
                }
            }
            case Button.ADD -> {
                operand_1 = Double.parseDouble(display.getText());
                operator = "+";
                display.setText("");
            }
            case Button.SUB -> {
                operand_1 = Double.parseDouble(display.getText());
                operator = "-";
                display.setText("");
            }
            case Button.MUL -> {
                operand_1 = Double.parseDouble(display.getText());
                operator = "*";
                display.setText("");
            }
            case Button.DIV -> {
                operand_1 = Double.parseDouble(display.getText());
                operator = "/";
                display.setText("");
            }
            case Button.BC -> {
                String currentText = display.getText();
                if (!currentText.isEmpty()) {
                    display.setText(currentText.substring(0, currentText.length() - 1));
                }
            }
            case Button.C -> {
                operand_1 = 0;
                operand_2 = 0;
                result = 0;
                operator = "";
                display.setText("");
            }
            case Button.CE -> {
                display.setText("");
            }
            case Button.EQ -> {
                if (display.getText().isEmpty()) {
                    return;
                }
                if (operator.isEmpty()) {
                    return;
                }
                operand_2 = Double.parseDouble(display.getText());
                result = switch (operator) {
                    case Button.ADD -> Calculator.add(operand_1, operand_2);
                    case Button.SUB -> Calculator.subtract(operand_1, operand_2);
                    case Button.MUL -> Calculator.multiply(operand_1, operand_2);
                    case Button.DIV -> {
                        if (operand_2 == 0) {
                            display.setText("Error");
                            yield Double.NaN; // or throw an exception
                        } else {
                            yield Calculator.divide(operand_1, operand_2);
                        }
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + operator);
                };
                display.setText(String.valueOf(formatResult(result)));
                calculatorGUI.addToHistory(formatResult(operand_1) + " " + operator + " " + formatResult(operand_2) + " = " + formatResult(result));
                operator = "";
            }
            case Button.SQ -> {
                operand_1 = Double.parseDouble(display.getText());
                result = Calculator.square(operand_1);
                display.setText(String.valueOf(formatResult(result)));
                calculatorGUI.addToHistory(operand_1 + " ^ 2 = " + formatResult(result));
            }
            case Button.SQRT -> {
                operand_1 = Double.parseDouble(display.getText());
                result = Calculator.sqrt(operand_1);
                display.setText(String.valueOf(formatResult(result)));
                calculatorGUI.addToHistory("√" + operand_1 + " = " + formatResult(result));
            }
            case Button.PERCENT -> {
                operand_1 = Double.parseDouble(display.getText());
                result = Calculator.percent(operand_1);
                display.setText(String.valueOf(formatResult(result)));
                calculatorGUI.addToHistory(operand_1 + " % = " + formatResult(result));
            }
            case Button.INV -> {
                operand_1 = Double.parseDouble(display.getText());
                if (operand_1 == 0) {
                    display.setText("Error");
                } else {
                    result = Calculator.inverse(operand_1);
                    display.setText(String.valueOf(formatResult(result)));
                    calculatorGUI.addToHistory("1 / " + operand_1 + " = " + formatResult(result));
                }
            }
            case Button.SIGN -> {
                if (display.getText().isEmpty()) {
                    display.setText("-");
                } else {
                    double value = Double.parseDouble(display.getText());
                    value = Calculator.negate(value);
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
