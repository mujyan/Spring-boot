package me.mujyan.springbootdeveloper.service;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class Selenium {
    public void selenium(String id, String pw) {
        String[] announcements = new String[1000];

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("https://door.deu.ac.kr/sso/login.aspx");

        //로그인 과정
        WebElement logIdInput = driver.findElement(By.id("logId"));
        WebElement logPwInput = driver.findElement(By.id("logPw"));
        logIdInput.sendKeys(id);
        logPwInput.sendKeys(pw);
        WebElement loginLink = driver.findElement(By.id("btn_Login"));
        loginLink.click();


        // WebDriverWait 초기화
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 최대 10초간 대기

        // title이 "강의실"인 <a> 태그를 찾아 클릭
        WebElement lectureLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@title, '강의실')]")));
        lectureLink.click();


        for (int i = 2; i <= 100; i++) {
            try {
                WebElement lecture = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div[3]/div[3]/table/tbody/tr[" + i + "]/td[3]/a")));
                // 선택한 행(row)에 대한 작업 수행
                lecture.click();
                System.out.println("강의 선택 성공");

                // 공지사항 탭 클릭
                WebElement announcement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='lnbContent']/div/div[5]/ul/li/ul/li[1]/a/span")));
                announcement.click();
                System.out.println("공지사항 클릭 성공");

                // 공지사항 tbody 선택
                WebElement tbodyElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='sub_content2']/div[2]/table/tbody")));

                String allText = tbodyElement.getText();
                System.out.println(allText);
                announcements[i - 2] = allText;

                WebElement lectureLink2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@title, '강의실')]")));
                lectureLink2.click();

            } catch (Exception e) {
                // 요소를 찾지 못한 경우 예외 처리
                i = 101;
            }
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(i + announcements[i]);
        }
        driver.quit();
    }
}