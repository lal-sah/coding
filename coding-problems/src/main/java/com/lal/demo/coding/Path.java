package com.lal.demo.coding;

import java.util.Stack;
import java.util.StringTokenizer;

public class Path {
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

	public static void main(String[] args) {
		Path path = new Path("/a/b/c/d");
		path.cd("../../x/y");
		System.out.println(path.getPath());
	}
}
