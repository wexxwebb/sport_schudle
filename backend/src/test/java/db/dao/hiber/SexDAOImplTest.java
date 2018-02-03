package db.dao.hiber;

import db.dao.SexDAO;
import db.entities.Impl.SexImpl;
import db.entities.Sex;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static common.InsertType.NEW;
import static org.junit.Assert.*;

public class SexDAOImplTest {

    private static ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext(
                 "database.xml");
    }

    @Test
    public void getAll() {
        SexDAO sexDAO = applicationContext.getBean(SexDAOImpl.class);
        for (Sex sex : sexDAO.getAll().get()) {
            System.out.println(sex);
        }

    }

    @Test
    public void insert() {
        SexDAO sexDAO = applicationContext.getBean(SexDAOImpl.class);
        Sex sex = new SexImpl("Женский");
        sexDAO.insert(sex, NEW);
    }
}