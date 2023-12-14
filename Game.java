
import java.lang.reflect.InvocationTargetException;
import java.util.InputMismatchException;
import java.util.Scanner;

import Building.BuildingFactory;
import Command.*;
import GameExceptions.*;
import Resource.ResourceQuantity;

public class Game {
    static Thread timeThread, inputThread;
    static int dayCounter;

    public static void main(String[] args) {

        String[] buildingTypes = BuildingFactory.getAllBuildingNames();

        Scanner scanner = new Scanner(System.in);
        ResourceQuantity initialQuantity = new ResourceQuantity(1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000);
        // ResourceQuantity initialQuantity = new ResourceQuantity(10, 20, 20);

        try {
            ManagerCommandExecutor.executeCommand(new AddInitResourcesCommand(initialQuantity));
        } catch (NotEnoughResourcesException | BuildingNonExistentException | ResourceAlreadyExistsException e) {
            e.printStackTrace();
        }

        timeThread = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(5000);
                    try {
                        dayPassed();
                    } catch (NotEnoughResourcesException | BuildingNonExistentException
                            | ResourceAlreadyExistsException e) {
                        gameOver();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        timeThread.start();

        inputThread = new Thread(() -> {
            while (true) {
                try {
                    showMenu();

                    char choice = scanner.next().toUpperCase().charAt(0);

                    switch (choice) {
                        case 'A':
                            newBuildingScenario(buildingTypes, scanner);
                            break;
                        case 'B':
                            removeBuildingScenario(scanner);
                            break;
                        case 'C':
                            addTravailleurScenario(scanner);
                            break;
                        case 'D':
                            removeTravailleurScenario(scanner);
                            break;
                        case 'E':
                            System.out.println("Checking resources");
                            break;
                        case 'F':
                            System.out.println("Checking buildings");
                            ManagerCommandExecutor.executeCommand(new ShowAvailableBuildingsCommand());
                            break;
                        case 'Q':
                            System.out.println("Au revoir!");
                            scanner.close(); // Fermez le scanner avant de quitter le programme
                            System.exit(0);
                        default:
                            System.out.println("Choix invalide. Veuillez réessayer.");
                    }
                    ManagerCommandExecutor.executeCommand(new CheckAvailableResourcesCommand());
                } catch (InputMismatchException | NotEnoughResourcesException | BuildingNonExistentException
                        | ResourceAlreadyExistsException e) {
                    System.out.println("Wrong input");
                }
            }
        });

        inputThread.start();

        try {
            timeThread.join();
            inputThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void gameOver() {
        System.out.println("You lost the game");
        System.exit(0);
    }

    public static void showMenu() {
        System.out.println("Choose what to do");
        System.out.println("A- New Building");
        System.out.println("B- Remove Building");
        System.out.println("C- Add Travailleur To Building");
        System.out.println("D- Remove Travailleur From Building");
        System.out.println("E- Check Resources");
        System.out.println("F- Check Buildings");
        System.out.println("Q- Exit");
    }

    public static void newBuildingScenario(String[] types, Scanner scanner) {
        showBuildingTypes(types);
        System.out.println("0- Cancel");

        int choice = scanner.nextInt();
        if (choice > 0) {
            try {
                ManagerCommandExecutor.executeCommand(new AddBuildingCommand(types[choice - 1]));
            } catch (NotEnoughResourcesException | BuildingNonExistentException | ResourceAlreadyExistsException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Canceled");
        }
    }

    public static void removeBuildingScenario(Scanner scanner)
            throws NotEnoughResourcesException, BuildingNonExistentException, ResourceAlreadyExistsException {
        genericFunction(scanner, "Select a building to remove:", "RemoveBuildingCommand");
    }

    public static void addTravailleurScenario(Scanner scanner)
            throws NotEnoughResourcesException, BuildingNonExistentException, ResourceAlreadyExistsException {
        genericFunction(scanner, "Select a building to add travailleur to:", "AddTravailleurToBuildingCommand");
    }

    public static void removeTravailleurScenario(Scanner scanner)
            throws NotEnoughResourcesException, BuildingNonExistentException, ResourceAlreadyExistsException {
        genericFunction(scanner, "Select a building to remove travailleur from:",
                "RemoveTravailleurFromBuildingCommand");
    }

    private static void genericFunction(Scanner scanner, String message, String command)
            throws NotEnoughResourcesException, BuildingNonExistentException, ResourceAlreadyExistsException {
        System.out.println(message);
        ManagerCommandExecutor.executeCommand(new ShowAvailableBuildingsCommand());
        System.out.println("0- Cancel");
        int selectedBuilding = scanner.nextInt();
        if (selectedBuilding > 0) {
            try {
                if (!ManagerCommandExecutor
                        .executeCommand((Command) Class.forName(command).getConstructor(Integer.class)
                                .newInstance(selectedBuilding))) {
                    System.out.println("Wrong input");
                }
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException
                    | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Canceled");
        }
    }

    public static void showBuildingTypes(String[] types) {
        System.out.println("\nChoose which building to build:");
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + "-" + types[i]);
        }
    }

    public static void dayPassed()
            throws NotEnoughResourcesException, BuildingNonExistentException, ResourceAlreadyExistsException {
        dayCounter++;
        System.out.println("One day has passed");
        if (!ManagerCommandExecutor.executeCommand(new DayHasPassedCommand(dayCounter))) {
            gameOver();
        }
    }
}