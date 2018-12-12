package Tiles;

import barleybreakers.GameField;
import barleybreakers.TilePosition;
import barleybreakers.FieldObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ismail
 */
abstract  public class Tile extends FieldObject<TilePosition> {
    public Tile(GameField field) {

        super(field);
    }


    protected String image;

    protected boolean CanBeMoved;

    protected String Label;


    public void setLabel(String label) {
        this.Label = label;
    }

    public String getLabel() {
        return this.Label;

    }

    @Override
    public void setPosition(TilePosition position) {
        if (position.isValid()) {
            _position = position;

        }

    }


    public String GetImage() {
        return this.image;
    }
    
}
