package ua.lviv.iot.passive.model.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.lviv.iot.passive.HibernateUtil;
import ua.lviv.iot.passive.model.dao.AbstractDAO;
import ua.lviv.iot.passive.model.entity.Aircraft;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AircraftDAOImplementation implements AbstractDAO<Aircraft> {
  protected final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  @Override
  public Collection<Aircraft> findAll() {
    List<Aircraft> aircrafts = new ArrayList<>();

    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      aircrafts = session.createQuery("from Aircraft ").getResultList();
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return aircrafts;
  }

  @Override
  public Aircraft findOne(Integer id) throws SQLException {
    Aircraft aircraft = null;

    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      aircraft = session.get(Aircraft.class, id);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return aircraft;
  }

  public List<Aircraft> findByAviacompanyId(Integer aviacompanyId) {
    List<Aircraft> aircraftsByAviacompanyId = new ArrayList<>();

    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      aircraftsByAviacompanyId = session.createQuery("from Aircraft where aviacompany.id = " + aviacompanyId).getResultList();
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return aircraftsByAviacompanyId;
  }

  @Override
  public void create(Aircraft aircraft) throws SQLException {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      session.save(aircraft);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(Integer id, Aircraft aircraft) throws SQLException {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      session.update(aircraft);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  @Override
  public void delete(Integer id) {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      Aircraft aircraft = session.get(Aircraft.class, id);
      if (aircraft != null) {
        session.delete(aircraft);
      }
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}


