package us.luckylu.dev.generator.config;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Properties;
import java.util.Set;

/**
 * 能将注释放到Swagger的@ApiModelProperty
 * @author lu
 * @create 2019-03-22 15:16
 */
public class CommentGeneratorWithSwagger implements CommentGenerator {

    public CommentGeneratorWithSwagger() {
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        String remarks = introspectedColumn.getRemarks();
        if (StringUtility.stringHasValue(remarks)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("@ApiModelProperty(value=\"");
            stringBuilder.append(introspectedColumn.getRemarks());
            stringBuilder.append("\"");
            stringBuilder.append(")");
            field.addAnnotation(stringBuilder.toString());
        }

    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String remarks = introspectedTable.getRemarks();
        topLevelClass.addImportedType("io.swagger.annotations.ApiModelProperty");
        if (StringUtility.stringHasValue(remarks)) {
            topLevelClass.addImportedType("io.swagger.annotations.ApiModel");
            topLevelClass.addAnnotation("@ApiModel(\"" + remarks + "\")");
        }

    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
    }

    @Override
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
    }

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
    }

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
    }

    @Override
    public void addComment(XmlElement xmlElement) {
    }

    @Override
    public void addRootComment(XmlElement rootElement) {
    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {
    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {
    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
        String remarks = introspectedColumn.getRemarks();
        field.addAnnotation("@ApiModelProperty(\"" + remarks + "\")");
        FullyQualifiedJavaType fullyQualifiedJavaType = new FullyQualifiedJavaType("io.swagger.annotations.ApiModelProperty");
        imports.add(fullyQualifiedJavaType);
    }

    @Override
    public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {
        String remarks = introspectedTable.getRemarks();
        innerClass.addAnnotation("@ApiModel(\"" + remarks + "\")");
        FullyQualifiedJavaType fullyQualifiedJavaType = new FullyQualifiedJavaType("io.swagger.annotations.ApiModel");
        imports.add(fullyQualifiedJavaType);
    }
}
