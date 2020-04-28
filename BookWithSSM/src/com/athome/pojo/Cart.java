package com.athome.pojo;

public class Cart {


    String id;
    int bookId;
    String userName;
    String bookName;
    Double unitPrice;
    int count;
    Double totalPrice;

    public Cart() {
    }

    public Cart(String id, int bookId, String userName, String bookName, Double unitPrice, int count, Double totalPrice) {
        this.id = id;
        this.bookId = bookId;
        this.userName = userName;
        this.bookName = bookName;
        this.unitPrice = unitPrice;
        this.count = count;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id='" + id + '\'' +
                ", bookId=" + bookId +
                ", userName='" + userName + '\'' +
                ", bookName='" + bookName + '\'' +
                ", unitPrice=" + unitPrice +
                ", count=" + count +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
