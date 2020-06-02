package com.goonmeonity.domain.service.comment.error;

import com.goonmeonity.core.error.UnauthorizedError;

public class UserDoesNotHaveCommentPermissionError extends UnauthorizedError {
    public UserDoesNotHaveCommentPermissionError(){
        this("user does not have comment permission error.");
    }

    public UserDoesNotHaveCommentPermissionError(String message){
        super(message);
    }
}
