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
import org.springframework.transaction.interceptor.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局
 * Aop
 * 事务配置PlatformTransactionManager
 */
//@Aspect
@Configuration
public class TransactionAdviceConfig {
    private static final int TX_METHOD_TIMEOUT=5;
    //切点在service层
    private static final String AOP_POINTCUT_EXPRESSION = "execution (* com.wjk.sstm.service..*(..))";
    @Autowired
    private PlatformTransactionManager transactionManager;

    /**
     * 配置事务传播特性和命名规则
     * @return
     */
    @Bean
    public TransactionInterceptor txAdvice(){
//        名字-规则
        NameMatchTransactionAttributeSource source=new NameMatchTransactionAttributeSource();
//        只读事务
        RuleBasedTransactionAttribute readOnlyRule=new RuleBasedTransactionAttribute();
        /*设置当前事务是否为只读事务，true为只读*z/
        readOnlyRule.setReadOnly(true);
        /* transactiondefinition 定义事务的隔离级别；
         * PROPAGATION_NOT_SUPPORTED事务传播级别5，以非事务运行，如果当前存在事务，则把当前事务挂起*/
        readOnlyRule.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);

        RuleBasedTransactionAttribute requireRule=new RuleBasedTransactionAttribute();
        /*抛出异常后执行切点回滚*/
        requireRule.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        /*PROPAGATION_REQUIRED:事务隔离性为1，若当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。这是默认值。 */
        requireRule.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        /*设置事务失效时间，如果超过5秒，则回滚事务*/
        requireRule.setTimeout(TX_METHOD_TIMEOUT);

        Map<String,TransactionAttribute> txMap=new HashMap<>();
        txMap.put("add*",requireRule);
        txMap.put("save*", requireRule);
        txMap.put("insert*",requireRule);
        txMap.put("update*",requireRule);
        txMap.put("delete*",requireRule);
        txMap.put("remove*",requireRule);
        txMap.put("get*",readOnlyRule);
        txMap.put("query*", readOnlyRule);
        txMap.put("find*", readOnlyRule);
        txMap.put("select*",readOnlyRule);
        source.setNameMap(txMap);
        TransactionInterceptor txAdvice=new TransactionInterceptor(transactionManager, source);
        return txAdvice;
    }

    /**
     * 配置切点
     * @return
     */
    @Bean
    public Advisor txAdviceAdvisor(){
        /* 声明切点的面
         * 切面（Aspect）：切面就是通知和切入点的结合。通知和切入点共同定义了关于切面的全部内容——它的功能、在何时和何地完成其功能。
         * */
        AspectJExpressionPointcut pointcut=new AspectJExpressionPointcut();
        /*声明和设置需要拦截的方法,用切点语言描写*/
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        /*设置切面=切点pointcut+通知TxAdvice*/
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
