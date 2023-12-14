package GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Command.AddBuildingCommand;
import Command.ManagerCommandExecutor;
import GameExceptions.BuildingNonExistentException;
import GameExceptions.NotEnoughResourcesException;
import GameExceptions.ResourceAlreadyExistsException;

public class NewBuildingPanel extends JPanel {

    private int maxHeight = 100;

    public NewBuildingPanel(String[] buildingName, JFrame frame) {
        this.setLayout(new GridLayout(1, 0));
        this.setMaximumSize(new Dimension(this.getMaximumSize().width, this.maxHeight));

        for (String label : buildingName) {
            JButton button = new JButton(label);
            button.setPreferredSize(new Dimension(button.getPreferredSize().width, this.maxHeight));
            button.setMaximumSize(new Dimension(button.getMaximumSize().width, this.maxHeight));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        ManagerCommandExecutor.executeCommand(new AddBuildingCommand(label));
                    } catch (NotEnoughResourcesException | BuildingNonExistentException | ResourceAlreadyExistsException err) {
                        JOptionPane.showMessageDialog(frame, err.getMessage());
                    }
                }
            });
            this.add(button);
        }
    }
}
