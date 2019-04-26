package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lines {
	public static void main(String[] args) throws IOException {
		List<String> paths = getPaths();
		int totalLines = 0;
		int totalFiles = 0;
		for (String path : paths) {
			System.out.print("Scanning: " + path.substring(path.indexOf("main")) + " -> ");
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
			long lines = in.lines().count();
			System.out.println(lines);
			totalLines += lines;
			totalFiles++;
			in.close();
		}
		System.out.println("Total files: " + totalFiles);
		System.out.println("Total lines: " + totalLines);
	}
	
	private static List<String> getPaths() {
		List<String> paths = new ArrayList<>();
		List<String> labs = Arrays.asList("mvc", "prefabs", "util");
		labs = labs.stream().map(s -> "C:\\Users\\fastr\\IdeaProjects\\Game\\src\\main\\" + s).collect(Collectors.toList());
		for (String lab : labs) {
			File[] files = new File(lab).listFiles();
			assert files != null;
			Arrays.stream(files).filter(file -> !file.isDirectory()).forEach(file -> paths.add(file.getAbsolutePath()));
		}
		return paths;
	}
}