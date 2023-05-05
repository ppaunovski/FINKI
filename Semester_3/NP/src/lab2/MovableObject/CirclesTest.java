//package lab2.MovableObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;




enum TYPE {
    POINT,
    CIRCLE
}

enum DIRECTION {
    UP,
    DOWN,
    LEFT,
    RIGHT
}
class ObjectCanNotBeMovedException extends Exception{
    public ObjectCanNotBeMovedException(String message) {
        super(message);
    }
}
class MovableObjectNotFittableException extends Exception{
    public MovableObjectNotFittableException(String message) {
        super(message + " can not be fitted into the collection");
    }
}


public class CirclesTest {

    public static void main(String[] args) {

        System.out.println("===COLLECTION CONSTRUCTOR AND ADD METHOD TEST===");
        MovablesCollection collection = new MovablesCollection(100, 100);
        Scanner sc = new Scanner(System.in);
        int samples = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < samples; i++) {
            String inputLine = sc.nextLine();
            String[] parts = inputLine.split(" ");

            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            int xSpeed = Integer.parseInt(parts[3]);
            int ySpeed = Integer.parseInt(parts[4]);

            if (Integer.parseInt(parts[0]) == 0) { //point
                try {
                    collection.addMovableObject(new MovablePoint(x, y, xSpeed, ySpeed));
                } catch (MovableObjectNotFittableException e) {
                    System.out.println(e.getMessage());
                }
            } else { //circle
                int radius = Integer.parseInt(parts[5]);
                try {
                    collection.addMovableObject(new MovableCircle(radius, new MovablePoint(x, y, xSpeed, ySpeed)));
                } catch (MovableObjectNotFittableException e) {
                    System.out.println(e.getMessage());
                }
            }

            if(i == samples-1) sc.close();
        }
        System.out.println(collection);

        System.out.println("MOVE POINTS TO THE LEFT");
        collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.LEFT);
        System.out.println(collection);

        System.out.println("MOVE CIRCLES DOWN");
        collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.DOWN);
        System.out.println(collection);

        System.out.println("CHANGE X_MAX AND Y_MAX");
        MovablesCollection.setxMax(90);
        MovablesCollection.setyMax(90);

        System.out.println("MOVE POINTS TO THE RIGHT");
        collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.RIGHT);
        System.out.println(collection);

        System.out.println("MOVE CIRCLES UP");
        collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.UP);
        System.out.println(collection);


    }


}


interface Movable {
    void moveUp() throws ObjectCanNotBeMovedException;
    void moveDown() throws ObjectCanNotBeMovedException;
    void moveRight() throws ObjectCanNotBeMovedException;
    void moveLeft() throws ObjectCanNotBeMovedException;
    int getCurrentXPosition();
    int getCurrentYPosition();
    String returnType();
}

class MovablePoint implements Movable{
    private int x;
    private int y;
    private final int xSpeed;
    private final int ySpeed;

    public MovablePoint(int x, int y, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public MovablePoint(MovablePoint point){
        this.x = point.x;
        this.y = point.y;
        this.xSpeed = point.xSpeed;
        this.ySpeed = point.ySpeed;
    }

    public int getXSpeed() {
        return xSpeed;
    }

    public int getYSpeed() {
        return ySpeed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void moveUp() throws ObjectCanNotBeMovedException {
        if(MovablesCollection.getY_MAX() < y + ySpeed){
            throw new ObjectCanNotBeMovedException("Point (" + x + "," + (y + ySpeed) + ") is out of bounds");
        }
        else setY(y + ySpeed);
    }

    @Override
    public void moveDown() throws ObjectCanNotBeMovedException {
        if(y - ySpeed < 0){
            throw new ObjectCanNotBeMovedException("Point (" + x + "," +( y - ySpeed) + ") is out of bounds");
        }
        else setY(y - ySpeed);
    }

    @Override
    public void moveRight() throws ObjectCanNotBeMovedException {
        if(MovablesCollection.getX_MAX() < x + xSpeed){
            throw new ObjectCanNotBeMovedException("Point (" + (x + xSpeed) + "," + y + ") is out of bounds");
        }
        else setX(x + xSpeed);
    }

    @Override
    public void moveLeft() throws ObjectCanNotBeMovedException {
        if(x - xSpeed < 0){
            throw new ObjectCanNotBeMovedException("Point (" + (x - xSpeed) + "," + y + ") is out of bounds");
        }
        else setX(x - xSpeed);
    }

    @Override
    public int getCurrentXPosition() {
        return x;
    }

    @Override
    public int getCurrentYPosition() {
        return y;
    }

    @Override
    public String returnType() {
        return "MovablePoint";
    }

    @Override
    public String toString() {
        return "Movable point with coordinates (" + getCurrentXPosition() + "," + getCurrentYPosition() + ")";
    }
}

class MovablesCollection {
    public Movable []movable;
    private static int x_MAX;
    private static int y_MAX;
    private int size;

    public MovablesCollection(int x_MAX, int y_MAX) {
        MovablesCollection.x_MAX = x_MAX;
        MovablesCollection.y_MAX = y_MAX;
        this.movable = new Movable[0];
        this.size = 0;
    }

    public static int getX_MAX() {
        return x_MAX;
    }

    public static int getY_MAX() {
        return y_MAX;
    }

    public static void setxMax(int max){
        x_MAX = max;
    }

    public static void setyMax(int max){
        y_MAX = max;
    }

    public void addMovableObject(Movable m) throws MovableObjectNotFittableException{
        if(m.returnType().equals("MovableCircle")){
            MovableCircle tmp = (MovableCircle) m;
            if(m.getCurrentXPosition() < 0 || m.getCurrentXPosition() > x_MAX
                    || m.getCurrentYPosition() < 0 || m.getCurrentYPosition() > y_MAX){
                StringBuilder message = new StringBuilder();
                message.append(m.toString());
                String retMess = message.substring(0, 27);
                retMess += message.substring(39);
                throw new MovableObjectNotFittableException(retMess);
            }
            else if(m.getCurrentXPosition() - ((MovableCircle) m).getRadius() < 0 || m.getCurrentXPosition() + ((MovableCircle) m).getRadius() > x_MAX
                    || m.getCurrentYPosition() - ((MovableCircle) m).getRadius() < 0 || m.getCurrentYPosition() + ((MovableCircle) m).getRadius() > y_MAX){
                StringBuilder message = new StringBuilder();
                message.append(m.toString());
                String retMess = message.substring(0, 27);
                retMess += message.substring(39);
                throw new MovableObjectNotFittableException(retMess);
            }
            this.movable = Arrays.copyOf(this.movable, ++size);
            this.movable[size-1] = new MovableCircle(tmp);
        }
        if(m.returnType().equals("MovablePoint")){
            if(m.getCurrentXPosition() < 0 || m.getCurrentXPosition() > x_MAX
                    || m.getCurrentYPosition() < 0 || m.getCurrentYPosition() > y_MAX){
                throw new MovableObjectNotFittableException(m.toString());
            }
            this.movable = Arrays.copyOf(this.movable, ++size);
            this.movable[size-1] = new MovablePoint((MovablePoint) m);
        }
    }

    public void moveObjectsFromTypeWithDirection (TYPE type, DIRECTION direction){
        for(int i=0; i<movable.length; i++){
            if(type.name().equals("POINT")){
                if(movable[i].returnType().equals("MovablePoint")){
                    switch (direction.name()){
                        case "UP":
                            try {
                                movable[i].moveUp();
                            } catch (ObjectCanNotBeMovedException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "DOWN":
                            try {
                                movable[i].moveDown();
                            } catch (ObjectCanNotBeMovedException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "LEFT":
                            try {
                                movable[i].moveLeft();
                            } catch (ObjectCanNotBeMovedException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "RIGHT":
                            try {
                                movable[i].moveRight();
                            } catch (ObjectCanNotBeMovedException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                    }
                }
            }
            if(type.name().equals("CIRCLE")){
                if(movable[i].returnType().equals("MovableCircle")){
                    switch (direction.name()){
                        case "UP":
                            try {
                                movable[i].moveUp();
                            } catch (ObjectCanNotBeMovedException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "DOWN":
                            try {
                                movable[i].moveDown();
                            } catch (ObjectCanNotBeMovedException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "LEFT":
                            try {
                                movable[i].moveLeft();
                            } catch (ObjectCanNotBeMovedException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "RIGHT":
                            try {
                                movable[i].moveRight();
                            } catch (ObjectCanNotBeMovedException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                    }
                }
            }
        }
    }
    @Override
    public String toString() {
        StringBuilder movablesString = new StringBuilder();
        for(Movable movable1 : movable){
            movablesString.append(movable1.toString() + "\n");
        }
        return "Collection of movable objects with size " + size + ":\n" + movablesString;
    }
}

class MovableCircle implements Movable{
    private final int radius;
    private final MovablePoint center;

    public MovableCircle(int radius, MovablePoint center) {
        this.radius = radius;
        this.center = center;
    }

    public MovableCircle(MovableCircle circle){
        this.radius = circle.radius;
        this.center = new MovablePoint(circle.center);
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public void moveUp() throws ObjectCanNotBeMovedException {
        if(MovablesCollection.getY_MAX() < center.getCurrentYPosition() + center.getYSpeed() ){
            throw new ObjectCanNotBeMovedException("Point (" + center.getCurrentXPosition() + "," + (center.getCurrentYPosition() + center.getYSpeed()) + ") is out of bounds");
        }
        else center.moveUp();
    }

    @Override
    public void moveDown() throws ObjectCanNotBeMovedException {
        if(center.getCurrentYPosition() - center.getYSpeed() < 0){
            throw new ObjectCanNotBeMovedException("Point (" + center.getCurrentXPosition() + "," + (center.getCurrentYPosition() - center.getYSpeed()) + ") is out of bounds");
        }
        else center.moveDown();
    }

    @Override
    public void moveRight() throws ObjectCanNotBeMovedException {
        if(MovablesCollection.getX_MAX() < center.getCurrentXPosition() + center.getXSpeed()){
            throw new ObjectCanNotBeMovedException("Point (" + (center.getCurrentXPosition() + center.getXSpeed()) + "," + center.getCurrentYPosition() + ") is out of bounds");
        }
        else center.moveRight();
    }

    @Override
    public void moveLeft() throws ObjectCanNotBeMovedException {
        if(center.getCurrentXPosition() - center.getXSpeed() < 0){
            throw new ObjectCanNotBeMovedException("Point (" + (center.getCurrentXPosition() - center.getXSpeed()) + "," + center.getCurrentYPosition() + ") is out of bounds");
        }
        else center.moveLeft();
    }

    @Override
    public int getCurrentXPosition() {
        return center.getCurrentXPosition();
    }

    @Override
    public int getCurrentYPosition() {
        return center.getCurrentYPosition();
    }

    @Override
    public String returnType() {
        return "MovableCircle";
    }

    @Override
    public String toString() {
        return "Movable circle with center coordinates (" + getCurrentXPosition() + "," + getCurrentYPosition() + ") and radius " + getRadius();
    }
}

