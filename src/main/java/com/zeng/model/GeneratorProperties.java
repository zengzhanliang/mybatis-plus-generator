package com.zeng.model;

import lombok.Data;

/**
 * 生成参数配置类
 * @author zengzhanliang
 */
@Data
public class GeneratorProperties {

    private String moduleName;

    private String mysqlUrl;

    private String mysqlUsername;

    private String mysqlPasswd;

    private String tableName;

    private String packageName;
}
