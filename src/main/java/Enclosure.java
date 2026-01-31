import java.util.ArrayList;
import java.util.List;

public class Enclosure {
    private final String id;
    private final String habitatType;
    private final int capacity;
    private final List<Animal> animals;

    public Enclosure(String id, String habitatType, int capacity) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Enclosure ID cannot be blank");
        if (habitatType == null || habitatType.isBlank()) throw new IllegalArgumentException("Habitat type cannot be blank");
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be positive");

        this.id = id;
        this.habitatType = habitatType;
        this.capacity = capacity;
        this.animals = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getHabitatType() { return habitatType; }
    public int getCapacity() { return capacity; }
    public List<Animal> getAnimals() { return new ArrayList<>(animals); }
    public int getCurrentOccupancy() { return animals.size(); }
    public boolean isFull() { return animals.size() >= capacity; }

    public boolean addAnimal(Animal animal) {
        if (animal == null) return false;
        if (!animal.getHabitatType().equals(habitatType)) return false;
        if (isFull()) return false;

        return animals.add(animal);
    }

    public boolean removeAnimal(Animal animal) {
        return animals.remove(animal);
    }

    public boolean containsAnimal(Animal animal) {
        return animals.contains(animal);
    }

    @Override
    public String toString() {
        return String.format("Enclosure{id='%s', habitat='%s', occupancy=%d/%d}",
                id, habitatType, getCurrentOccupancy(), capacity);
    }
}