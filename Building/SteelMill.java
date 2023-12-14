package Building;

import Resource.ResourceQuantity;

public class SteelMill extends Building implements BuildingDiscovery {
    private static final int woodNeededForConstruction = 100;
    private static final int stoneNeededForConstruction = 50;

    public SteelMill() {
        super(0, 40, 6,
                new ResourceQuantity(0, 0, 0, 4, 4, 0, 0, 0, 0),
                new ResourceQuantity(0, 0, 0, 0, 0, 4, 0, 0, 0));
    }

    public static ResourceQuantity getConstructionPrice() {
        return new ResourceQuantity(SteelMill.woodNeededForConstruction, SteelMill.stoneNeededForConstruction);
    }

}
