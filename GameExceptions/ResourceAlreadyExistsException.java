package GameExceptions;

import Resource.ResourceType;

public class ResourceAlreadyExistsException extends Exception {
    public ResourceAlreadyExistsException(ResourceType type) {
        super(type + " already exists");
    }
}
