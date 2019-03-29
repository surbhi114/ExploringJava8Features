package InterfaceCanImplMethod;

public interface InterfaceA {
	
	void methodWithoutDef();
	
	default String methodWithDef() {
		return "Interface A";
	}
	
	/**
	 * NOTE: try overriding an Object class method
	   Error: A default method cannot override a method from java.lang.Object
	 */
//	default boolean equals(Object a) {
//		return true;
//	}
	
	/**
	 * NOTE: you can now also have static methods in interface
	 */
	static String staticMethod() {
		return "static method of InterfaceA";
	}

}
