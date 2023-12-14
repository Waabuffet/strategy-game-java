package Building;

import Resource.ResourceQuantity;

public class ApartmentBuilding extends Building implements BuildingDiscovery {
    private static final int woodNeededForConstruction = 50;
    private static final int stoneNeededForConstruction = 50;

    public ApartmentBuilding() {
        super(60, 0, 6, null, null);
    }

    public static ResourceQuantity getConstructionPrice() {
        return new ResourceQuantity(ApartmentBuilding.woodNeededForConstruction,
                ApartmentBuilding.stoneNeededForConstruction);
    }
}
