package nyu.service.impl;

import nyu.dao.BookDao;
import nyu.dao.impl.BookDaoImpl;
import nyu.service.BookService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class BookServiceImpl implements BookService, InitializingBean, DisposableBean {
    private BookDao bookDao;
    public void save(){
        System.out.println("bookservice save");
    }

    public BookServiceImpl(){
        System.out.println("execute the bookservice constructor");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("after properties");

    }

    public void setBookDao(BookDaoImpl bookDao) {
        this.bookDao = bookDao;
        System.out.println("set book dao");
    }
}
