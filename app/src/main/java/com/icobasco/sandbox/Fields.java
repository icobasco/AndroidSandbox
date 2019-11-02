package com.icobasco.sandbox;

import java.io.Serializable;

public class Fields implements Serializable {
    public String name = "";
    public String password = "";

    @Override
    public String toString() {
        return name + ";" + password;
    }
}
