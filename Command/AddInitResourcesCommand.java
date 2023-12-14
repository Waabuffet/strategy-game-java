package Command;

import Game.Manager;
import GameExceptions.ResourceAlreadyExistsException;
import Resource.ResourceQuantity;

public class AddInitResourcesCommand implements Command {

    ResourceQuantity qty;

    public AddInitResourcesCommand(ResourceQuantity qty) {
        this.qty = qty;
    }

    @Override
    public boolean execute() throws ResourceAlreadyExistsException {
        Manager.getInstance().addInitResources(qty);
        return true;
    }

}
