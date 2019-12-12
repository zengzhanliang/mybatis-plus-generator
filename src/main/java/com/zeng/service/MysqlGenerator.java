package com.zeng.service;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.zeng.model.GeneratorProperties;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * mysql 代码生成器演示例子
 * </p>
 *
 * @author jobob
 * @since 2018-09-12
 */
@Slf4j
public class MysqlGenerator {

    private static String BASE_ENTITY_URL = "com.zeng.common.BaseEntity";

    private static String BASE_CONTROLLER_URL = "com.zeng.common.BaseController";

    private GeneratorProperties generatorProperties;

    public MysqlGenerator(GeneratorProperties generatorProperties)
    {
        this.generatorProperties = generatorProperties;
    }

    /**
     * 生成代码
     * RUN THIS
     */
    public void generator() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor( "zengzhanliang" );
        gc.setOpen(false);
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl( generatorProperties.getMysqlUrl() );
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername( generatorProperties.getMysqlUsername() );
        dsc.setPassword( generatorProperties.getMysqlPasswd() );
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName( generatorProperties.getModuleName() );
        pc.setParent( generatorProperties.getPackageName() );
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
                Map<String, Object> map = new HashMap<>(1);
                map.put("packageName", generatorProperties.getPackageName() + "." + generatorProperties.getModuleName() );
                this.setMap(map);
            }
        };

        mpg.setCfg(cfg);

        mpg.setTemplate( new TemplateConfig().setXml(null).setController( "/templates/controller.java" ) );

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass( BASE_ENTITY_URL );
        strategy.setEntityLombokModel(true);
        strategy.setSuperControllerClass( BASE_CONTROLLER_URL );

        //可以是多个表
        strategy.setInclude( generatorProperties.getTableName() );

        strategy.setSuperEntityColumns("id","create_time","builder","last_update_time","last_updater");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setRestControllerStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");

        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();

        log.info("代码生成完毕");
    }

}
