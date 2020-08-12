public class Weapon {
    private String name;
    Range damageRange;

    public Weapon() {
    }

    public Weapon(String name, int low, int high) {
        this.name = name;
        damageRange = new Range(low, high);
    }
    public String getName() {
        return this.name;
    }
}
