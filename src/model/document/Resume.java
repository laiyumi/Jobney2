package model.document;

public class Resume extends Document {
	public Resume(String docName) {
		super(docName);
	}

	@Override
	protected String getType() {
		return "Resume";
	}

}
