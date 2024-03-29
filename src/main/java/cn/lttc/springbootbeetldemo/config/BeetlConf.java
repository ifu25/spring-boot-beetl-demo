package cn.lttc.springbootbeetldemo.config;

import org.beetl.core.resource.FileResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * Beetl 模板配置
 * 作者：xinggang
 * 邮箱：willcoo@qq.com
 * 网址：https://weiku.co
 * 日期：2019-08-29
 * 说明：
 */
@Configuration
public class BeetlConf {

    @Value("${beetl.templatesPath}")
    String templatesPath; //模板根目录 ，比如 "templates"

    @Bean(name = "beetlConfig")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {

        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();

        //模板分离，打包成 jar 后模板文件放在 jar 所在目录的 resources 目录下
        ApplicationHome home = new ApplicationHome(getClass());
        String rootPath;
        if (home.getSource().getPath().endsWith(".jar")) {
            rootPath = home.getDir().getPath(); //打包后jar包目录
        } else {
            rootPath = home.getDir().getParentFile().getParentFile().getParent() + File.separator + "resources" + File.separator + "main"; //开发环境目录:idea 2019.2
            rootPath = home.getDir().getParent() + File.separator + "resources"; //开发环境目录:idea 2018.3
        }
        String root = rootPath + File.separator + templatesPath;
        FileResourceLoader resourceLoader = new FileResourceLoader(root, "utf-8");
        beetlGroupUtilConfiguration.setResourceLoader(resourceLoader);
        beetlGroupUtilConfiguration.init();
        return beetlGroupUtilConfiguration;
    }

    @Bean(name = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setSuffix(".html"); //此处设置为.html后在controller中就不能再写return "index.html"，要写return "index"
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
        return beetlSpringViewResolver;
    }
}
