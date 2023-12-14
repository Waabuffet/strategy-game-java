package Command;

import Game.Manager;

public class RemoveBuildingCommand implements Command {

    private int buildingIndex;

    public RemoveBuildingCommand(int buildingIndex) {
        this.buildingIndex = buildingIndex;
    }

    @Override
    public boolean execute() {
        return Manager.getInstance().removeBuilding(this.buildingIndex);
    }

}
