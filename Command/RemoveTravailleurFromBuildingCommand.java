package Command;

import Game.Manager;

public class RemoveTravailleurFromBuildingCommand implements Command {

    private int buildingIndex;

    public RemoveTravailleurFromBuildingCommand(int buildingIndex) {
        this.buildingIndex = buildingIndex;
    }

    @Override
    public boolean execute() {
        return Manager.getInstance().removeTravailleursFromBuilding(this.buildingIndex);
    }

}
