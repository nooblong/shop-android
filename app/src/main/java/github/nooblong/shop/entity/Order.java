package github.nooblong.shop.entity;

import java.util.Map;

public class Order {
    private String name;
    private String room;
    private String tele;
    private String remark;
    private Map<Integer,Integer> productMap;

    public Order() {
    }

    public Order(String name, String room, String tele, String remark, Map<Integer, Integer> productMap) {
        this.name = name;
        this.room = room;
        this.tele = tele;
        this.remark = remark;
        this.productMap = productMap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Map<Integer, Integer> getProductMap() {
        return productMap;
    }

    public void setProductMap(Map<Integer, Integer> productMap) {
        this.productMap = productMap;
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", room='" + room + '\'' +
                ", tele='" + tele + '\'' +
                ", remark='" + remark + '\'' +
                ", productMap=" + productMap +
                '}';
    }
}
