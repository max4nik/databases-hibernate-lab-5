package ua.lviv.iot;

import org.hibernate.Session;
import ua.lviv.iot.passive.HibernateUtil;
import ua.lviv.iot.passive.view.MyView;

public class Application {

  public static void main(String[] args) {
    try (Session SESSION = HibernateUtil.getSession()) {
      System.out.println("Test session established. ");
      new MyView().showMenu();
    } catch (Throwable ex) {
      System.err.println("Test session failed.");
      ex.printStackTrace();
    }
  }
}

