//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.georgiancollege.test1;

import java.util.Arrays;
import java.util.List;

public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String province;
    private String phoneNo;


    public Employee(int employeeId, String firstName, String lastName, String address, String city, String province, String phoneNo) {
        this.setEmployeeId(employeeId);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAddress(address);
        this.setCity(city);
        this.setProvince(province);
        this.setPhoneNo(phoneNo);
    }

    public int getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(int employeeId) {
        if (employeeId <= 200465000) {
            throw new IllegalArgumentException("Book Id should be greater than 200645000");
        } else {
            this.employeeId = employeeId;
        }
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) throws IllegalArgumentException {
        if (firstName.length() <= 1) {
            throw new IllegalArgumentException("First name should be more than 1 character");
        } else {
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.length() <= 1) {
            throw new IllegalArgumentException("Last name should be more than 1 character");
        } else {
            this.lastName = lastName;
        }
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        if (address.length() <= 5) {
            throw new IllegalArgumentException("Address must be more than 5 characters.");
        } else {
            this.address = address;
        }
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        if (city.length() <= 3) {
            throw new IllegalArgumentException("City must be more than 3 characters.");
        } else {
            this.city = city;
        }
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        List<String> validProvinces = Arrays.asList("AB", "BC", "MB", "NB", "NL", "NS", "NT", "NU", "ON", "PE", "QC", "SK", "YT");
        if (validProvinces.contains(province)) {
            this.province = province;
        } else {
            throw new IllegalArgumentException("Province should be in the list of: " + String.valueOf(validProvinces));
        }
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        if (!phoneNo.matches("\\(?[2-9][0-9][0-9]\\)?[-.\\s]?[2-9]\\d{2}[-.\\s]?\\d{4}")) {
            throw new IllegalArgumentException("Phone number does not match north american dialing plan");
        } else {
            this.phoneNo = phoneNo;
        }
    }
}
