public class Shield {
    private String type;
    private int resistance;

    private String[] types = {"wooden", "metallic", "paper", "glass"};
    private int[] resistances = {4, 6, 1, 2};

    public Shield(int input) {
        type = types[input];
        resistance = resistances[input];
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }
}
