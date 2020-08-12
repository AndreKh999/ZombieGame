public class Range {
    private int low;
    private int high;

    public Range(int a, int b) {
        low = a;
        high = b;
    }

    public Range() {
        low = 0;
        high = 0;
    }

    //getters and setters
    public void setHigh(int h) {
        if (h <= getLow())
            System.out.println("invalid case");
        this.high = h;
    }


    public void setLow(int l) {
        if (getHigh() <= l){
            System.out.println("invalid case");
        }
        this.low = l;
    }

    public int getHigh() {
        return high;
    }

    public int getLow() {
        return low;
    }
}
