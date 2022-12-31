package nyu;

import nyu.dao.BookDao;
import nyu.dao.impl.BookDaoImpl;
import nyu.service.BookService;
import nyu.service.impl.BookServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookDao bk = (BookDao) ctx.getBean("BookDao");
        bk.save();
        BookService bs = (BookService) ctx.getBean("BookService");
        bs.save();
        ctx.close();
    }
}
