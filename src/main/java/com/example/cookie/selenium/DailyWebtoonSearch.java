package com.example.cookie.selenium;

import com.example.cookie.webtoon.domain.Webtoon;
import com.example.cookie.webtoon.service.WebtoonService;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Slf4j
@Configuration
public class DailyWebtoonSearch {

    private WebDriver driver;
    private WebElement element;
    private String url;

    // 드라이버 설치 경로
    public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static String WEB_DRIVER_PATH = "/Users/hannah/cookieMonster/chromedriver";

    @Autowired
    private WebtoonService service;

    public DailyWebtoonSearch() {
        // WebDriver 경로 설정
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        // WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");
        // 브라우저 창이 뜨지 않도록 해주는 설정.
        options.addArguments("--headless");

        // 아래의 두줄은 selenium실행시 뜨는 빨간색 코드를 안뜨게 해주는 코드.
        System.setProperty("webdriver.chrome.silentOutput", "true");
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

        driver = new ChromeDriver(options);
    }

    public void activeBot() {
        try {
            List<Webtoon> webtoons = service.selectWebtoonList();
            webtoons.stream().forEach(webtoon -> {
                Webtoon newWebToon = webtoon;
                url = newWebToon.getLink();

                driver.get(url);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 이미지 url
                try {
                    this.element = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[1]/a/img"));
                } catch (Exception e) {
                    this.element = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/a/img"));
                }
                String thumbnailImg = this.element.getAttribute("src");
                newWebToon.setThumbnail(thumbnailImg);

                // 작가
                this.element = driver.findElement(By.className("wrt_nm"));
                String writer = this.element.getAttribute("innerHTML").trim();
                newWebToon.setWriter(writer);

                // 장르
                this.element = driver.findElement(By.className("genre"));
                String genre = this.element.getAttribute("innerHTML");
                int i = genre.indexOf(",");
                if (i != 0) {
                    String[] split = genre.split(", ");
                    genre = split[1];
                }
                newWebToon.setGenre(genre);

                // 연령가(나이제한)
                this.element = driver.findElement(By.className("age"));
                String age = this.element.getAttribute("innerHTML");
                if ("전체연령가".equals(age)) {
                    newWebToon.setAge(0);
                } else {
                    String[] split = age.split("세");
                    newWebToon.setAge(Integer.parseInt(split[0]));
                }

                // 플랫폼
                newWebToon.setPlatform("naver");

                service.insertWebtoon(newWebToon);
            });

            /*// 요일별 웹툰목록 가져와서 웹툰정보 저장
            this.element = driver.findElement(By.xpath("//*[@id=\"content\"]/div[4]"));
            List<WebElement> ul = this.element.findElements(By.tagName("ul"));

            ul.stream().forEach(day -> {
                log.info("========== 요일 ==========");
                List<WebElement> li = day.findElements(By.tagName("li"));

                li.stream().forEach(webtoon -> {
                    WebElement webtoonElement = webtoon.findElement(By.className("title"));
                    String homeUrl = webtoonElement.getAttribute("href");
                    String title = webtoonElement.getAttribute("innerHTML");

                    Webtoon webtoon1 = new Webtoon();
                    webtoon1.setTitle(title);
                    webtoon1.setLink(homeUrl);

                    Webtoon webtoon2 = service.insertWebtoon(webtoon1);
                    log.info("====== 결과: " + webtoon2);
                });
            });*/
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            driver.close();
        }
    }
}
