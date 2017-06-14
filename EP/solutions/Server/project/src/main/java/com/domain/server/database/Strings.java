package com.domain.server.database;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by schelde on 13/06/17.
 */
public class Strings {
    private static final Strings singleton = new Strings();
    private List<String> stringList = new ArrayList<>();

    private Strings() {
        this.stringList.add("Initial String");
    }

    public static Strings getSingleton() {
        return singleton;
    }

    public List<String> getStringList() {
        return stringList;
    }
}
