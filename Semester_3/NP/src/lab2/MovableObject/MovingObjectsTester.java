//package lab2.MovableObject;
//
//public class MovingObjectsTester {
//    public static void main(String[] args) {
//        MovablePoint point = new MovablePoint(5,5,2,3);
//        MovingCircle circle = new MovingCircle(5, new MovablePoint(5,5,3,3));
//
////        System.out.println(point);
////        point.moveUp();
////        System.out.println(point);
////        point.moveDown();
////        System.out.println(point);
////        point.moveLeft();
////        System.out.println(point);
////        point.moveRight();
////        System.out.println(point);
//        MovablesCollection collection = new MovablesCollection(100, 100);
//        try {
//            collection.addMovableObject(circle);
//        } catch (MovableObjectNotFittableException e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            collection.addMovableObject(point);
//        } catch (MovableObjectNotFittableException e) {
//            System.out.println(e.getMessage());
//        }
//
//            collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.DOWN);
//            collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.DOWN);
//            collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.DOWN);
//            collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.DOWN);
//            collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.DOWN);
//            collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.DOWN);
//            collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.UP);
//
//        for(int i=0; i<collection.movable.length; i++){
//            System.out.println(collection.movable[i]);
//        }
//    }
//
//
//}
