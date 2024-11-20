package model.document;

import java.util.Date;

public abstract class Document {

	private final String prefix = "doc-";
	private static int nextId = 1;
	private String docId;

	private String docName;
	private Date creationDate;
	private String filePath;
	private double version;

	public Document(String docName) {
		this.docId = prefix + nextId;
		this.docName = docName;
		this.creationDate = new Date();
		this.filePath = "";
		this.version = 1.0;
		nextId++;
	}

	public void updateName(String newName) {
		this.docName = newName;
	}

	public void updatePath(String newPath) {
		this.filePath = newPath;
	}

	protected abstract String getType(); // for cover letter and resume

	public String getDocId() {
		return docId;
	}

	public String getDocName() {
		return docName;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public String getFilePath() {
		return filePath;
	}

	public double getVersion() {
		return version;
	}

	// update document, change version
	// TODO: future work

}
