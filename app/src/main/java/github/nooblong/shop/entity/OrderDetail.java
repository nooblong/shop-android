package github.nooblong.shop.entity;

import java.util.Date;
import java.util.Map;

public class OrderDetail {

    private String number;
    private String mode;
    private int state;
    private Map<Integer, Integer> good;
    private Date time;
    private double price;
    private String name;
    private String address;
    private String tele;
    private int id;
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Integer, Integer> getGood() {
        return good;
    }

    public void setGood(Map<Integer, Integer> good) {
        this.good = good;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderList{" +
                "number='" + number + '\'' +
                ", mode='" + mode + '\'' +
                ", state=" + state +
                ", good=" + good +
                ", time=" + time +
                ", price=" + price +
                '}';
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}
