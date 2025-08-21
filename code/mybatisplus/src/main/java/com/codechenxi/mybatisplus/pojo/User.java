package com.codechenxi.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

/**
 * @author codechenxi
 * @since 2025-07-15 10:13
 */

@Data
//  设置实体类所对应的表名
//@TableName("t_user")
public class User {

    //  将属性所对应的字段指定为主键
    //  @TableId 注解的 value 属性用于指定主键的字段
    //  @TableId 注解的 type 属性设置主键生成策略
//    @TableId(value="uid",type= IdType.AUTO)
    @TableId(value="uid")
    private Long id;

//    private String userName;
    //  指定属性所对应的字段名
    @TableField("username")
    private String name;

    private Integer age;

    private String email;

    @TableLogic
    private Integer isDeleted;
}
