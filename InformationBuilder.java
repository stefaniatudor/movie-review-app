package org.example;

import org.example.User.Information;

public class InformationBuilder {
    private Information information;

    public InformationBuilder() {
        this.information = new Information();
    }

    public InformationBuilder setName(String name) {
        this.information.setName(name);
        return this;
    }

    public InformationBuilder setCountry(String country) {
        this.information.setCountry(country);
        return this;
    }

    public InformationBuilder setAge(Integer age) {
        this.information.setAge(age);
        return this;
    }

    public InformationBuilder setGender(String gender) {
        this.information.setGender(gender);
        return this;
    }

    public InformationBuilder setBirthDate(String birthDate) {
        this.information.setBirthDate(birthDate);
        return this;
    }

    public InformationBuilder setCredentials(Credentials credentials) {
        this.information.setCredentials(credentials);
        return this;
    }

    public Information build() {
        return this.information;
    }

}