package InterfaceCanImplMethod;

/**
 * 
 * @author SURBHI SHARMA
 * NOTE: List Interface was added to java in 1.2 version, and the method forEach was included in 1.8, 
 * The idea of interfaces was that once it is defined, you should not add new abstract methods,
 * but with 1.8 since java is allowing method definitions in interfaces, they modified List Interface to
 * include a default forEach(Consumer<T> consumer){...} method in interface and it doesn't break anything now :)
 *
 */

public class ImplementationClass implements InterfaceA, InterfaceB{

	@Override
	public void methodWithoutDef() {
		System.out.println("Class override gets preference!");
	}
	
	/**
	 * NOTE: Without the following override, we get the following errors, so we have to override the following methods:
	   Error: Duplicate default methods named methodWithDef with the parameters () and () are inherited from the types InterfaceB and InterfaceA
	 */
	@Override
	public String methodWithDef() {
		// TODO Auto-generated method stub
		return InterfaceA.super.methodWithDef();
	}

}
