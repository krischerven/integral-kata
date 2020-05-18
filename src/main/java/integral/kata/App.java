package integral.kata;
import java.util.ArrayList;

// this project was tested on a custom OpenJDK 15 build
// it should run on any version of java 10+
// run 'gradle run' to run the application
public final class App {

	private static short count = 0;

	public static void main(final String[] args) {
		System.out.println("Started up the application");
		coreLogic();
		System.out.println("Application ran without errors");
	}

	/****************************************************************************
	Feature: Publishing
		Scenario: Alice publishes messages to her personal timeline.
			Given Alice has published "I love the weather today."
			When Alice views her timeline
			Then Alice sees:
				"I love the weather today."

	Feature: Timeline
		Scenario: Alice views Bob's timeline.
			Given Bob has published "Darn! We lost!"
			And Bob has published "Good game though."
			When Alice views Bob's timeline
			Then Alice sees:
				Good game though. (1 minute ago)
				Darn! We lost! (2 minute ago)

	Feature: Following
		Scenario: Charlie can follow Alice and Bob, and he views an aggregated list of all timelines.
			Given Alice has published "I love the weather today."
			And Bob has published "Darn! We lost!"
			And Bob has published "Good game though."
			And Charlie has published "I'm in New York today! Anyone wants to have a coffee?"
			When Charlie follows Alice
			And Charlie follows Bob
			And Charlie views his wall
			Then Charlie sees:
				Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)
				Bob - Good game though. (1 minute ago)
				Bob - Damn! We lost! (2 minutes ago)
				Alice - I love the weather today (5 minutes ago)
	****************************************************************************/
	public static void coreLogic() {
		// scenario #1
		final var alice = new Person("Alice");
		alice.publish("I love the weather today.", 5.0);
		display(alice.seePosts());
		// scenario #2
		final var bob = new Person("Bob");
		bob.publish("Darn! We lost!", 2.0);
		bob.publish("Good game though.", 1.0);
		display(alice.viewTimeline(bob));
		// scenario #3
		final var charlie = new Person("Charlie");
		charlie.publish("I'm in New York today! Anyone wants to have a coffee?", 15.0/60.0);
		charlie.follow(alice);
		charlie.follow(bob);
		display(charlie.viewWall());
	}

	private static void display(final ArrayList<String> posts) {
		++count;
		System.out.println("(Scenario #"+count+")");
		System.out.println("-".repeat(55));
		if (posts.size() == 0) {
			System.out.println("(nothing to display)");
		} else {
			for (final var post: posts) {
				System.out.println(post);
			}
		}
		System.out.println("-".repeat(55));
	}
}
