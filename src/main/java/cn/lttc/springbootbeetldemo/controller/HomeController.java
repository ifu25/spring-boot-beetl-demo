package cn.lttc.springbootbeetldemo.controller;

import cn.lttc.springbootbeetldemo.entity.User;
import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.SqlServerStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 主页控制器
 * 作者：xinggang
 * 邮箱：willcoo@qq.com
 * 网址：https://weiku.co
 * 日期：2019-08-29
 * 说明：
 */
@Controller
public class HomeController {

    @Autowired
    SQLManager sqlManager;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "admin");
        Map<String, Object> map = new HashMap<>();
        map.put("uid", "xg");
        map.put("pwd", "263499118");
        map.put("mail", "web@vcoo.cc");
        map.put("age", 16);
        model.addAttribute("data", map);
        return "index";
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Object insert() {

//        //使用内置的生成的sql 新增用户，如果需要获取主键，可以传入KeyHolder
//        User user = new User();
//        user.setGuid("3322-98548-sdfadf5-v4ae8");
//        user.setUserName("xxxxx");
//        user.setNote("这是用beetlSQL插入的数据");
//        sqlManager.insert(user);


//        //使用内置sql查询用户
//        User u = sqlManager.unique(User.class, "3322-98548-sdfadf5-v4ae8");
//        System.out.println(u);


//        //模板更新,仅仅根据id更新值不为null的列
//        User newUser = new User();
//        newUser.setGuid("3322-98548-sdfadf5-v4ae8");
//        newUser.setNote("我改了备注");
//        sqlManager.updateTemplateById(newUser);


//        //模板查询
//        User userQuery = new User();
//        userQuery.setUserName("李四");
//        List<User> list = sqlManager.template(userQuery);

//        //Query查询
//        Query userQuery = sqlManager.getQuery(User.class);
//        List<User> users = userQuery.lambda().andEq(User::getName,"xiandafy").select();

        //使用user.md 文件里的select语句，参考下一节。
        User query2 = new User();
        query2.setUserName("李四");
        List<User> list2 = sqlManager.select("user.select", User.class, query2);

        return "ok";
    }

    @Bean
    public SQLManager getSqlManager() {
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Demo";
        String uid = "demo";
        String pwd = "demo";
        ConnectionSource source = ConnectionSourceHelper.getSimple(driver, url, uid, pwd);
        DBStyle mssql = new SqlServerStyle();
        // sql语句放在classpagth的/sql 目录下
        SQLLoader loader = new ClasspathLoader("/sql");
        // 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
        DefaultNameConversion nc = new DefaultNameConversion();
        // 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
        SQLManager sqlManager = new SQLManager(mssql, loader, source, nc, new Interceptor[]{new DebugInterceptor()});
        return sqlManager;
    }

    /**
     * 代码生成
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Demo";
        String uid = "demo";
        String pwd = "demo";
        ConnectionSource source = ConnectionSourceHelper.getSimple(driver, url, uid, pwd);
        DBStyle mssql = new SqlServerStyle();
        // sql语句放在classpagth的/sql 目录下
        SQLLoader loader = new ClasspathLoader("/sql");
        // 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
        DefaultNameConversion nc = new DefaultNameConversion();
        // 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
        SQLManager sqlManager = new SQLManager(mssql, loader, source, nc, new Interceptor[]{new DebugInterceptor()});

        sqlManager.genPojoCodeToConsole("sys_user");
        sqlManager.genSQLTemplateToConsole("sys_user");
    }
}
