public class Penguin extends Animal implements Swimmer {
    public Penguin(String id, String name, int age){
        super(id, name, age, "Spheniscus demersus", "Carnivore", "Aquatic");
    }

    @Override
    public String makeSound() {
        return "Honk";
    }

    @Override
    public void swim() {
        System.out.println(getName() + " the penguin is swimming.");
    }
}