package ua.lviv.iot.passive.model.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.lviv.iot.passive.HibernateUtil;
import ua.lviv.iot.passive.model.dao.AbstractDAO;
import ua.lviv.iot.passive.model.entity.Flight;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FlightDAOImplementation implements AbstractDAO<Flight> {
  protected final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  @Override
  public Collection<Flight> findAll() {
    List<Flight> flights = new ArrayList<>();

    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      flights = session.createQuery("from Flight ").getResultList();
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return flights;
  }

  @Override
  public Flight findOne(Integer id) throws SQLException {
    Flight flight = null;

    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      flight = session.get(Flight.class, id);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return flight;
  }

  @Override
  public void create(Flight flight) throws SQLException {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      session.save(flight);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(Integer id, Flight flight) throws SQLException {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      session.update(flight);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  @Override
  public void delete(Integer id) {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      Flight flight = session.get(Flight.class, id);
      if (flight != null) {
        session.delete(flight);
      }
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}

