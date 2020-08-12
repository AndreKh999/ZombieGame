public class GridMap extends Map {

    private int gridRows;
    private int gridColumns;
    private int[] obstacleMap; //{1 0 1 0 0 0 0 0 }

    public GridMap(int gR, int gC, int[] oM) {
        gridRows = gR;
        gridColumns = gC;
        obstacleMap = oM;
    }

    public void move1() {
        System.out.println("1) North, 2) East, 3) South, 4) West: ");
        int input = scanner.nextInt();


        switch (input){
            case 1:
                if (getPlayerYPos() + 1 >= gridRows) {
                    System.out.println("Out of map from Northern border");
                    this.move1();
                }
                else if (obstacleMap[(getPlayerYPos() * gridColumns) + gridColumns + getPlayerXPos()] == 1){
                    System.out.println("Obstacle found unable to move in that direction");
                    this.move1();
                } else if (obstacleMap[(getPlayerYPos() * gridColumns) + gridColumns + getPlayerXPos()] == 2) {
                    setPlayerYPos(getPlayerYPos() + 1);
                }
                else {
                    setPlayerYPos(getPlayerYPos() + 1);
                }
                break;
            case 2:
                if (getPlayerXPos() >= gridColumns - 1) {
                    System.out.println("Out of map from Eastern border");
                    this.move1();
                }
                if (obstacleMap[getPlayerXPos() + 1] == 1){
                    System.out.println("Obstacle found unable to move in that direction");
                    this.move1();
                } else if (obstacleMap[(getPlayerYPos() * gridColumns) + gridColumns + getPlayerXPos()] == 2) {
                    setPlayerXPos(getPlayerXPos() + 1);
                }
                else {
                    setPlayerXPos(getPlayerXPos() + 1);
                }
                break;
            case 3:
                if (getPlayerYPos() - 1 < 0) {
                    System.out.println("Out of map from Southern border");
                    this.move1();
                }
                if (obstacleMap[getPlayerYPos() * gridColumns + getPlayerXPos() - gridColumns] == 1){
                    System.out.println("Obstacle found unable to move in that direction");
                    this.move1();
                } else if (obstacleMap[(getPlayerYPos() * gridColumns) + gridColumns + getPlayerXPos()] == 2) {
                    setPlayerYPos(getPlayerYPos() - 1);
                }
                else {
                    setPlayerYPos(getPlayerYPos() - 1);
                }
                break;
            case  4:
                if (getPlayerXPos() - 1 < 0) {
                    System.out.println("Out of map from Western border");
                    this.move1();
                }
                if (obstacleMap[getPlayerXPos() - 1] == 1){
                    System.out.println("Obstacle found unable to move in that direction");
                    this.move1();
                } else if (obstacleMap[(getPlayerYPos() * gridColumns) + gridColumns + getPlayerXPos()] == 2) {
                    setPlayerXPos(getPlayerXPos() - 1);
                }
                else {
                    setPlayerXPos(getPlayerXPos() - 1);
                }
                break;
            default:
                System.out.println("please try to act smart, input valid direction xD");
                break;
        }
    }

    public WeaponTrader checkForShops() {
        if (obstacleMap[getPlayerYPos() * gridColumns + getPlayerXPos()] == 2)
            return new WeaponTrader();

        return null;
    }

    public int getGridRows() {
        return gridRows;
    }

    public void setGridRows(int gridRows) {
        this.gridRows = gridRows;
    }

    public int getGridColumns() {
        return gridColumns;
    }

    public void setGridColumns(int gridColumns) {
        this.gridColumns = gridColumns;
    }

    }