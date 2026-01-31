public class Monkey extends Animal implements Runner {
    public Monkey(String id, String name, int age){
        super(id, name, age, "Macaca mulatta", "Omnivore", "Tropical Forest");
    }

    @Override
    public String makeSound() {
        return "Chatter";
    }

    @Override
    public void run() {
        System.out.println(getName() + " the monkey is swinging.");
    }
}