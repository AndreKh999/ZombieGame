import java.util.ArrayList;
import java.util.Collections;

public class WeaponTrader implements Trader, Cloneable{

    private Inventory inventory;

    public WeaponTrader(){
        inventory = new Inventory(4);
        inventory.addItem(new Weapon("Knife", 1, 5), 5);
        inventory.addItem(new Weapon("Dagger", 1, 10), 10);
        inventory.addItem(new Weapon("FireBall", 1, 15), 15);
        Collections.sort(inventory.getInventoryItems());
    }

    public void printArrayOfInv(){
        for (int i = 0; i < inventory.getInventoryItems().size(); ++i) {
            System.out.println(inventory.getInventoryItems().get(i));
        }
    }

    protected Object clone() throws CloneNotSupportedException{
        WeaponTrader wT = (WeaponTrader) super.clone();
        wT.inventory = (Inventory) inventory.clone();
        return wT;
    }

    @Override
    public int buy(Weapon item) {
        inventory.addItem(item, 15);
        return 15;
    }

    @Override
    public Weapon sell(int itemNumber) {
        Weapon res = inventory.getWeapon(itemNumber - 1);
        inventory.getInventoryItems().remove(itemNumber - 1);
        return res;
    }

    @Override
    public int getPrice(int itemNumber) {
        return inventory.getItemPrice(itemNumber - 1);
    }

    @Override
    public void listAllItems() {
        Collections.sort(inventory.getInventoryItems());
        ArrayList<String> listOfNames = inventory.listAllItems();
        ArrayList<Integer> listOfRanges = inventory.listOfRanges();
        for (int i = 0; i < inventory.getInventoryItems().size(); i++){
            System.out.println(i + 1 + ") Weapon name: " + listOfNames.get(i) + ", Range: " + listOfRanges.get(i) + ", Price: " +
                    inventory.getItemPrice(i));
        }
    }

}
