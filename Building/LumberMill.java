package Building;

import Resource.ResourceQuantity;

public class LumberMill extends Building implements BuildingDiscovery {
    private static final int woodNeededForConstruction = 50;
    private static final int stoneNeededForConstruction = 50;

    public LumberMill() {
        super(0, 10, 4, new ResourceQuantity(4, 0),
                new ResourceQuantity(0, 0, 0, 0, 0, 0, 0, 4, 0));
    }

    public static ResourceQuantity getConstructionPrice() {
        return new ResourceQuantity(LumberMill.woodNeededForConstruction, LumberMill.stoneNeededForConstruction);
    }

}
