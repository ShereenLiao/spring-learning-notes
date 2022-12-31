package nyu.dao.impl;

import nyu.dao.BookDao;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class BookDaoImpl implements BookDao {
    private int connectNum;
    public void save(){
        System.out.println("dao saved");
    }

    public BookDaoImpl(){
        System.out.println("execute the constructor");
    }
    private void init() {
        System.out.println("init the book dao");
    }

    private void destory(){
        System.out.println("destory the book dao");
    }


    /**
     * 基本数据类型
     * 在xml中加property，设置name和value
     * 在类中加入setter
     * */
    private String databaseNum;
    public void setDatabaseNum(String databaseNum) {
        this.databaseNum = databaseNum;
        System.out.println(databaseNum);
    }
}
