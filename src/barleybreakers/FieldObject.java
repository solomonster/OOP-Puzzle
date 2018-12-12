package barleybreakers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ismail
 */
public abstract class FieldObject<Position> {

    // -------------------------------------------------------------------------
    // Игровое поле.
    // -------------------------------------------------------------------------
    protected GameField _field;

    public FieldObject(GameField field) {
        _field = field;
    }

    // -------------------------------------------------------------------------
    // Позиция.
    // -------------------------------------------------------------------------
    protected Position _position;

    public Position position() {

        return _position;
    }

    public abstract void setPosition(Position position);


}
