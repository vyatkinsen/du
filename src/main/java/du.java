import org.jetbrains.annotations.NotNull;
import java.io.File;
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

	private long sizeForFolder(File directory) {
		long lenOfFolder = 0;
		for (File attachedSmth : directory.listFiles()) {
			if (attachedSmth.isDirectory()) lenOfFolder += sizeForFolder(attachedSmth);
			else lenOfFolder += attachedSmth.length();
		}
		return lenOfFolder;
	}

	public String util() {
		StringBuilder result = new StringBuilder();
			for (String x : fileName){
				long fileSize;
				File input = new File(x);

				if (!(input.exists() || input.isDirectory())) {
					System.err.println("Вы ввели имя несуществующего файла");
					System.exit(1);
				}

				if (input.isFile()) fileSize = input.length();
				else fileSize = sizeForFolder(input);

				if (sumOfFilesFlag) sumOfFilesSize += fileSize;
				else result.append("\n").append("Размер ").append(x).append(" равен ").append(sizeDeterminant(fileSize));
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