package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
</#if>
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
    * ${table.comment!} 前端控制器
    * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
@Api(tags = "${table.entityPath} 表相关接口", description = "提供 ${table.entityPath} 表相关的 Rest API")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass}<${table.serviceName},${entity}> {
    <#else>
public class ${table.controllerName} {
    </#if>

    @Override
    @ApiOperation(value="获取 ${table.entityPath} 详细信息", notes="根据url的 id 来获取 ${table.entityPath} 详细信息")
    @ApiImplicitParam(name = "id", value = "唯一编码", dataType = "long", paramType="query", defaultValue = "10000015")
    @RequestMapping(value="/getById", method=RequestMethod.GET)
    public Object getById(long id) {
        return super.getById(id);
    }


    @Override
    @ApiOperation(value="分页获取 ${table.entityPath} 详细信息", notes="根据url的 pageNum, pageSize 来获取 ${table.entityPath} 详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int", paramType="query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", dataType = "int", paramType="query", defaultValue = "8")
    })
    @RequestMapping(value="/getPage", method=RequestMethod.GET)
    public Object getPage(int pageNum, int pageSize) {
        return super.getPage(pageNum, pageSize);
    }


    @Override
    @ApiOperation(value="创建 ${table.entityPath} ", notes="根据 ${table.entityPath} 对象创建实体")
    @RequestMapping(value="/create", method=RequestMethod.POST)
    public Object create(@RequestBody ${entity} ${table.entityPath}) {
        return super.create(${table.entityPath});
    }


    @Override
    @ApiOperation(value="修改 ${table.entityPath} ", notes="根据 ${table.entityPath} 对象修改实体")
    @RequestMapping(value="/update", method=RequestMethod.POST)
    public Object update(@RequestBody ${entity} ${table.entityPath}) {
        return super.update(${table.entityPath});
    }


    @Override
    @ApiOperation(value="删除 ${table.entityPath} ", notes="根据url的 id 来删除 ${table.entityPath} ")
    @ApiImplicitParam(name = "id", value = "唯一编码", dataType = "long", paramType="query", defaultValue = "10000015")
    @RequestMapping(value="/removeById", method=RequestMethod.GET)
    public Object removeById(long id) {
        return super.removeById(id);
    }

}
</#if>
