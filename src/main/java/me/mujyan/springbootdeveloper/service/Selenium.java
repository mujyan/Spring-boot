package me.mujyan.springbootdeveloper.service;


import me.mujyan.springbootdeveloper.dto.Lecturedto;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class Selenium {
    private LectureService lectureService;
    @Autowired
    public void lectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }
    public void crawling(String id, String pw) {
        Lecturedto announcements = new Lecturedto();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("https://door.deu.ac.kr/sso/login.aspx");
        try {
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
                    announcements = new Lecturedto();
                    WebElement lecture = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div[3]/div[3]/table/tbody/tr[" + i + "]/td[3]/a")));
                    // 선택한 행(row)에 대한 작업 수행

                    // 강의명 저장
                    announcements.setLecturename(lecture.getText());
                    // 분반 저장
                    announcements.setDivision(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div[3]/div[3]/table/tbody/tr[" + i + "]/td[4]"))).getText());
                    announcements.setProfessor(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div[3]/div[3]/table/tbody/tr[" + i + "]/td[5]"))).getText());
                    lecture.click();


                    // 공지사항 탭 클릭
                    WebElement announcement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='lnbContent']/div/div[5]/ul/li/ul/li[1]/a/span")));
                    announcement.click();

                    try { // 공지사항이 비어있으면 에러
                        // 공지사항 tbody 선택
                        for(int j=2; j<100; j++) {
                            announcements.setId(announcements.getId()+1);
                            announcements.setAnnouncement(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div[5]/form/div/div[2]/table/tbody/tr[" + j + "]/td[3]"))).getText());
                            // /html/body/div[2]/div[2]/div[5]/form/div/div[2]/table/tbody/tr[4]/td[3]
                            // /html/body/div[2]/div[2]/div[5]/form/div/div[2]/table/tbody/tr[5]/td[3]
                            announcements.setDate(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div[5]/form/div/div[2]/table/tbody/tr[" + j + "]/td[5]"))).getText());

                            //if(lectureService.Deduplication(announcements).isPresent()) {
                            lectureService.saveLecture(announcements);
                            //}
                        }
                    } catch(Exception e) {}
                    WebElement lectureLink2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@title, '강의실')]")));
                    lectureLink2.click();
                } catch (Exception e) {
                    // 요소를 찾지 못한 경우 예외 처리
                    break;
                }
            }
            driver.quit();
        } catch (Exception e) {
            driver.quit();
        }
    }
}

/* 사용 예시
private LectureService lectureService;
@Autowired
public void lectureController(LectureService lectureService) {
    this.lectureService = lectureService;
}
private Selenium sele;

@Autowired
public void SomeOtherClass(Selenium sele) {
    this.sele = sele;
}
@GetMapping("/testLecture")
public String test() {
    sele.crawling("20193063", "비밀번호");
    return "Done";
}

 */
