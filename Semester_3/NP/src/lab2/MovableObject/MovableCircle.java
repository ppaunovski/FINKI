//package lab2.MovableObject;
//
//public class MovableCircle implements Movable{
//    private int radius;
//    private MovablePoint center;
//
//    public MovableCircle(int radius, MovablePoint center) {
//        this.radius = radius;
//        this.center = center;
//    }
//
//    public MovableCircle(MovableCircle circle){
//        this.radius = circle.radius;
//        this.center = new MovablePoint(circle.center);
//    }
//
//    public int getRadius() {
//        return radius;
//    }
//
//    @Override
//    public void moveUp() throws ObjectCanNotBeMovedException {
//        if(MovablesCollection.getY_MAX() <= center.getCurrentYPosition()){
//            throw new ObjectCanNotBeMovedException();
//        }
//        else center.moveUp();
//    }
//
//    @Override
//    public void moveDown() throws ObjectCanNotBeMovedException {
//        if(center.getCurrentYPosition() <= 0){
//            throw new ObjectCanNotBeMovedException();
//        }
//        else center.moveDown();
//    }
//
//    @Override
//    public void moveRight() throws ObjectCanNotBeMovedException {
//        if(MovablesCollection.getX_MAX() <= center.getCurrentXPosition()){
//            throw new ObjectCanNotBeMovedException();
//        }
//        else center.moveRight();
//    }
//
//    @Override
//    public void moveLeft() throws ObjectCanNotBeMovedException {
//        if(center.getCurrentXPosition() <= 0){
//            throw new ObjectCanNotBeMovedException();
//        }
//        else center.moveLeft();
//    }
//
//    @Override
//    public int getCurrentXPosition() {
//        return center.getCurrentXPosition();
//    }
//
//    @Override
//    public int getCurrentYPosition() {
//        return center.getCurrentYPosition();
//    }
//
//    @Override
//    public String toString() {
//        return "Movable circle with center coordinates (" + getCurrentXPosition() + "," + getCurrentYPosition() + ") and radius " + getRadius();
//    }
//}
