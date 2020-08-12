public class Bomb extends Weapon {

    private int numberOfEnemies;

    public int getNumberOfEnemies() {
        return numberOfEnemies;
    }

    public void setNumberOfEnemies(int numberOfEnemies) {
        this.numberOfEnemies = numberOfEnemies;
    }

    public Bomb(int n) {
        super("Bomb", 10, 20);
        numberOfEnemies = n;
    }
}
