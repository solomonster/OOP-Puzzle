package barleybreakers;


import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ismail
 */
public class TilePosition {
    // -- Диапазоны возможных значений по горизонтали и вертикали для всех позиций --

    private static TileRange _horizontalRange = new TileRange(0, 15);
    private static TileRange _verticalRange = new TileRange(0, 15);

    public static void setHorizontalRange(int min, int max) {
        if (TileRange.isValidRange(min, max)) {
            _horizontalRange = new TileRange(min, max);
        }
    }

    public static TileRange horizontalRange() {
        return _horizontalRange;
    }

    public static void setVerticalRange(int min, int max) {
        if (TileRange.isValidRange(min, max)) {
            _verticalRange = new TileRange(min, max);
        }
    }

    public static TileRange verticalRange() {
        return _verticalRange;
    }

    // ------------------ Позиция внутри диапазона ---------------------

    private int _row;// = _verticalRange.min();
    private int _column;// = _horizontalRange.min();

    public TilePosition(int row, int col) {
        if (!isValid(row, col)) {  //  TODO породить исключение
        }

        _row = row;
        _column = col;
    }

    public int row() {

        if (!isValid()) {  //  TODO породить исключение
        }

        return _row;
    }

    public int column() {

        if (!isValid()) {  //  TODO породить исключение
        }

        return _column;
    }

    public void setRow(int row) {
        _row = row;
    }

    public void setColumn(int column) {
        _column = column;
    }

    // Позиция может стать невалидной, если изменились диапазоны допустимых значений
    public boolean isValid() {
        return isValid(_row, _column);
    }

    public static boolean isValid(int row, int col) {
        return _horizontalRange.contains(col) && _verticalRange.contains(row);
    }

    @Override
    public TilePosition clone() {
        return new TilePosition(_row, _column);
    }

    // ------------------ Порождение и проверка смежных позиций ---------------------

    public TilePosition next(Direction direct) {

        int[] newPos = calcNewPosition(_row, _column, direct);
        return new TilePosition(newPos[0], newPos[1]);
    }

    public boolean hasNext(Direction direct) {

        int[] newPos = calcNewPosition(_row, _column, direct);
        return isValid(newPos[0], newPos[1]);
    }

    // Вовзвращает массив из двух элементов: индекс строки, индекс столбца
    private int[] calcNewPosition(int row, int col, Direction direct) {

        // Таблица смещения для различных направлений: (горизонталь,вертикаль)
        HashMap<Direction, int[]> offset = new HashMap<Direction, int[]>();

        offset.put(Direction.north(), new int[]{0, -1});
        offset.put(Direction.south(), new int[]{0, 1});
        offset.put(Direction.east(), new int[]{1, 0});
        offset.put(Direction.west(), new int[]{-1, 0});

        int[] newPos = new int[2];

        newPos[0] = _row + offset.get(direct)[1];
        newPos[1] = _column + offset.get(direct)[0];

        return newPos;
    }

    // ------------------ Сравнение позиций ---------------------

    @Override
    public boolean equals(Object other) {

        if (!isValid()) {  //  TODO породить исключение
        }

        if (other instanceof TilePosition) {
            // Типы совместимы, можно провести преобразование
            TilePosition otherPosition = (TilePosition) other;
            // Возвращаем результат сравнения углов
            return _row == otherPosition._row && _column == otherPosition._column;
        }

        return false;
    }

    public void setPosition(TilePosition position) {
        if (position.isValid()) {
            position.setColumn(position.column());
            position.setRow(position.row());

        }

    }
}