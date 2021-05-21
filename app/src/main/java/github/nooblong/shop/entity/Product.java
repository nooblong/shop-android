package github.nooblong.shop.entity;

public class Product {
    private int id;
    private String name;
    private String img;
    private double price;
    private int stock;
    private int show;

    public Product() {
    }

    public Product(int id, String name, String img, double price, int stock, int show) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.price = price;
        this.stock = stock;
        this.show = show;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getShow() {
        return show;
    }

    public void setShow(int show) {
        this.show = show;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", show=" + show +
                '}';
    }
}
