package us.luckylu.dev.generator.config;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.JavaTypeResolver;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.util.StringUtility;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Mysql字段类型与Java属性类型映射
 * @author lu
 * @create 2019-03-22 15:40
 */
public class JavaTypeResolverCustomImpl implements JavaTypeResolver {

    protected List<String> warnings;
    protected Properties properties = new Properties();
    protected Context context;
    protected boolean forceBigDecimals;
    protected Map<Integer, JdbcTypeInformation> typeMap = new HashMap();

    public JavaTypeResolverCustomImpl() {
        this.typeMap.put(2003, new JavaTypeResolverCustomImpl.JdbcTypeInformation("ARRAY", new FullyQualifiedJavaType(Object.class.getName())));
        this.typeMap.put(-5, new JavaTypeResolverCustomImpl.JdbcTypeInformation("BIGINT", new FullyQualifiedJavaType(Long.class.getName())));
        this.typeMap.put(-2, new JavaTypeResolverCustomImpl.JdbcTypeInformation("BINARY", new FullyQualifiedJavaType("byte[]")));
        this.typeMap.put(-7, new JavaTypeResolverCustomImpl.JdbcTypeInformation("BIT", new FullyQualifiedJavaType(Boolean.class.getName())));
        this.typeMap.put(2004, new JavaTypeResolverCustomImpl.JdbcTypeInformation("BLOB", new FullyQualifiedJavaType("byte[]")));
        this.typeMap.put(16, new JavaTypeResolverCustomImpl.JdbcTypeInformation("BOOLEAN", new FullyQualifiedJavaType(Boolean.class.getName())));
        this.typeMap.put(1, new JavaTypeResolverCustomImpl.JdbcTypeInformation("CHAR", new FullyQualifiedJavaType(String.class.getName())));
        this.typeMap.put(2005, new JavaTypeResolverCustomImpl.JdbcTypeInformation("CLOB", new FullyQualifiedJavaType(String.class.getName())));
        this.typeMap.put(70, new JavaTypeResolverCustomImpl.JdbcTypeInformation("DATALINK", new FullyQualifiedJavaType(Object.class.getName())));
        this.typeMap.put(91, new JavaTypeResolverCustomImpl.JdbcTypeInformation("DATE", new FullyQualifiedJavaType(LocalDate.class.getName())));
        this.typeMap.put(3, new JavaTypeResolverCustomImpl.JdbcTypeInformation("DECIMAL", new FullyQualifiedJavaType(BigDecimal.class.getName())));
        this.typeMap.put(2001, new JavaTypeResolverCustomImpl.JdbcTypeInformation("DISTINCT", new FullyQualifiedJavaType(Object.class.getName())));
        this.typeMap.put(8, new JavaTypeResolverCustomImpl.JdbcTypeInformation("DOUBLE", new FullyQualifiedJavaType(Double.class.getName())));
        this.typeMap.put(6, new JavaTypeResolverCustomImpl.JdbcTypeInformation("FLOAT", new FullyQualifiedJavaType(Double.class.getName())));
        this.typeMap.put(4, new JavaTypeResolverCustomImpl.JdbcTypeInformation("INTEGER", new FullyQualifiedJavaType(Integer.class.getName())));
        this.typeMap.put(2000, new JavaTypeResolverCustomImpl.JdbcTypeInformation("JAVA_OBJECT", new FullyQualifiedJavaType(Object.class.getName())));
        this.typeMap.put(-16, new JavaTypeResolverCustomImpl.JdbcTypeInformation("LONGNVARCHAR", new FullyQualifiedJavaType(String.class.getName())));
        this.typeMap.put(-4, new JavaTypeResolverCustomImpl.JdbcTypeInformation("LONGVARBINARY", new FullyQualifiedJavaType("byte[]")));
        this.typeMap.put(-1, new JavaTypeResolverCustomImpl.JdbcTypeInformation("LONGVARCHAR", new FullyQualifiedJavaType(String.class.getName())));
        this.typeMap.put(-15, new JavaTypeResolverCustomImpl.JdbcTypeInformation("NCHAR", new FullyQualifiedJavaType(String.class.getName())));
        this.typeMap.put(2011, new JavaTypeResolverCustomImpl.JdbcTypeInformation("NCLOB", new FullyQualifiedJavaType(String.class.getName())));
        this.typeMap.put(-9, new JavaTypeResolverCustomImpl.JdbcTypeInformation("NVARCHAR", new FullyQualifiedJavaType(String.class.getName())));
        this.typeMap.put(0, new JavaTypeResolverCustomImpl.JdbcTypeInformation("NULL", new FullyQualifiedJavaType(Object.class.getName())));
        this.typeMap.put(2, new JavaTypeResolverCustomImpl.JdbcTypeInformation("NUMERIC", new FullyQualifiedJavaType(BigDecimal.class.getName())));
        this.typeMap.put(1111, new JavaTypeResolverCustomImpl.JdbcTypeInformation("OTHER", new FullyQualifiedJavaType(Object.class.getName())));
        this.typeMap.put(7, new JavaTypeResolverCustomImpl.JdbcTypeInformation("REAL", new FullyQualifiedJavaType(Float.class.getName())));
        this.typeMap.put(2006, new JavaTypeResolverCustomImpl.JdbcTypeInformation("REF", new FullyQualifiedJavaType(Object.class.getName())));
        this.typeMap.put(5, new JavaTypeResolverCustomImpl.JdbcTypeInformation("SMALLINT", new FullyQualifiedJavaType(Short.class.getName())));
        this.typeMap.put(2002, new JavaTypeResolverCustomImpl.JdbcTypeInformation("STRUCT", new FullyQualifiedJavaType(Object.class.getName())));
        this.typeMap.put(92, new JavaTypeResolverCustomImpl.JdbcTypeInformation("TIME", new FullyQualifiedJavaType(LocalTime.class.getName())));
        this.typeMap.put(93, new JavaTypeResolverCustomImpl.JdbcTypeInformation("TIMESTAMP", new FullyQualifiedJavaType(LocalDateTime.class.getName())));
        this.typeMap.put(-6, new JavaTypeResolverCustomImpl.JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(Integer.class.getName())));
        this.typeMap.put(-3, new JavaTypeResolverCustomImpl.JdbcTypeInformation("VARBINARY", new FullyQualifiedJavaType("byte[]")));
        this.typeMap.put(12, new JavaTypeResolverCustomImpl.JdbcTypeInformation("VARCHAR", new FullyQualifiedJavaType(String.class.getName())));
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
        this.properties.putAll(properties);
        this.forceBigDecimals = StringUtility.isTrue(properties.getProperty("forceBigDecimals"));
    }

    @Override
    public FullyQualifiedJavaType calculateJavaType(IntrospectedColumn introspectedColumn) {
        FullyQualifiedJavaType answer = null;
        JavaTypeResolverCustomImpl.JdbcTypeInformation jdbcTypeInformation = (JavaTypeResolverCustomImpl.JdbcTypeInformation)this.typeMap.get(introspectedColumn.getJdbcType());
        if (jdbcTypeInformation != null) {
            answer = jdbcTypeInformation.getFullyQualifiedJavaType();
            answer = this.overrideDefaultType(introspectedColumn, answer);
        }

        return answer;
    }

    protected FullyQualifiedJavaType overrideDefaultType(IntrospectedColumn column, FullyQualifiedJavaType defaultType) {
        FullyQualifiedJavaType answer = defaultType;
        switch(column.getJdbcType()) {
            case -7:
                answer = this.calculateBitReplacement(column, defaultType);
                break;
            case 2:
            case 3:
                answer = this.calculateBigDecimalReplacement(column, defaultType);
        }

        return answer;
    }

    protected FullyQualifiedJavaType calculateBitReplacement(IntrospectedColumn column, FullyQualifiedJavaType defaultType) {
        FullyQualifiedJavaType answer;
        if (column.getLength() > 1) {
            answer = new FullyQualifiedJavaType("byte[]");
        } else {
            answer = defaultType;
        }

        return answer;
    }

    protected FullyQualifiedJavaType calculateBigDecimalReplacement(IntrospectedColumn column, FullyQualifiedJavaType defaultType) {
        FullyQualifiedJavaType answer;
        if (column.getScale() <= 0 && column.getLength() <= 18 && !this.forceBigDecimals) {
            if (column.getLength() > 9) {
                answer = new FullyQualifiedJavaType(Long.class.getName());
            } else if (column.getLength() > 4) {
                answer = new FullyQualifiedJavaType(Integer.class.getName());
            } else {
                answer = new FullyQualifiedJavaType(Short.class.getName());
            }
        } else {
            answer = defaultType;
        }

        return answer;
    }

    @Override
    public String calculateJdbcTypeName(IntrospectedColumn introspectedColumn) {
        String answer = null;
        JavaTypeResolverCustomImpl.JdbcTypeInformation jdbcTypeInformation = (JavaTypeResolverCustomImpl.JdbcTypeInformation)this.typeMap.get(introspectedColumn.getJdbcType());
        if (jdbcTypeInformation != null) {
            answer = jdbcTypeInformation.getJdbcTypeName();
        }

        return answer;
    }

    @Override
    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    public static class JdbcTypeInformation {
        private String jdbcTypeName;
        private FullyQualifiedJavaType fullyQualifiedJavaType;

        public JdbcTypeInformation(String jdbcTypeName, FullyQualifiedJavaType fullyQualifiedJavaType) {
            this.jdbcTypeName = jdbcTypeName;
            this.fullyQualifiedJavaType = fullyQualifiedJavaType;
        }

        public String getJdbcTypeName() {
            return this.jdbcTypeName;
        }

        public FullyQualifiedJavaType getFullyQualifiedJavaType() {
            return this.fullyQualifiedJavaType;
        }
    }
}
