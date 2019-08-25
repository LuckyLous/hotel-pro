<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>
    <!--读取配置文件-->
    <properties resource="config.private.properties"/>
    <context id="testTables" targetRuntime="MyBatis3" defaultModelType="flat">

        <property name="isMergeable" value="false"/>

        <!--生成mapper.xml时覆盖原文件-->
        <plugin type="org.mybatis.generator.plugins.UnmergeableXmlMappersPlugin" />

        <!-- 自动生成tostring方法 -->
        <plugin type="org.mybatis.generator.plugins.ToStringPlugin"/>
        <!-- 自动生成equals方法和hashcode方法 -->
        <!--<plugin type="org.mybatis.generator.plugins.EqualsHashCodePlugin"/>-->
        <plugin type="org.mybatis.generator.plugins.SerializablePlugin"/>

        <!-- 自定义方法 -->
        <plugin type="org.mybatis.generator.plugins.MapperPlugin">
            <property name="targetProject" value="${modelTargetProject}"/>
            <property name="targetPackage" value="us.luckylu.dev.dao.mapper.common"/>
        </plugin>

        <!--可以自定义注释生成的方式，实现CommentGenerator接口-->
        <!--默认实现是org.mybatis.generator.internal.DefaultCommentGenerator-->
        <commentGenerator type="us.luckylu.dev.generator.config.CommentGeneratorWithSwagger">
            <property name="suppressAllComments" value="false"/>
        </commentGenerator>

        <!--设置数据库连接驱动-->
        <jdbcConnection driverClass="com.mysql.jdbc.Driver" userId="${DB_USER}" password="${DB_PASSWORD}"
                        connectionURL="jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME}?createDatabaseIfNotExist=true&amp;useUnicode=true&amp;characterEncoding=utf8">
        </jdbcConnection>

        <!-- 自定义字段对应java类型，可以实现JavaTypeResolver接口-->
        <!-- 默认实现是org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl-->
        <javaTypeResolver type="us.luckylu.dev.generator.config.JavaTypeResolverCustomImpl">
            <property name="forceBigDecimals" value="false"/>
            <property name="useJSR310Types" value="true" />
        </javaTypeResolver>

        <!-- 自定义字段对应java类型，可以实现JavaTypeResolver接口-->
        <!-- 默认实现是org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl-->
        <javaTypeResolver type="us.luckylu.dev.generator.config.JavaTypeResolverCustomImpl">
            <property name="forceBigDecimals" value="false"/>
            <property name="useJSR310Types" value="true" />
        </javaTypeResolver>

        <!-- Model模型生成器,用来生成含有主键key的类，记录类 以及查询Example类
            targetPackage     指定生成的model生成所在的包名
            targetProject     指定在该项目下所在的路径
        -->
        <!-- 生成模型的包名和位置-->
        <javaModelGenerator targetPackage="us.luckylu.dev.model" targetProject="${modelTargetProject}">
            <property name="enableSubPackages" value="false"/>
            <property name="trimStrings" value="true"/>
            <!--暂时不继承BaseModel，因为没设计有createAt、updateAt-->
            <property name="rootClass" value="us.luckylu.dev.common.model.BaseModelWithId"/>
        </javaModelGenerator>

        <!-- 生成映射文件的包名和位置-->
        <sqlMapGenerator targetPackage="us.luckylu.dev.dao.mapper" targetProject="${mapper_common_parent_path}/resources">
            <property name="enableSubPackages" value="false"/>
        </sqlMapGenerator>

        <!-- 生成DAO的包名和位置-->
        <javaClientGenerator type="XMLMAPPER" targetPackage="us.luckylu.dev.dao.mapper"
                             targetProject="${mapper_common_parent_path}/java">
            <property name="enableSubPackages" value="false"/>
        </javaClientGenerator>

    <#--此处添加需要忽略的表-->
        <#assign ignoreTable=["SCHEMA_VERSION","schema_version"]/>

        <#list tableNames?sort as item>
            <#if !ignoreTable?seq_contains(item)>
        <table tableName="${item}"></table>
            </#if>
        </#list>

    </context>
</generatorConfiguration>
