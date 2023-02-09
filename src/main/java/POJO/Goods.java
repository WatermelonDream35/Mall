package POJO;

/**
 * @author 杨梓韩
 * @version 1.0
 * @date 2023/2/3
 * @注释
 */
public class Goods {
    private Integer id;
    private String goodsName;
    private String img;
    private Float price;
    private Integer stockNumber;
    private Integer buyNumber;
    private Integer salesNumber;
    private String type;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(Integer stockNumber) {
        this.stockNumber = stockNumber;
    }

    public Integer getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }

    public Integer getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(Integer salesNumber) {
        this.salesNumber = salesNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", img='" + img + '\'' +
                ", price=" + price +
                ", stockNumber=" + stockNumber +
                ", buyNumber=" + buyNumber +
                ", salesNumber=" + salesNumber +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
