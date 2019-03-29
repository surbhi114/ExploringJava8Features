package InterfaceCanImplMethod;

public class TestingImpl {

	public static void main(String[] args) {
		ImplementationClass impl = new ImplementationClass();
		impl.methodWithoutDef();
		System.out.println("Default method output: " + impl.methodWithDef());
		System.out.println("Static method output: " + InterfaceA.staticMethod());
	}
}
