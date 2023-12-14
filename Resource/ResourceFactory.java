package Resource;

import java.util.ArrayList;

import GameExceptions.ResourceAlreadyExistsException;

public class ResourceFactory {
    private static ResourceFactory self;
    private ArrayList<Resource> createdResources;

    private ResourceFactory() {
        this.createdResources = new ArrayList<Resource>();
    }

    public static ResourceFactory getInstance() {
        if (ResourceFactory.self == null) {
            ResourceFactory.self = new ResourceFactory();
        }
        return ResourceFactory.self;
    }

    public Resource createResource(ResourceType type) throws ResourceAlreadyExistsException {
        for (int i = 0; i < this.createdResources.size(); i++) {
            if (this.createdResources.get(i).getType().equals(type)) {
                throw new ResourceAlreadyExistsException(type);
            }
        }
        return new Resource(type, 0);
    }

}
