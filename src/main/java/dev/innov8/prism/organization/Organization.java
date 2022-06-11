package dev.innov8.prism.organization;

import dev.innov8.prism.employee.Employee;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "organization")
    private List<Employee> employees;

    @Column(name = "auth_code", unique = true, nullable = false)
    private String authCode;

    @Column(nullable = false)
    private String key;

    public Organization() {
        super();
    }

    public Organization(String id) {
        this.id = id;
    }

    public Organization(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public Organization(String id, String name, String authCode) {
        this.id = id;
        this.name = name;
        this.authCode = authCode;
    }

    public Organization(String name, List<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployees(Employee... employees) {
        this.employees.addAll(Arrays.asList(employees));
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(employees, that.employees) && Objects.equals(authCode, that.authCode) && Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, employees, authCode, key);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", authCode='" + authCode + '\'' +
                ", key='" + key + '\'' +
                '}';
    }

}
