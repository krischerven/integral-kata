package integral.kata;

public final class Post {
	String message;
	int ago;

	public Post(final String message_, final int ago_) {
		message = message_;
		ago = ago_;
	}

	public Post(final String message_) {
		message = message_;
	}

	public String output(final boolean self) {
		return message + (!self ? " (" + ago + " " + "minutes ago)" : "");
	}
}
