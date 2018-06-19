import java.util.ArrayList;

public class ShipManagement {

    private int quantityShips;//количество кораблей желаемое пользователем
    private int quantityShipsReal;// реальное количество созданных (оставшихся) кораблей
    private Ship[] Ships;//массив с кораблями
    private int count;//подсчет количества ходов игрока

    //заполнение массива кораблями
    ShipManagement(GamePlace gamePlace, int size, int quantityShips){
        this.setQuantityShips(quantityShips);
        Ship ship;
        this.setQuantityShipsReal(0);//устанавливаем ноль реальное количество кораблей
        this.setCount(0);//устанавливаем на ноль количество ходов
        Ship[] ShipsTemp = new Ship[quantityShips];// создаем промежуточный массим кораблей
        //пытаемся создать(100 раз на один корабль) и заносим в массив
        for (int i = 0; i < quantityShips; i++){
            int tempter = 0;
            do{
                ship = new Ship(gamePlace, size);
                tempter++;
                if (ship.getLocationCells().size() != 0){
                    ShipsTemp[i] = ship;
                    setQuantityShipsReal(this.getQuantityShipsReal() + 1);
                    System.out.println(ship.getLocationCells());
                    break;}
            }
            while (tempter < 1000);
        }
        Ships = new Ship[this.getQuantityShipsReal()];//заносим данные о реально созданных кораблях
        for (int i = 0, j = 0; i < ShipsTemp.length; i++)
            if (ShipsTemp[i] != null){
                Ships[j] = ShipsTemp[i];
                j++;
            }

    }

    //произведение выстрела по игровому полю
    int fire(String cell){
        this.setCount(this.getCount() + 1);
        for(int i = 0; i < this.Ships.length ; i++){
            ArrayList<String> locationCells = this.Ships[i].getLocationCells();
            if(locationCells == null){continue;}
            if(locationCells.contains(cell)){
                locationCells.remove(cell);
                if (locationCells.isEmpty()){
                    quantityShipsReal--;
                    return 1;//подбил
                }
                else{
                    return 0;//попал
                }
            }
        }
        return 2;// промах
    }

    //проверка на наличия кораблей на поле
    boolean isStillAliveShips(){

        for(int i = 0; i < this.Ships.length ; i++){
            ArrayList<String> locationCells = this.Ships[i].getLocationCells();
            if(locationCells == null){continue;}
            if (!locationCells.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    //геттер желаемого количества кораблей
   int getQuantityShips() {
        return quantityShips;
    }

    //сеттер желаемого количества кораблей
    void setQuantityShips(int quantityShips) {
        this.quantityShips = quantityShips;
    }

    //геттер реального количества кораблей
    int getQuantityShipsReal() {
        return quantityShipsReal;
    }

    //сеттер реального количества кораблей
    void setQuantityShipsReal(int quantityShipsReal) {
        this.quantityShipsReal = quantityShipsReal;
    }

    //геттер количества ходов

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
