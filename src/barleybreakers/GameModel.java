package barleybreakers;


import Tiles.Tile;
import Factory.BasicBarleyBreakBoneFactory;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ismail
 */
public class GameModel {
     // -------------------------------- Поле -------------------------------------
    private GameField gameField = new GameField();

    public GameField field() {
        return gameField;
    }

    int size;

    public GameModel() {
        // Задаем размеры поля по умолчанию
        field().setSize(4, 4);
    }

// ---------------------- Порождение обстановки на поле ---------------------

    //Простая фабрика меток
    private BasicBarleyBreakBoneFactory _BasicBarleyBreakBoneFactory = new BasicBarleyBreakBoneFactory();

    private void generateField() {

        field().clear();

        field().setSize(4, 4);

        List<Tile> BoneList = _BasicBarleyBreakBoneFactory.createBones(field());

        field().setBones(BoneList);

    }

   

    public void shuffleField() {
        field().MixArray();
    }


    public void start() {

        // Генерируем поле
        generateField();
    }
    
}
