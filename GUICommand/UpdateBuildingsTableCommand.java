package GUICommand;

import java.util.ArrayList;

import Building.Building;
import GUI.GUIManager;

public class UpdateBuildingsTableCommand implements GUICommand {

    private ArrayList<Building> buildings;

    public UpdateBuildingsTableCommand(ArrayList<Building> buildings){
        this.buildings = buildings;
    }

    @Override
    public void execute() {
        GUIManager.getInstance().updateBuildingsTable(this.buildings);
    }
    
}
