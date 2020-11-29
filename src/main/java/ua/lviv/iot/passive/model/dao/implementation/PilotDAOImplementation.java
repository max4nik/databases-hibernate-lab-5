package ua.lviv.iot.passive.model.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.lviv.iot.passive.HibernateUtil;
import ua.lviv.iot.passive.model.dao.AbstractDAO;
import ua.lviv.iot.passive.model.entity.Pilot;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PilotDAOImplementation implements AbstractDAO<Pilot> {
  protected final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  @Override
  public Collection<Pilot> findAll() {
    List<Pilot> pilots = new ArrayList<>();

    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      pilots = session.createQuery("from Pilot ").getResultList();
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return pilots;
  }

  @Override
  public Pilot findOne(Integer id) throws SQLException {
    Pilot pilot = null;

    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      pilot = session.get(Pilot.class, id);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return pilot;
  }

  @Override
  public void create(Pilot pilot) throws SQLException {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      session.save(pilot);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(Integer id, Pilot pilot) throws SQLException {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      session.update(pilot);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void delete(Integer id) {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      Pilot pilot = session.get(Pilot.class, id);
      if (pilot != null) {
        session.delete(pilot);
      }
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

