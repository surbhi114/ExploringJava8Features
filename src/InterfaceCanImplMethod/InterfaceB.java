package InterfaceCanImplMethod;

public interface InterfaceB {
	
	void methodWithoutDef();
	
	default String methodWithDef() {
		return "Interface B";
	}

}
