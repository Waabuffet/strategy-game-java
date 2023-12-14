package Command;

import GameExceptions.BuildingNonExistentException;
import GameExceptions.NotEnoughResourcesException;
import GameExceptions.ResourceAlreadyExistsException;

public interface Command {
    boolean execute() throws NotEnoughResourcesException, BuildingNonExistentException, ResourceAlreadyExistsException;
}
