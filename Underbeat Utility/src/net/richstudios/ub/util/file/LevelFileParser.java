package net.richstudios.ub.util.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.richstudios.ub.util.level.Level;
import net.richstudios.ub.util.level.song.Song;
import net.richstudios.ub.util.level.song.note.HoldNote;
import net.richstudios.ub.util.level.song.note.NormalNote;
import net.richstudios.ub.util.level.song.note.Note;
import net.richstudios.ub.util.level.song.note.Note.NoteLine;
import net.richstudios.ub.util.level.song.note.Note.NoteType;
import net.richstudios.ub.util.ref.References;

import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LevelFileParser {

	public static Level parse(File f) throws ParseException, FileNotFoundException, WrongFileTypeException, Exception {
		if (!f.exists())
			throw new FileNotFoundException("Could not find: " + f.getPath());
		if (FilenameUtils.getExtension(f.getPath()) != References.LEVEL_FILE_EXTENTION)
			throw new WrongFileTypeException(FilenameUtils.getExtension(f.getPath()), References.LEVEL_FILE_EXTENTION);

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(f);
		
		return parse(doc);
	}

	public static Level parseLocal(String f) throws ParseException, FileNotFoundException, WrongFileTypeException, Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(f);

		return parse(doc);
	}

	private static Level parse(Document doc) {
		String Lname = "NULL", author = "NULL", Sname = "NULL", artist = "NULL";
		int difficulty = 0;
		ArrayList<Note> notes = new ArrayList<Note>();

		Element root = doc.getDocumentElement();
		Lname = root.getAttribute("name");
		author = root.getAttribute("author");

		NodeList nodes = root.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node n = nodes.item(i);
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) n;
				if (e.getNodeName() == "song") {
					Sname = e.getAttribute("name");
					artist = e.getAttribute("artist");
					difficulty = Integer.parseInt(e.getAttribute("difficulty"));
					NodeList songNodes = e.getChildNodes();
					for (int j = 0; j < songNodes.getLength(); j++) {
						Node sn = songNodes.item(i);
						if (sn.getNodeType() == Node.ELEMENT_NODE) {
							Element se = (Element) sn;
							if (se.getNodeName() == "notes") {
								NodeList noteNodes = se.getChildNodes();
								for (int k = 0; k < noteNodes.getLength(); k++) {
									Node nn = nodes.item(i);
									if (nn.getNodeType() == Node.ELEMENT_NODE) {
										Element ne = (Element) nn;
										if (ne.getNodeName() == "note") {
											Note note = null;
											NoteType type = NoteType.valueOf(ne.getAttribute("type").toUpperCase());
											long position = Long.parseLong(ne.getAttribute("position"));
											NoteLine line = null;
											switch (type) {
											case NORMAL:
												line = NoteLine.getLineFromId((byte) Integer.parseInt(ne.getAttribute("line")));
												note = new NormalNote(line, position);
												break;
											case HOLD:
												line = NoteLine.getLineFromId((byte) Integer.parseInt(ne.getAttribute("line")));
												long length = Long.parseLong(ne.getAttribute("length"));
												note = new HoldNote(line, position, length);
												break;
											}
											notes.add(note);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return new Level(Lname, author, new Song(Sname, artist, notes.toArray(new Note[notes.size()])), null);
	}

	public static boolean write(Level l, File f) {
		return false;
	}
}
