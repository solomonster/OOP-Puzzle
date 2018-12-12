/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import barleybreakers.GameField;
import barleybreakers.TilePosition;
import Tiles.SimpleTile;
import Tiles.EmptyTile;
import Tiles.FixedTile;
import Tiles.Tile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ismail
 */
public class BasicBarleyBreakBoneFactory  {

   // @Override

    public List<Tile> createBones(GameField field) {
        List<Tile> TileList = new ArrayList();

        int[] availableBlock = {1, 4, 6, 7, 10, 11, 13};


        int count = 1;
        int stick = (int) (Math.random() * 6);
        int block = 0;
        do {
            block = (int) (Math.random() * 6);
        } while (stick != block);
        for (int row = 1; row <= 4; row++) {
            for (int col = 1; col <= 4; col++) {
                if (count == availableBlock[stick]) {
 
                    SimpleTile newTile = new SimpleTile(field);
                    newTile.setPosition(new TilePosition(row, col));
                    newTile.setLabel(String.valueOf(count));
                    TileList.add(newTile);

                } else if (count == availableBlock[block]) {
                    FixedTile newTile = new FixedTile(field);
                    newTile.setPosition(new TilePosition(row, col));
                    newTile.setLabel(String.valueOf(count));
                    TileList.add(newTile);
                } else if (count < 16) {
                    SimpleTile newTile = new SimpleTile(field);
                    newTile.setPosition(new TilePosition(row, col));
                    newTile.setLabel(String.valueOf(count));
                    TileList.add(newTile);
                } else {
                    EmptyTile newTile = new EmptyTile(field);
                    newTile.setPosition(new TilePosition(row, col));
                    newTile.setLabel(String.valueOf(count));
                    TileList.add(newTile);
                }
                count++;
            }
        }

        return TileList;
    }
}
