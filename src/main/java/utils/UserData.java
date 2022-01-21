package utils;

import org.apache.commons.lang.RandomStringUtils;

class UserData {
    private String name;
    private String email;
    private String password;

    public UserData() {
        this.name = RandomStringUtils.randomAlphabetic(8);
        this.email = RandomStringUtils.random(8, true, true) + "@xyz.com";
        this.password = RandomStringUtils.random(10, true, true)+"X1";
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
