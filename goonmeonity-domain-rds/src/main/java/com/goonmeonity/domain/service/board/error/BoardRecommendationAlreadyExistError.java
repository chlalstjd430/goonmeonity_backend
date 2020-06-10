package com.goonmeonity.domain.service.board.error;

import com.goonmeonity.core.error.ConflictError;

public class BoardRecommendationAlreadyExistError extends ConflictError {
    public BoardRecommendationAlreadyExistError(){
        this("Board Recommendation already exists.");
    }

    public BoardRecommendationAlreadyExistError(String message){
        super(message);
    }
}
