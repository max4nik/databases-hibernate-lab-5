package ua.lviv.iot.passive.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "airport")
public class Airport {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name")
  private String name;
  @Column(name = "amount_of_workers")
  private Integer amountOfWorkers;
  @Column(name = "city")
  private String city;

  public Airport(Integer id, String name, Integer amountOfWorkers, String city) {
    this.id = id;
    this.name = name;
    this.amountOfWorkers = amountOfWorkers;
    this.city = city;
  }

  public Airport(String name, Integer amountOfWorkers, String city) {
    this(null, name, amountOfWorkers, city);
  }

  public Airport() {

  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAmountOfWorkers() {
    return amountOfWorkers;
  }

  public void setAmountOfWorkers(Integer amountOfWorkers) {
    this.amountOfWorkers = amountOfWorkers;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  @Override
  public String toString() {
    return "Airport{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", amountOfWorkers=" + amountOfWorkers +
        ", city='" + city + '\'' +
        '}';
  }
}
