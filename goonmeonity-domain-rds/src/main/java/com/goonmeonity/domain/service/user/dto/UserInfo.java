package com.goonmeonity.domain.service.user.dto;

import com.goonmeonity.domain.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfo {
    private Long id;
    private String email;
    private String nickname;

    public UserInfo(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
    }
}
