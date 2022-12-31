package nyu.factory;

import nyu.dao.UserDao;
import nyu.dao.impl.UserDaoImpl;

public class UserDaoFactory {
    public UserDao getUserDao(){
        return new UserDaoImpl();
    }
}
