package com.sapphire.manage.constant;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/5<br/>
 * Email: byp5303628@hotmail.com
 */
public enum TicketPriority {
                            P0(0), P1(1), P2(2), P3(3);

    private int code;

    TicketPriority(int code) {
        this.code = code;
    }

    public static TicketPriority toTicketPriority(String pr) {
        if ("p0".equalsIgnoreCase(pr)) {
            return TicketPriority.P0;
        } else if ("p1".equalsIgnoreCase(pr)) {
            return TicketPriority.P1;
        } else if ("p2".equalsIgnoreCase(pr)) {
            return TicketPriority.P2;
        } else if ("p3".equalsIgnoreCase(pr)) {
            return TicketPriority.P3;
        } else {
            throw new IllegalArgumentException(String.format("Unknown priority : \"%s\".", pr));
        }
    }

    public static TicketPriority toTicketPriority(int code) {
        if (code == 0) {
            return P0;
        } else if (code == 1) {
            return P1;
        } else if (code == 2) {
            return P2;
        } else if (code == 3) {
            return P3;
        } else {
            throw new IllegalArgumentException(
                String.format("Unknown priority code : \"%d\".", code));
        }
    }

    public int getCode() {
        return code;
    }
}
