

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.function.Consumer;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class HelloWorld {

	// public class Palindrome {
	public static boolean isPalindrome(String word) {
		if (word != null && !"".equals(word.trim())) { // use StringUtils.isNotBlank in real app
			StringBuffer buffer = new StringBuffer();

			for (int i = word.length(); i > 0; i--) {
				buffer.append(word.charAt(i - 1));
			}

			return word.equalsIgnoreCase(buffer.toString());
		}

		return false;
	}

	// public static void main(String[] args) {
	// System.out.println(HelloWorld.isPalindrome("Deleveled"));
	// System.out.println(HelloWorld.isPalindrome("rubber"));
	// System.out.println(HelloWorld.isPalindrome("rubbur"));
	// System.out.println(HelloWorld.isPalindrome("lal"));
	// }
	// }

	public static class TextInput {

		private StringBuffer value = new StringBuffer();

		public void add(char c) {
			value.append(c);
		}

		public String getValue() {
			return value.toString();
		}
	}

	public static class NumericInput extends TextInput {

		@Override
		public void add(char c) {
			if (c >= '0' && c <= '9') {
				super.add(c);
			} else {
				System.out.println("Invalid input character (" + c + ") was deteced and ignored!");
			}
		}
	}

	// public static void main(String[] args) {
	// TextInput input = new NumericInput();
	// input.add('1');
	// input.add('a');
	// input.add('0');
	// System.out.println(input.getValue());
	// }

	public static class Node {
		public int value;
		public Node left, right;

		public Node(int value, Node left, Node right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}
	}

	// public class BinarySearchTree {
	public static boolean contains(Node root, int value) {
		if (root == null) {
			return false;
		} else if (root.value == value) {
			return true;
		} else if (value < root.value) {
			return contains(root.left, value);
		} else {
			return contains(root.right, value);
		}
	}

	// public static void main(String[] args) {
	// Node n1 = new Node(1, null, null);
	// Node n3 = new Node(3, null, null);
	// Node n2 = new Node(2, n1, n3);
	//
	// System.out.println(contains(n2, 3));
	// }
	// }

	public static int[] findTwoSumOn(int[] list, int sum) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < list.length; i++) {
			int diff = sum - list[i];
			if (map.containsKey(diff) && map.get(diff) != i) {
				return new int[] { i, map.get(diff) };
			} else {
				map.put(list[i], i);
			}
		}

		return null;
	}

	public static int[] findTwoSum1(int[] list, int sum) {
		if (list == null || list.length < 2)
			return null;

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < list.length; i++) {
			if (map.containsKey(list[i])) {
				return new int[] { map.get(list[i]), i };
			} else {
				map.put(sum - list[i], i);
			}
		}

		return null;
	}

	public static int[] findTwoSum(int[] list, int sum) {
		for (int i = 0; i < list.length; i++) {
			for (int j = i + 1; j < list.length; j++) {
				if ((list[i] + list[j]) == sum) {
					return new int[] { i, j };
				}
			}
		}

		return null;
	}

	// public static void main(String[] args) {
	// int[] indices = findTwoSumOn(new int[] { 3, 1, 5, 7, 5, 9 }, 16);
	// if (indices != null) {
	// System.out.println(indices[0] + " " + indices[1]);
	// }
	// }

	public static Collection<String> folderNames(String xml, char startingLetter) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document document = factory.newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
		document.getDocumentElement().normalize();

		Collection<String> names = new ArrayList<>();
		collectFolderNames(names, document.getDocumentElement(), String.valueOf(startingLetter));

		return names;
	}

	private static void collectFolderNames(Collection<String> namesWithStartingLetter, org.w3c.dom.Node node,
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

	// public static void main(String[] args) throws Exception {
	// String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<folder
	// name=\"c\">"
	// + "<folder name=\"program files\">" + "<folder name=\"uninstall information\"
	// />" + "</folder>"
	// + "<folder name=\"users\" />" + "</folder>";
	//
	// Collection<String> names = folderNames(xml, 'u');
	// for (String name : names)
	// System.out.println(name);
	// }

	public static int countNumbersOn(int[] sortedArray, int lessThan) {
		int count = 0;
		for (int i = 0; i < sortedArray.length; i++) {
			if (sortedArray[i] < lessThan) {
				count++;
			} else {
				break;
			}
		}

		return count;
	}

	public static int binarySearch(int[] sortedArray, int lessThan, int l, int r) {
		if (r >= l) {
			int mid = l + (r - 1) / 2;

			if (sortedArray[mid] == lessThan) {
				return mid;
			} else if (lessThan > sortedArray[mid] && lessThan <= sortedArray[mid + 1]) {
				return mid + 1;
			}

			if (sortedArray[mid] > lessThan) {
				return binarySearch(sortedArray, lessThan, l, mid - 1);
			}

			return binarySearch(sortedArray, lessThan, mid + 1, r);
		}

		return -1;
	}

	public static int countNumbers(int[] sortedArray, int lessThan) {
		if (lessThan <= sortedArray[0]) {
			return 0;
		} else if (lessThan > sortedArray[sortedArray.length - 1]) {
			return sortedArray.length;
		} else {
			return binarySearch(sortedArray, lessThan, 0, sortedArray.length - 1);
		}
	}

	// public static void main(String[] args) {
	// System.out.println(countNumbers(new int[] { 1, 3, 5, 7 }, 0));
	// System.out.println(countNumbers(new int[] { 1, 3, 5, 7 }, 1));
	// System.out.println(countNumbers(new int[] { 1, 3, 5, 7 }, 2));
	// System.out.println(countNumbers(new int[] { 1, 3, 5, 7 }, 3));
	// System.out.println(countNumbers(new int[] { 1, 3, 5, 7 }, 4));
	// System.out.println(countNumbers(new int[] { 1, 3, 5, 7 }, 5));
	// System.out.println(countNumbers(new int[] { 1, 3, 5, 7 }, 6));
	// System.out.println(countNumbers(new int[] { 1, 3, 5, 7 }, 7));
	// System.out.println(countNumbers(new int[] { 1, 3, 5, 7 }, 8));
	// System.out.println(countNumbers(new int[] { 1, 3, 5, 7 }, 9));
	// }

	private static class Wagon {
		private int wagonId;
		private Wagon left;
		private Wagon right;

		public Wagon(int wagonId) {
			this.wagonId = wagonId;
		}
	}

	public static class TrainComposition {
		private Wagon leftMostWagon;
		private Wagon rightMostWagon;

		public void attachWagonFromLeft(int wagonId) {
			Wagon wagon = new Wagon(wagonId);
			if (this.leftMostWagon == null) {
				this.leftMostWagon = this.rightMostWagon = wagon;
			} else {
				this.leftMostWagon.left = wagon;
				wagon.right = this.leftMostWagon;
				this.leftMostWagon = wagon;
			}
		}

		public void attachWagonFromRight(int wagonId) {
			Wagon wagon = new Wagon(wagonId);

			if (this.rightMostWagon == null) {
				this.leftMostWagon = this.rightMostWagon = wagon;
			} else {
				this.rightMostWagon.right = wagon;
				wagon.left = this.rightMostWagon;
				this.rightMostWagon = wagon;
			}
		}

		public int detachWagonFromLeft() {
			if (this.leftMostWagon == null) {
				return -1;
			}
			int wagonId = this.leftMostWagon.wagonId;
			Wagon wagon = this.leftMostWagon.right;
			if (wagon != null) {
				wagon.left = null;
			}
			this.leftMostWagon = null;
			this.leftMostWagon = wagon;

			return wagonId;
		}

		public int detachWagonFromRight() {
			if (this.rightMostWagon == null) {
				return -1;
			}
			int wagonId = this.rightMostWagon.wagonId;
			Wagon wagon = this.rightMostWagon.left;
			if (wagon != null) {
				wagon.right = null;
			}
			this.rightMostWagon = null;
			this.rightMostWagon = wagon;

			return wagonId;
		}
	}

	// public static void main(String[] args) {
	// TrainComposition tree = new TrainComposition();
	// tree.attachWagonFromLeft(7);
	// tree.attachWagonFromLeft(13);
	// System.out.println(tree.detachWagonFromRight()); // 7
	// System.out.println(tree.detachWagonFromLeft()); // 13
	// }

	private static class Path {
		private String path;

		public Path(String path) {
			this.path = path;
		}

		public String getPath() {
			return path;
		}

		public void cd(String newPath) {
			StringTokenizer pathTokenizer = new StringTokenizer(path, "/");
			Stack<String> pathStack = new Stack<>();
			while (pathTokenizer.hasMoreTokens()) {
				pathStack.push(pathTokenizer.nextToken());
			}

			StringTokenizer newPathTokenizer = new StringTokenizer(newPath, "/");
			while (newPathTokenizer.hasMoreTokens()) {
				String token = newPathTokenizer.nextToken();
				if (token.equals("..")) {
					pathStack.pop();
				} else {
					pathStack.push(token);
				}
			}

			StringBuffer buffer = new StringBuffer();
			while (!pathStack.isEmpty()) {
				buffer.append(pathStack.pop()).append("/");
			}

			this.path = buffer.reverse().toString();
		}
	}

	// public static void main(String[] args) {
	// Path path = new Path("/a/b/c/d");
	// path.cd("w/../../x/y");
	// System.out.println(path.getPath());
	// }

	private static class FirstException extends Exception {
	}

	private static class SecondException extends Exception {
	}

	private void rethrowException() throws FirstException, SecondException {
		try {

		} catch (Exception e) {
			throw e;
		}
	}

	@FunctionalInterface
	public interface MyInterface {
		void instanceMethod();
		// void instanceMethod2();

		default void interfaceMethod() {
			basics();
		}

		default void interfaceMethod2() {
			basics();
		}

		static void staticMethod() {
			basic();
		}

		private void basics() {
			System.out.println("Interface method.");
		}

		private static void basic() {
			System.out.println("Interface method static.");
		}
	}

	private static class MyImpl implements MyInterface {

		@Override
		public void instanceMethod() {
			interfaceMethod();
		}

	}

	@FunctionalInterface
	public interface MyAnotherInterface {
		void abstractMethod(String name);
	}

	public static void main(String[] args) {
		List<Integer> numbers = List.of(1, 2, 3, 7, 5, 4, 11, 21, -1, 2000);

		numbers.forEach(new Consumer<Integer>() {
			@Override
			public void accept(Integer t) {
				System.out.print(t % 2 == 1 ? t + " " : "");
			}
		});

		System.out.println("Stream API");
		numbers
			.stream()
			.filter(n -> n % 2 == 1)
			.forEach(n -> System.out.println(n));
		
		System.out.println("Stream API");
		numbers
			.stream()
			.filter(n -> n % 2 == 1)
			.forEach(System.out::println);
		
		System.out.println("Parallel streaming...");
		numbers
			.parallelStream()
			.filter(n -> n % 2 == 1)
			.forEach(System.out::println);

		MyInterface mi = new MyImpl();
		mi.basics();
		mi.instanceMethod();
		mi.interfaceMethod();
		mi.interfaceMethod2();
		MyInterface.basic();
		MyInterface.staticMethod();

		MyInterface annImpl1 = () -> System.out.println("Ann impl 1s");
		annImpl1.instanceMethod();

		MyInterface annImpl = () -> {
			System.out.println("Anonymous...");
			MyInterface.basic();
		};

		annImpl.instanceMethod();

		MyAnotherInterface maAnn1 = (namea) -> System.out.println("My another annn " + namea);
		maAnn1.abstractMethod("X");

	}

	public abstract class AbstractClass implements MyInterface {

		public AbstractClass() {
			instanceMethod();
			interfaceMethod();
			interfaceMethod2();
		}
	}
}
