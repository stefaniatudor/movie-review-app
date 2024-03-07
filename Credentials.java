package org.example;

public class Credentials implements Comparable<Credentials> {
    private String email;
    private String password;

    public Credentials(String email,String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public int compareTo(Credentials o) {
        return this.email.equals(o.email) && this.password.equals(o.password)
                ? 0
                : 1;
    }
}
