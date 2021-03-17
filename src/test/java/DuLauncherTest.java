import java.io.IOException;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DuLauncherTest {
	String[] fileNames = {"files/ИсторияРоссии.rtf", "files/kitten.jpg", "files/Козодой.jpg", "files/car.jpeg"};
	String[] directoryNames = {"files", "files/directory1", "files/directory1/directory2"};
	String[] filesAndDirectories = {"files/ИсторияРоссии.rtf", "files/directory1/directory2", "files/car.jpeg", "files/directory1", "files/kitten.jpg" };

	@Test
	void testWithFiles(){
		Du sumWithFiles1 = new Du(true, true, true, Arrays.asList(fileNames));
		assertEquals("Сумма всех файлов равна 4.915 MB", sumWithFiles1.util());

		Du sumWithFiles2 = new Du(true, true, false, Arrays.asList(fileNames));
		assertEquals("Сумма всех файлов равна 4.687 MB", sumWithFiles2.util());

		Du sumWithFiles3 = new Du(false, true, true, Arrays.asList(fileNames));
		assertEquals("Сумма всех файлов равна 4914.664 KB", sumWithFiles3.util());

		Du sumWithFiles4 = new Du(false, true, false, Arrays.asList(fileNames));
		assertEquals("Сумма всех файлов равна 4799.477 KB", sumWithFiles4.util());

		Du withFiles1 = new Du(true, false, true, Arrays.asList(fileNames));
		assertEquals("""
       
       Размер files/ИсторияРоссии.rtf равен 804.981 KB
       Размер files/kitten.jpg равен 46.962 KB
       Размер files/Козодой.jpg равен 92.007 KB
       Размер files/car.jpeg равен 3.971 MB
       """, withFiles1.util());

		Du withFiles2 = new Du(true, false, false, Arrays.asList(fileNames));
		assertEquals("""
       
		Размер files/ИсторияРоссии.rtf равен 786.114 KB
		Размер files/kitten.jpg равен 45.861 KB
		Размер files/Козодой.jpg равен 89.851 KB
		Размер files/car.jpeg равен 3.787 MB
       """, withFiles2.util());

		Du withFiles3 = new Du(false, false, true, Arrays.asList(fileNames));
		assertEquals("""
       
       Размер files/ИсторияРоссии.rtf равен 804.981 KB
       Размер files/kitten.jpg равен 46.962 KB
       Размер files/Козодой.jpg равен 92.007 KB
       Размер files/car.jpeg равен 3970.714 KB
       """, withFiles3.util());

		Du withFiles4 = new Du(false, false, false, Arrays.asList(fileNames));
		assertEquals("""
       
		Размер files/ИсторияРоссии.rtf равен 786.114 KB
		Размер files/kitten.jpg равен 45.861 KB
		Размер files/Козодой.jpg равен 89.851 KB
		Размер files/car.jpeg равен 3877.650 KB
       	""", withFiles4.util());
	}

	@Test
	void testWithDirectory() {
		Du sumWithDirectory1 = new Du(true, true, true, Arrays.asList(directoryNames));
		assertEquals("Сумма всех файлов равна 19.538 MB", sumWithDirectory1.util());

		Du sumWithDirectory2 = new Du(true, true, false, Arrays.asList(directoryNames));
		assertEquals("Сумма всех файлов равна 18.633 MB", sumWithDirectory2.util());

		Du sumWithDirectory3 = new Du(false, true, true, Arrays.asList(directoryNames));
		assertEquals("Сумма всех файлов равна 19538.131 KB", sumWithDirectory3.util());

		Du sumWithDirectory4 = new Du(false, true, false, Arrays.asList(directoryNames));
		assertEquals("Сумма всех файлов равна 19080.206 KB", sumWithDirectory4.util());

		Du withDirectory1 = new Du(true, false, true, Arrays.asList(directoryNames));
		assertEquals("""
       
		Размер files равен 9.842 MB
		Размер files/directory1 равен 4.921 MB
		Размер files/directory1/directory2 равен 4.776 MB
      	""", withDirectory1.util());

		Du withDirectory2 = new Du(true, false, false, Arrays.asList(directoryNames));
		assertEquals("""
       
		Размер files равен 9.386 MB
		Размер files/directory1 равен 4.693 MB
		Размер files/directory1/directory2 равен 4.554 MB
       	""", withDirectory2.util());

		Du withDirectory3 = new Du(false, false, true, Arrays.asList(directoryNames));
		assertEquals("""
       
		Размер files равен 9841.624 KB
		Размер files/directory1 равен 4920.812 KB
		Размер files/directory1/directory2 равен 4775.695 KB
       	""", withDirectory3.util());

		Du withDirectory4 = new Du(false, false, false, Arrays.asList(directoryNames));
		assertEquals("""
       
		Размер files равен 9610.961 KB
		Размер files/directory1 равен 4805.480 KB
		Размер files/directory1/directory2 равен 4663.765 KB
       	""", withDirectory4.util());
	}

	@Test
	void testFilesWithDirectories() {
		Du sumWithFilesAndDirectories1 = new Du(true, true, true, Arrays.asList(filesAndDirectories));
		assertEquals("Сумма всех файлов равна 14.519 MB", sumWithFilesAndDirectories1.util());

		Du sumWithFilesAndDirectories2 = new Du(true, true, false, Arrays.asList(filesAndDirectories));
		assertEquals("Сумма всех файлов равна 13.847 MB", sumWithFilesAndDirectories2.util());

		Du sumWithFilesAndDirectories3 = new Du(false, true, true, Arrays.asList(filesAndDirectories));
		assertEquals("Сумма всех файлов равна 14519.164 KB", sumWithFilesAndDirectories3.util());

		Du sumWithFilesAndDirectories4 = new Du(false, true, false, Arrays.asList(filesAndDirectories));
		assertEquals("Сумма всех файлов равна 14178.871 KB", sumWithFilesAndDirectories4.util());

		Du withFilesAndDirectories1 = new Du(true, false, true, Arrays.asList(filesAndDirectories));
		assertEquals("""
       
		Размер files/ИсторияРоссии.rtf равен 804.981 KB
		Размер files/directory1/directory2 равен 4.776 MB
		Размер files/car.jpeg равен 3.971 MB
		Размер files/directory1 равен 4.921 MB
		Размер files/kitten.jpg равен 46.962 KB
      	""", withFilesAndDirectories1.util());

		Du withFilesAndDirectories2 = new Du(true, false, false, Arrays.asList(filesAndDirectories));
		assertEquals("""
       
		Размер files/ИсторияРоссии.rtf равен 786.114 KB
		Размер files/directory1/directory2 равен 4.554 MB
		Размер files/car.jpeg равен 3.787 MB
		Размер files/directory1 равен 4.693 MB
		Размер files/kitten.jpg равен 45.861 KB
       	""", withFilesAndDirectories2.util());

		Du withFilesAndDirectories3 = new Du(false, false, true, Arrays.asList(filesAndDirectories));
		assertEquals("""
       
		Размер files/ИсторияРоссии.rtf равен 804.981 KB
		Размер files/directory1/directory2 равен 4775.695 KB
		Размер files/car.jpeg равен 3970.714 KB
		Размер files/directory1 равен 4920.812 KB
		Размер files/kitten.jpg равен 46.962 KB
       	""", withFilesAndDirectories3.util());

		Du withFilesAndDirectories4 = new Du(false, false, false, Arrays.asList(filesAndDirectories));
		assertEquals("""
       
		Размер files/ИсторияРоссии.rtf равен 786.114 KB
		Размер files/directory1/directory2 равен 4663.765 KB
		Размер files/car.jpeg равен 3877.650 KB
		Размер files/directory1 равен 4805.480 KB
		Размер files/kitten.jpg равен 45.861 KB
       	""", withFilesAndDirectories4.util());
	}
}