package com.fjl.springboot325.utils;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Map;

/**
 * @author fangjialiang
 * @Description:
 * @date 2024/4/25 22:21
 */
public class ExpressionUtils {

    //创建ExpressionParser解析表达式
    private static ExpressionParser parser = new SpelExpressionParser();
    public static boolean RunExpression(String expression, Map<String, Object> params) {
        //创建一个虚拟的容器EvaluationContext
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (Map.Entry e : params.entrySet()) {
            String key = (String) e.getKey();
            Integer value = (Integer) e.getValue();
            //向容器内添加变量
            context.setVariable(key, value);
        }
        return Boolean.TRUE.equals(parser.parseExpression(expression).getValue(context, Boolean.class));
    }

}
