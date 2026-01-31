public class Elephant extends Animal implements Runner {
    public Elephant(String id, String name, int age){
        super(id, name, age, "Loxodonta africana", "Herbivore", "Savanna");
    }

    @Override
    public String makeSound() {
        return "Trumpet";
    }

    @Override
    public void run() {
        System.out.println(getName() + " the elephant is walking.");
    }
}