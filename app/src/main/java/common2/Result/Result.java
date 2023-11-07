package common2.Result;

import java.util.Optional;

public interface Result<K,V>{

    K getKey();
    Optional<V> getValue();
}
