package com.chens.service;
import com.chens.BaseMd5;
import com.chens.dao.BookDao;
import com.chens.entry.Book;
import com.chens.entry.NoteResult;
import com.chens.service.base.BaseBookService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
@Service
public class BookService implements BaseBookService {
    public BookDao getBookDao() {
        return bookDao;
    }

    @Resource(name="bookDao")
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    private BookDao bookDao;
    @Override
    public NoteResult loadBook(String id) {
        NoteResult result = new NoteResult();
        if(id == null){
            result.setStatus(0);
            result.setMsg("系统异常N");
            return result;
        }
        List<Book> list = bookDao.findByUserId(id);
        if(list == null){
            result.setMsg("空");
            result.setStatus(0);
        }
        if(list != null){
            result.setObj(list);
            result.setMsg("ok");
            result.setStatus(1);
        }
        return result;
    }
    //添加books
    public NoteResult addBooks(String userid,String bookName){
        NoteResult nr = new NoteResult();
        if(userid.equals("")){
            nr.setStatus(0);
            nr.setMsg("登入后才能创建");
            return nr;
        }
        if(bookName.equals("")){
            nr.setStatus(0);
            nr.setMsg("笔记名字不能为空！");
            return nr;
        }

        if(!bookName.equals("")){
            Book  book = new Book();
            String uid = BaseMd5.createId();
            book.setCn_notebook_id(uid);
            book.setCn_user_id(userid);
            book.setCn_notebook_name(bookName);
           book.setCn_notebook_type_id("1");
           book.setCn_notebook_desc("1");
            Timestamp times = new Timestamp(System.currentTimeMillis());
            book.setCn_notebook_createtime(times);
            int dao = bookDao.addBooks(book);
            System.out.println(book);
            nr.setObj(book);
            nr.setMsg("ok");
            nr.setStatus(dao);
            return nr;
        }
        return null;
    }
}
