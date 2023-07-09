//package com.example.mqtt_springBoot_demo.aop;
//
//
//import com.example.mqtt_springBoot_demo.common.PageInfo;
//import com.example.mqtt_springBoot_demo.common.SortInfo;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.*;
//import java.util.Comparator;
//import java.util.List;
//
//@Aspect
//@Component
//public class OrderDataFrontendSynchronization {
//
//    @Before("@annotation( com.example.mqtt_springBoot_demo.annoation.SortInfoSortFieldCheck)")
//    public void deBefore(JoinPoint joinPoint){
//        for (Object arg : joinPoint.getArgs()) {
//            if(arg instanceof PageInfo){
//                arg.getClass().getGenericSuperclass()
//                List<SortInfo> sortList = ((PageInfo<?>) arg).getSortList();
//            }
//
//        }
//
//    }
//
//    @Around("@annotation( com.example.mqtt_springBoot_demo.annoation.SortInfoSortFieldCheck)")
//    public Object dataSort(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        // 被切的方法
//        Method method = methodSignature.getMethod();
//        // 获取注解
//        ListSort listSort = method.getAnnotation(ListSort.class);
//        // 获取注解中clazz的值
//        Class clazz = listSort.clazz();
//        // 获取注解中attributeGetMethodName的值
//        String attributeGetMethodName = listSort.attributeGetMethod();
//        // 获取字段的get方法
//        Method attributeGetMethod = clazz.getMethod(attributeGetMethodName);
//        // 获取方法的返回值类型
//        Class attributeType = attributeGetMethod.getReturnType();
//
//        List dataList = (List) joinPoint.proceed();
//        dataList.sort(new Comparator() {
//            @Override
//            public int compare(Object o1, Object o2) {
//                try {
//                    return SortData.SortData(attributeGetMethod,attributeType,o1,o2);
//                } catch (InvocationTargetException e) {
//                    throw new RuntimeException(e);
//                } catch (IllegalAccessException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        return dataList;
//
//
//
//    }
//
//    @After("dataSynchronization()")
//    public void doAfter(JoinPoint joinPoint){
//        KitchenWebSocket.OrderDataFrontendSynchronization();
//    }
//}
