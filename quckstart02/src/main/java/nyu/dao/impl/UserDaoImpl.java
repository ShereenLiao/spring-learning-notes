package nyu.dao.impl;

import nyu.dao.UserDao;

public class UserDaoImpl implements UserDao {

    @Override
    public void save() {
        System.out.println("user dao saved");
    }
}
