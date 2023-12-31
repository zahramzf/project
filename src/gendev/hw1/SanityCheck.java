package gendev.hw1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SanityCheck {

	public static void main(String[] args) {
		System.out.println(
				"This is a sanity check to tell you whether you are handling files in the correct way. It DOES NOT mean anything about the mark you will receive.\n\n\n");
		task1();
		task2uc();
		task2ad();
		task3();
		task4();
		
		System.out.println("\n\nEND OF SANITY CHECK: Please read the output above carefully.");
	}

	private static void task1() {
		System.out.println("Task 1 Sanity Checking");
		System.out.println("---");
		String fname = "task1_description/description.txt";
		File f = new File(fname);
		if (!f.exists()) {
			System.out.println("The file " + fname + " appears to be missing. WARNING: This results in 0 marks.");
			System.out.println();
			return;
		}
		System.out.println("Found file " + fname);
		String content = "";
		try {
			content = Files.readString(f.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		int numWords = countWordsUsingSplit(content);
		System.out.println("It looks like your description contains " + numWords + " of max 200 words.");
		if (numWords < 120) {
			System.out.println("WARNING The number of words is a bit low.");
		}
		if (numWords > 220) {
			System.out.println("WARNING 10% over the wordlimit will incurr a penalty.");
		}
		System.out.println();
	}

	private static void task2ad() {
		System.out.println("Task 2 Acticity Sanity Checking");
		System.out.println("---");
		String fname = "task2_activity/activity.drawio";
		File f = new File(fname);
		if (!f.exists()) {
			System.out.println("The file " + fname + " appears to be missing. WARNING: This results in 0 marks.");
			System.out.println();
			return;
		}
		System.out.println("Found file " + fname);

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		Document doc = null;
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(f);
		} catch (Exception e) {
			System.out.println("WARNING: Document parsing failed. This results in 0 marks.");
			return;
		}
		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("mxCell");
		if (nList.getLength() == 0) {
			System.out.println(
					"WARNING: It looks like it is compressed for exporting. Export it uncompressed from app.diagrams.net. This results in 0 marks.");
			System.out.println();
			return;
		}
		int actions = 0;
		for (int i = 0; i < nList.getLength(); i++) {
			Node n = nList.item(i);
			String style = "";
			try {
				style = n.getAttributes().getNamedItem("style").getNodeValue();
			} catch (Exception e) {
			}
			if (style.contains("rounded=1")) {
				actions++;
			}
		}

		System.out.println("It looks like you have " + actions + " actions in your activity diagram.");
		if (actions < 5) {
			System.out.println("WARNING the number of actions looks too low for full marks.");
		}
		System.out.println();
	}

	private static void task2uc() {
		System.out.println("Task 2 UseCase Sanity Checking");
		System.out.println("---");
		String fname = "task2_usecase/usecase.drawio";
		try {
			Files.write(Paths.get("build.properties"), ("#" + System.currentTimeMillis()).getBytes(),
					StandardOpenOption.APPEND);
		} catch (IOException e) {
		}
		File f = new File(fname);
		if (!f.exists()) {
			System.out.println("The file " + fname + " appears to be missing. WARNING: This results in 0 marks.");
			System.out.println();
			return;
		}
		System.out.println("Found file " + fname);

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		Document doc = null;
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(f);
		} catch (Exception e) {
			System.out.println("WARNING: Document parsing failed. This results in 0 marks.");
			System.out.println();
			return;
		}
		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("mxCell");
		if (nList.getLength() == 0) {
			System.out.println(
					"WARNING: It looks like it is compressed for exporting. Export it uncompressed from app.diagrams.net. This results in 0 marks.");
			System.out.println();
			return;
		}

		int usecases = 0;
		for (int i = 0; i < nList.getLength(); i++) {
			Node n = nList.item(i);
			String style = "";
			try {
				style = n.getAttributes().getNamedItem("style").getNodeValue();
			} catch (Exception e) {
			}
			if (style.contains("ellipse")) {
				usecases++;
			}
		}

		System.out.println("It looks like you have " + usecases + " usecases in your usecase diagram.");
		if (usecases < 5) {
			System.out.println("WARNING the number of usecases looks to low for full marks.");
		}
		System.out.println();
	}

	private static void task3() {
		System.out.println("Task 3 Sanity Checking");
		System.out.println("---");
		String fname = "model/hw1.ecore";
		File f = new File(fname);
		if (!f.exists()) {
			System.out.println("The file " + fname + " appears to be missing. WARNING: This results in 0 marks.");
			return;
		}
		System.out.println("Found file " + fname);

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		Document doc = null;
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(f);
		} catch (Exception e) {
			System.out.println("WARNING: Document parsing failed. This results in 0 marks.");
			return;
		}
		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("eClassifiers");
		int classes = nList.getLength();

		System.out.println("It looks like you have " + classes + " classes in your class diagram.");
		if (classes < 5) {
			System.out.println("WARNING the number of classes looks too low for full marks.");
		}
		System.out.println();
	}

	private static void task4() {
		System.out.println("Task 4 Sanity Checking");
		System.out.println("---");
		String fname = "task4_instance/instance.xmi";
		File f = new File(fname);
		if (!f.exists()) {
			System.out.println("The file " + fname + " appears to be missing. WARNING: This results in 0 marks.");
		} else {
			System.out.println("Found file " + fname);
		}

		for (int i = 1; i <= 3; i++) {
			fname = "task4_instance/inv" + i + "_sat.xmi";
			if (!Files.exists(Paths.get(fname))) {
				System.out.println("The file " + fname + " appears to be missing. WARNING: This results in 0 marks.");
			} else {
				System.out.println("Found file " + fname);
			}
			fname = "task4_instance/inv" + i + "_fail.xmi";
			if (!Files.exists(Paths.get(fname))) {
				System.out.println("The file " + fname + " appears to be missing. WARNING: This results in 0 marks.");
			} else {
				System.out.println("Found file " + fname);
			}
		}

		System.out.println();
	}

	public static int countWordsUsingSplit(String input) {
		if (input == null || input.isEmpty()) {
			return 0;
		}

		String[] words = input.split("\\s+");
		return words.length;
	}
}
