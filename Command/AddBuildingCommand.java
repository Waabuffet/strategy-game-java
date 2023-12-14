package Command;

import Game.Manager;
import GameExceptions.BuildingNonExistentException;
import GameExceptions.NotEnoughResourcesException;

public class AddBuildingCommand implements Command {
    String buildingName;

    public AddBuildingCommand(String buildingName) {
        this.buildingName = buildingName;
    }

    @Override
    public boolean execute() throws NotEnoughResourcesException, BuildingNonExistentException {
        return Manager.getInstance().createBuilding(this.buildingName);
    }

}
