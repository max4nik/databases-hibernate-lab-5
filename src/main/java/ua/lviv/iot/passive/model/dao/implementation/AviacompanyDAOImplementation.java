package ua.lviv.iot.passive.model.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.lviv.iot.passive.HibernateUtil;
import ua.lviv.iot.passive.model.dao.AbstractDAO;
import ua.lviv.iot.passive.model.entity.Aviacompany;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AviacompanyDAOImplementation implements AbstractDAO<Aviacompany> {
  protected final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  @Override
  public Collection<Aviacompany> findAll() {
    List<Aviacompany> aviacompanies = new ArrayList<>();

    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      aviacompanies = session.createQuery("from Aviacompany ").getResultList();
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return aviacompanies;
  }

  @Override
  public Aviacompany findOne(Integer id) throws SQLException {
    Aviacompany aviacompany = null;

    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      aviacompany = session.get(Aviacompany.class, id);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return aviacompany;
  }

  @Override
  public void create(Aviacompany aviacompany) throws SQLException {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      session.save(aviacompany);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(Integer id, Aviacompany aviacompany) throws SQLException {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      session.update(aviacompany);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  @Override
  public void delete(Integer id) {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      Aviacompany aviacompany = session.get(Aviacompany.class, id);
      if (aviacompany != null) {
        session.delete(aviacompany);
      }
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

