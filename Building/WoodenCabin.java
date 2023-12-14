package Building;

import Resource.ResourceQuantity;

public class WoodenCabin extends Building implements BuildingDiscovery {
    private static final int woodNeededForConstruction = 1;
    private static final int stoneNeededForConstruction = 0;

    public WoodenCabin() {
        super(2, 2, 2, null,
                new ResourceQuantity(2, 2, 0));
    }

    public static ResourceQuantity getConstructionPrice() {
        return new ResourceQuantity(WoodenCabin.woodNeededForConstruction, WoodenCabin.stoneNeededForConstruction);
    }

}
