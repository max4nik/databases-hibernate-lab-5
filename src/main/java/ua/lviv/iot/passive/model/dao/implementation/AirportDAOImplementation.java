package ua.lviv.iot.passive.model.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.lviv.iot.passive.HibernateUtil;
import ua.lviv.iot.passive.model.dao.AbstractDAO;
import ua.lviv.iot.passive.model.entity.Airport;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AirportDAOImplementation implements AbstractDAO<Airport> {
  protected final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  @Override
  public Collection<Airport> findAll() {
    List<Airport> airports = new ArrayList<>();

    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      airports = session.createQuery("from Airport ").getResultList();
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return airports;
  }

  @Override
  public Airport findOne(Integer id) throws SQLException {
    Airport airport = null;

    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      airport = session.get(Airport.class, id);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return airport;
  }

  @Override
  public void create(Airport airport) throws SQLException {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      session.save(airport);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(Integer id, Airport airport) throws SQLException {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      session.update(airport);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  @Override
  public void delete(Integer id) {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      Airport airport = session.get(Airport.class, id);
      if (airport != null) {
        session.delete(airport);
      }
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}

