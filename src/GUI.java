import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by smith on 23.03.17.
 */
public class GUI extends JFrame implements ActionListener, Constants{

    private JPanel placeGameGui;//игровое поле
    private JPanel choseOpt;//панель для выбора опций игры и вывода результатов
    private JButton btnNewGame;//кнопка новая игра
    private JLabel dialog;//интерактивные сообщения для пользователя
    private JLabel labelQuantityCells;
    private JLabel labelSizeShip;
    private JLabel labelRealShips;
    private JLabel labelQuantityRealShips;
    private JLabel labelTurn;
    private JLabel labelQuantityTurn;
    private JComboBox<Integer> boxQuantityCells;
    private JComboBox<Integer> boxQuantityShips;
    private JComboBox<Integer> boxSizeShip;
    private static Integer [] quantityCells = {5, 6, 7, 8, 9, 10};
    private static Integer [] sizeShip = {1, 2, 3, 4};
    private static Integer [] quantityShips = {1, 2, 3, 4, 5, 6};
    private JButton[][] buttons;
    ShipManagement shipManagement = null;

    GUI(){

        //создание фрэйма с неизменным размером 600*400
        super( "Морской бой" ) ;
        setBounds(100, 100, 600,450);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(Color.lightGray);

        //создаем панель и метку для общения интерактивного общения с пользователем
        dialog = new JLabel("Выбирите параметры и нажмите \"Новая игра\"");
        dialog.setVerticalAlignment(SwingConstants.CENTER);
        dialog.setHorizontalAlignment(SwingConstants.CENTER);
        dialog.setOpaque(true);
        dialog.setBackground(Color.GRAY);
        dialog.setBounds(10, getHeight() - 50, getWidth() - 20, 20 );
        dialog.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        add(dialog);

        // создаем кнопку новая игра
        btnNewGame = new JButton("Новая игра");
        btnNewGame.setMargin(new Insets(2,5,2,5));
        btnNewGame.setBounds(getWidth() - 110,10, 100, 25);
        btnNewGame.addActionListener(this);
        add(btnNewGame);

        //создаем панель для выбора настроек пользователем
        choseOpt = new JPanel();
        choseOpt.setOpaque(true);
        choseOpt.setBackground(Color.lightGray);
        choseOpt.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        choseOpt.setBounds(getWidth() * 2 / 3 - 10, 20 + btnNewGame.getHeight(), getWidth() / 3,
                getHeight() - 60 - btnNewGame.getHeight() - dialog.getHeight() );
        add(choseOpt);
        choseOpt.setLayout(null);
        //размещаем компоненты для выбора настроек
        labelQuantityCells = new JLabel("<HTML>Размер<p>поля</HTML>");
        labelQuantityCells.setBounds(10, 10, 100, 30);
        labelSizeShip = new JLabel("<HTML>Размер<p>корабля</HTML>");
        labelSizeShip.setBounds(10, 20 + labelQuantityCells.getHeight(), 100, 30);
        JLabel labelQuantityShips = new JLabel("<HTML>Количество<p>кораблей</HTML>");
        labelQuantityShips.setBounds(10, 30 +labelQuantityCells.getHeight() +
                labelSizeShip.getHeight(), 100, 30);
        labelRealShips = new JLabel("<HTML>Осталось<p>кораблей</HTML>");
        labelRealShips.setBounds(10, 40 + labelQuantityCells.getHeight() +
                labelSizeShip.getHeight() + labelQuantityShips.getHeight(), 100, 30);
        labelTurn = new JLabel("<HTML>Количество<p>ходов</HTML>");
        labelTurn.setBounds(10, 50 + labelQuantityCells.getHeight() +
                labelSizeShip.getHeight() + labelQuantityShips.getHeight() +
                labelRealShips.getHeight(), 100, 30);
        boxQuantityCells = new JComboBox<>(quantityCells);
        boxQuantityCells.setSelectedIndex(2);
        boxQuantityCells.setBounds(110, 15, 50, 20);
        boxSizeShip = new JComboBox<>(sizeShip);
        boxSizeShip.setSelectedIndex(2);
        boxSizeShip.setBounds(110, 35 + boxQuantityCells.getHeight(), 50, 20);
        boxQuantityShips = new JComboBox<>(quantityShips);
        boxQuantityShips.setSelectedIndex(3);
        boxQuantityShips.setBounds(110, 55 + boxQuantityCells.getHeight() +
                boxSizeShip.getHeight(), 50, 20);
        labelQuantityRealShips = new JLabel();
        labelQuantityRealShips.setBounds(112, 75 + boxQuantityCells.getHeight() +
                boxSizeShip.getHeight() + getBoxQuantityShips().getHeight(), 50, 20);
        labelQuantityTurn = new JLabel();
        labelQuantityTurn.setBounds(112, 95 + boxQuantityCells.getHeight() +
                boxSizeShip.getHeight() + boxQuantityShips.getHeight() + labelQuantityRealShips.getHeight(),
                50, 20);
        choseOpt.add(labelQuantityCells);
        choseOpt.add(labelSizeShip);
        choseOpt.add(labelQuantityShips);
        choseOpt.add(labelRealShips);
        choseOpt.add(labelTurn);
        choseOpt.add(boxQuantityCells);
        choseOpt.add(boxSizeShip);
        choseOpt.add(boxQuantityShips);
        choseOpt.add(labelQuantityRealShips);
        choseOpt.add(labelQuantityTurn);

        //создаем панель для игрового поля
        placeGameGui = new JPanel();
        placeGameGui.setBounds(10, 10, getWidth() - choseOpt.getWidth()-30, getHeight()
                - dialog.getHeight() - 50);
        placeGameGui.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        placeGameGui.setOpaque(true);
        placeGameGui.setBackground(Color.BLUE);

        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setVisible(true);

    }

    public JComboBox<Integer> getBoxQuantityCells() {
        return boxQuantityCells;
    }

    public JComboBox<Integer> getBoxSizeShip() {
        return boxSizeShip;
    }

    public JComboBox<Integer> getBoxQuantityShips() {
        return boxQuantityShips;
    }

    public void actionPerformed (ActionEvent event) {

        if (event.getSource() == btnNewGame) {
            placeGameGui.removeAll();
            GamePlace gamePlace = new GamePlace((Integer) this.getBoxQuantityCells().getSelectedItem());
            this.shipManagement = new ShipManagement(gamePlace, (Integer)
                    this.getBoxSizeShip().getSelectedItem(), (Integer)
                    this.getBoxQuantityShips().getSelectedItem());
            labelQuantityRealShips.setText(Integer.toString(shipManagement.getQuantityShipsReal()));
            labelQuantityTurn.setText(Integer.toString(shipManagement.getCount()));
            placeGameGui.setLayout(new GridLayout((Integer) this.getBoxQuantityCells().getSelectedItem(),
                    (Integer) this.getBoxQuantityCells().getSelectedItem()));
           buttons = new JButton[ (Integer) this.getBoxQuantityCells().getSelectedItem()]
                    [ (Integer) this.getBoxQuantityCells().getSelectedItem()];
            for (int i = 0; i < (Integer) this.getBoxQuantityCells().getSelectedItem(); i++)
                for (int j = 0; j < (Integer) this.getBoxQuantityCells().getSelectedItem(); j++){
                Font f = new Font("TimesRoman", Font.PLAIN,
                        60/ (Integer) this.getBoxQuantityCells().getSelectedItem());
                buttons[i][j] = new JButton(Character.toString(ALPHABET.charAt(j))
                        + Integer.toString(i +1));
                buttons[i][j].setBackground(Color.white);
                buttons[i][j].setMargin(new Insets(2,5,2,5));
                buttons[i][j].setFont(f);
                buttons[i][j].addActionListener(this);
                placeGameGui.add(buttons[i][j]);

            }
            super.repaint();
            //placeGameGui.repaint();
            add(placeGameGui);
            dialog.setText("Выбирете ячейки для хода и нажмите на неё");
        }
        if (event.getSource() != btnNewGame){
            JButton clkBtn = (JButton)event.getSource();
            int fireShip = this.shipManagement.fire(clkBtn.getText());
            if ( fireShip < 2){
                switch (fireShip){
                    case 0:
                        dialog.setText("Вы попали в корабль, совершите следующий ход");
                        break;
                    case 1:
                        dialog.setText("Вы подбили корабль" +
                                ((shipManagement.getQuantityShipsReal() != 0)?", совершите следующий ход":""));
                }
                labelQuantityRealShips.setText(Integer.toString(shipManagement.getQuantityShipsReal()));
                labelQuantityTurn.setText(Integer.toString(shipManagement.getCount()));
                clkBtn.setBackground(Color.red);
                clkBtn.setText("X");
                clkBtn.setEnabled(false);
            }
            else{
                labelQuantityRealShips.setText(Integer.toString(shipManagement.getQuantityShipsReal()));
                labelQuantityTurn.setText(Integer.toString(shipManagement.getCount()));
                clkBtn.setBackground(Color.BLUE);
                clkBtn.setText("");
                clkBtn.setEnabled(false);
            }
            if (!shipManagement.isStillAliveShips()){
                for (JButton[] button: buttons)
                    for (JButton btn: button)
                        if (btn.getBackground() != Color.red){
                            btn.setBackground(Color.BLUE);
                            btn.setText("");
                            btn.setEnabled(false);
                        }
                dialog.setText("Поздравляем, вы подбили все корабли, совершив " + shipManagement.getCount() +
                        (shipManagement.getCount() == 1?" ход.":" ход-а(-ов)."));
            }
        }


    }
}