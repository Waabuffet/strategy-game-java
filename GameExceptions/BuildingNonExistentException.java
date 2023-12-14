package GameExceptions;

public class BuildingNonExistentException extends Exception {
    public BuildingNonExistentException(String buildingName) {
        super(buildingName + " does not exists");
    }
}
