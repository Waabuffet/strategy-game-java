package GUICommand;

import GUI.GUIManager;

public class RemoveBuildingFromTableCommand implements GUICommand {

    private int index;

    public RemoveBuildingFromTableCommand(int index){
        this.index = index;
    }

    @Override
    public void execute() {
        GUIManager.getInstance().removeBuildingFromTable(this.index);
    }
    
}
