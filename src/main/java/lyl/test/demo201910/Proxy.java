package lyl.test.demo201910;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Proxy {
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class clazz = Class.forName("lyl.test.demo201910.DynamicProxy");
		DynamicProxy dynamicProxy = (DynamicProxy) clazz.newInstance();
		dynamicProxy.test();
		Constructor constructor = clazz.getConstructor();
		dynamicProxy = (DynamicProxy) constructor.newInstance();
		dynamicProxy.test();
		Method method = clazz.getMethod("test2",new Class[] {String.class});
		String tem = (String) method.invoke(new DynamicProxy(), "test2");
		System.out.println(tem);
		method = clazz.getDeclaredMethod("test1",new Class[] {String.class});
		method.setAccessible(true);
		method.invoke(new DynamicProxy(), "test1");
		System.out.println("---------------------------");
		Method[] methods = clazz.getMethods();
		for(Method method2:methods) {
			System.out.println(method2.getName());
		}
		System.out.println("---------------------------");
		Method[] methods2 = clazz.getDeclaredMethods();
		for(Method method2:methods2) {
			System.out.println(method2.getName());			
		}
		System.out.println("---------------------------");
		
	}

}
