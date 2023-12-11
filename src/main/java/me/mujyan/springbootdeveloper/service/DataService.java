package me.mujyan.springbootdeveloper.service;

import com.opencsv.exceptions.CsvValidationException;
import me.mujyan.springbootdeveloper.dto.Exceldto;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {

    private List<Exceldto> dataList = new ArrayList<>();


    private String csvFilePath = "C:\\Users\\USER\\Desktop\\Spring-boot\\src\\main\\resources\\csv\\";

    private String timeFilter = "=\"0101";
    private String buildingFilter = "정보공학관";
    public DataService() {
        return;
    }
    public DataService(String timeinfo, String buildinginfo) {
        timeFilter = timeinfo;
        buildingFilter = buildinginfo;
        csvFilePath+=buildinginfo+".csv";
        initData();
    }

    private void initData() {
        try {
            FileReader fileReader = new FileReader(csvFilePath);
            CSVReader csvReader = new CSVReaderBuilder(fileReader).build();

            String[] header = csvReader.readNext(); // 헤더 행 읽기

            String[] line;
            while ((line = csvReader.readNext()) != null) {
                String buildingName = line[0];
                String time = line[1];
                String classroomNumber = line[2];
                String subjectName = line[3];
                String instructor = line[4];
                if (time.equals(timeFilter) && buildingName.equals(buildingFilter)) {
                    Exceldto data = new Exceldto(buildingName, time, classroomNumber, subjectName, instructor);
                    dataList.add(data);
                }
            }

            csvReader.close();
            fileReader.close();
        } catch (IOException | CsvValidationException e) { // CsvValidationException 추가
            e.printStackTrace();
        }
    }

    public List<Exceldto> getAllData() {
        return dataList;
    }
}