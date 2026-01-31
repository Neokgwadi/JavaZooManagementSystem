public class Lion extends Animal implements Runner {
    public Lion(String id, String name, int age){
        super(id, name, age, "Panthera leo", "Carnivore", "Savanna");
    }

    @Override
    public String makeSound() {
        return "Roar";
    }

    @Override
    public void run() {
        System.out.println(getName() + " the lion is running.");
    }
}
