package kr.co.practices.pro1_eatgo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Restaurant {

    private  String name;
    private  String addr;
    @Id
    @GeneratedValue
    private  Long id;
    @Transient
    private final List<MenuItem> menuItems = new ArrayList<>();

    public Restaurant() {
    }

    public Restaurant(Long id, String name, String addr)
    {
        this.name = name;
        this.addr = addr;
        this.id = id;
    }

    public Restaurant(String name, Long id){
        this.name =name;
        this.id = null;
        this.addr = null;
    }

    public Restaurant(String name, String addr) {
        this.name = name;
        this.addr = addr;
    }

    public Long getId(){
        return id;
    }
    public String getName() {
        return name;
    }

    public String getAddr() {
        return addr;
    }
    public String getInfo() {
        return name + " in " + addr;
    }

    public List<MenuItem> getMenuItem(){
        return menuItems;
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public void setMenuItem(List<MenuItem> menuItems) {
        for(MenuItem menuItem : menuItems) {
            addMenuItem(menuItem);
        }
    }

    public void setId(long l) {
        this.id = l;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void updInfo(String name, String addr) {
        this.name =name;
        this.addr =addr;
    }
}
