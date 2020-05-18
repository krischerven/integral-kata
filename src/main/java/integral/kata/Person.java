package integral.kata;
import java.util.LinkedList;
import java.util.ArrayList;

public final class Person {

	private final Timeline timeline = new Timeline();
	// linked list is more performant when this scales to hundreds or more items
	private final LinkedList<Person> followers = new LinkedList<>();
	// arraylist is more performant here (better cache locality, etc)
	private final ArrayList<Person> following = new ArrayList<>();
	private final String name;

	public Person(final String name_) {
		name = name_;
	}

	public void publish(final String post, final double when) {
		timeline.push(new Post(post, when));
	}

	public void follow(final Person person) {
		person.followers.add(this);
		following.add(person);
	}

	public ArrayList<String> showPosts(final Person person) {
		return showPostsCallback(person, false, false);
	}

	public ArrayList<String> showPostsDirect(final Person person) {
		return showPostsCallback(person, false, true);
	}

	public ArrayList<String> seePosts() {
		return showPostsCallback(this, true, false);
	}

	public ArrayList<String> viewTimeline(final Person person) {
		return person.showPostsDirect(this);
	}

	public ArrayList<String> viewWall() {
		// it's us, but we want to see the full message
		final var posts = showPostsCallback(this, false, false);
		for (final var person: following) {
			// if we are following them, it's guaranteed
			// that they have us in their followers list
			posts.addAll(viewTimeline(person));
		}
		return posts;
	}

	private ArrayList<String> showPostsCallback(
			final Person person,
			final boolean self,
			final boolean nofollow
	) {
		if (nofollow || person == this || followers.contains(person)) {
			final var posts = new ArrayList<String>();
			for (final var post : timeline.getPosts()) {
				if (self) {
					posts.add(post.output(self));
				} else {
					posts.add(name + " - " + post.output(self));
				}
			}
			return posts;
		} else {
			return new ArrayList<>();
		}
	}
}
