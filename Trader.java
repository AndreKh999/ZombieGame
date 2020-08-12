public interface Trader {
    int buy (Weapon item);
    Weapon sell(int itemNumber);
    int getPrice(int itemNumber);
    void listAllItems();
}
