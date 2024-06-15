package org.example.com;

public class Article {
    String article;
    String price;
    String discount;

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Article(String article, String price, String discount) {
        this.article = article;
        this.price = price;
        this.discount = discount;
    }
}
