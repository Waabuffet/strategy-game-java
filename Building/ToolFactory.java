package Building;

import Resource.ResourceQuantity;

public class ToolFactory extends Building implements BuildingDiscovery {
    private static final int woodNeededForConstruction = 50;
    private static final int stoneNeededForConstruction = 50;

    public ToolFactory() {
        super(0, 12, 8,
                new ResourceQuantity(0, 0, 0, 4, 0, 4, 0, 0, 0),
                new ResourceQuantity(0, 0, 0, 0, 0, 0, 0, 0, 4));
    }

    public static ResourceQuantity getConstructionPrice() {
        return new ResourceQuantity(ToolFactory.woodNeededForConstruction, ToolFactory.stoneNeededForConstruction);
    }

}
