package Building;

import Resource.ResourceQuantity;

public class CementPlant extends Building implements BuildingDiscovery {
    private static final int woodNeededForConstruction = 50;
    private static final int stoneNeededForConstruction = 50;

    public CementPlant() {
        super(0, 10, 4,
                new ResourceQuantity(0, 4, 0, 4, 0, 0, 0, 0, 0),
                new ResourceQuantity(0, 0, 0, 0, 0, 0, 4, 0, 0));
    }

    public static ResourceQuantity getConstructionPrice() {
        return new ResourceQuantity(CementPlant.woodNeededForConstruction, CementPlant.stoneNeededForConstruction);
    }
}
