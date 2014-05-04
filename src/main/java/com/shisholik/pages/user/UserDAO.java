package com.shisholik.pages.user;

import com.shisholik.pages.GenericDAO;
import com.shisholik.pages.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UserDAO extends GenericDAO<UserEntity> {
    public UserDAO() {
        super(UserEntity.class);
    }

    public UserEntity getByLogin(String login) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return (UserEntity)session.createCriteria(UserEntity.class).add(Restrictions.eq("login", login)).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
