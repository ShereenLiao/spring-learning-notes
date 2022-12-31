package nyu.factory;

import nyu.dao.OrderDao;
import nyu.dao.impl.OrderDaoImpl;

public class OrderDaoFactory {
    public static OrderDao getOrderDao(){
        return new OrderDaoImpl();
    }


}
