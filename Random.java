public class Random {

    public int random(int low, int high){
        int random = (int)(Math.random() * (high - low)) + low;
        return random;
    }

    public int random(Range r){
        int random = (int)(Math.random() * (r.getHigh() - r.getLow())) + r.getLow();
        return random;
    }

}
