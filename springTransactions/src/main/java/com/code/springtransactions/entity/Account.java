package com.code.springtransactions.entity;



public class Account {
    private Integer id;
    private Integer money;
    private String name;

    public Account() {
    }

    public Account(Integer id, Integer money, String name) {
        this.id = id;
        this.money = money;
        this.name = name;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return money
     */
    public Integer getMoney() {
        return money;
    }

    /**
     * 设置
     * @param money
     */
    public void setMoney(Integer money) {
        this.money = money;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Account{id = " + id + ", money = " + money + ", name = " + name + "}";
    }
}
