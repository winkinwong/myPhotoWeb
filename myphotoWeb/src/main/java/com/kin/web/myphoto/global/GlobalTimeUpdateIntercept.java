package com.kin.web.myphoto.global;


import com.kin.web.myphoto.base.BaseEntity;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Properties;


@Component
@Intercepts({
        @Signature(
            type = Executor.class,
            method ="update",
                args ={MappedStatement.class,Object.class}
        )
})
public class GlobalTimeUpdateIntercept implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        String mappedId = mappedStatement.getId();
        String operation = mappedId.substring(mappedId.lastIndexOf(".") + 1).toLowerCase();
        Object object = invocation.getArgs()[1];

        if (operation.contains("insert")) {
            // 插入
            if (object instanceof Map && ((Map) object).get("list") != null && ((Map) object).get("list") instanceof List && !((List) ((Map) object).get("list")).isEmpty()) {
                // 批量插入
                for (Object item : (List) ((Map) object).get("list")) {
                    autoInsertBaseEntity(item);
                }
            } else {
                // 插入单条数据
                autoInsertBaseEntity(object);
            }
        } else if (operation.contains("update")) {
            // 更新

            if (object != null && object instanceof Map) {
                autoUpdateBaseEntity(((Map) object).get("record"));
            } else {
                autoUpdateBaseEntity(object);
            }
        }

        return invocation.proceed();
    }

    /**
     * @param target 表示被拦截的对象，此处为 Executor 的实例对象
     *               作用：如果被拦截对象所在的类有实现接口，就为当前拦截对象生成一个代理对象
     *               如果被拦截对象所在的类没有指定接口，这个对象之后的行为就不会被代理操作
     * @return
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    /**
     * 自动为 Entity 设置 插入时间/更新时间/版本号
     *
     * @param object
     */
    protected void autoInsertBaseEntity(Object object) {
        LocalDateTime now = LocalDateTime.now();

        if (object != null && object instanceof BaseEntity) {
            if (((BaseEntity) object).getCreateTime() == null) ((BaseEntity) object).setCreateTime(now);
            if (((BaseEntity) object).getUpdateTime() == null) ((BaseEntity) object).setUpdateTime(now);
            //if (((BaseEntity) object).getVersion() == null) ((BaseEntity) object).setVersion(0L);
        }

       /* if (object != null && object instanceof BaseEntityWithId && ((BaseEntityWithId) object).getId() == null) {
            // 这里可以实现自动set uuid
        }*/
    }

    /**
     * 自动为 Entity 更新 更新时间/版本号
     * @param object
     */
    protected void autoUpdateBaseEntity(Object object) {
        LocalDateTime now = LocalDateTime.now();

        if (object != null && object instanceof BaseEntity) {
            BaseEntity BaseEntity = (BaseEntity) object;
            BaseEntity.setUpdateTime(now);
            // 这里可以自动对版本号+1
        /*    if (BaseEntity.getVersion() != null) {
                BaseEntity.setVersion(BaseEntity.getVersion() + 1);
            }*/
        }
    }
}
