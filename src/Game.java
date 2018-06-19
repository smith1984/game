/**
 * Created by smith on 18.03.17.
 */
public class Game {

    public static void main (String[] args){
        GameDialog dialog = new GameDialog();//создаем объект диалога игры с пользователем
        GamePlace gamePlace = new GamePlace(dialog.quantityCells());//создаем объект игрового поля
        ShipManagement shipManagement = new ShipManagement(gamePlace,
                dialog.sizeShips(), dialog.quantityShips()); //создаем объект управления кораблями
        dialog.quantityShipsReal(shipManagement);//выводим количества созданных кораблей
        while(shipManagement.isStillAliveShips()){
            dialog.result(shipManagement.fire(dialog.cell()));//ввод цели-проверка-вывод результата
        }
        dialog.gameOver(shipManagement.getCount());//финальное слово

    }
}
