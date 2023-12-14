package Command;

import Game.Manager;

public class CheckAvailableResourcesCommand implements Command {

    @Override
    public boolean execute() {
        Manager.getInstance().checkAvailableResources();
        return true;
    }

}
