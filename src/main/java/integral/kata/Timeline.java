package integral.kata;
import java.util.ArrayList;

public final class Timeline {
	private final ArrayList<Post> posts = new ArrayList<>();

	public ArrayList<Post> getPosts() {
		return posts;
	}

	public void push(final Post post) {
		posts.add(post);
	}
}
