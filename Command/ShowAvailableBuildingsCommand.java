package Command;

import Game.Manager;

public class ShowAvailableBuildingsCommand implements Command {

    @Override
    public boolean execute() {
        Manager.getInstance().showAvailableBuildings();
        return true;
    }

}
