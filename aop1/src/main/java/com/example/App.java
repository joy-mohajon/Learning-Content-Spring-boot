package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Student student = context.getBean("student", Student.class);
        student.study();

        Employee employee = context.getBean("employee", Employee.class);
        employee.study();
    }
}
