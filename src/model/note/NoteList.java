package model.note;

import java.util.ArrayList;
import java.util.List;

public class NoteList {

	private static int count = 0;

	private ArrayList<Note> noteList;

	public NoteList() {
		this.noteList = new ArrayList<>();
	}

	public ArrayList<Note> getAllNotes() {
		return noteList;
	}

	public List<String> getNoteTitleList() {
		List<String> titleList = new ArrayList<>();
		for (Note n : noteList) {
			titleList.add(n.getTitle());
		}
		return titleList;
	}

	public void addNote(Note note) {
		noteList.add(note);
		count++;
	}

	public int getCount() {
		return this.count;
	}

	public Note findNoteById(String noteId) {
		for (Note note : noteList) {
			if (note.getNoteId().equals(noteId)) {
				return note;
			}
		}
		return null;
	}

	// update corresponding part base on the input condition
	public void updateNote(String noteId, String target, String updateinfo) {
		if (findNoteById(noteId) != null) {
			Note targetNote = findNoteById(noteId);
			switch (target) {
			case "title":
				targetNote.updateContent(updateinfo);
				break;
			case "content":
				targetNote.updateContent(updateinfo);
				break;
			default:
				break;
			}

		}
		System.out.println("cannot find the note");

	}

}
