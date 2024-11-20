package model.note;

import java.time.LocalDate;

public class Note {

	// note id format: note-1, note-2, etc.
	final String prefix = "note-";
	private static int nextId = 1;
	private String noteId;

	private String title;
	private String content;
	private LocalDate createDate;
	private LocalDate updateDate;

	public Note(String title, String content) {
		this.noteId = prefix + nextId;
		this.title = title;
		this.content = content;
		this.createDate = LocalDate.now();
		this.updateDate = LocalDate.now();
		nextId++;
	}

	public String getNoteId() {
		return this.noteId;
	}

	public String getTitle() {
		return this.title;
	}

	public String getContent() {
		return this.content;
	}

	public void updateContent(String newContent) {
		this.content = newContent;
		this.updateDate = LocalDate.now();
	}

	public void updateTitle(String newTitle) {
		this.title = newTitle;
		this.updateDate = LocalDate.now();
	}

	@Override
	public String toString() {
		return this.title;
	}
}
