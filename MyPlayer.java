import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MyPlayer implements Player{
    private Map map;
    private Position position;
    // обьект круг для ballman
    private Circle ballman;

    MyPlayer(Map map) {
        // получение карты
        this.map = map;
        // установка начальной позиции
        position = map.getStartPosition();
        // настройка круга для отображения
        setupCircle();
    }

    private void setupCircle() {
        ballman = new Circle();
        // установка радиуса
        ballman.setRadius(map.getUnit()/2);
        // утстановка координат
        ballman.setCenterX(position.getX()*map.getUnit()+ ballman.getRadius());
        ballman.setCenterY(position.getY()*map.getUnit()+ ballman.getRadius());
        // закрашивание цвета ballman
        ballman.setFill(Color.BLUE);
        // добавление к Pane ballman
        map.getChildren().add(ballman);
        // аниматирование шара
        animateCircle();
    }

    public void animateCircle() {
        // запуск нового потока
        new Thread(() -> {
            // бесконечный цикл для выполнения анимации
            while (true) {
                // если радиус равен 10 то поменять цвет и вернуть прежний радиус
                if(ballman.getRadius() == 10) {
                    Platform.runLater(() -> ballman.setRadius(map.getUnit() / 2));
                    // случайные числа от 0 до 256 для герерации цвета с помощью RGB
                    int red = (int) (Math.random() * 256);
                    int blue = (int) (Math.random() * 256);
                    int green = (int) (Math.random() * 256);
                    ballman.setFill(Color.rgb(red, green, blue));
                }
                // уменьшение радиуса в 0.5 пикселей
                Platform.runLater(() -> ballman.setRadius(ballman.getRadius() - 0.5));
                // остановка потока на 50 миллисекунд
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }
    // изменение координат шара
    public void moveUp() {
        int futureY = position.getY()-1;
        int futureX = position.getX();
        int currentY = position.getY();
        if(currentY > 0) {
            if (map.getValueAt(futureY, futureX) != 1) {
                position.setY(futureY);
                changeCirclePosition();
            } else {
                System.out.println("Wall!");
            }
        } else {
            System.out.println("Out of zone");
        }
    }
    // изменение координат шара
    public void moveRight() {
        int futureX = position.getX() + 1;
        int futureY = position.getY();
        int currentX = position.getX();
        if(currentX < map.getSize()-1) {
            if (map.getValueAt(futureY, futureX) != 1) {
                position.setX(futureX);
                changeCirclePosition();
            } else {
                System.out.println("Wall!");
            }
        } else {
            System.out.println("Out of zone");
        }
    }
    // изменение координат шара
    public void moveLeft() {
        int futureX = position.getX()-1;
        int futureY = position.getY();
        int currentX = position.getX();
        if(currentX > 0) {
            if (map.getValueAt(futureY, futureX) != 1) {
                position.setX(futureX);
                changeCirclePosition();
            } else {
                System.out.println("Wall!");
            }
        } else {
            System.out.println("Out of zone!");
        }
    }
    // изменение координат шара
    public void moveDown() {
        int futureY = position.getY()+1;
        int futureX = position.getX();
        int currentY = position.getY();
        if(currentY < map.getSize()-1) {
            if (map.getValueAt(futureY, futureX) != 1) {
                position.setY(futureY);
                changeCirclePosition();
            } else {
                System.out.println("Wall!");
            }
        } else {
            System.out.println("Out of zone");
        }
    }

    // установка координат шара по текщему положения
    private void changeCirclePosition() {
        ballman.setCenterX(position.getX()*map.getUnit()+map.getUnit()/2);
        ballman.setCenterY(position.getY()*map.getUnit()+map.getUnit()/2);
    }

    public Position getPosition() {
        return position;
    }
}
