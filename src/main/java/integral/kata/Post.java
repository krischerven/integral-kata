package integral.kata;

public final class Post {
	String message;
	double ago;

	public Post(final String message_, final double ago_) {
		message = message_;
		ago = ago_;
	}

	public Post(final String message_) {
		message = message_;
	}

	public String output(final boolean self) {
		if (ago < 1) {
			return message + (!self ? " (" + ((int)(ago*60)) + " " + "seconds ago)" : "");
		} else {
			return message + (!self ? " (" + ((int)ago) + " " + "minutes ago)" : "");
		}
	}
}
