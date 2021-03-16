import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;

public class duL {
	private final boolean human;
	private final boolean sumOfFilesFlag;
	private final boolean siSize;
	private final List<String> fileName;
	private double sumOfFilesSize;


	public duL(boolean human, boolean sumOfFilesFlag, boolean siSize, List<String> fileName) {
		this.sumOfFilesFlag = sumOfFilesFlag;
		this.siSize = siSize;
		this.human = human;
		this.fileName = fileName;
	}

	public String util() throws IOException {
		StringBuilder result = new StringBuilder();
		try{
			for (String x : fileName){
				String[] dir = x.split("/");
				String s = dir[dir.length - 1];
				double fileSize = Files.size(Path.of(x));
				if (sumOfFilesFlag) sumOfFilesSize += fileSize;
				else result.append("Размер файла ").append(s).append(" равен ").append(sizeDeterminant(Files.size(Path.of(x)))).append("\n");
			}
		} catch (NoSuchFileException e){
			e.printStackTrace();
			System.err.println("Вы ввели имя несуществующего файла");
			System.exit(1);
		}
		if (sumOfFilesFlag) return "Сумма всех файлов равна " + sizeDeterminant(sumOfFilesSize);
		return result.toString();
	}

	@NotNull
	private String sizeDeterminant(double num){
		String unit = "KB";
		int mn = 0;
		if (!siSize) mn = 7;
		int scale = 3 + mn;

		if (human){
			if (num > 1073741823) {
				unit = "GB";
				scale = 9 + mn * 3;
			} else if (num > 1048576) {
				unit = "MB";
				scale = 6 + mn * 2;
			} else if (num > 1023) {
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