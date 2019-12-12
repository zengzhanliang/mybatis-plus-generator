package com.zeng.common;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 基础类
 * @author zengZhanLiang
 * @since 2019-09-05
 */
@Data
@Accessors(chain = true)
public class BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "编码",example = "10000013")
    private Integer id;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建人")
    private String builder;

    @Version
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "最近一次修改时间")
    private LocalDateTime lastUpdateTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "最近一次修改时间")
    private String lastUpdater;
}
