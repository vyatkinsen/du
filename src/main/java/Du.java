import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

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

	static class PersonalFilevisitor extends SimpleFileVisitor<Path>{
		private long sumOfFiles;

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException{
			sumOfFiles += Files.size(file);
			return FileVisitResult.CONTINUE;
		}
		public long getSum(){ return sumOfFiles; }
	}

	private String collector(Map<String, String> resultFileWithSize){
		StringBuilder res = new StringBuilder();
		resultFileWithSize.forEach((name, size) -> res.append("\n").append("Размер ").append(name).append(" равен ").append(size));
		return res.append("\n").toString();
	}

	@NotNull
	private String sizeDeterminant(long num){
		int gb = (int) (Math.pow(2, 30) - 1);
		int mb = (int) (Math.pow(2, 20) - 1);
		int kb = (int) (Math.pow(2, 10) - 1);
		int mn = 0;

		if (!siSize) mn = 7;
		int scale = 3 + mn;
		String unit = "KB";

		if (human){
			if (num > gb) {
				unit = "GB";
				scale = 9 + mn * 3;
			} else if (num > mb) {
				unit = "MB";
				scale = 6 + mn * 2;
			} else if (num > kb) {
				unit = "KB";
			} else {
				unit = "B";
				scale = 0;
			}
		}
		if (siSize) return String.format(Locale.ROOT, "%.3f", num * Math.pow(10, -scale)) + " " + unit;
		else return String.format(Locale.ROOT, "%.3f", num * Math.pow(2, -scale)) + " " + unit;
	}

	private String distributor(Map<String, Long> fileWithSize){
		Map<String, String> resultFileWithSize = new LinkedHashMap<>();
		fileWithSize.forEach((path, size) -> {
			if (sumOfFilesFlag) sumOfFilesSize += size;
			else resultFileWithSize.put(path, sizeDeterminant(size));
		});
		if (sumOfFilesFlag) return "Сумма всех файлов равна " + sizeDeterminant(sumOfFilesSize);
		return collector(resultFileWithSize);
	}

	public String util() throws IOException {
		Map<String, Long> fileWithSize = new LinkedHashMap<>();

		for (String x : fileName) {
			long fileSize;
			Path path = Paths.get(x);
			if (!Files.exists(path)) {
				System.err.println("Вы ввели имя несуществующего файла");
				System.exit(1);
			}
			if (Files.isDirectory(path)) {
				PersonalFilevisitor fv = new PersonalFilevisitor();
				Files.walkFileTree(path, fv);
				fileSize = fv.getSum();
			} else fileSize = Files.size(Path.of(x));
			fileWithSize.put(x, fileSize);
		}
		return distributor(fileWithSize);
	}
}