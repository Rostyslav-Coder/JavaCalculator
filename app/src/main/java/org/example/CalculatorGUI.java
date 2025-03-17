package org.example;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class CalculatorGUI extends JFrame {

    private final JTextField DISPLAY = new JTextField();
    private final JPanel PANEL = new JPanel();
    private final JButton[] BUTTONS = new JButton[24];
    private final ButtonClickListener BCL;
    private final DefaultListModel<String> HISTORY_MODEL = new DefaultListModel<>();
    private final JList<String> HISTORY_LIST = new JList<>(HISTORY_MODEL);
    private final JScrollPane SCROLL_PANE = new JScrollPane(HISTORY_LIST);

    private final Dimension displaySize = new Dimension(600, 100);
    private final Font displayFont = new Font("Roboto", Font.PLAIN, 48);
    private final GridLayout panelLayout = new GridLayout(6, 4);
    private final Font buttonFont = new Font("Roboto", Font.PLAIN, 24);
    private final Font historyFont = new Font("Roboto", Font.PLAIN, 20);
    private final Dimension historySize = new Dimension(200, 400);

    public CalculatorGUI() {
        setSize(600, 500);
        setTitle("Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        BCL = new ButtonClickListener(DISPLAY, this);

        setupDisplay();        
        setupPanel();
        setupButtons(PANEL, BCL);
        setupHistoryList();
    }

    private void setupDisplay() {
        DISPLAY.setEditable(false);
        DISPLAY.setPreferredSize(displaySize);
        DISPLAY.setFont(displayFont);
        DISPLAY.setHorizontalAlignment(JTextField.RIGHT);
        add(DISPLAY, BorderLayout.NORTH);
    }
    
    private void  setupPanel() {
        PANEL.setLayout(panelLayout);
        add(PANEL, BorderLayout.CENTER);
    }
    
    private void setupButtons(JPanel panel, ButtonClickListener buttonClickListener) {
        String[] labels = Button.BUTTON_LABELS;
        
        for (int i = 0; i < BUTTONS.length; i++) {
            BUTTONS[i] = new JButton(labels[i]);
            BUTTONS[i].setFont(buttonFont);
            BUTTONS[i].addActionListener(buttonClickListener);
            panel.add(BUTTONS[i]);
        }
    }
    
    private void setupHistoryList() {
        HISTORY_LIST.setFont(historyFont);
        HISTORY_LIST.setCellRenderer(new RightAlignedCellRenderer());
        SCROLL_PANE.setPreferredSize(historySize);
        HISTORY_LIST.addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                String selectedValue = HISTORY_LIST.getSelectedValue();
                if (selectedValue != null) {
                    DISPLAY.setText(selectedValue.split(" = ")[1]);
                }
            }
        });
        add(SCROLL_PANE, BorderLayout.EAST);
    }

    public void addToHistory(String entry) {
        HISTORY_MODEL.addElement(entry);
    }

    private static class RightAlignedCellRenderer extends DefaultListCellRenderer {
        @Override
        public java.awt.Component getListCellRendererComponent(
            JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus
        ) {
            JLabel label = (JLabel) super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus
            );
            label.setHorizontalAlignment(JLabel.RIGHT);
            return label;
        }
    }

}
