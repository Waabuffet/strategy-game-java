package Building;

import java.lang.reflect.InvocationTargetException;

import GameExceptions.BuildingNonExistentException;
import Resource.ResourceQuantity;
import Building.ApartmentBuilding;
import Building.Farm;
import Building.CementPlant;
import Building.House;
import Building.LumberMill;
import Building.Quarry;
import Building.SteelMill;
import Building.ToolFactory;
import Building.WoodenCabin;

public class BuildingFactory {

    private static final String[] availableBuildings = new String[] { "ApartmentBuilding", "CementPlant", "Farm", "House", "LumberMill", "Quarry", "SteelMill", "ToolFactory", "WoodenCabin" };

    public static Building createBuilding(String buildingName) throws BuildingNonExistentException {
        Building building = null;
        try {
            building = (Building) Class.forName("Building." + buildingName).getConstructor().newInstance();
        } catch (ClassNotFoundException cnfe) {
            throw new BuildingNonExistentException(buildingName);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return building;
    }

    public static String[] getAllBuildingNames() {
        return BuildingFactory.availableBuildings;

        // ArrayList<String> buildingNames = new ArrayList<String>();
        // ServiceLoader<BuildingDiscovery> sl = ServiceLoader.load(BuildingDiscovery.class);
        // for (BuildingDiscovery building : sl) {
        //     buildingNames.add(building.getClass().getSimpleName());
        // }
        // System.out.println("found " + buildingNames.size() + " buildings");
        // return buildingNames.toArray(new String[0]);
    }

    public static ResourceQuantity getConstructionPrice(String buildingName) {
        ResourceQuantity price = null;
        try {
            price = (ResourceQuantity) Class.forName("Building." + buildingName).getMethod("getConstructionPrice")
                    .invoke(null);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return price;
    }
}
