import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HealthRecord {
    private final String animalId;
    private final List<MedicalEntry> entries;

    public static class MedicalEntry {
        private final LocalDate date;
        private final String description;
        private final String veterinarian;
        private final String treatment;

        public MedicalEntry(LocalDate date, String description, String veterinarian, String treatment) {
            this.date = date;
            this.description = description;
            this.veterinarian = veterinarian;
            this.treatment = treatment;
        }

        public LocalDate getDate() { return date; }
        public String getDescription() { return description; }
        public String getVeterinarian() { return veterinarian; }
        public String getTreatment() { return treatment; }

        @Override
        public String toString() {
            return String.format("[%s] %s - Dr. %s: %s", date, description, veterinarian, treatment);
        }
    }

    public HealthRecord(String animalId) {
        if (animalId == null || animalId.isBlank()) throw new IllegalArgumentException("Animal ID cannot be blank");
        this.animalId = animalId;
        this.entries = new ArrayList<>();
    }

    public String getAnimalId() { return animalId; }
    public List<MedicalEntry> getEntries() { return new ArrayList<>(entries); }

    public void addEntry(String description, String veterinarian, String treatment) {
        entries.add(new MedicalEntry(LocalDate.now(), description, veterinarian, treatment));
    }

    public List<MedicalEntry> getRecentEntries(int days) {
        LocalDate cutoff = LocalDate.now().minusDays(days);
        return entries.stream()
                .filter(entry -> entry.getDate().isAfter(cutoff))
                .toList();
    }

    @Override
    public String toString() {
        return String.format("HealthRecord{animalId='%s', entries=%d}", animalId, entries.size());
    }
}