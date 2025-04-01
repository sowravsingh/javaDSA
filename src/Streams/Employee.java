package Streams;

import java.util.List;

public class Employee implements Comparable<Employee> {
    int id;
    int age;
    String gender;
    String city;
    int rank;
    String name;
    String designation;
    List<String> contacts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public List<String> getContacts() {
        return contacts;
    }

    public void setContacts(List<String> contacts) {
        this.contacts = contacts;
    }

    public Employee(int id, int age, String gender, String city, int rank, String name, String designation, List<String> contacts) {
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.city = city;
        this.rank = rank;
        this.name = name;
        this.designation = designation;
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", city='" + city + '\'' +
                ", rank=" + rank +
                ", name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", contacts=" + contacts +
                '}';
    }

    @Override
    public int compareTo(Employee employee) {
        return this.rank- employee.getRank();
    }

//    @Override
//    public int compareTo(Object o) {
//        return this.name.compareTo(o.);
//    }
}
