package com.shisholik.pages;


import com.shisholik.pages.exceptions.UserAlreadyCreatedException;
import com.shisholik.pages.user.UserDAO;
import com.shisholik.pages.user.UserEntity;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class Pages {
    public UserEntity registerUser(String login, String password, String name) {
        UserDAO userDAO = new UserDAO();


        UserEntity user = userDAO.getByLogin(login);
        if (user != null) {
            throw new UserAlreadyCreatedException();
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        userEntity.setLogin(login);
        userEntity.setPassword_md5(DigestUtils.md5Hex(password));
        userDAO.create(userEntity);
        return userEntity;
    }
}
