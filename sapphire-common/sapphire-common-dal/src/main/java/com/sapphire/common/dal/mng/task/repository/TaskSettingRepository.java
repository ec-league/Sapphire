/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.dal.mng.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapphire.common.dal.mng.task.domain.TaskSetting;

/**
 *
 * @author yunpeng.byp
 * @version $Id: TaskSettingRepository.java, v 0.1 2017年10月25日 下午4:49 yunpeng.byp Exp $
 */
public interface TaskSettingRepository extends JpaRepository<TaskSetting, Long> {
}