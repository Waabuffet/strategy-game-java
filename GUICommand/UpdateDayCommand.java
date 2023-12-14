package GUICommand;

import GUI.GUIManager;

public class UpdateDayCommand implements GUICommand {

    private int dayCounter;

    public UpdateDayCommand(int dayCounter){
        this.dayCounter = dayCounter;
    }

    @Override
    public void execute() {
        GUIManager.getInstance().updateDay(this.dayCounter);
    }
    
}
