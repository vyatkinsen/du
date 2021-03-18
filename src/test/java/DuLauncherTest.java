import java.io.IOException;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DuLauncherTest {
	String[] fileNames = {"files/ИсторияРоссии.rtf", "files/kitten.jpg", "files/Козодой.jpg", "files/car.jpeg"};
	String[] directoryNames = {"files", "files/directory1", "files/directory1/directory2"};
	String[] filesAndDirectories = {"files/ИсторияРоссии.rtf", "files/directory1/directory2", "files/car.jpeg", "files/directory1", "files/kitten.jpg" };

	@Test
	void allTrueWithFilesTest() throws IOException {
		assertEquals("Сумма всех файлов равна 4.915 MB", new Du(true, true, true, Arrays.asList(fileNames)).util());
	}

	@Test
	void allFalseWithFiles() throws IOException {
		assertEquals("""
       
		Размер files/ИсторияРоссии.rtf равен 786.114 KB
		Размер files/kitten.jpg равен 45.861 KB
		Размер files/Козодой.jpg равен 89.851 KB
		Размер files/car.jpeg равен 3877.650 KB
       	""", new Du(false, false, false, Arrays.asList(fileNames)).util());
	}

	@Test
	void humanReadableSumOfFilesWithFilesTest() throws IOException {
		assertEquals("Сумма всех файлов равна 4.687 MB", new Du(true, true, false, Arrays.asList(fileNames)).util());
	}

	@Test
	void sumOfFilesSiWithFilesTest() throws IOException {
		assertEquals("Сумма всех файлов равна 4914.664 KB", new Du(false, true, true, Arrays.asList(fileNames)).util());
	}

	@Test
	void sumOfFilesWithFilesTest() throws IOException {
		assertEquals("Сумма всех файлов равна 4799.477 KB", new Du(false, true, false, Arrays.asList(fileNames)).util());
	}

	@Test
	void humanReadableSiWithFilesTest() throws IOException {
		assertEquals("""
       
		Размер files/ИсторияРоссии.rtf равен 804.981 KB
		Размер files/kitten.jpg равен 46.962 KB
		Размер files/Козодой.jpg равен 92.007 KB
		Размер files/car.jpeg равен 3.971 MB
       	""", new Du(true, false, true, Arrays.asList(fileNames)).util());
	}

	@Test
	void humanReadableWithFilesTest() throws IOException {
		assertEquals("""
       
		Размер files/ИсторияРоссии.rtf равен 786.114 KB
		Размер files/kitten.jpg равен 45.861 KB
		Размер files/Козодой.jpg равен 89.851 KB
		Размер files/car.jpeg равен 3.787 MB
       """, new Du(true, false, false, Arrays.asList(fileNames)).util());
	}

	@Test
	void siWithFilesTest() throws IOException {
		assertEquals("""
       
       Размер files/ИсторияРоссии.rtf равен 804.981 KB
       Размер files/kitten.jpg равен 46.962 KB
       Размер files/Козодой.jpg равен 92.007 KB
       Размер files/car.jpeg равен 3970.714 KB
       """, new Du(false, false, true, Arrays.asList(fileNames)).util());
	}

	@Test
	void allTrueWithDirectoriesTest() throws IOException {
		assertEquals("Сумма всех файлов равна 19.538 MB", new Du(true, true, true, Arrays.asList(directoryNames)).util());
	}

	@Test
	void allFalseWithDirectories() throws IOException {
		assertEquals("""
       
		Размер files равен 9610.961 KB
		Размер files/directory1 равен 4805.480 KB
		Размер files/directory1/directory2 равен 4663.765 KB
       	""", new Du(false, false, false, Arrays.asList(directoryNames)).util());
	}

	@Test
	void humanReadableSumOfFilesWithDirectoriesTest() throws IOException {
		assertEquals("Сумма всех файлов равна 18.633 MB", new Du(true, true, false, Arrays.asList(directoryNames)).util());
	}

	@Test
	void sumOfFilesSiWithDirectoriesTest() throws IOException {
		assertEquals("Сумма всех файлов равна 19538.131 KB", new Du(false, true, true, Arrays.asList(directoryNames)).util());
	}

	@Test
	void sumOfFilesWithDirectoriesTest() throws IOException {
		assertEquals("Сумма всех файлов равна 19080.206 KB", new Du(false, true, false, Arrays.asList(directoryNames)).util());
	}

	@Test
	void humanReadableSiWithDirectoriesTest() throws IOException {
		assertEquals("""
       
		Размер files равен 9.842 MB
		Размер files/directory1 равен 4.921 MB
		Размер files/directory1/directory2 равен 4.776 MB
       	""", new Du(true, false, true, Arrays.asList(directoryNames)).util());
	}

	@Test
	void humanReadableWithDirectoriesTest() throws IOException {
		assertEquals("""
       
		Размер files равен 9.386 MB
		Размер files/directory1 равен 4.693 MB
		Размер files/directory1/directory2 равен 4.554 MB
       	""", new Du(true, false, false, Arrays.asList(directoryNames)).util());
	}

	@Test
	void siWithDirectoriesTest() throws IOException {
		assertEquals("""
       
		Размер files равен 9841.624 KB
		Размер files/directory1 равен 4920.812 KB
		Размер files/directory1/directory2 равен 4775.695 KB
       	""", new Du(false, false, true, Arrays.asList(directoryNames)).util());
	}

	@Test
	void allTrueWithFilesAndDirectoriesTest() throws IOException {
		assertEquals("Сумма всех файлов равна 14.519 MB", new Du(true, true, true, Arrays.asList(filesAndDirectories)).util());
	}

	@Test
	void allFalseWithFilesAndDirectories() throws IOException {
		assertEquals("""
       
		Размер files/ИсторияРоссии.rtf равен 786.114 KB
		Размер files/directory1/directory2 равен 4663.765 KB
		Размер files/car.jpeg равен 3877.650 KB
		Размер files/directory1 равен 4805.480 KB
		Размер files/kitten.jpg равен 45.861 KB
       	""", new Du(false, false, false, Arrays.asList(filesAndDirectories)).util());
	}

	@Test
	void humanReadableSumOfFilesWithFilesAndDirectoriesTest() throws IOException {
		assertEquals("Сумма всех файлов равна 13.847 MB", new Du(true, true, false, Arrays.asList(filesAndDirectories)).util());
	}

	@Test
	void sumOfFilesSiWithFilesAndDirectoriesTest() throws IOException {
		assertEquals("Сумма всех файлов равна 14519.164 KB", new Du(false, true, true, Arrays.asList(filesAndDirectories)).util());
	}

	@Test
	void sumOfFilesWithFilesAndDirectoriesTest() throws IOException {
		assertEquals("Сумма всех файлов равна 14178.871 KB", new Du(false, true, false, Arrays.asList(filesAndDirectories)).util());
	}

	@Test
	void humanReadableSiWithFilesAndDirectoriesTest() throws IOException {
		assertEquals("""
       
		Размер files/ИсторияРоссии.rtf равен 804.981 KB
		Размер files/directory1/directory2 равен 4.776 MB
		Размер files/car.jpeg равен 3.971 MB
		Размер files/directory1 равен 4.921 MB
		Размер files/kitten.jpg равен 46.962 KB
       	""", new Du(true, false, true, Arrays.asList(filesAndDirectories)).util());
	}

	@Test
	void humanReadableWithFilesAndDirectoriesTest() throws IOException {
		assertEquals("""
       
		Размер files/ИсторияРоссии.rtf равен 786.114 KB
		Размер files/directory1/directory2 равен 4.554 MB
		Размер files/car.jpeg равен 3.787 MB
		Размер files/directory1 равен 4.693 MB
		Размер files/kitten.jpg равен 45.861 KB
       	""", new Du(true, false, false, Arrays.asList(filesAndDirectories)).util());
	}

	@Test
	void siWithFilesAndDirectoriesTest() throws IOException {
		assertEquals("""
       
		Размер files/ИсторияРоссии.rtf равен 804.981 KB
		Размер files/directory1/directory2 равен 4775.695 KB
		Размер files/car.jpeg равен 3970.714 KB
		Размер files/directory1 равен 4920.812 KB
		Размер files/kitten.jpg равен 46.962 KB
       	""", new Du(false, false, true, Arrays.asList(filesAndDirectories)).util());
	}
}