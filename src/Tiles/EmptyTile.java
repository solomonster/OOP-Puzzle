package Tiles;


import barleybreakers.GameField;
import Tiles.Tile;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ismail
 */

public class EmptyTile extends Tile {


    public EmptyTile(GameField field) {
        super(field);
        image = "img/off.png";
        CanBeMoved = false;


    }
}
