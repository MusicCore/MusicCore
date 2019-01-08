package com.wjk.sstm.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * 全局
 * Aop
 * 事务配置PlatformTransactionManager
 */
@Aspect
@Configuration
public class TransactionAdviceConfig {
    //切点在service层
    private static final String AOP_POINCUT_EXPRESSION = "execution (* com.***.service.*.*(..))";

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice(){
        DefaultTransactionAttribute tx_REQUIRED = new DefaultTransactionAttribute();
//        设置事务传播行为----如果没有事务就创建事务
        tx_REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//        tx_REQUIRED.rollbackOn();
        DefaultTransactionAttribute txAttr_REQUIRED_READONLY = new DefaultTransactionAttribute();
        txAttr_REQUIRED_READONLY.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//        只读
        txAttr_REQUIRED_READONLY.setReadOnly(true);

//        名字-规则
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
//        可操作
        source.addTransactionalMethod("save*", tx_REQUIRED);
        source.addTransactionalMethod("delete*", tx_REQUIRED);
        source.addTransactionalMethod("update*", tx_REQUIRED);
        source.addTransactionalMethod("exec*", tx_REQUIRED);
        source.addTransactionalMethod("set*", tx_REQUIRED);
        source.addTransactionalMethod("send*", tx_REQUIRED);
        source.addTransactionalMethod("process*", tx_REQUIRED);
        source.addTransactionalMethod("complete*", tx_REQUIRED);
//        只读
        source.addTransactionalMethod("insert*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("get*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("query*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("find*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("list*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("count*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("is*", txAttr_REQUIRED_READONLY);

        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public Advisor txAdviceAdvisor(){
        //设置切点
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        //* com.***.service.*.*(..) service目录下的任意方法
        pointcut.setExpression(AOP_POINCUT_EXPRESSION);
        //设置事务名称权限规则
        return new DefaultPointcutAdvisor(pointcut,txAdvice());
    }
}
