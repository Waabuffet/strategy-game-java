package GUICommand;

import java.util.ArrayList;

import GUI.GUIManager;
import Resource.Resource;

public class InitializeGUICommand implements GUICommand {

    ArrayList<Resource> resources;

    public InitializeGUICommand(ArrayList<Resource> resources){
        this.resources = resources;
    }

    @Override
    public void execute() {
        GUIManager.getInstance().initializeGUI(this.resources);
    }
    
}
