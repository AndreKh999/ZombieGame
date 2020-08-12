import java.util.Scanner;

public class Player extends Character {

    Scanner scanner = new Scanner(System.in);

    private int experiencePoints;
    private int level = 1;
    private int maxHealth = 20;
    private int nextLevelExperience = 500;
    private int goldCount;
    private Weapon weapon;
    private Bomb bomb;
    private Range damage;

    Random randomNumber = new Random();

    public Weapon getWeapon(){
        return this.weapon;
    }

    private int getLevel() {return this.level;}

    public Player(){
        accuracy = 10;
        health = 20;
        experiencePoints = 0;
        weapon = new Weapon("Sword",1,10);
    }

    //copy constructor
    public Player(Player p) {
        setAccuracy(p.getAccuracy());
        setHealth(p.getHealth());
        experiencePoints = p.experiencePoints;

    }

    //equals
    public boolean equals(Player p) {
        return p.getName().equals(this.getName()) && p.getLevel() == this.getLevel()
                && p.getGold() == this.getGold();
    }

    public void takeDamage (int damage) {
        health -= damage;
    }

    public boolean isDead() {
        boolean res = health <= 0 ? true : false;
        return res;
    }

    public void addGolds(){
        this.goldCount += randomNumber.random(1,20);
    }

    public int getGold(){
        return this.goldCount;
    }

    public boolean attack(Character[] enemy){
        System.out.println(this.name + "'s health = " + this.health);
        for(int i = 0; i < enemy.length; i++) {
            if(enemy[i].health <= 0) continue;          // nor poxac
            System.out.println(enemy[i].getName() + "'s health = " + enemy[i].getHealth());
        }
        System.out.println();
        System.out.println("1) Attack, 2) Run: ");

        int input = scanner.nextInt();
        int randomNum = 0;

        switch (input) {
            case 1:
                System.out.println("Choose you weapon: 1) bomb 2) " + this.weapon.getName());
                int weaponNumber = scanner.nextInt();
                switch (weaponNumber) {
                    case 1:
                        this.bomb = new Bomb(enemy.length);
                        this.damage = bomb.damageRange;
                        System.out.println("You attack with a bomb");
                        break;
                    case 2:
                        this.damage = this.weapon.damageRange;
                        System.out.println("You attack with a " + weapon.getName());
                        break;
                    default:
                        System.out.println("Enter valid weapon number");
                        attack(enemy);
                        break;
                }

                randomNum = randomNumber.random(0, 20);

                if (accuracy > randomNum) {
                    int newRandomNum = randomNumber.random(damage);
                    Enemy b = (Enemy) enemy[0];
                    if (weaponNumber == 1) {
                        for (int i = 0; i < enemy.length; i++) {
                            if(enemy[i].isDead()) continue;
                            Enemy a = (Enemy) enemy[i];
                            a.takeDamage(newRandomNum);
                            System.out.println("You attack for " + newRandomNum + " damage!");
                            newRandomNum = randomNumber.random(weapon.damageRange);
                        }
                    } else {
                        b.takeDamage(newRandomNum);
                        System.out.println("You attack for " + newRandomNum + " damage!");
                    }

                }
                else{
                    System.out.println("You missed the attack");
                }
                System.out.println();
                for(int i = 0; i < enemy.length; i++) {
                    Enemy b = (Enemy)enemy[i];
                    if (b.isDead()) {
                        win(500);
                    }
                }
                break;
            case 2:
                randomNum = randomNumber.random(0,100);
                if (randomNum <= 25)
                    System.out.println("Escaped");
                break;
            default:
                break;
        }

        return input == 2 && randomNum <= 25;
    }

    public void win(int experience){
        System.out.println("One of them is dead!");
        experiencePoints += experience;
        levelUp();
        addGolds();
    }


    public void levelUp() {
        if (experiencePoints >= nextLevelExperience){
            nextLevelExperience = (int) (nextLevelExperience * 2);
            accuracy += randomNumber.random(1, 20);
            maxHealth += randomNumber.random(1, 10);
            level += 1;
        }
    }

    public void gameOver(){
        if (isDead())
            System.out.println("Game over");
        System.exit(0);
        addGolds();
    }
    public void buyFrom(Trader trader, int itemNumber){
        if(trader.getPrice(itemNumber) > this.goldCount){
            System.out.println("You don't have enough resources");
            System.out.println("You only have " + goldCount + " coins");
            return;
        }
        this.goldCount -= trader.getPrice(itemNumber);
        this.weapon = trader.sell(itemNumber);
        System.out.println("Your new weapon is " + this.weapon.getName());
    }

    public void sellTo(Trader trader){
        if(this.weapon == null){
            System.out.println("You don't have a weapon, so you can't sell it xD");
            return;
        }
        this.goldCount += trader.buy(this.weapon);
        this.weapon = null;
    }

    public void rest(){
        health = maxHealth;     //this feature makes the whole game absolute non-sense xD
    }

    public void viewStats(){
        System.out.println("Name = " + name);
        System.out.println("Health = " + health);
        System.out.println("Experience = " + experiencePoints);
        System.out.println("Experience for next level = " + nextLevelExperience);
        System.out.println("Level = " + level);
        System.out.println("Weapon Name = " + weapon.getName());
        System.out.println("Weapon damage = 1- 10");
        System.out.println("Gold: " + getGold());
    }
}
