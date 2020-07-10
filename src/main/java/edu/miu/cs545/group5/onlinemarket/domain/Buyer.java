package edu.miu.cs545.group5.onlinemarket.domain;

import javax.persistence.Entity;

@Entity
public class Buyer extends User {
    private int point = 0;


    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
