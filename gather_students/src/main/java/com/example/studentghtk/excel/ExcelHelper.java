package com.example.studentghtk.excel;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import com.example.studentghtk.score.FirstRoundScore;
import com.example.studentghtk.student.Student;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String SHEET = "data";
    public static boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }
    public static List<Student> excelToStudents(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<Student> students = new ArrayList<>();
            List<FirstRoundScore> scores = new ArrayList<>();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            int rowNumber = 0;
            String birthday = ""; // YYYY-MM-DD
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber < 5) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Student student = new Student();
                FirstRoundScore score = new FirstRoundScore();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
//                            score.setId((long) currentCell.getNumericCellValue());
//                            student.setId((long) currentCell.getNumericCellValue());
                            break;
                        case 1:
                            student.setSchool(currentCell.getStringCellValue());
                            break;
                        case 2:
                            student.setDistrict(currentCell.getStringCellValue());
                            break;
                        case 3:
                            student.setStudentCode(currentCell.getStringCellValue());
                            break;
                        case 4:
                            student.setStudentClass(currentCell.getStringCellValue());
                            break;
                        case 5:
                            student.setFullName(currentCell.getStringCellValue());
                            break;
                        case 6:
                        case 7:
                            birthday =  "-" + currentCell.getStringCellValue() + birthday;
                            break;
                        case 8:
                            birthday =  currentCell.getStringCellValue() + birthday;
                            student.setBirthday(formatter.parse(birthday));
                            break;
                        case 9:
                            student.setSex(currentCell.getStringCellValue());
                            break;
                        case 10:
                            student.setBirthplace(currentCell.getStringCellValue());
                            break;
                        case 11:
                            student.setEthnic(currentCell.getStringCellValue());
                            break;
                        case 12:
                            student.setAddress(currentCell.getStringCellValue());
                            break;
                        case 13:
                            student.setPhone(currentCell.getStringCellValue());
                            break;
                        case 14:
                            score.setFirstYear((int) currentCell.getNumericCellValue());
                            break;
                        case 15:
                            score.setSecondYear((int) currentCell.getNumericCellValue());
                            break;
                        case 16:
                            score.setThirdYear((int) currentCell.getNumericCellValue());
                            break;
                        case 17:
                            score.setFourthYear((int) currentCell.getNumericCellValue());
                            break;
                        case 18:
                            score.setFifthYear((int) currentCell.getNumericCellValue());
                            break;
                        case 20:
                            score.setPriorityScore((int) currentCell.getNumericCellValue());
                            break;
                        case 22:
                            student.setDescription(currentCell.getStringCellValue());
                            student.setScore(score);
                            score.setStudent(student);
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                students.add(student);
                scores.add(score);
            }
            workbook.close();
            return students;
        } catch (IOException | ParseException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}