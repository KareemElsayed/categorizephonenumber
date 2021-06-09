package com.jumiatest.categorizephonenumber.domain;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerDetail {

    private String name;

    private String number;

    private String countryCode;

    private String countryName;

    @Enumerated(value = EnumType.STRING)
    private CountryDetail country;

    @Enumerated(value = EnumType.STRING)
    private State state;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return country.getCode(number);
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return country.getCountryName(number);
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public State validatePhoneNumber(String number) {
        String patterns = "^(\\(237)\\)\\ ?[2368]\\d{7,8}$"
                + "|^(\\(251)\\)\\ ?[1-59]\\d{8}$"
                + "|^(\\(212)\\)\\ ?[5-9]\\d{8}$"
                + "|^(\\(258)\\)\\ ?[28]\\d{7,8}$"
                + "|^(\\(256)\\)\\ ?\\d{9}$";

        Pattern pattern = Pattern.compile(patterns);
        Matcher matcher = pattern.matcher(number);
        if (matcher.matches()) {
            state = State.VALID;
        } else
            state = State.NOT_VALID;
        return state;
    }
}
