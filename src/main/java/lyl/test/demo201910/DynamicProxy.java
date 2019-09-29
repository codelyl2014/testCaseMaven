package lyl.test.demo201910;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {
	
	public static void consumer(Interface inter) {
		inter.doSomething();
		inter.someThingElse("simon");
	}
	
	public void test() {
		System.out.println("darlin");
	}
	
	private void test1(String input) {
		System.out.println(input);
	}
	
	public void test2(String input) {
		System.out.println(input);
	}
	
	interface Interface{
		void doSomething();
		void someThingElse(String arg);
	}
	
	static class Target implements Interface{
		public void doSomething() {
			System.out.println("target do something ");
		}
		
		public void someThingElse(String args) {
			System.out.println("target something else " + args);
		}
	}
	
	class ProxyHandler implements Interface{
		
		public Interface interface1;
		
		public ProxyHandler(Target target) {
			interface1 = target;
		}
		
		public void doSomething() {
			interface1.doSomething();
		}
		
		public void someThingElse(String arg) {
			interface1.someThingElse(arg);
		}
	}
	
	static class DynamicProxyHandler implements InvocationHandler{
		private Object targer;
		
		public DynamicProxyHandler(Object target) {
			this.targer = target;
		}
		
		public Object invoke(Object proxy,Method method,Object[] args) throws Throwable {
			System.out.println("InvocationHandler:" + proxy.getClass() + ",method:" + method + ",args:" + args);
			if(args != null) {
				for(Object arg:args) {
					System.out.println("InvocationHandler args:" + arg);
				}
			}
			return method.invoke(targer, args);
		}
	}
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		Target target = new Target();
		consumer(target);
		
		Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(), new Class[] { Interface.class}, new DynamicProxyHandler(target));
//		consumer(proxy);
		proxy.doSomething();
		proxy.someThingElse("jeff");
		InvocationHandler handler = new DynamicProxyHandler(target);
		Class<?> clazz = Proxy.getProxyClass(Interface.class.getClassLoader(), new Class[] { Interface.class});
		Constructor<?> constructor = clazz.getConstructor(new Class[] {InvocationHandler.class});
		Interface proxy1 = (Interface) constructor.newInstance(new Object[] {handler});
//		proxy1.doSomething();
//		proxy1.someThingElse("joney");
		consumer(proxy1);
		
		
	}

}
