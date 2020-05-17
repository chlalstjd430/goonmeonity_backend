package com.goonmeonity.core.validator;

import java.util.function.Function;

public abstract class ValidatorWithError<T> implements Function<T, Boolean> {
    private Error error;

    public ValidatorWithError(Error error){
        this.error = error;
    }

    public void verify(T t){
        if(!apply(t)) {
            throw error;
        }
    }
}
