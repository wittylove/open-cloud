package com.opencloud.base.client.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户-登录日志
 * @author liuyadu
 */
@Data
@NoArgsConstructor
@TableName("base_account_logs")
public class BaseAccountLogs implements Serializable {
    @TableId(type= IdType.ID_WORKER)
    private Long id;

    private Date loginTime;

    /**
     * 登录Ip
     */
    private String loginIp;

    /**
     * 登录设备
     */
    private String loginAgent;

    /**
     * 登录次数
     */
    private Integer loginNums;

    private Long userId;

    private String account;

    private String accountType;

    private String accountId;

    private String domain;
}
