package model.document;

import java.util.ArrayList;
import java.util.List;

public class DocumentList {

	private static int count;
	private List<Document> documentList;

	public DocumentList() {
		this.documentList = new ArrayList<>();
	}

	public void addDoc(Object doc) {
		// implicitly casting
		Document result = (Document) doc;
		documentList.add(result);
		count++;

	}

	public int getCount() {
		return count;
	}

	public void removeDoc(String docID) {
		documentList.removeIf(doc -> doc.getDocId().equals(docID));
	}

	public Document findDoc(String docID) {
		for (Document doc : documentList) {
			if (doc.getDocId().equals(docID)) {
				return doc;
			}
		}
		return null;
	}
}
