package StreamAPIsExploration;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * 
 * @author i856147
 * Notes: It permits functional programming: Functional programming is a programming paradigm — 
 * def: a style of building the structure and elements of computer programs,
 * that treats computation as the evaluation of mathematical functions and avoids changing-state and mutable data 
 * Stream is like stream of water, once you have gone through it, you can't go though it again.
 * Stream is also Lazy, like unless a termination method like (findFirst()) is called it won't execute the lambda function.
 * We can use stream apis two times with intermediate methods.
 * Streams can process huge amounts of data and also provides parallel streams for quicker processing.
 *
 */

public class StreamMapReduceExplorer {

	public static void main(String[] args) {
		List<Integer> values = Arrays.asList(1,2,3,4,5,6);
		
		//1. lets double all the values and print the sum of them
		int res1 = 0;
		for(int i: values) {
			/*
			* 2. here we are changing the value of result in every step (mutation)
			* this leads to consistency issues in multi-threaded system
			*/
			res1 += i*2;
		}
		System.out.println("Result using for loop: " + res1);
		//3. how to avoid mutation while doing the above, this can be achieved with streams
		//4. lets convert the list into a stream (part of Util package, is a type of collection) and use map reduce
		Stream stream1 = values.stream();
		//5. lets create a function to be used in map
		Function<Integer, Integer> fn = new Function<Integer, Integer>() {
			@Override
			public Integer apply(Integer i) {
				//6. this will be executed on every data in the stream
				return i*2;
			}
		};
		//7. a new stream because we already went through stream1 during map
		Stream stream2 = stream1.map(fn);
//		stream1.forEach(System.out::println); 8. Error: stream has already been operated upon
//		stream2.forEach(System.out::println); //9. to see all values doubled
		//10. lets add up all the data in stream2 (doubled values) using reduce
		//11. lets create a binary operator to operate inside reduce function
		BinaryOperator<Integer> op = new BinaryOperator<Integer>() {

			@Override
			public Integer apply(Integer t, Integer u) {
				// TODO Auto-generated method stub
				return t+u;
			}
		};
		//12. reduce function without identity parameter returns optional
//		Optional<Integer> optional = stream2.reduce(op);
//		System.out.println(optional.get());
		//13. with identity, it know what to return if stream is empty because of "x op identity = x"
		// thus, you can get Integer directly by giving a base (identity) value, 0 in this case
		Integer res2 = (Integer) stream2.reduce(0,op);//
		System.out.println("Result using stream's map-reduce: " + res2);
		
		
		//14. lets try to optimize the code now
		Stream stream3 = values.stream();
		Integer res3 =  (Integer) stream3.map(new Function<Integer, Integer>(){
							@Override
							public Integer apply(Integer t) {
								return t*2;
							}	
						}).reduce(0, new BinaryOperator<Integer>() {
							@Override
							public Integer apply(Integer t, Integer u) {
								System.out.println("t: " + t + "u: " + u); 
								//above prints => t:0 u:2 \n t:2 u:4 \n t:6 u:6 ... 
								//t=>acts like result, u=>next val in stream
								return t+u;
							}
						});
		System.out.println("Result using stream's map-reduce optimized: " + res3);
		
		
		//15. lets further optimize it...
		/*16. we can use lambda function instead of the entire anonymous object like above,
		 * because both Function and BinaryOperator interfaces are SAM (Single abstract method) interfaces
		 * thus JDK1.8 knows that i will be the input to the apply method of Function interface
		 * and (j,k) are input params to apply method of BiFunction (SAM) which is extended in BinaryOperator (not SAM) 
		 */
		Stream<Integer> stream4 = values.stream();
		Integer res4 = stream4.map(i -> i*2).reduce(0,(j,k) -> j+k);
		System.out.println("Result using stream's map-reduce in a single line: " + res4);
		
		
		//17. Using an external (pre-built) function in reduce function
		Stream<Integer> stream5 = values.stream();
		Integer res5 = stream5.map(i -> i*2).reduce(0, (j,k) -> Integer.sum(j, k));
		System.out.println("Result using stream's map-reduce with Integer.sum function: " + res4);
		
		//18. Because sum is a static function we can do the following:
		Stream<Integer> stream6 = values.stream();
		Integer res6 = stream6.map(i -> i*2).reduce(0, Integer::sum);
		System.out.println("Result using Integer.sum static function: " + res6);
	}

}
