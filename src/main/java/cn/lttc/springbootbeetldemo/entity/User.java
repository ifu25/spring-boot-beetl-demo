package cn.lttc.springbootbeetldemo.entity;

import lombok.Data;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;

/**
 * 用户
 * 作者：xinggang
 * 邮箱：willcoo@qq.com
 * 网址：https://weiku.co
 * 日期：2019-08-30
 * 说明：
 */
@Data
@Table(name="sys_user")
public class User {

    @AssignID
    private String guid;

    private String userName;

    private String password;

    private String note;
}
