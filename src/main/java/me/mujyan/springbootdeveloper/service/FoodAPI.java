package me.mujyan.springbootdeveloper.service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
-------------------------------
| 건물       |gubun1|gubun2|
-------------------------------
| 수덕전식당(0)  |  1   |   1   |
-------------------------------
| 정보공학관(1)  |  1   |   2   |
-------------------------------
| 교직원식당(2)  |  3   | Null  |
-------------------------------
return  0: 파일 존재
return  1: 파일 생성 성공
return -1: 파일 생성 실패

-------------------------------
MAVEN 의존성 설치
<dependencies>
    <dependency>
        <groupId>org.json</groupId>
        <artifactId>json</artifactId>
        <version>20210307</version>
    </dependency>
</dependencies>
-------------------------------
Main 함수 참고용
FoodAPI foodAPI = new FoodAPI();
for (int i = 0; i < 3; i++) foodAPI.getFood(i);
for (int i = 0; i < 3; i++) System.out.println(foodAPI.returnFood(i));
-------------------------------
*/

public class FoodAPI {
    public static int getFood(int Number) {
        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String[] buildingQuery = {"&gubun1=1&gubun2=1", "&gubun1=1&gubun2=2", "&gubun1=3&gubun2="};
        String[] buildingName = {"-suduk", "-info", "-emply"};
        String apiUrl = "https://smart.deu.ac.kr/m/sel_dfood?date=" + formattedDate + buildingQuery[Number];
        formattedDate = formattedDate + buildingName[Number];
        File fileCheck = new File(formattedDate + ".json");
        if (fileCheck.exists()){
//            System.out.println("파일이 존재합니다.");
            return 0;
        }
        else {
//            System.out.println("파일을 생성합니다.");
            try {
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    byte[] responseData = inputStream.readAllBytes();
                    inputStream.close();
                    String filePath = formattedDate + ".json";
                    Files.write(Paths.get(filePath), responseData);
                } else {
                    System.out.println("HTTP 요청 실패: " + responseCode);
                }
                connection.disconnect();
            } catch (IOException e) {
                System.out.println("Json 데이터 저장 실패");
                e.printStackTrace();
                return -1;
            }
            try {
                String filePath = formattedDate + ".json";
                String jsonData = new String(Files.readAllBytes(Paths.get(filePath)));
                JSONObject jsonObject = new JSONObject(jsonData);
                JSONObject newJsonObject = new JSONObject();
                for (String key : jsonObject.keySet()) {
                    JSONArray jsonArray = jsonObject.getJSONArray(key);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        newJsonObject.put(obj.getString("kioskName"), obj.getString("menuName"));
                    }
                }
                try (FileWriter file = new FileWriter(formattedDate + ".json")) {
                    file.write(newJsonObject.toString(4));
                }
            } catch (Exception e) {
//                System.out.println("Json 데이터 정리 실패");
                e.printStackTrace();
                return -1;
            }
            return 1;
        }
    }

    public static JSONObject returnFood(int Number) {
        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String[] buildingQuery = {"&gubun1=1&gubun2=1", "&gubun1=1&gubun2=2", "&gubun1=3&gubun2="};
        String[] buildingName = {"-suduk", "-info", "-emply"};
        formattedDate = formattedDate + buildingName[Number] + ".json";
//        System.out.println(formattedDate);
        try {
            String jsonData = new String(Files.readAllBytes(Paths.get(formattedDate)));
            return new JSONObject(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}