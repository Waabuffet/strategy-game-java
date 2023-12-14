package GUICommand;

import java.util.ArrayList;

import Building.Building;
import GUI.GUIManager;

public class AddBuildingToTableCommand implements GUICommand {

    private ArrayList<Building> buildings;

    public AddBuildingToTableCommand(ArrayList<Building> buildings){
        this.buildings = buildings;
    }

    @Override
    public void execute() {
        GUIManager.getInstance().addBuildingToTable(this.buildings);
    }
    
}
