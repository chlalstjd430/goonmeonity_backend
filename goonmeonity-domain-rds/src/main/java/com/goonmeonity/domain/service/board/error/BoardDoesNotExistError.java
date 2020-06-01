package com.goonmeonity.domain.service.board.error;

import com.goonmeonity.core.error.NotFoundError;

public class BoardDoesNotExistError extends NotFoundError {
    public BoardDoesNotExistError(){
        this("Board does not exist.");
    }

    public BoardDoesNotExistError(String message){
        super(message);
    }
}
