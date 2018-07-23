
package co.com.accenture.Facebook.gradle.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.EmptyFileException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManager {

	private String ruta;
	private FileInputStream input;
	private FileOutputStream outPut;
	private File archivo;
	private Workbook workbook;

	public static void main(String[] args) {

		ExcelManager excel = new ExcelManager("C:\\Users\\danny.rojas\\Desktop\\Retos\\5-Gradle\\nombres.xls");

		// excel.cargarDatos();
		excel.prepareWrite();
		excel.write(1, 2, "X");
		excel.saveChanges();
		// excel.cargarDatos();

	}

	public ExcelManager(String ruta) {
		this.ruta = ruta;
		archivo = new File(ruta);
		crearEnlazeArchivo();

	}

	public void prepareWrite() {
		try {
			outPut = new FileOutputStream(archivo);
			System.out.println("Preparado");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error " + e.getMessage());
		}
	}

	public void write(int column, int row, String number) {

		Sheet sheet = workbook.getSheetAt(0);

		Row rowWriter = sheet.getRow(row);
		Cell cellWriter = rowWriter.createCell(column);

		cellWriter.setCellValue(number);

	}

	public void saveChanges() {

		try {
			workbook.write(outPut);

			// Cerramos el libro para concluir operaciones
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void crearEnlazeArchivo() {

		try {
			input = new FileInputStream(new File(ruta));
			// outPut = new FileOutputStream(new File(ruta));
			workbook = WorkbookFactory.create(input); // crear un libro excel

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al cargar el archivo" + e.getMessage());
		}

	}

	public ArrayList<Dato> cargarDatos() {

		crearEnlazeArchivo();

		ArrayList<Dato> datos_cargados = new ArrayList<>();

		Sheet firstSheet = workbook.getSheetAt(0);

		Iterator<Row> filas = firstSheet.iterator();

		// Me deshago de las titulo
		filas.next();

		int contadorColumnas = 0;
		int row = 0;

		while (filas.hasNext()) {

			Iterator<Cell> columna = filas.next().iterator();

			contadorColumnas = 0;

			Dato dato = new Dato();

			while (columna.hasNext()) {

				Cell celda = columna.next();

				switch (contadorColumnas) {
				case 0:
					System.out.println(celda.getStringCellValue());

					// System.out.println("F" + row + " c" + contadorColumnas);
					dato.setNombre(celda.getStringCellValue());
					break;

				}

				contadorColumnas++;

			}

			row++;
			datos_cargados.add(dato.isEmpty() == true ? null : dato);

			System.out.println("");
		}

		return datos_cargados.isEmpty() ? null : datos_cargados;
	}

}
