package com.goonmeonity.domain.service.board.error;

import com.goonmeonity.core.error.NotFoundError;

public class BoardRecommendationDoesNotExistError extends NotFoundError {
    public BoardRecommendationDoesNotExistError(){
        this("Board Recommendation does not exist.");
    }

    public BoardRecommendationDoesNotExistError(String message){
        super(message);
    }
}
