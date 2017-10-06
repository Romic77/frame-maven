package com.frame.test;

public class Singleton {
	/*private static Singleton instant=null;

	//private的构造函数无法new
    private Singleton() {
		System.out.println("111");
	}

    public synchronized static Singleton getInstant() {
                if (instant == null) {
					instant = new Singleton();
				}
        return instant;
    }

    static {
		System.out.println("2222");
	}*/

	private static class SingletonHolder {
		private static final Singleton INSTANCE = new Singleton();
	}

	private Singleton() {
		System.out.println("111");
	}

	public static final Singleton getInstance() {
		System.out.println("222");
		return SingletonHolder.INSTANCE;
	}

}