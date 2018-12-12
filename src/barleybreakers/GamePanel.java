/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barleybreakers;

import Tiles.SimpleTile;
import Tiles.EmptyTile;
import Tiles.FixedTile;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
//import java.awt.List;
import java.awt.Point;
import javafx.event.ActionEvent;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.List;
import javax.imageio.ImageIO;
import Tiles.Tile;
import java.awt.Font;

/**
 *
 * @author Ismail
 */
public class GamePanel extends JFrame {
    private JPanel fieldPanel = new JPanel();
    private JMenuBar menu = null;
    private final String fileItems[] = new String[]{"новая игра", "выход" ,"решить"};

    private final int CELL_SIZE = 90;
    private final int TITLE_HEIGHT = 80;

    private GameModel _model = new GameModel();

    public GamePanel() {
        super();
        this.setTitle("Пятнашки");

        // Меню
        createMenu();
        setJMenuBar(menu);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Box mainBox = Box.createVerticalBox();

        // Игровое поле
        mainBox.add(Box.createVerticalStrut(10));
        fieldPanel.setDoubleBuffered(true);
        _model.start();
        createField();
        setEnabledField(false);
        mainBox.add(fieldPanel);

        setContentPane(mainBox);
        pack();
       // setResizable(false);
    }


// --------------------------- Отрисовываем поле ------------------------------    

    private void createField() {

        fieldPanel.setDoubleBuffered(true);
        fieldPanel.setLayout(new GridLayout(_model.field().height(), _model.field().width()));

        Dimension fieldDimension = new Dimension(CELL_SIZE * _model.field().height(), CELL_SIZE * _model.field().width());

        fieldPanel.setPreferredSize(fieldDimension);
        fieldPanel.setMinimumSize(fieldDimension);
        fieldPanel.setMaximumSize(fieldDimension);

        repaintField();
    }

    public void repaintField() {

        fieldPanel.removeAll();

        List<Tile> bones = _model.field().getBones();

        for (int row = 1; row <= _model.field().height(); row++) {

            for (int col = 1; col <= _model.field().width(); col++) {
                Tile temp = getBone(bones, col, row);

                if (temp != null) {

                    try {

                        Image img = ImageIO.read(getClass().getClassLoader().getResource(temp.GetImage()));
                        Image newimg = img.getScaledInstance(80, 70, Image.SCALE_SMOOTH);
                        JButton button = new JButton();
  
                        if (temp instanceof EmptyTile) {
                            button.setIcon(new ImageIcon(newimg));
                        } else {
                            if (temp instanceof SimpleTile) {
                                button = new JButton(((SimpleTile) temp).getLabel(), new ImageIcon(newimg));
                            }
                            if (temp instanceof FixedTile) {
                                button = new JButton(((FixedTile) temp).getLabel(), new ImageIcon(newimg));
                            }
                           

                        }
                        button.setFocusable(false);

                        if (temp instanceof FixedTile || temp instanceof EmptyTile) {
                            button.setEnabled(false);
                        }

                        button.setHorizontalTextPosition(SwingConstants.CENTER);

                        fieldPanel.add(button);
                        button.addActionListener(new ClickListener());
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }


                }

            }
        }

        fieldPanel.validate();
    }

    public Tile getBone(List<Tile> bones, double x, double y) {
        for (Tile item : bones) {
            if (item.position().row() == x && item.position().column() == y) {
                return item;
            }
        }
        return null;
    }

    private TilePosition buttonPosition(JButton btn) {

        int index = 0;
        for (Component widget : fieldPanel.getComponents()) {
            if (widget instanceof JButton) {
                if (btn.equals((JButton) widget)) {
                    break;
                }

                index++;
            }
        }

        int fieldWidth = _model.field().width();
        return new TilePosition(index % fieldWidth + 1, index / fieldWidth + 1);
    }

    private JButton getButton(Point pos) {

        int index = _model.field().width() * (pos.y - 1) + (pos.x - 1);

        for (Component widget : fieldPanel.getComponents()) {
            if (widget instanceof JButton) {
                if (index == 0) {
                    return (JButton) widget;
                }
                index--;
            }
        }

        return null;
    }


    private void setEnabledField(boolean on) {

        Component comp[] = fieldPanel.getComponents();
        for (Component c : comp) {
            c.setEnabled(on);
        }
    }

// ----------------------------- Создаем меню ----------------------------------  

    private void createMenu() {

        menu = new JMenuBar();
        JMenu fileMenu = new JMenu("Option");

        for (int i = 0; i < fileItems.length; i++) {

            JMenuItem item = new JMenuItem(fileItems[i]);
            item.setActionCommand(fileItems[i].toLowerCase());
            item.addActionListener(new NewMenuListener());
            fileMenu.add(item);
        }
        fileMenu.insertSeparator(1);

        menu.add(fileMenu);
    }

    public class NewMenuListener implements ActionListener {
        //@Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            String command = e.getActionCommand();
            if ("выход".equals(command)) {
                System.exit(0);
            }
            if ("новая игра".equals(command)) {
                _model.start();
                _model.shuffleField();
                createField();
            }
                
            if("решить".equals(command)) {
                _model.start();
                
            }

            
        }
    }

// ------------------------- Реагируем на действия игрока ----------------------


    private class ClickListener implements ActionListener {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {

            JButton button = (JButton) e.getSource();

            TilePosition p = buttonPosition(button);

            if (_model.field().move(p)) {
                repaintField();
                if (_model.field().determinateWin()) {
                    declareVictory();
                }
            } else {
                reportCanNotBeMoved();
            }

        }
    }

    private void declareVictory() {
        JOptionPane.showMessageDialog(null, "Вы выиграли", "Победа!", JOptionPane.PLAIN_MESSAGE);
        setEnabledField(false);
    }

    private void reportCanNotBeMoved() {
        JOptionPane.showMessageDialog(null, "Кость не может быть перемещена!", "Упс!", JOptionPane.PLAIN_MESSAGE);
    }

    
}
