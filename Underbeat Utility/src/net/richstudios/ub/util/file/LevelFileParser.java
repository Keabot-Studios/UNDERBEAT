package net.richstudios.ub.util.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.richstudios.ub.util.level.Level;
import net.richstudios.ub.util.level.song.note.Note;
import net.richstudios.ub.util.ref.References;

import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LevelFileParser {
	public static Level parse(File f) throws ParseException, FileNotFoundException, WrongFileTypeException, Exception {
		if(!f.exists()) throw new FileNotFoundException("Could not find: " + f.getPath());
		if(FilenameUtils.getExtension(f.getPath()) != References.LEVEL_FILE_EXTENTION) throw new WrongFileTypeException(FilenameUtils.getExtension(f.getPath()), References.LEVEL_FILE_EXTENTION);
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(f);
		Element root = doc.getDocumentElement();
		NodeList nodes = root.getChildNodes();
		for(int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				if(element.getNodeName() == "name") {
					
				}
			}
		}
		return null;
	}
	
	public static Level parseLocal(String f) throws ParseException, FileNotFoundException, WrongFileTypeException, Exception {
		String Lname, author, Sname, artist;
		ArrayList<Note> notes = new ArrayList<Note>();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(f);
		
		Element root = doc.getDocumentElement();
		NodeList nodes = root.getChildNodes();
		
		for(int i = 0; i < nodes.getLength(); i++) {
			Node n = nodes.item(i);
			if(n.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) n;
				if(e.getNodeName() == "name") {
					Lname = e.getTextContent();
				} else if(e.getNodeName() == "author") {
					author = e.getTextContent();
				} else if(e.getNodeName() == "song") {
					NodeList songNodes =  e.getChildNodes();
					for(int j = 0; j < songNodes.getLength(); j++) {
						Node sn = songNodes.item(i);
						if(sn.getNodeType() == Node.ELEMENT_NODE) {
							Element se = (Element) sn;
							if(se.getNodeName() == "name") {
								
							} else if(se.getNodeName() == "artist") {
								
							} else if(e.getNodeName() == "notes") {
								NodeList noteNodes =  e.getChildNodes();
								for(int k = 0; k < noteNodes.getLength(); k++) {
									Node nn = nodes.item(i);
									if(nn.getNodeType() == Node.ELEMENT_NODE) {
										Element ne = (Element) nn;
										if(e.getNodeName() == "name") {
											
										} else if(e.getNodeName() == "artist") {
											
										} else if(e.getNodeName() == "notes") {
											
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	public static boolean write(Level l, File f) {
		return false;
	}
}
