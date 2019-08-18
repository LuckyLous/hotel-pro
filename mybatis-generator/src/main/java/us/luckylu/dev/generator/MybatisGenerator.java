package us.luckylu.dev.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import us.luckylu.dev.generator.config.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MybatisGenerator {


    public static void generate() throws Exception {
        //警告信息集合
        List<String> warnings = new ArrayList<>();
        //true时，如果有相同的文件则覆盖文件
        DefaultShellCallback callback = new DefaultShellCallback(true);
        //读取生成器的配置文件
        File configFile = new File(MybatisGenerator.class.getClassLoader().getResource("mybatisGeneratorConfiguration.xml").getPath());
        //创建配置解析器
        ConfigurationParser cp = new ConfigurationParser(warnings);
        //解析配置文件
        Configuration config = cp.parseConfiguration(configFile);
        //创建生成器对象
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        //执行生成代码
        myBatisGenerator.generate(null);
        // 别人的封装schemas
//        myBatisGenerator.generateSchemas(null,null,true);
    }

    public static void main(String[] args) {
        try {
            MybatisGenerator.generate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
