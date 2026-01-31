import java.util.Scanner;

public class ZooConsoleApp {
    private final Zoo zoo = new Zoo();
    private final ZooStorage storage = new ZooStorage();
    private final Scanner scanner = new Scanner(System.in);
    private static final String FILE_PATH = "zoo_data.csv";

    public void start() {
        try {
            storage.load(FILE_PATH, zoo);
            System.out.println("Data loaded successfully.");
        } catch (Exception e) {
            System.out.println("Starting with a fresh zoo.");
        }

        while (true) {
            System.out.println("\n=== ZOO MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Animal | 2. List All Animals | 3. View Animal Details");
            System.out.println("4. Remove Animal | 5. Statistics | 6. Manage Enclosures");
            System.out.println("7. Health Records | 8. Feed Animals | 9. Exit");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addAnimal();
                case "2" -> listAnimals();
                case "3" -> viewAnimalDetails();
                case "4" -> removeAnimal();
                case "5" -> showStatistics();
                case "6" -> manageEnclosures();
                case "7" -> manageHealthRecords();
                case "8" -> feedAnimals();
                case "9" -> { saveAndExit(); return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void addAnimal() {
        System.out.println("Available animal types: Lion, Dolphin, Elephant, Penguin, Monkey");
        System.out.print("Enter animal type: ");
        String type = scanner.nextLine().toUpperCase();

        try {
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Age: ");
            int age = Integer.parseInt(scanner.nextLine());

            // Generate unique ID
            String id = java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase();

            Animal animal = AnimalFactory.createAnimal(type, id, name, age);
            if (zoo.addAnimal(animal)) {
                System.out.println("Success! " + animal.getName() + " added to the zoo with ID: " + id);
            } else {
                System.out.println("Error: Failed to add animal.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }

    private void listAnimals() {
        var animals = zoo.listSortedByType();
        if (animals.isEmpty()) {
            System.out.println("No animals in the zoo.");
            return;
        }

        System.out.println("\n--- ANIMALS IN ZOO ---");
        animals.forEach(a ->
            System.out.printf("[%s] ID: %s | Name: %s | Age: %d | Health: %s%n",
                a.getType(), a.getId(), a.getName(), a.getAge(), a.getHealthStatus()));
    }

    private void viewAnimalDetails() {
        System.out.print("Enter animal name: ");
        String name = scanner.nextLine();
        var matchingAnimals = zoo.getByName(name);

        if (matchingAnimals.isEmpty()) {
            System.out.println("Animal not found.");
            return;
        }

        if (matchingAnimals.size() > 1) {
            System.out.println("Multiple animals found with that name:");
            for (int i = 0; i < matchingAnimals.size(); i++) {
                Animal animal = matchingAnimals.get(i);
                System.out.printf("%d. %s (%s) - ID: %s%n",
                    i + 1, animal.getName(), animal.getType(), animal.getId());
            }
            System.out.print("Enter the number of the animal to view: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine()) - 1;
                if (choice < 0 || choice >= matchingAnimals.size()) {
                    System.out.println("Invalid choice.");
                    return;
                }
                displayAnimalDetails(matchingAnimals.get(choice));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        } else {
            displayAnimalDetails(matchingAnimals.get(0));
        }
    }

    private void displayAnimalDetails(Animal animal) {
        System.out.println("\n--- ANIMAL DETAILS ---");
        System.out.println("ID: " + animal.getId());
        System.out.println("Name: " + animal.getName());
        System.out.println("Type: " + animal.getType());
        System.out.println("Species: " + animal.getSpecies());
        System.out.println("Age: " + animal.getAge());
        System.out.println("Diet: " + animal.getDiet());
        System.out.println("Habitat: " + animal.getHabitatType());
        System.out.println("Health Status: " + animal.getHealthStatus());
        System.out.println("Arrival Date: " + animal.getArrivalDate());
        System.out.println("Sound: " + animal.makeSound());

        // Show enclosure info
        zoo.getEnclosures().stream()
            .filter(e -> e.containsAnimal(animal))
            .findFirst()
            .ifPresentOrElse(
                e -> System.out.println("Enclosure: " + e.getId()),
                () -> System.out.println("Enclosure: Not assigned")
            );
    }

    private void removeAnimal() {
        Animal animal = selectAnimalByName("Enter name of animal to remove: ");
        if (animal != null) {
            Animal removed = zoo.removeById(animal.getId());
            System.out.println(removed != null ? "Removed: " + removed.getName() : "Failed to remove animal.");
        }
    }

    private void showStatistics() {
        System.out.println("\n--- ZOO STATISTICS ---");
        System.out.println("Total Animals: " + zoo.listSortedByType().size());

        System.out.println("\nBy Type:");
        zoo.countByType().forEach((type, count) ->
            System.out.println("  " + type + ": " + count));

        System.out.println("\nBy Habitat:");
        zoo.countByHabitat().forEach((habitat, count) ->
            System.out.println("  " + habitat + ": " + count));

        System.out.println("\nEnclosures:");
        zoo.getEnclosures().forEach(e ->
            System.out.println("  " + e));

        var sickAnimals = zoo.getAnimalsNeedingMedicalAttention();
        if (!sickAnimals.isEmpty()) {
            System.out.println("\nAnimals needing medical attention: " + sickAnimals.size());
        }
    }

    private void manageEnclosures() {
        System.out.println("\n--- ENCLOSURE MANAGEMENT ---");
        System.out.println("1. List Enclosures | 2. View Enclosure Details | 3. Add Enclosure");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> zoo.getEnclosures().forEach(System.out::println);
            case "2" -> {
                System.out.print("Enter enclosure ID: ");
                String id = scanner.nextLine();
                Enclosure enclosure = zoo.getEnclosure(id);
                if (enclosure != null) {
                    System.out.println(enclosure);
                    System.out.println("Animals:");
                    enclosure.getAnimals().forEach(a ->
                        System.out.println("  - " + a.getName() + " (" + a.getType() + ")"));
                } else {
                    System.out.println("Enclosure not found.");
                }
            }
            case "3" -> {
                try {
                    System.out.print("Enter enclosure ID: ");
                    String id = scanner.nextLine();

                    System.out.println("Available habitat types:");
                    System.out.println("1. Savanna (for lions, elephants)");
                    System.out.println("2. Aquatic (for dolphins, penguins)");
                    System.out.println("3. Tropical Forest (for monkeys)");
                    System.out.println("4. Custom habitat type");
                    System.out.print("Choose habitat type (1-4): ");

                    String habitatChoice = scanner.nextLine();
                    String habitat;

                    switch (habitatChoice) {
                        case "1" -> habitat = "Savanna";
                        case "2" -> habitat = "Aquatic";
                        case "3" -> habitat = "Tropical Forest";
                        case "4" -> {
                            System.out.print("Enter custom habitat type: ");
                            habitat = scanner.nextLine();
                        }
                        default -> {
                            System.out.println("Invalid choice.");
                            return;
                        }
                    }

                    System.out.print("Enter capacity: ");
                    int capacity = Integer.parseInt(scanner.nextLine());

                    Enclosure enclosure = new Enclosure(id, habitat, capacity);
                    if (zoo.addEnclosure(enclosure)) {
                        System.out.println("Enclosure added successfully.");
                    } else {
                        System.out.println("Enclosure ID already exists.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input: " + e.getMessage());
                }
            }
        }
    }

    private void manageHealthRecords() {
        System.out.println("\n--- HEALTH RECORDS ---");
        System.out.println("1. View Health Record | 2. Add Health Entry | 3. Update Health Status");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> {
                Animal animal = selectAnimalByName("Enter animal name: ");
                if (animal != null) {
                    HealthRecord record = zoo.getHealthRecord(animal.getId());
                    System.out.println("Health Record for " + animal.getName() + ":");
                    record.getEntries().forEach(System.out::println);
                }
            }
            case "2" -> {
                Animal animal = selectAnimalByName("Enter animal name: ");
                if (animal != null) {
                    System.out.print("Description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Veterinarian: ");
                    String vet = scanner.nextLine();
                    System.out.print("Treatment: ");
                    String treatment = scanner.nextLine();

                    zoo.addHealthEntry(animal.getId(), desc, vet, treatment);
                    System.out.println("Health entry added.");
                }
            }
            case "3" -> {
                Animal animal = selectAnimalByName("Enter animal name: ");
                if (animal != null) {
                    System.out.println("Current health status: " + animal.getHealthStatus());
                    System.out.println("Available statuses: HEALTHY, SICK, CRITICAL, RECOVERING");
                    System.out.print("New status: ");
                    try {
                        Animal.HealthStatus status = Animal.HealthStatus.valueOf(scanner.nextLine().toUpperCase());
                        animal.setHealthStatus(status);
                        System.out.println("Health status updated.");
                    } catch (Exception e) {
                        System.out.println("Invalid status.");
                    }
                }
            }
        }
    }

    private Animal selectAnimalByName(String prompt) {
        System.out.print(prompt);
        String name = scanner.nextLine();
        var matchingAnimals = zoo.getByName(name);

        if (matchingAnimals.isEmpty()) {
            System.out.println("Animal not found.");
            return null;
        }

        if (matchingAnimals.size() > 1) {
            System.out.println("Multiple animals found with that name:");
            for (int i = 0; i < matchingAnimals.size(); i++) {
                Animal animal = matchingAnimals.get(i);
                System.out.printf("%d. %s (%s) - ID: %s%n",
                    i + 1, animal.getName(), animal.getType(), animal.getId());
            }
            System.out.print("Enter the number of the animal: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine()) - 1;
                if (choice < 0 || choice >= matchingAnimals.size()) {
                    System.out.println("Invalid choice.");
                    return null;
                }
                return matchingAnimals.get(choice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
                return null;
            }
        } else {
            return matchingAnimals.get(0);
        }
    }

    private void feedAnimals() {
        System.out.println("\n--- FEEDING TIME ---");
        var animals = zoo.listSortedByType();
        if (animals.isEmpty()) {
            System.out.println("No animals to feed.");
            return;
        }

        System.out.println("Feeding all animals...");
        animals.forEach(animal -> {
            String food = switch (animal.getDiet().toLowerCase()) {
                case "carnivore" -> "meat";
                case "herbivore" -> "plants";
                case "omnivore" -> "mixed food";
                default -> "food";
            };
            System.out.println(animal.getName() + " the " + animal.getType().toLowerCase() + " is eating " + food + ".");
        });
        System.out.println("All animals have been fed!");
    }

    private void saveAndExit() {
        try {
            storage.save(FILE_PATH, zoo);
            System.out.println("Data saved. Goodbye!");
        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new ZooConsoleApp().start();
    }
}
