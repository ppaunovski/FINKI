//package lab2.MovableObject;
//
//import java.util.Arrays;
//
//public class MovablesCollection {
//    public Movable []movable;
//    private static int x_MAX;
//    private static int y_MAX;
//    private int size;
//
//    public MovablesCollection(int x_MAX, int y_MAX) {
//        MovablesCollection.x_MAX = x_MAX;
//        MovablesCollection.y_MAX = y_MAX;
//        this.movable = new Movable[0];
//        this.size = 0;
//    }
//
//    public static int getX_MAX() {
//        return x_MAX;
//    }
//
//    public static int getY_MAX() {
//        return y_MAX;
//    }
//
//    public void addMovableObject(Movable m) throws MovableObjectNotFittableException{
//        if(m.getClass().toString().equals("class lab2.MovableObject.MovableCircle")){
//            MovableCircle tmp = (MovableCircle) m;
//            if(m.getCurrentXPosition() < 0 || m.getCurrentXPosition() > x_MAX
//            || m.getCurrentYPosition() < 0 || m.getCurrentYPosition() > y_MAX){
//                System.out.println("With center out of bound");
//                throw new MovableObjectNotFittableException();
//            }
//            else if(m.getCurrentXPosition() - ((MovableCircle) m).getRadius() < 0 || m.getCurrentXPosition() + ((MovableCircle) m).getRadius() > x_MAX
//                    || m.getCurrentYPosition() - ((MovableCircle) m).getRadius() < 0 || m.getCurrentYPosition() + ((MovableCircle) m).getRadius() > y_MAX){
//                System.out.println("With radius out of bound");
//                throw new MovableObjectNotFittableException();
//            }
//            this.movable = Arrays.copyOf(this.movable, ++size);
//            this.movable[size-1] = new MovableCircle(tmp);
//        }
//        if(m.getClass().toString().equals("class lab2.MovableObject.MovablePoint")){
//            if(m.getCurrentXPosition() < 0 || m.getCurrentXPosition() > x_MAX
//            || m.getCurrentYPosition() < 0 || m.getCurrentYPosition() > y_MAX){
//                System.out.println("With center out of bound");
//                throw new MovableObjectNotFittableException();
//            }
//            this.movable = Arrays.copyOf(this.movable, ++size);
//            this.movable[size-1] = new MovablePoint((MovablePoint) m);
//        }
//    }
//
//    public void moveObjectsFromTypeWithDirection (TYPE type, DIRECTION direction){
//        for(int i=0; i<movable.length; i++){
//            if(type.name().equals("POINT")){
//                if(movable[i].getClass().toString().equals("class lab2.MovableObject.MovingPoint")){
//                    switch (direction.name()){
//                        case "UP":
//                            try {
//                                movable[i].moveUp();
//                            } catch (ObjectCanNotBeMovedException e) {
//                                System.out.println(e.getMessage());
//                            }
//                            break;
//                        case "DOWN":
//                            try {
//                                movable[i].moveDown();
//                            } catch (ObjectCanNotBeMovedException e) {
//                                System.out.println(e.getMessage());
//                            }
//                            break;
//                        case "LEFT":
//                            try {
//                                movable[i].moveLeft();
//                            } catch (ObjectCanNotBeMovedException e) {
//                                System.out.println(e.getMessage());
//                            }
//                            break;
//                        case "RIGHT":
//                            try {
//                                movable[i].moveRight();
//                            } catch (ObjectCanNotBeMovedException e) {
//                                System.out.println(e.getMessage());
//                            }
//                            break;
//                    }
//                }
//            }
//            if(type.name().equals("CIRCLE")){
//                if(movable[i].getClass().toString().equals("class lab2.MovableObject.MovableCircle")){
//                    switch (direction.name()){
//                        case "UP":
//                            try {
//                                movable[i].moveUp();
//                            } catch (ObjectCanNotBeMovedException e) {
//                                System.out.println(e.getMessage());
//                            }
//                            break;
//                        case "DOWN":
//                            try {
//                                movable[i].moveDown();
//                            } catch (ObjectCanNotBeMovedException e) {
//                                System.out.println(e.getMessage());
//                            }
//                            break;
//                        case "LEFT":
//                            try {
//                                movable[i].moveLeft();
//                            } catch (ObjectCanNotBeMovedException e) {
//                                System.out.println(e.getMessage());
//                            }
//                            break;
//                        case "RIGHT":
//                            try {
//                                movable[i].moveRight();
//                            } catch (ObjectCanNotBeMovedException e) {
//                                System.out.println(e.getMessage());
//                            }
//                            break;
//                    }
//                }
//            }
//        }
//    }
//    @Override
//    public String toString() {
//        StringBuilder movablesString = new StringBuilder();
//        for(Movable movable1 : movable){
//            movablesString.append(movable1.toString() + "\n");
//        }
//        return "Collection of movable objects with size [" + size + "]:\n" + movablesString;
//    }
//}
//
//enum TYPE {
//    POINT,
//    CIRCLE
//}
//
//enum DIRECTION {
//    UP,
//    DOWN,
//    LEFT,
//    RIGHT
//}
