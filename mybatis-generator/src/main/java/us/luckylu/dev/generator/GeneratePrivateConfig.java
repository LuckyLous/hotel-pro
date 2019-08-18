package us.luckylu.dev.generator;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class GeneratePrivateConfig {

    private static String TEMPLATE_NAME = "config.private.properties.ftl";
    private static String XML_PATH = "mybatis-generator/src/main/resources/config.private.properties";

    public static void main(String[] args) throws IOException, TemplateException {
        Map<String, Object> data = new HashMap<>();


        File currentDirectory = new File(System.getProperty("user.dir"));
        for (File file : currentDirectory.listFiles()) {
            if (file.getName().equals("model")) {
                data.put("modelTargetProject", convertWindowsPath(file.getCanonicalPath()) + "/src/main/java");
            } else if (file.getName().equals("dao")) {
                data.put("sqlTargetProject", convertWindowsPath(file.getCanonicalPath()) + "/src/main/resources");
                data.put("javaClientTargetProject", convertWindowsPath(file.getCanonicalPath()) + "/src/main/java");

                data.put("mapper_common_parent_path", convertWindowsPath(file.getCanonicalPath()) + "/src/main");
            }
        }

        //开始生成配置文件
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));
        configuration.setDirectoryForTemplateLoading(new File(GenerateConfiguration.class.getResource("/").getPath()));
        Template template = configuration.getTemplate(TEMPLATE_NAME, Locale.CHINA);

        FileWriter fileWriter = new FileWriter(XML_PATH);
        template.process(data, fileWriter);
        fileWriter.flush();
    }

    private static String convertWindowsPath(String originPath) {
        String targetPath = originPath;
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            targetPath = originPath.replaceAll("\\\\", "/");
        }
        return targetPath;
    }
}
