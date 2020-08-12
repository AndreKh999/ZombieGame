import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameDemo {
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("grig.txt");
        Scanner forFile = new Scanner(file);        //scanner for file
        Scanner sc = new Scanner(System.in);        //scanner for console input

        String str = forFile.nextLine();
        String str1 = str.replace(" ", "");
        str1 = str1.replace(":", "");


        int numberOfRows =  str1.charAt(0) - 48;
        System.out.println("number of rows is " + numberOfRows);
        int numberOfColumns = str1.charAt(1) - 48;
        System.out.println("number of col is " + numberOfColumns);

        int[] arr = new int[numberOfColumns * numberOfRows];


        // filling array from the file
        for (int i = 2, j = 0; i < str1.length(); i++) {
            if(str1.charAt(i) == 48 || str1.charAt(i) == 49 || str1.charAt(i) == 50) {
                arr[j] = str1.charAt(i) - 48;
                j += 1;
            }
        }

        //creating new map
        GridMap map = new GridMap(numberOfRows, numberOfColumns, arr);

        //this is our player
        Player player = new Player();


        System.out.println("Enter player's name: ");
        player.setName(sc.next());
        map.printPlayerPos();

        while(!player.isDead()) {
            System.out.println("1) Move, 2) Rest, 3) View Stats, 4) Quit:");
            int input = sc.nextInt();
            if(input > 0 && input < 5) {
                switch (input) {
                    case 1:
                        map.move1();
                        map.printPlayerPos();

                        //check for shops here
                        if (map.checkForShops() == null){                  //if there is no enemy than check for enemies
                            Character[] enemy =  map.checkForEnemies();    //creating enemy array

                            if (enemy != null) {        //in this case you encountered enemy
                                //****note that****
                                //in case of our attack if you choose your second weapon you will attack only the
                                //first enemy of the array. this is how we were asked to develop the code
                                //***************
                                for (int i = 0; i < enemy.length; i++) {
                                    Enemy b = (Enemy) enemy[i];
                                    while (!b.isDead() && !player.isDead()) {
                                        player.attack(b);
                                        if (!player.attack(enemy) && enemy[i].getHealth() > 0) {
                                            for (int j = 0; j < enemy.length; j++) {
                                                enemy[j].attack(player);
                                            }
                                        }
                                    }
                                }
                                if(player.getHealth() > 0) System.out.println("You won the battle!");
                            }
                        } else {                //in this case shop is found
                            WeaponTrader myShop = new WeaponTrader();
                            System.out.println("Welcome to weapon shop!");
                            System.out.println("-----------------------");
                            System.out.println("1) Buy a weapon, 2) Sell your weapon, 3) List all items 4) Exit.");
                            while(true) {
                                int input2 = sc.nextInt();
                                if(input2 == 1) {
                                    System.out.println("Weapon Shop Items");
                                    System.out.println("------------------");
                                    myShop.listAllItems();
                                    System.out.println("------------------");
                                    System.out.println("Select the item number to buy: ");
                                    int weaponNumber = sc.nextInt();
                                    player.buyFrom(myShop, weaponNumber);
                                    System.out.println("1) Buy a weapon, 2) Sell your weapon, 3) List all items 4) Exit.");

                                } else if (input2 == 2) {
                                    player.sellTo(myShop);
                                    System.out.println(player.getName() + "'s coins " + player.getGold());
                                    System.out.println(player.getName() + " has no weapon");
                                    System.out.println("1) Buy a weapon, 2) Sell your weapon, 3) List all items 4) Exit.");

                                } else if (input2 == 3) {
                                    System.out.println("Weapon Shop Items");
                                    System.out.println("------------------");
                                    myShop.listAllItems();
                                    System.out.println("------------------");
                                } else if (input2 == 4) {
                                    if(player.getWeapon() == null){
                                        System.out.println("You don't have a weapon, please buy one");
                                        System.out.println("1) Buy a weapon, 2) Sell your weapon, 3) List all items 4) Exit.");
                                    }
                                    else break;
                                } else {
                                    System.out.println("invalid input");
                                }
                            }
                        }

                        break;
                    case 2:
                        player.rest();
                        break;
                    case 3:
                        player.viewStats();
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            }
            else {
                System.out.println("Enter valid input");
            }
        }
    }
}