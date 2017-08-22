import java.io.*;
import java.util.Arrays;

public class ListDirectory {
	static int depth = 0;

	public static void main(String[] args) {
		listDirectory("D:\\cu");
	}

	public static void listDirectory(String path) {
		File file = new File(path);
		File[] files = file.listFiles();
		String[] directories = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File current, String name) {
				return new File(current, name).isDirectory();
			}
		});
		//
		System.out.println(path);
		depth++;
		//
		if (files != null) {
			for (File _file : files) {
				for (int i = 0; i < depth; i++)
					System.out.print("\t");
				System.out.println(_file.getAbsolutePath());
			}
		}
		if (directories != null) {
			for (String directory : directories) {
				listDirectory(path.substring(0, path.lastIndexOf('\\')) + "\\" + directory);
				depth--;
			}
		}
	}
}
