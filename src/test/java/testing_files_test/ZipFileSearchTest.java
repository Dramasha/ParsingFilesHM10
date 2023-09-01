package testing_files_test;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;

public class ZipFileSearchTest {
    private static ClassLoader classLoader = ZipFileSearchTest.class.getClassLoader();

    private static String zipFile = "TestZipParsing.zip";
    private static String pdfFile = "testPdfFile.pdf";
    private static String filesContentPDF = "Я Никита, мой размер S";
    private static String filesContentXLSX = "Понедельник";
    private static String filesContentCSV = "{";
    private static String xlsxFile = "TestXLSX.xlsx";
    private static String csvFile = "test.csv.csv";

    private InputStream getInputStreamFileInZipFile(String fileName) throws IOException {
        ZipInputStream is = new ZipInputStream(classLoader.getResourceAsStream(zipFile));
        ZipEntry entry;
        while ((entry = is.getNextEntry()) != null) {
            if (entry.getName().equals(fileName)){
                return is;
            }
        }
        throw new IOException("Файл " + fileName + " файл не найден в zip-архиве");
    }

    @Test
    @DisplayName("поиск значения в PDF файле в ZIP-архиве")
    void searchTextPdfInZipFileTest() throws IOException {
        try (InputStream inputStream = getInputStreamFileInZipFile(pdfFile)) {
            PDF pdf = new PDF(inputStream);
            assertThat(pdf).containsExactText(filesContentPDF);
        }
    }

    @Test
    @DisplayName("поиск значения в XLSX файле в ZIP-архиве")
    void searchTextXlsxInZipFileTest () throws IOException {
        try (InputStream inputStream = getInputStreamFileInZipFile(xlsxFile)) {
            XLS xlsx = new XLS(inputStream);
            String cellValueXlsx = xlsx.excel.getSheetAt(0).getRow(0)
                    .getCell(0).getStringCellValue();
            assertThat(cellValueXlsx).contains(filesContentXLSX);
        }
    }

    @Test
    @DisplayName("поиск значения в CSV файле в ZIP-архиве")
    void searchTextCsvInZipFileTest() throws Exception {
        try (InputStream inputStream = getInputStreamFileInZipFile(csvFile);
            CSVReader reader = new CSVReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String valueCsv = reader.readNext()[0];
            assertThat(valueCsv).contains(filesContentCSV);
        }
    }
}