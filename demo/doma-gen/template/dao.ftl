<#-- このテンプレートに対応するデータモデルのクラスは org.seasar.doma.extension.gen.DaoDesc です -->

<#-- カスタマイズfunction定義 -->
<#function convertDataType dataType>
    <#local result = dataType?replace("BigInteger", "Long")>
    <#return result>
</#function>
<#-- カスタマイズfunction定義 -->

<#import "/lib.ftl" as lib>
<#if lib.copyright??>
    ${lib.copyright}
</#if>
<#if packageName??>
package ${packageName};
</#if>

<#list importNames as importName>
import ${importName};
</#list>
import org.seasar.doma.boot.ConfigAutowireable;

/**
<#if entityDesc.comment??>
 * ${entityDesc.comment}DAO
</#if>
<#if lib.author??>
 * @author ${lib.author}
</#if>
 */
@Dao<#if configClassSimpleName??>(config = ${configClassSimpleName}.class)</#if>
@ConfigAutowireable
public interface ${simpleName} {

<#if entityDesc.idEntityPropertyDescs?size gt 0>
    /**
    <#if entityDesc.comment??>
     * 主キーを指定して${entityDesc.comment}を検索します。
    </#if>
    <#list entityDesc.idEntityPropertyDescs as property>
    <#if property.comment??>
     * @param ${property.name} ${property.comment}
    <#else>
     * @param ${property.name}
    </#if>
    </#list>
    <#if entityDesc.comment??>
     * @return ${entityDesc.comment}エンティティ
    <#else>
     * @return the <#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityDesc.simpleName}<#if entityDesc.entitySuffix??>${entityDesc.entitySuffix}</#if> entity
    </#if>
     */
    @Select
    <#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityDesc.simpleName}<#if entityDesc.entitySuffix??>${entityDesc.entitySuffix}</#if> selectById(<#list entityDesc.idEntityPropertyDescs as property>${convertDataType(property.propertyClassSimpleName)} ${property.name}<#if property_has_next>, </#if></#list>);

</#if>
<#if entityDesc.idEntityPropertyDescs?size gt 0 && entityDesc.versionEntityPropertyDesc??>
    /**
    <#list entityDesc.idEntityPropertyDescs as property>
     * @param ${property.name}
    </#list>
     * @param ${entityDesc.versionEntityPropertyDesc.name}
     * @return the <#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityDesc.simpleName}<#if entityDesc.entitySuffix??>${entityDesc.entitySuffix}</#if> entity
     */
    @Select(ensureResult = true)
    <#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityDesc.simpleName}<#if entityDesc.entitySuffix??>${entityDesc.entitySuffix}</#if> selectByIdAndVersion(<#list entityDesc.idEntityPropertyDescs as property>${property.propertyClassSimpleName} ${property.name}, </#list>${entityDesc.versionEntityPropertyDesc.propertyClassSimpleName} ${entityDesc.versionEntityPropertyDesc.name});

</#if>
    /**
<#if entityDesc.comment??>
     * ${entityDesc.comment}を登録します。
     * @param entity ${entityDesc.comment}エンティティ
<#else>
     * @param entity
</#if>
     * @return 登録された行数
     */
    @Insert
    int insert(<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityDesc.simpleName}<#if entityDesc.entitySuffix??>${entityDesc.entitySuffix}</#if> entity);

    /**
<#if entityDesc.comment??>
     * ${entityDesc.comment}を更新します。
     * @param entity ${entityDesc.comment}エンティティ
<#else>
     * @param entity
</#if>
     * @return 更新された行数
     */
    @Update
    int update(<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityDesc.simpleName}<#if entityDesc.entitySuffix??>${entityDesc.entitySuffix}</#if> entity);

    /**
<#if entityDesc.comment??>
     * ${entityDesc.comment}を削除します。
     * @param entity ${entityDesc.comment}エンティティ
<#else>
     * @param entity
</#if>
     * @return 削除された行数
     */
    @Delete
    int delete(<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityDesc.simpleName}<#if entityDesc.entitySuffix??>${entityDesc.entitySuffix}</#if> entity);

}
