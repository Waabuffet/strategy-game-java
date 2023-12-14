package Game;

import java.util.ArrayList;

import Building.Building;
import Building.BuildingFactory;
import GUICommand.*;
import GameExceptions.*;
import Resource.Resource;
import Resource.ResourceFactory;
import Resource.ResourceQuantity;
import Resource.ResourceType;

public class Manager {
    private static Manager self;
    private ArrayList<Building> buildings;
    private ArrayList<Resource> resources, consumptions, productions;
    private boolean gameOver = false;

    private Manager() {
        this.buildings = new ArrayList<Building>();
        this.resources = new ArrayList<Resource>();
        this.consumptions = new ArrayList<Resource>();
        this.productions = new ArrayList<Resource>();
    }

    public static Manager getInstance() {
        if (Manager.self == null) {
            Manager.self = new Manager();
        }
        return Manager.self;
    }

    public void addInitResources(ResourceQuantity qty) throws ResourceAlreadyExistsException {

        for (ResourceType type : ResourceType.values()) {
            this.resources.add(ResourceFactory.getInstance().createResource(type));
            this.consumptions.add(ResourceFactory.getInstance().createResource(type));
            this.productions.add(ResourceFactory.getInstance().createResource(type));
        }
        this.addResources(qty);

        GUICommandExecutor.executeCommand(new InitializeGUICommand(this.resources));
    }

    public void checkAvailableResources() {
        GUICommandExecutor.executeCommand(new UpdateResourcesTableCommand(this.resources, this.consumptions, this.productions));
        String leftAlignFormat = "| %-9s | %-4d |%n";

        System.out.format("+-----------+------+%n");
        System.out.format("| Resource  | QTY  |%n");
        System.out.format("+-----------+------+%n");
        for (int i = 0; i < this.resources.size(); i++) {
            System.out.format(leftAlignFormat, this.resources.get(i).getType().toString(),
                    this.resources.get(i).getQty());
        }
        System.out.format("+-----------+------+%n");
    }

    public boolean createBuilding(String buildingName)
            throws NotEnoughResourcesException, BuildingNonExistentException {

        this.consumeResources(BuildingFactory.getConstructionPrice(buildingName));

        Building newBuilding = BuildingFactory.createBuilding(buildingName);
        this.buildings.add(newBuilding);
        this.refreshProductionConsumption();
        System.out.println(buildingName + " building added");
        GUICommandExecutor.executeCommand(new AddBuildingToTableCommand(this.buildings));
        return true;
    }

    private void refreshProductionConsumption() {
        this.consumptions = new ArrayList<Resource>();
        this.productions = new ArrayList<Resource>();

        try {
            for (ResourceType type : ResourceType.values()) {
                this.consumptions.add(ResourceFactory.getInstance().createResource(type));
                this.productions.add(ResourceFactory.getInstance().createResource(type));
            }
        } catch (ResourceAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }

        for (int b = 0; b < this.buildings.size(); b++) {
            Building building = this.buildings.get(b);

            ResourceQuantity consumption = building.getConsumption();
            ResourceQuantity production = building.getProduction();

            if (consumption != null) {
                for (ResourceType type : consumption.all.keySet()) {
                    for (int i = 0; i < this.consumptions.size(); i++) {
                        if (this.consumptions.get(i).getType().equals(type)) {
                            this.consumptions.get(i).changeQty(consumption.all.get(type));
                        }
                    }
                }
            }
            if (production != null) {
                for (ResourceType type : production.all.keySet()) {
                    for (int i = 0; i < this.productions.size(); i++) {
                        if (this.productions.get(i).getType().equals(type)) {
                            this.productions.get(i).changeQty(production.all.get(type));
                        }
                    }
                }
            }
        }

        GUICommandExecutor.executeCommand(new UpdateResourcesTableCommand(this.resources, this.consumptions, this.productions));

    }

    public boolean isGameOver() {
        return this.gameOver;
    }

    public void showAvailableBuildings() {

        String leftAlignFormat = "| %-3d | %-17s | %-12d | %-16d | %-25s | %-25s | %-17s |%n";

        System.out.format(
                "+-----+-------------------+--------------+------------------+---------------------------+---------------------------+-------------------+%n");
        System.out.format(
                "| ID  | Building          | Nbr Habitant | Nbr Travailleurs | Consommation              | Production                | Construction Fini |%n");
        System.out.format(
                "+-----+-------------------+--------------+------------------+---------------------------+---------------------------+-------------------+%n");
        for (int i = 0; i < this.buildings.size(); i++) {
            Building building = this.buildings.get(i);
            System.out.format(leftAlignFormat, i + 1, building, building.getNbrHabitants(),
                    building.getNbrTravailleurs(), building.getConsumption(), building.getProduction(),
                    building.getConstructionFinished());
        }
        System.out.format(
                "+-----+-------------------+--------------+------------------+---------------------------+---------------------------+-------------------+%n");
    }

    public boolean removeBuilding(int index) {
        if (index > this.buildings.size() || index < 1) {
            return false;
        }
        Building removedBuilding = this.buildings.get(index - 1);
        System.out.println("Removed building " + removedBuilding);
        this.buildings.remove(index - 1);
        this.refreshProductionConsumption();
        GUICommandExecutor.executeCommand(new RemoveBuildingFromTableCommand(index - 1));
        return true;
    }

    public boolean addTravailleursToBuilding(int index) {
        if (index > this.buildings.size() || index < 1) {
            return false;
        }
        if (this.buildings.get(index - 1).addTravailleurs()) {
            System.out.println("Added 1 travailleur to building " + this.buildings.get(index - 1));
            this.refreshProductionConsumption();
        GUICommandExecutor.executeCommand(new UpdateBuildingsTableCommand(this.buildings));
            return true;
        }
        System.out.println("Reached max number of travailleurs");
        return false;
    }

    public boolean removeTravailleursFromBuilding(int index) {
        if (index > this.buildings.size() || index < 1) {
            return false;
        }
        if (this.buildings.get(index - 1).removeTravailleurs()) {
            System.out.println("Removed 1 travailleur from building " + this.buildings.get(index - 1));
            this.refreshProductionConsumption();
        GUICommandExecutor.executeCommand(new UpdateBuildingsTableCommand(this.buildings));
            return false;
        }
        return true;
    }

    private void consumeResources(ResourceQuantity resourcesToConsume) throws NotEnoughResourcesException {
        boolean canBeConsumed = true;
        if (resourcesToConsume != null) {
            for (ResourceType type : resourcesToConsume.all.keySet()) {
                for (int i = 0; i < this.resources.size(); i++) {
                    if (this.resources.get(i).getType().equals(type)) {
                        if (this.resources.get(i).getQty() < resourcesToConsume.all.get(type)) {
                            canBeConsumed = false;
                        }
                    }
                }
            }
            if (canBeConsumed) {
                for (ResourceType type : resourcesToConsume.all.keySet()) {
                    for (int i = 0; i < this.resources.size(); i++) {
                        if (this.resources.get(i).getType().equals(type)) {
                            this.resources.get(i).changeQty(-resourcesToConsume.all.get(type));
                        }
                    }
                }
            } else {
                throw new NotEnoughResourcesException();
            }
        }
    }

    private void addResources(ResourceQuantity resourcesProduced) {
        if (resourcesProduced != null) {
            for (ResourceType type : resourcesProduced.all.keySet()) {
                for (int i = 0; i < this.resources.size(); i++) {
                    if (this.resources.get(i).getType().equals(type)) {
                        this.resources.get(i).changeQty(resourcesProduced.all.get(type));
                    }
                }
            }
        }
    }

    public void dayHasPassed(int dayCounter) throws NotEnoughResourcesException {
        for (int i = 0; i < this.buildings.size(); i++) {
            if (this.buildings.get(i).manage()) {
                this.consumeResources(this.buildings.get(i).consume());
                this.addResources(this.buildings.get(i).produce());
            }
        }
        GUICommandExecutor.executeCommand(new UpdateBuildingsTableCommand(this.buildings));
        GUICommandExecutor.executeCommand(new UpdateResourcesTableCommand(this.resources, this.consumptions, this.productions));
        GUICommandExecutor.executeCommand(new UpdateDayCommand(dayCounter));
    }

}
