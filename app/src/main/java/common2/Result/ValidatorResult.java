package common2.Result;

import common2.Move.Move;
import common2.validator.MovementValidator;

import java.util.Optional;

public class ValidatorResult implements Result<Boolean, Move>{

    private final Boolean key;
    private final Move value;

    public ValidatorResult(Boolean key, Move value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Boolean getKey() {
        return this.key;
    }

    @Override
    public Optional<Move> getValue() {
        return Optional.ofNullable(this.value);
    }
}
