package Command;

import GameExceptions.BuildingNonExistentException;
import GameExceptions.NotEnoughResourcesException;
import GameExceptions.ResourceAlreadyExistsException;

public class ManagerCommandExecutor {

    public static boolean executeCommand(Command command) throws NotEnoughResourcesException, BuildingNonExistentException, ResourceAlreadyExistsException {
        return command.execute();
    }
}
