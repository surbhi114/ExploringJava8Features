package StreamAPIsExploration;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class LambdaExplorer {

	public static void main(String[] args) {
		List<Integer> values = Arrays.asList(1,2,3,4,5);
		
		//1. Using consumer interface with anonymous class
		System.out.println("Way 1: Using consumer interface with anonymous class");
		Consumer<Integer> consumer1 = new Consumer<Integer>() {

			@Override
			public void accept(Integer t) {
				System.out.println(t);
			}
		};
		values.forEach(consumer1);
		System.out.println();

		//2. Using consumer interface with external class that implements Consumer class
		System.out.println("Way 2: Using consumer interface with external class that implements Consumer class");
		Consumer<Integer> consumer2 = new MyConsumer();
		values.forEach(consumer2);
		
		//3. Lets try to omit a lot of extra code using lambda expression ("->")
		System.out.println("Way 3: Using Lamda expression to define consumer");
		Consumer<Integer> consumer3 = (Integer i) -> {System.out.println(i);};
		values.forEach(consumer3);
		
		//4. We know that consumer is of type Integer so we can remove more code
		System.out.println("Way 4: Removes extra Integer declarations");
		Consumer<Integer> consumer4 = i -> {System.out.println(i);};
		values.forEach(consumer4);
		
		//5. Internal iteration using a lambda expression, we don't need to have explicit consumer
		/**
		 * NOTE: Lambda function works only in case the interface which are SAM (Single Abstract Method) 
		 * or if they have @FunctionalInterface annotation
		 */
		System.out.println("Way 5: Without explicit consumer, direct lambda expression");
		values.forEach(i -> System.out.println(i));
		System.out.println();
		
		//6. Lets call a static function
		System.out.println("Calling a static function from the current class");
		values.forEach(i -> doubleIt(i));
		System.out.println();
		
		//7. Lets call a static function fancy way
		System.out.println("Fancy way of calling a static function from the current class");
		values.forEach(LambdaExplorer :: doubleIt);
		System.out.println();
		
		//8. Lets call a non-static function
		System.out.println("Calling a non-static function from the current class");
		LambdaExplorer lmdExplore = new LambdaExplorer();
		values.forEach(LambdaExplorer :: tripleItNonStatic);
		System.out.println();
		
	}
	
	public static void doubleIt(Integer x) {
		System.out.println(x*2);
	}
	
	public static void tripleItNonStatic(Integer x) {
		System.out.println(x*3);
	}

}

class MyConsumer implements Consumer<Integer> {

	@Override
	public void accept(Integer t) {
		System.out.println(t);
	}
	
}
