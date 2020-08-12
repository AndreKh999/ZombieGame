import java.util.Scanner;

public class Map {
    Scanner scanner = new Scanner(System.in);

    private int playerXPos;
    private int playerYPos;

    public int getPlayerXPos() {
        return playerXPos;
    }

    public void setPlayerXPos(int playerXPos) {
        this.playerXPos = playerXPos;
    }

    public int getPlayerYPos() {
        return playerYPos;
    }

    public void setPlayerYPos(int playerYPos) {
        this.playerYPos = playerYPos;
    }

    Random randomNum = new Random();

    public void move(){
        System.out.println("1) North, 2) East, 3) South, 4) West: ");
        int input = scanner.nextInt();

        switch (input){
            case 1:
                if( playerYPos + 1 != 1) {
                    playerYPos++;
                } else{
                    System.out.println("Obstacle found please change direction");
                    move();
                }
                break;
            case 2:if(playerXPos + 1 != 1) {
                playerXPos++;
            } else{
                System.out.println("Obstacle found please change direction");
                move();
            }
                break;
            case 3:
                if (playerYPos <= 0){
                    System.out.println("Out of map");
                    move();
                }
                else if(playerYPos - 1 != 1) {
                    playerYPos--;
                } else{
                    System.out.println("Obstacle found please change direction");
                    move();
                }

                break;
            case  4:
                if (playerXPos <= 0){
                    System.out.println("Out of map");
                    move();
                }
                else  if(playerYPos - 1 != 1) {
                    playerYPos--;
                } else{
                    System.out.println("Obstacle found please change direction");
                    move();
                }
                break;
            default:
                System.out.println("please try to act smart, input valid direction xD");
                break;
        }
    }

    public void printPlayerPos(){
        System.out.println("Player position = (" + playerXPos + ", " + playerYPos + ")");
        System.out.println();

    }

    public Character[] checkForEnemies(){
        int random = randomNum.random(0,21);
        Enemy myEnemy = new Enemy();

        System.out.println();
        if (random >= 0 && random <= 10){
            myEnemy.setName("Zombie");
            System.out.println("Prepare for a battle");
            System.out.println("You encountered a " + myEnemy.getName());
        }
        else if (random >= 11 && random <= 15){
            myEnemy.setName("Dark soul");
            System.out.println("Prepare for a battle");
            System.out.println("You encountered a " + myEnemy.getName());

        } else{myEnemy = null;}



        if (myEnemy != null) {
            if (random >= 0 && random <= 6) {
                Character[] result = new Character[4];
                result[0] = myEnemy;
                for (int i = 1; i < result.length; ++i) {
                    result[i] = new Enemy(myEnemy);
                    System.out.println("You encountered a " + myEnemy.getName());

                }
                return result;
            } else if (random > 6 && random <= 10) {
                Character[] result = new Character[3];
                result[0] = myEnemy;
                for (int i = 1; i < result.length; ++i) {
                    result[i] = new Enemy(myEnemy);
                    System.out.println("You encountered a " + myEnemy.getName());
                }
                return result;
            } else if (random > 10 && random <= 15) {
                Character[] result = new Character[2];
                result[0] = myEnemy;
                for (int i = 1; i < result.length; ++i) {
                    result[i] = new Enemy(myEnemy);
                    System.out.println("You encountered a " + myEnemy.getName());

                }
                return result;
            } else {
                Character[] result = new Character[1];
                result[0] = myEnemy;
                System.out.println("You encountered a " + myEnemy.getName());

                return result;
            }
    }

        return null;
    }
}
