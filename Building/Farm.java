package Building;

import Resource.ResourceQuantity;

public class Farm extends Building implements BuildingDiscovery {
    private static final int woodNeededForConstruction = 5;
    private static final int stoneNeededForConstruction = 5;

    public Farm() {
        super(5, 3, 2, null,
                new ResourceQuantity(10));
    }

    public static ResourceQuantity getConstructionPrice() {
        return new ResourceQuantity(Farm.woodNeededForConstruction, Farm.stoneNeededForConstruction);
    }
}
