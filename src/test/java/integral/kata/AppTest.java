package integral.kata;

import org.junit.Test;
import static org.junit.Assert.*;

// run 'gradle test' to run unit tests
public class AppTest {
	@Test public void testAppHasAGreeting() {
		final var john = new Person("John Doe");
		final var jane = new Person("Jane Doe");
		final var joe = new Person("Joe Genero");
		john.publish("did a thing", 5);
		//
		assertEquals(
				"Want: size() == " + 1 + ", Got: size() == " + john.seePosts().size(),
				john.seePosts().size(),
				1
		);
		//
		assertEquals(
				"Want: size() == " + 0 + ", Got: size() == " + john.showPosts(joe).size(),
				john.showPosts(joe).size(),
				0
		);
		//
		joe.follow(john);
		assertEquals(
				"Want: size() == " + 1 + ", Got: size() == " + john.showPosts(joe).size(),
				john.showPosts(joe).size(),
				1
		);
		//
		jane.follow(john);
		jane.follow(joe);
		for (short i = 0; i < 2; ++i) {
			var x = john.showPosts(jane).size()+joe.showPosts(jane).size();
			assertEquals(
					"Want: size() == " + i+1 + ", Got: size() == " + x,
					x,
					i+1
			);
			if (i == 0) {
				joe.publish("did something else", 4);
			}
		}
	}
}
