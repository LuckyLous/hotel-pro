package us.luckylu.dev.generator;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class GenerateConfiguration {

    private static String TEMPLATE_NAME = "mybatisGeneratorConfiguration.ftl";
    private static String XML_PATH = "mybatis-generator/src/main/resources/mybatisGeneratorConfiguration.xml";

    public static void main(String[] args) throws IOException, TemplateException, ClassNotFoundException, SQLException {
        // 获取 config.private.properties 配置
        Properties properties = new Properties();
        properties.load(new FileReader(GenerateConfiguration.class.getResource("/config.private.properties").getPath()));
        // 将properties属性填充入map中
        Map<String, Object> data = new HashMap<>(5);
        for (String key : properties.stringPropertyNames()) {
            data.put(key, properties.getProperty(key));
        }
        data.put("tableNames", introspectDatabase(properties));
        data.put("modelTargetProject", "${modelTargetProject}");
        data.put("mapper_common_parent_path", "${mapper_common_parent_path}");
        data.put("DB_USER", "${DB_USER}");
        data.put("DB_PASSWORD", "${DB_PASSWORD}");
        data.put("DB_HOST", "${DB_HOST}");
        data.put("DB_NAME", "${DB_NAME}");

        //开始生成配置文件
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));
        configuration.setDirectoryForTemplateLoading(new File(GenerateConfiguration.class.getResource("/").getPath()));
        Template template = configuration.getTemplate(TEMPLATE_NAME, Locale.CHINA);

        FileWriter fileWriter = new FileWriter(XML_PATH);
        template.process(data, fileWriter);
        fileWriter.flush();
    }


    public static List<String> introspectDatabase(Properties properties) throws ClassNotFoundException, SQLException {
        // 数据库配置
        List<String> tableNameList = new ArrayList<>();

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://" + properties.get("DB_HOST") + ":" + properties.get("DB_PORT") + "/" + properties.get("DB_NAME")
                        + "?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf8",
                properties.getProperty("DB_USER"), properties.getProperty("DB_PASSWORD"));

        DatabaseMetaData databaseMetaData = connection.getMetaData();

        ResultSet rs = databaseMetaData.getTables(null, null, null, new String[]{"TABLE", "VIEW"});
        while (rs.next()) {
            tableNameList.add(rs.getString(3));
        }
        return tableNameList;
    }


}
