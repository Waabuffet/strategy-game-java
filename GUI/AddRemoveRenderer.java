package GUI;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

class AddRemoveRenderer extends DefaultTableCellRenderer {
    private JPanel panel;
    private JLabel label;
    private JButton addButton;
    private JButton removeButton;

    public AddRemoveRenderer() {
        panel = new JPanel();
        label = new JLabel();
        addButton = new JButton("+");
        removeButton = new JButton("-");

        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        panel.add(removeButton);
        panel.add(label);
        panel.add(addButton);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        label.setText(" " + value.toString().trim() + " ");

        return panel;
    }
}