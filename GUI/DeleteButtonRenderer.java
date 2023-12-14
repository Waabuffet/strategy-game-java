package GUI;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

class DeleteButtonRenderer extends DefaultTableCellRenderer {
    private JPanel panel;
    private JButton deleteButton;

    public DeleteButtonRenderer() {
        panel = new JPanel();
        deleteButton = new JButton("X");

        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        panel.add(deleteButton);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        return panel;
    }
}