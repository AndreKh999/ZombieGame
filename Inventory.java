import java.util.ArrayList;

public class Inventory implements Cloneable{

    private ArrayList<Item> inventoryItems;
    private Item item;

    public ArrayList<Item> getInventoryItems() { return inventoryItems; }

    private class Item implements Comparable<Item>, Cloneable {
        private Weapon weapon;
        private int price;

        public Item(Weapon w, int p) {
            weapon = w;
            price = p;
        }

        public Weapon getWeapon(){
            return weapon;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return "Item { " +
                    "weapon = " + weapon.getName() +
                    ", price = " + price +
                    '}';
        }

        @Override
        public int compareTo(Item o) {
            if (this.getPrice() > o.getPrice()) return 1;
            else if (this.getPrice() < o.getPrice()) return -1;
            return 0;
            //returns positive if current obj is greater than the specified obj
            //returns zero if equal
            //returns negative if current obj is smaller than the specified obj
        }           //has weapon and price

        protected Object clone() throws CloneNotSupportedException{
            return (Item) super.clone();
        }
    }

    protected Object clone() throws CloneNotSupportedException{
        Inventory inv = (Inventory) super.clone();
        inv.item = (Item) item.clone();
        return inv;
    }

    public Inventory (int capacity) {
        inventoryItems = new ArrayList(capacity);
    }


    public void addItem(Weapon w, int price){
        Item it = new Item(w, price);
        inventoryItems.add(it);
    }

    public Weapon removeItem(int i){
        Weapon res = inventoryItems.get(i).weapon;
        inventoryItems.remove(i);
        return res;
    }

    public int getItemPrice(int index) {
        return inventoryItems.get(index).price;
    }

    public ArrayList<String> listAllItems() {
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < inventoryItems.size(); i++) {
            res.add(inventoryItems.get(i).weapon.getName());
        }
        return res;
    }


    //helper function for getting access to all ranges of weapons of the inventory
    public ArrayList<Integer> listOfRanges() {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < inventoryItems.size(); ++i) {
            res.add(inventoryItems.get(i).weapon.damageRange.getHigh() - inventoryItems.get(i).weapon.damageRange.getLow());
        }
        return res;
    }
    public Weapon getWeapon(int i){
        return inventoryItems.get(i).weapon;
    }


}
