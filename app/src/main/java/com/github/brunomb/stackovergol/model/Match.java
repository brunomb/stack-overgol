package com.github.brunomb.stackovergol.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by brunomb on 3/16/2017
 */

public class Match {
    private String name;
    private Date date;

    public Match(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
        return sdf.format(date);
    }

    public Date getDate() {
        return date;
    }
}
