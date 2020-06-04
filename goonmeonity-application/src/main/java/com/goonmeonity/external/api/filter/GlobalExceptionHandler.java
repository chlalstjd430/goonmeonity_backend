package com.goonmeonity.external.api.filter;

import com.goonmeonity.core.error.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = ConflictError.class)
    public String conflictException(ConflictError conflictError){
        return conflictError.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NotFoundError.class)
    public String notFoundException(NotFoundError notFoundError){
        return notFoundError.getMessage();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = UnauthorizedError.class)
    public String unAuthorizedException(UnauthorizedError unauthorizedError){
        return unauthorizedError.getMessage();
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = ForbiddenError.class)
    public String forbiddenException(ForbiddenError forbiddenError){
        return forbiddenError.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BadRequestError.class)
    public String badRequestException(BadRequestError badRequestError){
        return badRequestError.getMessage();
    }
}
