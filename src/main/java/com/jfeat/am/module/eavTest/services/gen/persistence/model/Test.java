package com.jfeat.am.module.eavTest.services.gen.persistence.model;

import com.baomidou.mybatisplus.annotation.TableField;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Code generator
 * @since 2021-03-24
 */
@TableName("eav_test")
public class Test extends Model<Test> {

    @TableField(exist = false)
    private com.alibaba.fastjson.JSONObject extra;

    public com.alibaba.fastjson.JSONObject getExtra() {
        return extra;
    }

    public void setExtra(com.alibaba.fastjson.JSONObject extra) {
        this.extra = extra;
    }


    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    private String name;

    private Long orgId;


    public Long getId() {
        return id;
    }

    public Test setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Test setName(String name) {
        this.name = name;
        return this;
    }

    public Long getOrgId() {
        return orgId;
    }

    public Test setOrgId(Long orgId) {
        this.orgId = orgId;
        return this;
    }

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String ORG_ID = "org_id";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name=" + name +
                ", orgId=" + orgId +
                "}";
    }
}
