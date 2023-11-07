//package common2.validator;
//
//import common2.Board;
//import common2.Position;
//import common2.validator.MovementValidator;
//
//import java.util.List;
//
//public class OrValidator implements MovementValidator {
//
//    private final List<MovementValidator> validators;
//
//    public OrValidator(List<MovementValidator> validators) {
//        this.validators = validators;
//    }
//
//    @Override
//    public boolean validateMove(Board board, Position from, Position to) {
//        for (MovementValidator validator : validators) {
//            if (validator.validateMove(board, from, to)) {
//                return true;
//            }
//        }
//        return false;
//    }
//}
