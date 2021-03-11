import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

import java.util.List;

public class Game extends Application {
    @Override
    public void start(Stage stage) {
        String s = "";
        // получаем аргументы из cmd
        Parameters params = getParameters();
        List<String> arguments = params.getRaw();
        // если есть аргументы то считываем с файла
        if(arguments.size() > 0) {
            s = arguments.get(0);
        } else {
            System.out.println("No args found\nUsage: java Game mapName.txt");
            System.exit(1);
        }
        // создание карты
        Map map = new Map(s);
        // создание обьекта для упрвления
        MyPlayer myPlayer = new MyPlayer(map);
        // еда для игрока
        Food food = new Food(map, myPlayer);
        // двигаем игрока на основе кнопок
        map.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP: myPlayer.moveUp(); break;
                case DOWN: myPlayer.moveDown(); break;
                case LEFT: myPlayer.moveLeft(); break;
                case RIGHT: myPlayer.moveRight(); break;
            }
        });
        Scene scene = new Scene(map, map.getUnit()*map.getSize(), map.getUnit()*map.getSize());
        // запрос фокуса для получения данных с клавиатуры
        scene.getRoot().requestFocus();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
