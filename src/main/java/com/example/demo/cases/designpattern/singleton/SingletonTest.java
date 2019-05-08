package com.example.demo.cases.designpattern.singleton;


public class SingletonTest {

	public static void main(String[] args) {
		Singleton4 s = Singleton4.INSTANCE;
		s.instanceOperation();
	}
}

//单例模式中的饿汉式(预先创建)
class Singleton{

	private static Singleton s = new Singleton();

	private Singleton(){
		//防止通过反射创建单例对象
		if(s != null){
			throw new RuntimeException();
		}
	}

	public static Singleton getInstance(){
		return s;
	}

	//防止反序列化创建单例对象
	private Object readResolve(){
		return s;
	}
}


//单例模式中的懒汉式(未创建)
class Singleton2{

	private static Singleton2 s;

	private Singleton2(){}

	public static synchronized Singleton2 getInstance(){
		if(s == null){
			s = new Singleton2();
		}
		return s;
	}
}

//单例模式的静态内部类实现，线程安全，调用效率高，懒加载
class Singleton3{

	//静态内部类在加载时不执行，在调用时才进行初始化，懒加载
	private static class SingletonClass{
		private static final Singleton3 instance = new Singleton3();
	}

	private Singleton3(){}

	public static Singleton3 getInstance(){
		return SingletonClass.instance;
	}

}

//单例模式的枚举式实现
enum Singleton4{

	INSTANCE;

	public void instanceOperation(){
		System.out.println("instanceOperation");
	}

}

//双检锁实现的单例模式
class Singleton5{
	//使用volatile是为了禁止指令重排序
	private static volatile Singleton5 singleton = null;

	private Singleton5(){}

	public static Singleton5 newInstance(){
		if(singleton == null){
			synchronized (Singleton5.class) {
				if(singleton == null){
					singleton = new Singleton5();//这里会重排序
				}
			}
		}
		return singleton;
	}
}