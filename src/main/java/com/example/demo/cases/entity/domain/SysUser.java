package com.example.demo.cases.entity.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser implements Serializable {

    @TableId
    private Integer id;

    private String name;

    private Integer age;
}
