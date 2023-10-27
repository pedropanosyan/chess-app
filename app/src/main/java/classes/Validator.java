//package classes;
//
//import classes.enums.Direction;
//
//public class Validator {
//
//    public static boolean validateMove(Board board, Position from, Position to, Movement[] movements) {
//        Direction direction = getDirection(from, to);
//        int movementLength = getMovementLength(from, to, direction);
//
//        return !areObstacles(board, from, to, direction);
//    }
//
//    private static Direction getDirection(Position from, Position to) {
//        int rowDiff = Math.abs(to.getRow() - from.getRow());
//        int colDiff = Math.abs(to.getCol() - from.getCol());
//
//        if (rowDiff == 0 && colDiff > 0) {
//            return Direction.HORIZONTAL;
//        } else if (rowDiff > 0 && colDiff == 0) {
//            return Direction.DOWN;
//        } else if (rowDiff < 0 && colDiff == 0) {
//            return Direction.UP;
//        } else if (rowDiff == colDiff) {
//            return Direction.DIAGONAL;
//        } else if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
//            return Direction.HORSE;
//        } else {
//            throw new IllegalArgumentException("Invalid move");
//        }
//    }
//
//    private static int getMovementLength(Position from, Position to, Direction direction) {
//        return switch (direction) {
//            case HORIZONTAL -> Math.abs(to.getCol() - from.getCol());
//            case DOWN, UP, DIAGONAL -> Math.abs(to.getRow() - from.getRow());
//            case HORSE -> 3;
//        };
//    }
//
//    private static boolean areObstacles(Board board, Position from, Position to, Direction direction) {
//        return false;
//    }
//}
