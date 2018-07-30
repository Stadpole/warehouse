package com.bupt.aop;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)//注解会在class中存在，运行时可通过反射获取
@Target(ElementType.METHOD)//目标是方法
@Documented//文档生成时，该注解将被包含在javadoc中，可去掉
public @interface OperationLogger
{
    /**
     * 模块名字
     */
    String moduleName() default "";

    /**
     * 操作类型
     */
    String option()default "";

    /**
     * 操作内容
     */
    String operationDetail()default "";

}
