package com.fjl.springboot325;

import com.fjl.springboot325.utils.ExpressionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * @author fangjialiang
 * @Description: https://blog.csdn.net/lixinkuan328/article/details/126588218
 * @date 2024/4/25 21:59
 */
public class SpringExpressionTest {

    @Test
    public void testExecExpression() {
        // Arrange
        String expression = "#a > 1";
        Map<String, Object> params = new HashMap<>();
        params.put("a", 2);

        // Act
        boolean result = ExpressionUtils.RunExpression(expression, params);

        // Assert
        Assertions.assertTrue(result);
    }

    @Test
    public void testExecExpressionTrue() {
        // Arrange
        String expression = "#a > 1";
        Map<String, Object> params = new HashMap<>();
        params.put("a", 2);

        // Act
        boolean result = ExpressionUtils.RunExpression(expression, params);

        // Assert
        Assertions.assertTrue(result);
    }

    @Test
    public void testExecExpressionFalse() {
        // Arrange
        String expression = "#a < 1";
        Map<String, Object> params = new HashMap<>();
        params.put("a", 2);

        // Act
        boolean result = ExpressionUtils.RunExpression(expression, params);

        // Assert
        Assertions.assertFalse(result);
    }

    @Test
    public void testSqEL() {

        //创建表达式列表
        ArrayList<String> list = new ArrayList<>();
        list.add("#A == 1");
        list.add("#B == 1");
        list.add("#C == 1 && #D == 1");
        list.add("#B == 1 && #D == 1");

        //创建参数map
        HashMap<String, Object> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 1);
        map.put("D", 1);

        System.out.print("参数值:");
        System.out.println(map.toString());

        //创建ExpressionParser解析表达式
        ExpressionParser parser = new SpelExpressionParser();
        //创建一个虚拟的容器EvaluationContext
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (Map.Entry e : map.entrySet()) {
            String key = (String) e.getKey();
            Integer value = (Integer) e.getValue();
            //向容器内添加变量
            context.setVariable(key, value);
        }

        list.stream().forEach(expression -> {
            System.out.print("expression : " + expression);
            //getValue有参数context，从新的容器中根据SpEL表达式获取所需的值
            Boolean flag = parser.parseExpression(expression).getValue(context, Boolean.class);
            System.out.println("\t结果:" + flag);
        });
    }
}
