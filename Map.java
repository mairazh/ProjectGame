import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map extends Pane {
    private int[][] map;
    private int size;
    private Position start;
    private int UNIT = 50;

    Map(String s) {
        try {
            // читает текстовый файл
            Scanner scanner = new Scanner(new File(s));
            size = scanner.nextInt();
            // задаем размер массива
            map = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    map[i][j] = scanner.nextInt();
                    // берем координаты игрока
                    if(map[i][j] == 2) start = new Position(i, j);
                }
            }
            // вызов метода для рисования стен
            drawWalls();
        } catch (FileNotFoundException exception) {
            System.out.println("> java Game map.txt\nEnter in this format.");
        }
    }

    public void drawWalls() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // создание квадрата в точке x y
                // и установака длины и ширины
                Rectangle rectangle = new Rectangle(j*UNIT, i*UNIT, UNIT, UNIT);
                getChildren().add(rectangle);
                // делает границы черными
                rectangle.setStroke(Color.BLACK);
                // красит квадрат в нужный цвет
                if(map[i][j] == 0) {
                    rectangle.setFill(Color.WHITE);
                } else if (map[i][j] == 1) {
                    rectangle.setFill(Color.BLACK);
                } else if(map[i][j] == 2) {
                    rectangle.setFill(Color.WHITE);
                }
            }
        }
    }

    public int getValueAt(int x, int y) {
        return map[x][y];
    }

    public int getUnit() {
        return UNIT;
    }

    public Position getStartPosition() {
        return start;
    }

    public int getSize() {
        return size;
    }
}
