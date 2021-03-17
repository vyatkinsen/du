import org.kohsuke.args4j.*;
import java.util.ArrayList;
import java.util.List;

public class DuLauncher {
	@Option(name = "-h", usage = "Size of file in human variable")
	private boolean human;

	@Option(name = "-c", usage = "Sum of all files")
	private boolean sumOfFilesFlag;

	@Option(name = "--si", usage = "Size of file in SI")
	private boolean siSize;

	@Argument(required = true, metaVar = "fileName", usage = "File name")
	private List<String> inputFileName = new ArrayList<>();

	public static void main(String[] args) { new DuLauncher().launch(args); }

	private void launch(String[] args) {
		CmdLineParser parser = new CmdLineParser(this);
		try {
			parser.parseArgument(args);
		} catch (CmdLineException e) {
			System.err.println(e.getMessage());
			System.err.println("java -jar diskUsage.jar [-h] [-c] [--si] fileName1 fileName2 filename3...");
			parser.printUsage(System.err);
			System.exit(1);
		}

		Du du = new Du(human, sumOfFilesFlag, siSize, inputFileName);
		System.out.println(du.util());
	}
}