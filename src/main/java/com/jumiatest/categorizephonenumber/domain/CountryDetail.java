package com.jumiatest.categorizephonenumber.domain;

public enum CountryDetail {
    Cameroon(237), Ethiopia(251), Morocco(212), Mozambique(258), Uganda(256);

    private int code;

    CountryDetail(int code) {
        this.code = code;
    }

    public static String getCode(String number) {
        for (CountryDetail countryDetail : values()) {
            if (number.contains(String.valueOf("(" + countryDetail.code + ")")))
                return String.valueOf("(" + countryDetail.code + ")");
        }

        return null;
    }

    public static String getCountryName(String number) {

        for (CountryDetail countryDetail : values()) {
            if (number.contains(String.valueOf("(" + countryDetail.code + ")")))
                return countryDetail.name();
        }
        return null;
    }

    public static String getCountryCodeFromName(String countryName) {
        for (CountryDetail countryDetail : values()) {
            if (countryDetail.name().compareToIgnoreCase(countryName)==0)
                return String.valueOf("(" + countryDetail.code + ")");
        }

        return null;
    }
}
