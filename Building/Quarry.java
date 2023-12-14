package Building;

import Resource.ResourceQuantity;

public class Quarry extends Building implements BuildingDiscovery {
    private static final int woodNeededForConstruction = 50;
    private static final int stoneNeededForConstruction = 0;

    public Quarry() {
        super(2, 30, 2, null,
                new ResourceQuantity(0, 0, 4, 4, 4, 0, 0, 0, 0));
    }

    public static ResourceQuantity getConstructionPrice() {
        return new ResourceQuantity(Quarry.woodNeededForConstruction, Quarry.stoneNeededForConstruction);
    }

}
