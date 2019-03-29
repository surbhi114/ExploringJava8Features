package StreamAPIsExploration;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamFilterExplorer {
	
	public static void main(String[] args) {
		//1. let's define and array with random numbers and print a sum of all numbers divisible by 5
		List<Integer> values = Arrays.asList(12,20,35,46,55,68,75);
		int res1 = 0;
		for(int value: values) {
			if(value%5 == 0) {
				res1 += value;
			}
		}
		System.out.println("Conventional for-loop result: " + res1);
		
		/*2. The problem with external for-loop is that we have to focus on what to do and how to do.
		 * Stream tries to make our life simple by letting us focus on "what to do" and it takes care of the "how to do" part.
		 */
		//3. let's use filter, which needs a predicate object for eliminating all the values that are in-divisible by 5
		Stream<Integer> stream2 = values.stream();
		Predicate<Integer> predicate = new Predicate<Integer>() {
			@Override
			public boolean test(Integer t) {
				return t%5==0;
			}
		};
		Integer res2 = stream2.filter(predicate).reduce(0, Integer::sum);
		System.out.println("Result using filter(predicate object)-reduce: " + res2);
		
		//4. Making code more efficient, beyond using anonymous object, 
		//using lambda as perdicate because test method is present in Predicate Interface which is @FunctionalInterface
		Integer res3 = values.stream()
							.filter(i -> i%5 == 0)
							.reduce(0, Integer::sum);
		System.out.println("Result using filter-reduce: " + res3);
		
		//5. let's double all the numbers divisible by 5 and print a sum of them
		Integer res4 = values.stream()
				.filter(i -> i%5 == 0)
				.map(i -> i*2)
				.reduce(0, Integer::sum);
		System.out.println("Result using filter-map-reduce: " + res4);
		
		//6. Let's explore other methods and optional
		Optional<Integer> optional = values.stream()
										.filter(i -> i%5 == 0)
										.map(i -> i*2)
										.findFirst();
		System.out.println("Optional: " + optional);
		//7. if optional doesn't anything, it doesn't break your logic, it simply returns Optional.empty
		//else returns Optional[40]
		
		//8. let's try to deal with Optional.empty
		Integer res5 = values.stream()
				.filter(i -> i%50 == 0)
				.map(i -> i*2)
				.findFirst()
				.orElse(0);
		System.out.println("Dealing with Optional.empty using orElse method: " + res5);
		/*9. Efficiency: stream is lazy evaluation, meaning unless we add a terminal method like 
		 * findFirst(),it will only take in the operations like filter and map but will not execute them.
		 * so the process is: 
		 * it sees filter and says done without executing the actual code
		 * it sees map and says done without executing the actual code
		 * then it sees findFirst() and says it will be done when map is done, which inturn says it will be done when filter is done
		 * thus, filter needs to execute the code, then map executes then findFirst executes and gets the first value.
		 * Also, stream has also optimized map and filter on the basis of terminal methods.
		 * Eg: for findFirst, map knows only  the first value is needed and filter also knows only the first value is needed
		 * it will execute it accordingly so that it is  done in the most optimized way
		*/
		//10. proof
		System.out.println(values.stream()
								.filter(StreamFilterExplorer::isDiv)
								.map(StreamFilterExplorer::myMap)
								.findFirst()
								.orElse(0));
		/*11. isDiv will be called till 1 value is got, 
		 * that is passed to map and hence myMap is called only on that 1 value
		 * Thus, streams are more efficient than conventional for-loops
		 * */
		
	}
	
	public static boolean isDiv(int i) {
		System.out.println("isDiv received: " + i);
		return i%5==0;
	}
	
	public static int myMap(int i) {
		System.out.println("myMap received: " + i);
		return i*2;
	}

}
