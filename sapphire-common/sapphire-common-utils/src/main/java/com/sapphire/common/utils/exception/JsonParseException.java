/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.utils.exception;

/**
 *
 * @author yunpeng.byp
 * @version $Id: JsonParseException.java, v 0.1 2017年10月21日 下午10:39 yunpeng.byp Exp $
 */
public class JsonParseException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param  message the detail message (which is saved for later retrieval
     *         by the {@link #getMessage()} method).
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A <tt>null</tt> value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     * @since 1.4
     */
    public JsonParseException(String message, Throwable cause) {
        super(message, cause);
    }
}