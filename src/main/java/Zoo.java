import java.util.*;
import java.util.stream.Collectors;

public class Zoo {
    private final Map<String, Animal> animals = new HashMap<>();
    private final Map<String, Enclosure> enclosures = new HashMap<>();
    private final Map<String, HealthRecord> healthRecords = new HashMap<>();

    public Zoo() {
        // Initialize default enclosures
        addEnclosure(new Enclosure("SAVANNA_1", "Savanna", 5));
        addEnclosure(new Enclosure("AQUATIC_1", "Aquatic", 10));
        addEnclosure(new Enclosure("TROPICAL_1", "Tropical Forest", 8));
    }

    public boolean addAnimal(Animal animal) {
        if (animal == null) return false;

        String normalizedId = normalizeId(animal.getId());
        if (animals.containsKey(normalizedId)) {
            return false;
        }

        animals.put(normalizedId, animal);
        healthRecords.put(normalizedId, new HealthRecord(normalizedId));

        // Try to assign to appropriate enclosure
        assignToEnclosure(animal);

        return true;
    }

    public Animal removeById(String id) {
        String normalizedId = normalizeId(id);
        Animal animal = animals.remove(normalizedId);
        if (animal != null) {
            // Remove from enclosure
            enclosures.values().forEach(enc -> enc.removeAnimal(animal));
            healthRecords.remove(normalizedId);
        }
        return animal;
    }

    public Animal getById(String id) {
        return animals.get(normalizeId(id));
    }

    public List<Animal> getByName(String name) {
        if (name == null) return new ArrayList<>();
        String normalizedName = name.trim();
        return animals.values().stream()
                .filter(animal -> animal.getName().equalsIgnoreCase(normalizedName))
                .collect(Collectors.toList());
    }

    public List<Animal> listSortedByType() {
        return animals.values().stream()
                .sorted(Comparator.comparing(Animal::getType))
                .collect(Collectors.toList());
    }

    public Map<String, Integer> countByType() {
        Map<String, Integer> counts = new HashMap<>();
        for (Animal animal : animals.values()) {
            counts.merge(animal.getType(), 1, Integer::sum);
        }
        return counts;
    }

    public Map<String, Integer> countByHabitat() {
        Map<String, Integer> counts = new HashMap<>();
        for (Animal animal : animals.values()) {
            counts.merge(animal.getHabitatType(), 1, Integer::sum);
        }
        return counts;
    }

    public List<Animal> getAnimalsNeedingMedicalAttention() {
        return animals.values().stream()
                .filter(Animal::needsMedicalAttention)
                .collect(Collectors.toList());
    }

    public boolean addEnclosure(Enclosure enclosure) {
        if (enclosure == null) return false;
        String normalizedId = normalizeId(enclosure.getId());
        if (enclosures.containsKey(normalizedId)) return false;
        enclosures.put(normalizedId, enclosure);
        return true;
    }

    public Enclosure getEnclosure(String id) {
        return enclosures.get(normalizeId(id));
    }

    public List<Enclosure> getEnclosures() {
        return new ArrayList<>(enclosures.values());
    }

    public HealthRecord getHealthRecord(String animalId) {
        return healthRecords.get(normalizeId(animalId));
    }

    public void addHealthEntry(String animalId, String description, String veterinarian, String treatment) {
        HealthRecord record = healthRecords.get(normalizeId(animalId));
        if (record != null) {
            record.addEntry(description, veterinarian, treatment);
        }
    }

    private void assignToEnclosure(Animal animal) {
        for (Enclosure enclosure : enclosures.values()) {
            if (enclosure.getHabitatType().equals(animal.getHabitatType()) && !enclosure.isFull()) {
                enclosure.addAnimal(animal);
                break;
            }
        }
    }

    private String normalizeId(String id) {
        return id == null ? "" : id.trim().toUpperCase();
    }
}
