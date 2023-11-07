//package common2.validator;
//
//import common2.Board;
//import common2.Position;
//
//import java.util.List;
//
//public class AndValidator implements MovementValidator {
//
//    private final List<MovementValidator> validators;
//
//    public AndValidator(List<MovementValidator> validators) {
//        this.validators = validators;
//    }
//
//    @Override
//    public boolean validateMove(Board board, Position from, Position to) {
//        for (MovementValidator validator : validators) {
//            if (!validator.validateMove(board, from, to)) {
//                return false;
//            }
//        }
//        return true;
//    }
//}
