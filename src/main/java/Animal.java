import java.time.LocalDate;

public abstract class Animal {
    private final String id;
    private final String name;
    private final int age;
    private final String species;
    private final String diet;
    private final String habitatType;
    private final LocalDate arrivalDate;
    private HealthStatus healthStatus;

    public enum HealthStatus {
        HEALTHY, SICK, CRITICAL, RECOVERING
    }

    public Animal(String id, String name, int age, String species, String diet, String habitatType) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("ID cannot be null or blank");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be blank");
        if (age < 0) throw new IllegalArgumentException("Age cannot be negative");
        if (species == null || species.isBlank()) throw new IllegalArgumentException("Species cannot be blank");
        if (diet == null || diet.isBlank()) throw new IllegalArgumentException("Diet cannot be blank");
        if (habitatType == null || habitatType.isBlank()) throw new IllegalArgumentException("Habitat type cannot be blank");

        this.id = id;
        this.name = name;
        this.age = age;
        this.species = species;
        this.diet = diet;
        this.habitatType = habitatType;
        this.arrivalDate = LocalDate.now();
        this.healthStatus = HealthStatus.HEALTHY;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getSpecies() { return species; }
    public String getDiet() { return diet; }
    public String getHabitatType() { return habitatType; }
    public LocalDate getArrivalDate() { return arrivalDate; }
    public HealthStatus getHealthStatus() { return healthStatus; }

    // Setters
    public void setHealthStatus(HealthStatus healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getType() {
        return this.getClass().getSimpleName();
    }

    public abstract String makeSound();

    public boolean needsMedicalAttention() {
        return healthStatus == HealthStatus.SICK || healthStatus == HealthStatus.CRITICAL;
    }

    @Override
    public String toString() {
        return String.format("%s{id='%s', name='%s', age=%d, species='%s', diet='%s', habitat='%s', health=%s}",
                getType(), id, name, age, species, diet, habitatType, healthStatus);
    }
}