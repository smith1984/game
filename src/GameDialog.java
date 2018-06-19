import java.io.*;

public class GameDialog {
    private BufferedReader reader;

    //конструктор GameDialog
    GameDialog(){
        reader= getUserInput();
    }

    // создание ридера для ввода пользовательских данных
    private BufferedReader getUserInput(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader;
    }

    //ввод размера поля
    int quantityCells() {
        System.out.println("Введите размер игрового поля x (минимальный размер x = 5, " +
                "максимальный размер  x = 10, по умолчанию x = 7):");
        int quantity = 0;
        try {
            quantity = Integer.parseInt(reader.readLine().trim());
            if (quantity > 10 || quantity < 5)
                throw new Exception();
        }
        catch (Exception exc) {
        System.out.println("Вы ввели не коректное число, поэтому поле " +
                "будет назначено по умолчанию - 7");
        quantity = 7;
        }
        return quantity;
    }

    //ввод желаемого количества короблей
    int quantityShips() {
        System.out.println("Введите количество кораблей y (максимально - 6, по умолчанию - 5): ");
        int quantity = 0;
        try {
            quantity = Integer.parseInt(reader.readLine().trim());
            if (quantity > 6 || quantity < 1)
                throw new Exception();
        }
        catch (Exception exc) {
            System.out.println("Вы ввели не коректное число, поэтому количество кораблей " +
                    "будет назначено по умолчанию - 5");
            quantity = 5;
        }
        return quantity;
    }

    //ввод размера корабля
    int sizeShips() {
        System.out.println("Введите размер кораблей y (максимально - 4, по умолчанию - 3): ");
        int size = 0;
        try {
            size = Integer.parseInt(reader.readLine().trim());
            if (size > 4 || size < 1)
                throw new Exception();
        }
        catch (Exception exc) {
            System.out.println("Вы ввели не коректное число, поэтому количество кораблей " +
                    "будет назначено по умолчанию - 3");
            size = 3;
        }
        return size;
    }

    //вывод реального количества созданных кораблей
    void quantityShipsReal (ShipManagement shipManagement){
        if (shipManagement.getQuantityShips() == shipManagement.getQuantityShipsReal())
            System.out.println("Было созданно введенное количество кораблей - "
                    + shipManagement.getQuantityShipsReal() + " шт.");
        else
            System.out.println("Извините, но было создано кораблей в количестве"
                    + shipManagement.getQuantityShipsReal() + " шт.");
    }

    //ввод цели
    String cell(){
        System.out.println("Введите цель вида А2 или в1 (буква это номер столбца от А до Й при размере поля 10*10,\n" +
                "а цифра это номер ряда от 1 до 10 при размере поля 10*10: ");
        String  cell = "";
        try {
            cell = reader.readLine().toUpperCase().trim();
            if (Ship.ALPHABET.contains(cell.charAt(0) + "") == false ||
                    (Integer.parseInt(cell.charAt(1) + "") > 9) ||
                    ((cell.length() > 2) ? (Integer.parseInt(cell.charAt(2) + "") > 0): false) ||
                    cell.length() > 3)
                throw new Exception();//проверка на коректность введенных данных
            System.out.println("Будет осуществлен выстрел в квадрат - " + cell);
        }
        catch (Exception exc) {
            System.out.println("Вы ввели не коректные данные");
            cell = "";
        }
        return cell;
    }

    //вывод после получения цели
    void result (int fire){
        switch (fire){
            case 0:
                System.out.println("Вы попали в корабль.");
                break;
            case 1:
                System.out.println("Вы полностью потопили корабль.");
                break;
            case 2:
                System.out.println("Вы промахнулись.");
        }

    }

    //финальный вывод
    void gameOver (int count){
        System.out.println("Поздравляем, Вы выиграли. \nДля победы вы сделали " + count + " ходов(а).");
    }
}