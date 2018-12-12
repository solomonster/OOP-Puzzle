package barleybreakers;


import Tiles.SimpleTile;
import Tiles.EmptyTile;
import Tiles.FixedTile;
import Tiles.Tile;
import java.util.ArrayList;
import java.util.List;
//import BarleyBreak.Bones.*;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ismail
 */
/**
 * ������������� ����, ��������� �� �����
 */
public class GameField {

    // ----------------------------------------------------------------------------
    public GameField() {
        setSize(4, 4);
    }

    //������ ��������
    private List<Tile> BoneList = new ArrayList();


    Tile bone(TilePosition pos) {
        for (Tile item : this.BoneList) {
            if (item.position().equals(pos)) {
                return item;
            }
        }

        return null;
    }

    void setBone(int col, int row, Tile bone, String number) {

        bone.setPosition(new TilePosition(row, col));

        if (bone instanceof EmptyTile) {
        }
        else if (bone instanceof FixedTile) {
            ((FixedTile) bone).setLabel(number); }
        else if (bone instanceof SimpleTile) {
            ((SimpleTile) bone).setLabel(number);}

        BoneList.add(bone);

    }

    void setBones(List<Tile> List) {
        BoneList.clear();
        BoneList = List;

    }

    public boolean move(TilePosition p) {
        Tile current = bone(p);
        Tile emptyBone;
        for (Tile item : this.BoneList) {
            if (item instanceof EmptyTile) {
                emptyBone = item;
         
                if (canBeMoved(current.position().row(), current.position().column(), emptyBone.position().row(), emptyBone.position().column())) {

                    TilePosition emptyPosition = emptyBone.position();

                    int Y = current.position().row();
                    int X = current.position().column();

                    current.setPosition(emptyPosition);
                    emptyBone.setPosition(new TilePosition(Y, X));

                    return true;
                } else
                    return false;


            }
        }
        return false;

    }

    public boolean determinateWin() {
        int start = 1;
        for (int row = 1; row <= height(); row++) {

            for (int col = 1; col <= width(); col++) {
                TilePosition position = new TilePosition(col, row);
                Tile current = bone(position);//.label().getNumber();
                int Number = -1;
                if (current instanceof EmptyTile) {
                    if (col != 4 & row != 4)
                        return false;
                } else {
                    if (current instanceof FixedTile) {
                        Number = Integer.parseInt(((FixedTile) current).getLabel());
                    }

                    if (current instanceof SimpleTile) {
                        Number = Integer.parseInt(((SimpleTile) current).getLabel());
                    }


                    if (start != Number)
                        return false;
                }
                start++;

            }
        }

        return true;
    }


    private boolean canBeMoved(double x_1, double y_1, double x_2, double y_2) {


        if (y_1 > y_2) {
            if ((x_1 == x_2) && y_1 - y_2 == 1) {
                return true;
            }
        } else if (y_1 < y_2) {
            if ((x_1 == x_2) && y_2 - y_1 == 1) {
                return true;
            }
        }

        if (x_1 > x_2) {
            if ((y_1 == y_2) && x_1 - x_2 == 1) {
                return true;
            }
        } else if (x_1 < x_2) {
            if ((y_1 == y_2) && x_2 - x_1 == 1) {
                return true;
            }
        }


        return false;
    }

    public List<Tile> getBones() {

        return this.BoneList;
    }
    
    
    public void NoMixArray() {
        List<Integer> Numbers = new ArrayList();


        for (int i = 0; Numbers.size() != 14; i++) {
            int fistnumber;
            int secondnumber;
            do {
                fistnumber = (int) (Math.random() * 15);
                secondnumber = (int) (Math.random() * 15);
            }
            while (Numbers.contains(fistnumber) || Numbers.contains(secondnumber));

            Numbers.add(fistnumber);
            Numbers.add(secondnumber);

            Tile first = BoneList.get(fistnumber);

            Tile second = BoneList.get(secondnumber);

            if (first instanceof FixedTile || second instanceof FixedTile)
                continue;

            TilePosition firstBone = first.position();

            int Y = second.position().row();
            int X = second.position().column();


            second.setPosition(firstBone);

            first.setPosition(new TilePosition(Y, X));


        }
        
    }

    public void MixArray() {
        List<Integer> Numbers = new ArrayList();


        for (int i = 0; Numbers.size() != 14; i++) {
            int fistnumber;
            int secondnumber;
            do {
                fistnumber = (int) (Math.random() * 15);
                secondnumber = (int) (Math.random() * 15);
            }
            while (Numbers.contains(fistnumber) || Numbers.contains(secondnumber));

            Numbers.add(fistnumber);
            Numbers.add(secondnumber);

            Tile first = BoneList.get(fistnumber);

            Tile second = BoneList.get(secondnumber);

            if (first instanceof FixedTile || second instanceof FixedTile)
                continue;

            TilePosition firstBone = first.position();

            int Y = second.position().row();
            int X = second.position().column();


            second.setPosition(firstBone);

            first.setPosition(new TilePosition(Y, X));


        }


    }

    public void clear() {
        BoneList.clear();
    }

    private void removeBone(TilePosition pos) {

        BoneList.remove(bone(pos));
    }


    // ----------------------- ������ � ������ ���� ------------------------------
    private int _width;
    private int _height;

    public void setSize(int width, int height) {
        _width = width;
        _height = height;
    }

    public int width() {
        return _width;
    }

    public int height() {
        return _height;
    }


}
