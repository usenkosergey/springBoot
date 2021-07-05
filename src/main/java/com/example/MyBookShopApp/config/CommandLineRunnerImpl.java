package com.example.MyBookShopApp.config;

import com.example.MyBookShopApp.entity.TestEntity;
import org.aspectj.weaver.ast.Test;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.logging.Logger;

@Configuration
public class CommandLineRunnerImpl implements CommandLineRunner {

    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public CommandLineRunnerImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 5; i++) {
            createTestEntity(new TestEntity());
        }

        TestEntity testEntity = readTestEntity(3L);
        if (testEntity != null) {
            Logger.getLogger(CommandLineRunnerImpl.class.getSimpleName()).info(testEntity.toString());
        } else {
            throw new NullPointerException();
        }

        TestEntity testEntity1 = updateTestEntityById(5L);
        if (testEntity1 != null) {
            Logger.getLogger(CommandLineRunnerImpl.class.getSimpleName()).info("update " + testEntity1.toString());
        } else {
            throw new NullPointerException();
        }
    }

    private TestEntity updateTestEntityById(Long id) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        Transaction tx = null;
        TestEntity result = null;


        try {
            tx = session.beginTransaction();
            TestEntity testEntity = readTestEntity(id);
            testEntity.setData("NEW DATA");
            result = (TestEntity) session.merge(testEntity);
            tx.commit();
        } catch (HibernateException hex) {
            if (tx != null) {
                tx.rollback();
            } else {
                hex.printStackTrace();
            }
        } finally {
            session.close();
        }

        return result;

    }

    private TestEntity readTestEntity(long id) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        Transaction tx = null;
        TestEntity result = null;


        try {
            tx = session.beginTransaction();
            result = session.find(TestEntity.class, id);
            tx.commit();
        } catch (HibernateException hex) {
            if (tx != null) {
                tx.rollback();
            } else {
                hex.printStackTrace();
            }
        } finally {
            session.close();
        }

        return result;
    }

    private void createTestEntity(TestEntity testEntity) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            testEntity.setData(testEntity.getClass().getSimpleName() + testEntity.hashCode());
            session.save(testEntity);
            tx.commit();
        } catch (HibernateException hex) {
            if (tx != null) {
                tx.rollback();
            } else {
                hex.printStackTrace();
            }
        } finally {
            session.close();
        }
    }
}
