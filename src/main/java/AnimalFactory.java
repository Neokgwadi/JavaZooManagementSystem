public class AnimalFactory {
    public static Animal createAnimal(String type, String id, String name, int age) {
        String normalizedType = (type == null) ? "" : type.trim().toUpperCase();

        return switch (normalizedType) {
            case "LION" -> new Lion(id, name, age);
            case "DOLPHIN" -> new Dolphin(id, name, age);
            case "ELEPHANT" -> new Elephant(id, name, age);
            case "PENGUIN" -> new Penguin(id, name, age);
            case "MONKEY" -> new Monkey(id, name, age);
            default -> throw new IllegalArgumentException("Unknown animal type: " + type);
        };
    }
}
