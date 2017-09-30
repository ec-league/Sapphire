package com.sapphire.manage.constant;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/4<br/>
 * Email: byp5303628@hotmail.com
 */
public enum TicketStatus {
                          BUG(0), FIXING(1), NEED_VERIFY_ON_TEST(2), NEED_VERIFY_ON_PRODUCT(3), FIXED(4);

    private int status;

    TicketStatus(int status) {
        this.status = status;
    }

    public static String getTicketStatus(int status) {
        TicketStatus ticketStatus;
        switch (status) {
            case 0:
                ticketStatus = BUG;
                break;
            case 1:
                ticketStatus = FIXING;
                break;
            case 2:
                ticketStatus = NEED_VERIFY_ON_TEST;
                break;
            case 3:
                ticketStatus = NEED_VERIFY_ON_PRODUCT;
                break;
            case 4:
                ticketStatus = FIXED;
                break;
            default:
                throw new IllegalArgumentException(
                    String.format("Unknown Ticket Status code : \"%d\"", status));
        }
        return ticketStatus.toString();
    }

    public int getStatus() {
        return status;
    }
}
