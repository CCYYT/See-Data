//package com.example.mqtt_springBoot_demo.aop;
//
//import com.example.mqtt_springBoot_demo.common.page.ConditionInfo;
//import com.example.mqtt_springBoot_demo.common.page.PageInfo;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Field;
//import java.util.List;
//import java.util.Map;
//
//@Aspect
//@Component
//public class ConditionInfoSqlJoint {
//    private Field[] declaredFields;
//    private Map<String,List> elementRangeFieldsNum;
//
//    @Before("execution(* com.example.mqtt_springBoot_demo.dao.*.*(com.example.mqtt_springBoot_demo.common.page.PageInfo))")
//    public void api(JoinPoint jp) throws IllegalAccessException {
//        Object[] args = jp.getArgs();
//        for (Object arg : args) {
//            if(arg instanceof PageInfo){
//                ConditionInfo conditionInfo = ((PageInfo<?>) arg).getConditionInfo();
//                if(conditionInfo != null){
//                    Object startElement = conditionInfo.getStartElement();
//                    Object endElement = conditionInfo.getEndElement();
//                    List elementRange = conditionInfo.getElementRange();
//
//
//
//                    if(startElement != null && endElement != null){
//                        declaredFields = getObjectFields(startElement);
//
//                    } else if (elementRange != null && elementRange.size() != 0) {
//                        declaredFields = getObjectFields(elementRange);
//                    }else {
//                        return;
//                    }
//
//
//
//                        for (Object o : elementRange) {
//                            for (Field field : declaredFields) {
//                                Object o1 = field.get(o);
//                            }
//                        }
//
//
//                }
//            }
//        }
//    }
//
//    private Field[] getObjectFields(Object o){
//        return o.getClass().getDeclaredFields();
//    }
//}
