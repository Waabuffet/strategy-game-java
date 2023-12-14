package GUI;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import Command.AddTravailleurToBuildingCommand;
import Command.ManagerCommandExecutor;
import Command.RemoveTravailleurFromBuildingCommand;
import GameExceptions.BuildingNonExistentException;
import GameExceptions.NotEnoughResourcesException;
import GameExceptions.ResourceAlreadyExistsException;

class AddRemoveEditor extends AbstractCellEditor implements TableCellEditor {
    private JPanel panel;
    private JLabel label;
    private JButton addButton;
    private JButton removeButton;

    public AddRemoveEditor() {
        panel = new JPanel();
        label = new JLabel();
        addButton = new JButton("+");
        removeButton = new JButton("-");

        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        panel.add(removeButton);
        panel.add(label);
        panel.add(addButton);
    }

    @Override
    public Object getCellEditorValue() {
        return label.getText();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label.setText(" " + value.toString().trim() + " ");

        // Remove previous action listeners (if present)
        ActionListener[] removeListeners = removeButton.getActionListeners();
        if (removeListeners != null && removeListeners.length > 0) {
            removeButton.removeActionListener(removeListeners[0]);
        }

        ActionListener[] addListeners = addButton.getActionListeners();
        if (addListeners != null && addListeners.length > 0) {
            addButton.removeActionListener(addListeners[0]);
        }

        addButton.addActionListener(e -> {
            int clickedRow = table.convertRowIndexToModel(row);
            try {
                ManagerCommandExecutor.executeCommand(new AddTravailleurToBuildingCommand(clickedRow + 1));
            } catch (NotEnoughResourcesException | BuildingNonExistentException | ResourceAlreadyExistsException e1) {
                e1.printStackTrace();
            }
            fireEditingStopped();
        });

        removeButton.addActionListener(e -> {
            int clickedRow = table.convertRowIndexToModel(row);
            try {
                ManagerCommandExecutor.executeCommand(new RemoveTravailleurFromBuildingCommand(clickedRow + 1));
            } catch (NotEnoughResourcesException | BuildingNonExistentException | ResourceAlreadyExistsException e1) {
                e1.printStackTrace();
            }
            fireEditingStopped();
        });

        return panel;
    }
}