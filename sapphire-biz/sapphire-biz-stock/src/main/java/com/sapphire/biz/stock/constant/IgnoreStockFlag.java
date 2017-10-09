package com.sapphire.biz.stock.constant;

/**
 * Author: Ethan Date: 2016/4/5
 */
public enum IgnoreStockFlag {
    PERSONAL(0),
    WEAK_TIME(1),
    STRONG_TIME(2);

    private int code;

    IgnoreStockFlag(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static IgnoreStockFlag toIgnoreStockFlag(int code) {
        switch (code) {
            case 0:
                return IgnoreStockFlag.PERSONAL;
            case 1:
                return IgnoreStockFlag.WEAK_TIME;
            case 2:
                return IgnoreStockFlag.STRONG_TIME;
            default:
                throw new IllegalArgumentException("Wrong code of IgnoreStockFlag");
        }
    }
}
