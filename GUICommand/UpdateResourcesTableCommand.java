package GUICommand;

import java.util.ArrayList;

import GUI.GUIManager;
import Resource.Resource;

public class UpdateResourcesTableCommand implements GUICommand {

    private ArrayList<Resource> resources, consumptions, productions;

    public UpdateResourcesTableCommand(ArrayList<Resource> resources, ArrayList<Resource> consumptions, ArrayList<Resource> productions){
        this.resources = resources;
        this.consumptions = consumptions;
        this.productions = productions;
    }

    @Override
    public void execute() {
        GUIManager.getInstance().updateResourcesTable(this.resources, this.consumptions, this.productions);
    }
    
}
