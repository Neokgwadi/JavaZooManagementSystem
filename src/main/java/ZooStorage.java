import java.io.*;
import java.nio.file.*;
import java.util.List;

public class ZooStorage {
    private static final String DELIMITER = ",";

    public void save(String filePath, Zoo zoo) throws IOException {
        // Save animals
        List<String> animalLines = zoo.listSortedByType().stream()
                .map(a -> String.join(DELIMITER,
                    a.getType(),
                    a.getId(),
                    a.getName(),
                    String.valueOf(a.getAge()),
                    a.getSpecies(),
                    a.getDiet(),
                    a.getHabitatType(),
                    a.getHealthStatus().toString()))
                .toList();

        // Save enclosures
        List<String> enclosureLines = zoo.getEnclosures().stream()
                .map(e -> "ENCLOSURE" + DELIMITER +
                    e.getId() + DELIMITER +
                    e.getHabitatType() + DELIMITER +
                    e.getCapacity())
                .toList();

        List<String> allLines = new java.util.ArrayList<>();
        allLines.addAll(animalLines);
        allLines.addAll(enclosureLines);

        Files.write(Paths.get(filePath), allLines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public void load(String filePath, Zoo zoo) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) return;

        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            String[] parts = line.split(DELIMITER);
            if (parts.length >= 1) {
                try {
                    if ("ENCLOSURE".equals(parts[0]) && parts.length == 4) {
                        // Load enclosure
                        Enclosure enclosure = new Enclosure(parts[1], parts[2], Integer.parseInt(parts[3]));
                        zoo.addEnclosure(enclosure);
                    } else if (parts.length == 8) {
                        // Load animal with new format
                        Animal animal = AnimalFactory.createAnimal(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
                        // Set additional properties if animal supports them
                        if (animal instanceof Animal) {
                            Animal.HealthStatus status = Animal.HealthStatus.valueOf(parts[7]);
                            animal.setHealthStatus(status);
                        }
                        zoo.addAnimal(animal);
                    } else if (parts.length == 4) {
                        // Backward compatibility with old format
                        Animal animal = AnimalFactory.createAnimal(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
                        zoo.addAnimal(animal);
                    }
                } catch (Exception e) {
                    // Silently skip malformed entries to maintain stability
                    System.out.println("Skipping malformed entry: " + line);
                }
            }
        }
    }
}
