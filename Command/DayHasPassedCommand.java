package Command;

import Game.Manager;
import GameExceptions.NotEnoughResourcesException;

public class DayHasPassedCommand implements Command {
    private int dayCounter;

    public DayHasPassedCommand(int dayCounter){
        this.dayCounter = dayCounter;
    }


    @Override
    public boolean execute() throws NotEnoughResourcesException {
        Manager.getInstance().dayHasPassed(this.dayCounter);
        return true;
    }

}
