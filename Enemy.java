
public class Enemy extends Character {
    private Shield shield;
    Random randNum = new Random();
    Shield myShield = new Shield(randNum.random(0, 3));

    private int experienceReward;
    public boolean isDead(){
        boolean res = (health <= 0) ? true : false;
        return res;
    }

    public Enemy(){
        accuracy = 10;
        health = 20;
        experienceReward = 500;
        weapon = new Weapon("Dagger", 1, 6);
        shield = myShield;
    }

    //copy constructor
    public Enemy(Enemy e){
        accuracy = e.accuracy;
        health = e.health;
        experienceReward = e.experienceReward;
        weapon = e.weapon;
        name = e.name;
        shield = e.shield;
    }

    //equals
    public boolean equals(Enemy e) {
        return e.getName().equals(this.name) && e.getHealth() == this.getHealth();
    }

    public void attack(Player player) {
        System.out.println(this.getName() +" attacks you with a " + weapon.getName());
        int randomNumber = randNum.random(0,20);
        if (accuracy > randomNumber && !this.isDead()) {
            int damage = randNum.random(weapon.damageRange);
            player.takeDamage(damage);
            System.out.println("You are hit for " + damage + " damage! ");
            if(player.isDead())
                player.gameOver();
        }
        else if(this.isDead()) {
            System.out.println("This one is dead it won't do any harm");
        }
        else{
            System.out.println("Zombie missed the attack");
        }
        System.out.println();
    }

    protected void takeDamage(int damage){
        if(this.health < 0) {
            return;
        }
        if(shield.getResistance() - damage < 0){
            this.health += shield.getResistance() - damage;
            shield.setResistance(0);
        } else if(shield.getResistance() == 0) {
            this.health -= damage;
        }
        else if(shield.getResistance() > damage){
            shield.setResistance(shield.getResistance() - damage);

        }
    }
}
