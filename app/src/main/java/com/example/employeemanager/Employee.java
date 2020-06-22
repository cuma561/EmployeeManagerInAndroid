package com.example.employeemanager;

public class Employee {

    private long empid;
    private String firstname;
    private String lastname;
    private String gender;
    private String departments;
    private String salary;

    public Employee() {
    }

    public Employee(long empid, String firstname, String lastname, String gender, String departments, String salary) {
        this.empid = empid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.departments = departments;
        this.salary = salary;
    }

    public long getEmpid() {
        return empid;
    }

    public void setEmpid(long empid) {
        this.empid = empid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartments() {
        return departments;
    }

    public void setDepartments(String departments) {
        this.departments = departments;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empid=" + empid +
                ", Firstname='" + firstname + '\'' +
                ", Lastname='" + lastname + '\'' +
                ", Gender='" + gender + '\'' +
                ", Departments='" + departments + '\'' +
                ", Salary='" + salary + '\'' +
                '}';
    }
}
