package ustc.sse.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author LRK
 * @project_name mybatishelloworld
 * @package_name ustc.sse.interceptor
 * @date 2018/12/29 13:44
 * @description God Bless, No Bug!
 *
 *  插件编写
 *      1 编写org.apache.ibatis.plugin.Interceptor的实现类
 *      2 使用@Intercepts注解指定要拦截的方法
 *      3 将拦截器注册到配置文件中
 */
@Intercepts(
    {   // 拦截的方法签名,拦截哪个对象的哪个方法
        @Signature(type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class,Integer.class} )
    }
)
public class MyParamInterceptor implements Interceptor {

    /**
     * 拦截
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

//        System.out.println("拦截到的方法信息: "+ invocation.getMethod());
//        System.out.println("拦截到的方法参数信息: ");
        for (Object o : invocation.getArgs()) {
            System.out.println("  "+o);
        }
        /**
         * 动态修改sql
         *
          */
        Object target = invocation.getTarget();
        System.out.println("拦截到的target对象(Statement): "+ target);
        /**
         * StatementHandler ==> ParameterHandler ===>DefaultParameterHandler 可修改数据信息
         *      MappedStatement mappedStatement; 封装的增删改查操作
         *      Object parameterObject;  封装sql的参数信息
         *      BoundSql boundSql;  sql信息
         *      Configuration configuration; 配置信息
          */
        // 得到target的元数据信息 修改参数 1==>2
        MetaObject metaObject = SystemMetaObject.forObject(target);
        Object value = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println("参数信息: " + value);

        metaObject.setValue("parameterHandler.parameterObject",2);
        Object proceed = invocation.proceed();// 放行
        return proceed; // 返回方法的执行结果
    }

    /**
     * 包装代理对象
     * @param target Mybatis四大对象 StatementHandler ParameterHandler ResultSetHandler TypeHandler
     * @return 代理对象
     */
    @Override
    public Object plugin(Object target) {
//        System.out.println("被代理对象信息: "+target);
        Object wrap = Plugin.wrap(target, this);
//        System.out.println("生成的代理对象信息: " + wrap);
        return wrap;
    }

    /**
     * 将插件配置的属性信息设置进来
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("注入的属性信息: " + properties);
    }
}
