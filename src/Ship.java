import java.util.ArrayList;

public class Ship implements Constants{
    private ArrayList<String> locationCells;//список занятых ячеек

    private int size;//размер корабля

    //конструктор корабля
    Ship(GamePlace gamePlace, int size) {
        this.setSize(size);
        int firstCell;//начальная ячейка
        int lastCell;//конечная ячейка
        int line;//строка или столбец в зависимости от положения корабля

        Direction direction = getDirection();//определения располжения корабля вертикально или горизонтально
        line = getLine(gamePlace.getQuantityCells());//нахождение строки или столбца в зависимости от положения корабля
        firstCell = getFirstCell(gamePlace.getQuantityCells());//номер ячейки начала корабля
        lastCell = firstCell + this.getSize() - 1;//номер ячейки конца корабля
        locationCells = new ArrayList<>();

        if (checkCells(gamePlace, line, firstCell, lastCell, direction))
            setLocationCells(gamePlace, line, firstCell, lastCell, direction);//создаем и размещаем корабль если это возможно
    }


    //определения расположения коробля вертикально или горизонтально
    Direction getDirection(){
        if((int) (Math.round(Math.random())) == 0) return Direction.Horizontal;
        else return Direction.Vertical;
    }//getDirection()

    //определение строки (столбца) где начнется корабль
    int getLine(int quantityCells){
        return (int) (Math.random()* quantityCells);
    }// getLine

    // определния столбца (строки) где начнется корабль
    private int getFirstCell(int quantityCells){
        return (int)Math.round(Math.random()*(quantityCells-this.getSize()));
    }//getFirstCell()

    //проверка возможности размещения  и размещение корабля
    private boolean checkCells(GamePlace gamePlace, int line, int firstCell, int lastCell, Direction dir){

        boolean checking = true;

        if (dir == Direction.Vertical){
            for (int i = firstCell; i <= lastCell; i++)
                if (gamePlace.getPlaceForGame()[i][line] != 1)
                    checking = false;}
        else {
            for (int i = firstCell; i <= lastCell; i++)
                    if (gamePlace.getPlaceForGame()[line][i] != 1)
                        checking = false;
            }
        return checking;
    }//checkCells

    //заполнение ячеек кораблем(0) и соседние с ним (2)
    void setLocationCells(GamePlace gamePlace, int line, int firstCell, int lastCell, Direction dir){
        if (dir == Direction.Vertical){
            for (int i = firstCell; i <= lastCell; i++){
                gamePlace.setPlaceForGame(line, i, 0);
                this.locationCells.add("" + ALPHABET.charAt(line) + (i + 1));
            }
            if((firstCell - 1) >= 0)
                gamePlace.setPlaceForGame(line, (firstCell - 1), 2);
            if((lastCell + 1) < gamePlace.getQuantityCells())
                gamePlace.setPlaceForGame(line, (lastCell + 1), 2);
            if ((line - 1) >= 0){
                for (int i = firstCell; i <= lastCell; i++)
                    gamePlace.setPlaceForGame((line - 1), i, 2);
                if((firstCell - 1) >= 0)
                    gamePlace.setPlaceForGame((line - 1), (firstCell - 1), 2);
                if((lastCell + 1) < gamePlace.getQuantityCells())
                    gamePlace.setPlaceForGame((line - 1), (lastCell + 1), 2);
            }
            if ((line + 1) < gamePlace.getQuantityCells()){
                for (int i = firstCell; i <= lastCell; i++)
                    gamePlace.setPlaceForGame((line + 1), i, 2);
                if((firstCell - 1) >= 0)
                    gamePlace.setPlaceForGame((line + 1), (firstCell - 1), 2);
                if((lastCell + 1) < gamePlace.getQuantityCells())
                    gamePlace.setPlaceForGame((line + 1), (lastCell + 1), 2);
            }

        }
        else {
            for (int i = firstCell; i <= lastCell; i++){
                gamePlace.setPlaceForGame(i, line, 0);
                this.locationCells.add("" + ALPHABET.charAt(i) + (line + 1));
            }
            if((firstCell - 1) >= 0)
                gamePlace.setPlaceForGame((firstCell - 1), line, 2);
            if((lastCell + 1) < gamePlace.getQuantityCells())
                gamePlace.setPlaceForGame((lastCell + 1), line, 2);
            if ((line - 1) >= 0){
                for (int i = firstCell; i <= lastCell; i++)
                    gamePlace.setPlaceForGame(i, (line - 1), 2);
                if((firstCell - 1) >= 0)
                    gamePlace.setPlaceForGame((firstCell - 1), (line - 1),2);
                if((lastCell + 1) < gamePlace.getQuantityCells())
                    gamePlace.setPlaceForGame((lastCell + 1), (line - 1),2);
            }
            if ((line + 1) < gamePlace.getQuantityCells()){
                for (int i = firstCell; i <= lastCell; i++)
                    gamePlace.setPlaceForGame(i, (line + 1),2);
                if((firstCell - 1) >= 0)
                    gamePlace.setPlaceForGame((firstCell - 1), (line + 1), 2);
                if((lastCell + 1) < gamePlace.getQuantityCells())
                    gamePlace.setPlaceForGame((lastCell + 1), (line + 1), 2);
            }

        }
    }

    //геттер занятых ячеек коробля
    ArrayList<String> getLocationCells() {
        return locationCells;
    }

    //геттер размера корабля
    int getSize() {
        return size;
    }

    //сеттер размера корабля
    void setSize(int size) {
        this.size = size;
    }
}
