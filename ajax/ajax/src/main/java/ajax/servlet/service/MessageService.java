package ajax.servlet.service;


import ajax.servlet.dao.MessageDao;
import ajax.servlet.entity.Student;

import java.util.List;

/**
 * Created by Acer on 2016/12/9.
 */
public class MessageService {
    private MessageDao messageDao = new MessageDao();

    public List<Student> findAll(){
       return messageDao.findAll();
    }

    public List<Student> findById(int id) {
        return messageDao.findById(id);
    }
}
