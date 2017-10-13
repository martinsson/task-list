package com.codurance.training.tasks.java.lang;

import java.util.Optional;
import java.util.function.Consumer;

public class IfPresentDoOrElse {

    public interface Otherwise {
        void otherwise(Runnable action);
    }

    public interface IfPresent<T> {
        Otherwise then(Consumer<T> consumer);
    }

    public static <T> IfPresent<T> ifPresent(Optional<T> optional) {
        return ifPresent -> otherwise -> {
            if (optional.isPresent()) {
                ifPresent.accept(optional.get());
            } else {
                otherwise.run();
            }
        };
    }


}
