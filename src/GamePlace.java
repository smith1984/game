public class GamePlace {

    private int quantityCells; //размер игрового поля
    private int[][] placeForGame; //матрица игрового поля, содержащая размещение кораблей

    //конструктор объекта GamePlace для создания игрового поля и заполнения его еденицами
    GamePlace(int quantityCells){
        this.setQuantityCells(quantityCells);
        this.placeForGame = new int[this.getQuantityCells()][this.getQuantityCells()];
        for (int i = 0; i < this.getQuantityCells(); i++)
            for (int j = 0; j < this.getQuantityCells(); j++)
                placeForGame[i][j] = 1;
    }//GamePlace()

    //геттер quantityCells
    int getQuantityCells() {
        return quantityCells;
    }

    //сеттер quantityCells
    void setQuantityCells(int quantityCells) {
        this.quantityCells = quantityCells;
    }

    //геттер placeForGame
    int[][] getPlaceForGame() {
        return placeForGame;
    }

    //сеттер ячейки поля
    public void setPlaceForGame(int x, int y, int value) {
        this.placeForGame[y][x] = value;
    }
}
