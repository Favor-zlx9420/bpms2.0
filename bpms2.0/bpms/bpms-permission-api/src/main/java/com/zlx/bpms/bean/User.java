package com.zlx.bpms.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @Package: com.zlx.bpms.bean
 * @Author: LQW
 * @Date: 2020/3/17
 * @Description:权限认证用户实体
 */
@TableName("sys_user")
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends Model<User> implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("remark_name")
    private String remarkName;
    @TableField("user_name")
    private String userName;
    @TableField("password")
    private String password;
    @TableField("phone_number")
    private String phoneNumber;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
    @TableField("is_status")
    private Integer isStatus;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", remarkName='" + remarkName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isStatus=" + isStatus +
                '}';
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
