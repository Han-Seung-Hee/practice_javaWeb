package kr.co.practices.pro1_eatgo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MenuItem {

    @Id
    @GeneratedValue
    private long id;
    private long resId;
    private String name;

    public MenuItem(String name) {
        this.name =name;
    }

    public MenuItem() {

    }

    public String getName(){
        return name;
    }
}
