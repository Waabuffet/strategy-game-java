package Command;

import Game.Manager;

public class AddTravailleurToBuildingCommand implements Command {

    private int buildingIndex;

    public AddTravailleurToBuildingCommand(int buildingIndex) {
        this.buildingIndex = buildingIndex;
    }

    @Override
    public boolean execute() {
        return Manager.getInstance().addTravailleursToBuilding(this.buildingIndex);
    }

}
