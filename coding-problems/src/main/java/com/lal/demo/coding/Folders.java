package com.lal.demo.coding;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public class Folders {
	public static Collection<String> folderNames(String xml, char startingLetter) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document document = factory.newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
		document.getDocumentElement().normalize();

		Collection<String> names = new ArrayList<>();
		collectFolderNames(names, document.getDocumentElement(), String.valueOf(startingLetter));

		return names;
	}

	private static void collectFolderNames(Collection<String> namesWithStartingLetter, Node node,
			String startingLetter) {
		if (node != null && node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
			Element element = (Element) node;

			String name = element.getAttribute("name");
			if (name.startsWith(startingLetter)) {
				namesWithStartingLetter.add(name);
			}
		}

		if (node.hasChildNodes()) {
			NodeList nodeList = node.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				collectFolderNames(namesWithStartingLetter, nodeList.item(i), startingLetter);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// @formatter:off
        String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<folder name=\"c\">" +
                    "<folder name=\"program files\">" +
                        "<folder name=\"uninstall information\" />" +
                    "</folder>" +
                    "<folder name=\"users\" />" +
                "</folder>";
		// @formatter:on

		Collection<String> names = folderNames(xml, 'u');
		for (String name : names)
			System.out.println(name);
	}
}
