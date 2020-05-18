package integral.kata;
import java.util.LinkedList;
import java.util.ArrayList;

public final class Person {

	private final Timeline timeline = new Timeline();
	// linked list is more performant when this scales to hundreds or more items
	private final LinkedList<Person> followers = new LinkedList<Person>();
	private final String name;

	public Person(final String name_) {
		name = name_;
	}

	public void publish(final String post, final int when) {
		timeline.push(new Post(post, when));
	}

	public void follow(final Person person) {
		person.followers.add(this);
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
