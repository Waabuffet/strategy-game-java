package GUI;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import Command.ManagerCommandExecutor;
import Command.RemoveBuildingCommand;
import GameExceptions.BuildingNonExistentException;
import GameExceptions.NotEnoughResourcesException;
import GameExceptions.ResourceAlreadyExistsException;

class DeleteButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private JPanel panel;
    private JButton deleteButton;
    private String label = "X";

    public DeleteButtonEditor() {
        panel = new JPanel();
        deleteButton = new JButton(label);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        panel.add(deleteButton);
    }

    @Override
    public Object getCellEditorValue() {
        return label;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        
        // Remove previous action listeners (if present)
        ActionListener[] deleteListeners = deleteButton.getActionListeners();
        if (deleteListeners != null && deleteListeners.length > 0) {
            deleteButton.removeActionListener(deleteListeners[0]);
        }

        deleteButton.addActionListener(e -> {
            int clickedRow = table.convertRowIndexToModel(row);
            fireEditingStopped();
            try {
                ManagerCommandExecutor.executeCommand(new RemoveBuildingCommand(clickedRow + 1));
            } catch (NotEnoughResourcesException | BuildingNonExistentException | ResourceAlreadyExistsException e1) {
                e1.printStackTrace();
            }
        });

        return panel;
    }

    
}