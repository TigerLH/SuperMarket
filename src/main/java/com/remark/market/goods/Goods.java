package com.remark.market.goods;

/**
 * @author hong.lin
 * @description
 * @date 2017/1/19.
 */
public abstract class Goods {
    /**
     * 商品编号
     */
    private Integer id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品价格
     */
    private double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
