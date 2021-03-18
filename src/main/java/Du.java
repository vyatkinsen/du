import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Locale;

public class Du {
	private final boolean human;
	private final boolean sumOfFilesFlag;
	private final boolean siSize;
	private final List<String> fileName;
	private long sumOfFilesSize;


	public Du(boolean human, boolean sumOfFilesFlag, boolean siSize, List<String> fileName) {
		this.sumOfFilesFlag = sumOfFilesFlag;
		this.siSize = siSize;
		this.human = human;
		this.fileName = fileName;
	}

	static class personalFilevisitor extends SimpleFileVisitor<Path>{
		public static long sumOfFiles;

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException{
			sumOfFiles += Files.size(file);
			return FileVisitResult.CONTINUE;
		}

		public static long getSum(){
			long x = sumOfFiles;
			sumOfFiles = 0;
			return x;
		}
	}

	public String util() throws IOException {
		StringBuilder result = new StringBuilder();
			for (String x : fileName){
				long fileSize;
				Path path = Paths.get(x);

				if (!Files.exists(path)) {
					System.err.println("Вы ввели имя несуществующего файла");
					System.exit(1);
				}

				if (Files.isDirectory(path)) {
					Files.walkFileTree(path, new personalFilevisitor());
					fileSize = personalFilevisitor.getSum();
				} else fileSize = Files.size(Path.of(x));

				if (sumOfFilesFlag) sumOfFilesSize += fileSize;
				else {
					String resultFileSize = sizeDeterminant(fileSize);
					result.append("\n").append("Размер ").append(x).append(" равен ").append(resultFileSize);
				}
			}

		if (sumOfFilesFlag) return "Сумма всех файлов равна " + sizeDeterminant(sumOfFilesSize);
		return result.append("\n").toString();
	}

	@NotNull
	private String sizeDeterminant(long num){
		int GB = (int) (Math.pow(2, 30) - 1);
		int MB = (int) (Math.pow(2, 20) - 1);
		int KB = (int) (Math.pow(2, 10) - 1);
		int mn = 0;

		if (!siSize) mn = 7;
		int scale = 3 + mn;
		String unit = "KB";

		if (human){
			if (num > GB) {
				unit = "GB";
				scale = 9 + mn * 3;
			} else if (num > MB) {
				unit = "MB";
				scale = 6 + mn * 2;
			} else if (num > KB) {
				unit = "KB";
			} else {
				unit = "B";
				scale = 0;
			}
		}
		if (siSize) return String.format(Locale.ROOT, "%.3f", num * Math.pow(10, -scale)) + " " + unit;
		else return String.format(Locale.ROOT, "%.3f", num * Math.pow(2, -scale)) + " " + unit;
	}
}