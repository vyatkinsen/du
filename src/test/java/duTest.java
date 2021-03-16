import java.io.IOException;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class duTest {

	@Test
	void du() throws IOException {
		String[] fileName = {"files/ИсторияРоссии.rtf", "files/kitten.jpg", "files/Козодой.jpg"};
		String[] fileName2 = {"files/car.jpeg", "files/kitten.jpg"};

		duL kw = new duL(true, false, true, Arrays.asList(fileName));
		assertEquals("""
    
				Размер файла ИсторияРоссии.rtf равен 804.981 KB
				Размер файла kitten.jpg равен 46.962 KB
				Размер файла Козодой.jpg равен 92.007 KB""", kw.util());

		duL kw2 = new duL(false, true, false, Arrays.asList(fileName));
		assertEquals("Сумма всех файлов равна 921.826 KB", kw2.util());

		duL kw3 = new duL(false, false, false, Arrays.asList(fileName));
		assertEquals("""
    
				Размер файла ИсторияРоссии.rtf равен 786.114 KB
				Размер файла kitten.jpg равен 45.861 KB
				Размер файла Козодой.jpg равен 89.851 KB""", kw3.util());

		duL kw4 = new duL(true, false, false, Arrays.asList(fileName2));
		assertEquals("""
   
			Размер файла car.jpeg равен 3.787 MB
			Размер файла kitten.jpg равен 45.861 KB""", kw4.util());

		duL kw5 = new duL(true, true, false, Arrays.asList(fileName2));
		assertEquals("Сумма всех файлов равна 3.832 MB", kw5.util());

		duL kw6 = new duL(true, false, true, Arrays.asList(fileName2));
		assertEquals("""
				
				Размер файла car.jpeg равен 3.971 MB
				Размер файла kitten.jpg равен 46.962 KB""", kw6.util());


		duL kw7 = new duL(true, true, true, Arrays.asList(fileName2));
		assertEquals("Сумма всех файлов равна 4.018 MB", kw7.util());
	}
}