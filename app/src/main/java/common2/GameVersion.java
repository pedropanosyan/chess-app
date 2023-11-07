package common2;

import common2.Move.Move;
import common2.enums.PieceType;
import common2.validator.MovementValidator;

import java.util.List;
import java.util.Map;

public class GameVersion {

    private final List<WinningValidator> winningValidators;
    private final List<MovementValidator> globalValidators;
    private final Map<PieceType, List<MovementValidator>> validatorsByPieceType;

    public GameVersion(List<WinningValidator> winningValidators, List<MovementValidator> globalValidators, Map<PieceType, List<MovementValidator>> validatorsByPieceType) {
        this.winningValidators = winningValidators;
        this.globalValidators = globalValidators;
        this.validatorsByPieceType = validatorsByPieceType;
    }

    public List<MovementValidator> getGlobalValidators() {
        return globalValidators;
    }

    public List<MovementValidator> getValidatorsByPieceType(PieceType pieceType) {
        return validatorsByPieceType.get(pieceType);
    }

    public List<WinningValidator> getWinningValidators() {
        return winningValidators;
    }
}
