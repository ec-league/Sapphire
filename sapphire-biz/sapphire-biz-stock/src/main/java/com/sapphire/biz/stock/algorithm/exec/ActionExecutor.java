/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.sapphire.biz.stock.algorithm.exec;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapphire.biz.stock.algorithm.action.AlgorithmAction;
import com.sapphire.biz.stock.algorithm.context.StockContext;

/**
 *
 * @author yunpeng.byp
 * @version $Id: ActionExecutor.java, v 0.1 2018年01月21日 下午9:46 yunpeng.byp Exp $
 */
@Service
public class ActionExecutor {

    private BeanFactory   beanFactory;

    private ActionBuilder actionBuilder;

    /**
     *
     */
    public void execute(StockContext context, ActionCategory category) {
        List<String> actionNames;
        switch (category) {
            case MACD_BUY:
                actionNames = actionBuilder.getShouldBuyList();
                break;
            case GENERAL_SELL:
                actionNames = actionBuilder.getShouldSellList();
                break;
            default:
                throw new IllegalArgumentException("Not Supported Type!");
        }

        for (String actionName : actionNames) {
            AlgorithmAction action = beanFactory.getBean(actionName, AlgorithmAction.class);

            if (action == null) {
                throw new IllegalArgumentException("Bean Name Wrong : " + actionName);
            }

            action.doAction(context);

            if (context.isFisish())
                return;
        }
    }

    /**
     * Setter method for property <tt>beanFactory</tt>.
     *
     * @param beanFactory  value to be assigned to property beanFactory
     */
    @Autowired
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * Setter method for property <tt>actionBuilder</tt>.
     *
     * @param actionBuilder  value to be assigned to property actionBuilder
     */
    @Autowired
    public void setActionBuilder(ActionBuilder actionBuilder) {
        this.actionBuilder = actionBuilder;
    }
}