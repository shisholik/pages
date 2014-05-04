package com.shisholik.pages.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.shisholik.pages.user.UserEntity;



public class UserResource extends BaseResource<UserEntity> {


    public UserResource(UserEntity baseEntity) {
        super(baseEntity);
    }

    @JsonView(Views.Public.class)
    public String getName() {
        return getType().getName();
    }



    public String getLogin() {
        return getType().getLogin();
    }


    @JsonView(Views.ExtendedPublic.class)
    public String getPassword_md5() {
        return getType().getPassword_md5();
    }

    @JsonView(Views.Internal.class)
    public String getInternalValue() {
        return "1212412412";
    }
}
