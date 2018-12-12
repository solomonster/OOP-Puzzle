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
    // -- ��������� ��������� �������� �� ����������� � ��������� ��� ���� ������� --

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

    // ------------------ ������� ������ ��������� ---------------------

    private int _row;// = _verticalRange.min();
    private int _column;// = _horizontalRange.min();

    public TilePosition(int row, int col) {
        if (!isValid(row, col)) {  //  TODO �������� ����������
        }

        _row = row;
        _column = col;
    }

    public int row() {

        if (!isValid()) {  //  TODO �������� ����������
        }

        return _row;
    }

    public int column() {

        if (!isValid()) {  //  TODO �������� ����������
        }

        return _column;
    }

    public void setRow(int row) {
        _row = row;
    }

    public void setColumn(int column) {
        _column = column;
    }

    // ������� ����� ����� ����������, ���� ���������� ��������� ���������� ��������
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

    // ------------------ ���������� � �������� ������� ������� ---------------------

    public TilePosition next(Direction direct) {

        int[] newPos = calcNewPosition(_row, _column, direct);
        return new TilePosition(newPos[0], newPos[1]);
    }

    public boolean hasNext(Direction direct) {

        int[] newPos = calcNewPosition(_row, _column, direct);
        return isValid(newPos[0], newPos[1]);
    }

    // ����������� ������ �� ���� ���������: ������ ������, ������ �������
    private int[] calcNewPosition(int row, int col, Direction direct) {

        // ������� �������� ��� ��������� �����������: (�����������,���������)
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

    // ------------------ ��������� ������� ---------------------

    @Override
    public boolean equals(Object other) {

        if (!isValid()) {  //  TODO �������� ����������
        }

        if (other instanceof TilePosition) {
            // ���� ����������, ����� �������� ��������������
            TilePosition otherPosition = (TilePosition) other;
            // ���������� ��������� ��������� �����
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