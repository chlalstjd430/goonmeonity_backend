package com.goonmeonity.domain.service.board.error;

import com.goonmeonity.core.error.UnauthorizedError;

public class BoardDoesNotHavePermissionError extends UnauthorizedError {
    public BoardDoesNotHavePermissionError(){
        this("Board does not have permission error.");
    }

    public BoardDoesNotHavePermissionError(String message){
        super(message);
    }
}
