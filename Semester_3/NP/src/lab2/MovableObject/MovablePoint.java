//package lab2.MovableObject;
//
//public class MovablePoint implements Movable{
//    private int x;
//    private int y;
//    private int xSpeed;
//    private int ySpeed;
//
//    public MovablePoint(int x, int y, int xSpeed, int ySpeed) {
//        this.x = x;
//        this.y = y;
//        this.xSpeed = xSpeed;
//        this.ySpeed = ySpeed;
//    }
//
//    public MovablePoint(MovablePoint point){
//        this.x = point.x;
//        this.y = point.y;
//        this.xSpeed = point.xSpeed;
//        this.ySpeed = point.ySpeed;
//    }
//
//    public void setX(int x) {
//        this.x = x;
//    }
//
//    public void setY(int y) {
//        this.y = y;
//    }
//
//    @Override
//    public void moveUp() throws ObjectCanNotBeMovedException {
//        if(MovablesCollection.getY_MAX() <= y){
//            throw new ObjectCanNotBeMovedException();
//        }
//        else y++;
//    }
//
//    @Override
//    public void moveDown() throws ObjectCanNotBeMovedException {
//        if(y <= 0){
//            throw new ObjectCanNotBeMovedException();
//        }
//        else y--;
//    }
//
//    @Override
//    public void moveRight() throws ObjectCanNotBeMovedException {
//        if(MovablesCollection.getX_MAX() <= x){
//            throw new ObjectCanNotBeMovedException();
//        }
//        else x++;
//    }
//
//    @Override
//    public void moveLeft() throws ObjectCanNotBeMovedException {
//        if(x <= 0){
//            throw new ObjectCanNotBeMovedException();
//        }
//        else x--;
//    }
//
//    @Override
//    public int getCurrentXPosition() {
//        return x;
//    }
//
//    @Override
//    public int getCurrentYPosition() {
//        return y;
//    }
//
//    @Override
//    public String toString() {
//        return "Movable point with coordinates (" + getCurrentXPosition() + "," + getCurrentYPosition() + ")";
//    }
//}
