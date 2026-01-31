public class Dolphin extends Animal implements Swimmer {
    public Dolphin(String id, String name, int age){
        super(id, name, age, "Tursiops truncatus", "Carnivore", "Aquatic");
    }

    @Override
    public String makeSound() {
        return "Click";
    }

    @Override
    public void swim() {
        System.out.println(getName() + " the dolphin is swimming.");
    }
}
