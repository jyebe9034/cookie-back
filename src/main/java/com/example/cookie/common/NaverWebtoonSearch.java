package com.example.cookie.common;

import com.example.cookie.webtoon.domain.Webtoon;
import com.example.cookie.webtoon.service.WebtoonService;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Slf4j
@Configuration
public class NaverWebtoonSearch {

//    private WebDriver driver;
//    private WebElement element;
//    private String url;
//
//    // TODO 서버 경로로 변경 필요 - 드라이버 설치 경로
//    public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
//    public static String WEB_DRIVER_PATH = "/Users/hannah/cookieMonster/chromedriver";
//
//    private WebtoonService service;
//
//    public NaverWebtoonSearch(WebtoonService service) {
//        this.service = service;
//    }
//
//    public NaverWebtoonSearch() {
//        // WebDriver 경로 설정
//        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
//
//        // WebDriver 옵션 설정
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--start-maximized");
//        options.addArguments("--disable-popup-blocking");
//        // 브라우저 창이 뜨지 않도록 해주는 설정.
//        options.addArguments("--headless");
//
//        // 아래의 두줄은 selenium실행시 뜨는 빨간색 코드를 안뜨게 해주는 코드.
//        System.setProperty("webdriver.chrome.silentOutput", "true");
//        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
//
//        driver = new ChromeDriver(options);
//    }
//
//    public List<Webtoon> activeBot(String keyword) {
//        url = "https://comic.naver.com/search?keyword=" + keyword;
//        try {
//            driver.get(url);
//            Thread.sleep(2000);
//
//            // 검색결과의 웹툰이 몇개인지 조회
//            this.element = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/h4/span"));
//            String resultCount = this.element.getAttribute("innerHTML");
//            String[] splitArr = resultCount.split("/ ");
//            String totalStr = splitArr[1].split("건")[0];
//            int total = Integer.parseInt(totalStr);
//            log.info("====== 검색건수: " + total);
//
//            List<Webtoon> webtoons = new ArrayList<>();
//
//            if (total > 1) { // 검색결과가 1개 이상인 경우
//                for (int i = 1; i < 6; i++) {
//                    Webtoon webtoon = new Webtoon();
//                    this.element = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/ul/li[" + i + "]/h5/a"));
//                    String titleStr = this.element.getAttribute("innerHTML");
//                    webtoon.setTitle(titleStr);
//                    webtoons.add(webtoon);
//                }
//
//                return webtoons;
//            } else { // 검색결과가 1개인 경우
//                Webtoon newWebToon = new Webtoon();
//
//                this.element = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/ul/li/h5/a"));
//                String href = this.element.getAttribute("href");
//                newWebToon.setLink(href);
//
//                this.element = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/ul/li[1]/h5/a"));
//                this.element.click();
//
//                // 이미지 url
//                try {
//                    this.element = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[1]/a/img"));
//                } catch (Exception e) {
//                    this.element = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/a/img"));
//                }
//                String thumbnailImg = this.element.getAttribute("src");
//                newWebToon.setThumbnail(thumbnailImg);
//
//                // 제목
//                this.element = driver.findElement(By.className("title"));
//                String title = this.element.getAttribute("innerHTML");
//                newWebToon.setTitle(title);
//
//                // 작가
//                this.element = driver.findElement(By.className("wrt_nm"));
//                String writer = this.element.getAttribute("innerHTML").trim();
//                newWebToon.setWriter(writer);
//
//                // 장르
//                this.element = driver.findElement(By.className("genre"));
//                String genre = this.element.getAttribute("innerHTML");
//                int i = genre.indexOf(",");
//                if (i != 0) {
//                    String[] split = genre.split(", ");
//                    genre = split[1];
//                }
//                newWebToon.setGenre(genre);
//
//                // 연령가(나이제한)
//                this.element = driver.findElement(By.className("age"));
//                String age = this.element.getAttribute("innerHTML");
//                if ("전체연령가".equals(age)) {
//                    newWebToon.setAge(0);
//                } else {
//                    String[] split = age.split("세");
//                    newWebToon.setAge(Integer.parseInt(split[0]));
//                }
//
//                // 플랫폼
//                newWebToon.setPlatform("naver");
//
//                service.insertWebtoon(newWebToon);
//                webtoons.add(newWebToon);
//
//                return webtoons;
//            }
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        } finally {
//            driver.close();
//        }
//        return null;
//    }
}
