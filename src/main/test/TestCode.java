import com.chens.BaseMd5;
import com.chens.dao.BookDao;
import com.chens.dao.NoteDao;
import com.chens.dao.ShareDao;
import com.chens.dao.UserDao;
import com.chens.entry.*;
import com.chens.service.NoteService;
import com.chens.service.base.BaseActivityService;
import com.chens.service.base.BaseNoteService;
import com.chens.service.base.BaseUserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Administrator on 2017/7/12.
 */
public class TestCode {
    @Test
    public void test1(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mvc.xml");
        System.out.println(ac);
        UserDao userDao = ac.getBean(UserDao.class);
        System.out.println(userDao);
        User user = userDao.findByName("zhouj00");
        System.out.println(user);
        BaseUserService base = ac.getBean("userService",BaseUserService.class);

    }
    //用户添加
    @Test
    public void test2(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mvc.xml");
        System.out.println(ac);
        UserDao userDao = ac.getBean(UserDao.class);
        User user  = new User();
        user.setCn_user_id(BaseMd5.manage("2"));
        user.setCn_user_name("testss");
        user.setCn_user_password(BaseMd5.manage("ping"));
        System.out.println(user);
        userDao.addUser(user);

    }
    @Test
    public void test5(){
        String coc = "[INFO] this is test [DEBUG] this is test [ERROR] this is test this is test this is test this is test [DEBUG] this is test";
       String ss = BaseMd5.createId();
       System.out.println(ss);
    }
    @Test
    public void test6(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mvc.xml");
        System.out.println(ac);
        UserDao userDao = ac.getBean(UserDao.class);
        User user = userDao.findByName("test");
        System.out.println(user);
        user.setCn_user_password(BaseMd5.manage("65999"));
        System.out.println(user);
        userDao.uppass(user);
    }
    @Test
    public void test7(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mvc.xml");
        System.out.println(ac);
       BaseUserService userService = ac.getBean(BaseUserService.class);
       Map map = userService.login("test3","65999");
       System.out.print("状态："+map.get("status"));
        System.out.print("[||]");
       System.out.print(map.get("msg"));
    }
    @Test
    public void test8 (){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mvc.xml");
        System.out.println(ac);
        NoteDao noteDao = ac.getBean(NoteDao.class);
         List<Note>  note = noteDao.findBookById("77ff706adc4043e8a733ff3a90decae6");
         System.out.println(note);
    }
    @Test
    public  void test9(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mvc.xml");
        System.out.println(ac);
        BookDao bookDao = ac.getBean(BookDao.class);
        System.out.println(bookDao);
        List<Book> lis = bookDao.findByUserId("ea09d9b1-ede7-4bd8-b43d-a546680df00b");
        System.out.println(lis.toString());

    }
    @Test
    public void test10(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mvc.xml");
        System.out.println(ac);
        BaseNoteService service =ac.getBean(BaseNoteService.class);
        NoteResult li = service.loadNote(null);
        System.out.println(li.toString());

    }
    @Test
    public void  test11(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mvc.xml");
        BaseActivityService service = ac.getBean(BaseActivityService.class);
        NoteResult nr = service.onloadActivity();
        System.out.println(nr);

    }
    @Test
    public void test12(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mvc.xml");
        BaseNoteService service = ac.getBean(BaseNoteService.class);
        NoteResult nr =  service.updateNote("051538a6-0f8e-472c-8765-251a795bc88f","保存了么？","是吗？真的保存了");
        System.out.println(nr);
        StringBuffer sb = new StringBuffer();
        sb.append("                   _ooOoo_\n");
        sb.append("                  o8888888o\n");
        sb.append("                  88\" . \"88\n");
        sb.append("                  (| -_- |)\n");
        sb.append("                  O\\  =  /O\n");
        sb.append("               ____/`---'\\____\n");
        sb.append("             .'  \\\\|     |//  `.\n");
        sb.append("            /  \\\\|||  :  |||//  \\ \n");
        sb.append("           /  _||||| -:- |||||-  \\ \n");
        sb.append("           |   | \\\\\\  -  /// |   |\n");
        sb.append("           | \\_|  ''\\---/''  |   |\n");
        sb.append("           \\  .-\\__  `-`  ___/-. /\n");
        sb.append("         ___`. .'  /--.--\\  `. . __\n");
        sb.append("      .\"\" '<  `.___\\_<|>_/___.'  >'\"\".\n");
        sb.append("     | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |\n");
        sb.append("     \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /\n");
        sb.append("======`-.____`-.___\\_____/___.-`____.-'======\n");
        sb.append("                   `=---='\n");
        sb.append("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
        sb.append("\t\t佛祖保佑       永无BUG\n");
        System.err.println(sb.toString());

    }
    @Test
    public void test13(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mvc.xml");
        System.out.println(ac);
       Book book = new Book();
     String id = BaseMd5.createId();
     book.setCn_notebook_id(id);
     book.setCn_user_id("66666666666666666");
     book.setCn_notebook_desc("1");
     book.setCn_notebook_type_id("1");
     book.setCn_notebook_name("笔记哈哈");
     Timestamp times = new Timestamp(System.currentTimeMillis());
     book.setCn_notebook_createtime(times);
        //初始状态下的：
        BookDao  dao = ac.getBean(BookDao.class);
        System.out.println(dao);
      int i =  dao.addBooks(book);
      System.out.println(i);

    }
    @Test
    public void test14(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mvc.xml");
        NoteDao noteDao = ac.getBean(NoteDao.class);
        System.out.println(noteDao);
        Note note = new Note();
        note.setCn_note_id(BaseMd5.createId());
        note.setCn_notebook_id("01e24d89-15ab-4b6a-bf6f-2e5ad10b2041");
        note.setCn_user_id("66666666666");
        note.setCn_note_title("添加添加");
        note.setCn_note_body("");
        note.setCn_note_last_modify_time(System.currentTimeMillis());
        note.setCn_note_create_time(System.currentTimeMillis());
        System.out.println(note);
        noteDao.addNote(note);
    }
    @Test
    public void test (){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mvc.xml");
        NoteDao noteDao = ac.getBean(NoteDao.class);
        noteDao.deleteNote("3e41c10632f14017a87cbfdeacce9ba1");

    }
    @Test
    public void test15 (){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mvc.xml");
           ShareDao shareDao = ac.getBean(ShareDao.class);
           Map<String ,Object >  map = new HashMap<>();
           String keyword = "月";
           int page = 20;
        //处理查询条件值
        String title = "%";
        if(keyword!=null && !"".equals(keyword)){
            title = "%"+keyword+"%";
        }
        System.out.println(title);
        //计算抓取起点
        if(page<1){
            page = 1;
        }
        int begin = (page-1)*5;
           map.put("keyword",title);
          map.put("begint",begin);
          List<Share>  ls = shareDao.findLike(map);
          System.out.println(ls);


    }
    @Test
    public void test16(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mvc.xml");
        BaseNoteService noteService = ac.getBean(BaseNoteService.class);
        NoteResult nr = noteService.searchNote("月",1);
        //keyword
        System.out.print(nr);
    }

}
