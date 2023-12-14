package Building;

import Resource.ResourceQuantity;

public class House extends Building implements BuildingDiscovery {
    private static final int woodNeededForConstruction = 2;
    private static final int stoneNeededForConstruction = 2;

    public House() {
        super(4, 0, 4, null, null);
    }

    public static ResourceQuantity getConstructionPrice() {
        return new ResourceQuantity(House.woodNeededForConstruction, House.stoneNeededForConstruction);
    }

}
