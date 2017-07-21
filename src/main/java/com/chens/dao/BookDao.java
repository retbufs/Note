package com.chens.dao;
import com.chens.dao.annotation.MyBatisAnnotation;
import com.chens.entry.Book;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@MyBatisAnnotation
public interface BookDao {
  public List<Book> findByUserId(String userId);
  public int addBooks(Book book);

}
