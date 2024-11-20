
package model.document;

public class CoverLetter extends Document {

	public CoverLetter(String docName) {
		super(docName);
	}

	@Override
	protected String getType() {
		return "Cover Letter";
	}

}
