
package com.mycompany.kyc_assignment;

/**
 *
 * @author Gautam
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.time.Duration;
import java.util.*;


public class KYC_Assignment {

     public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://akleg.gov/senate.php");

        WebElement listBlock = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("#tab1-2 ul:nth-child(1) > li:nth-child(1) > ul:nth-child(1)")
        ));

        List<Map<String, String>> senatorsBrief = new ArrayList<>();

        for (WebElement li : listBlock.findElements(By.tagName("li"))) {
            try {
                String name = li.findElement(By.cssSelector("strong.name")).getText();
                String profileUrl = li.findElement(By.cssSelector("a[href*='Member/Detail']")).getAttribute("href");

                List<WebElement> details = li.findElements(By.cssSelector("dl dd"));
                String address = safe(details, 0);
                String party = safe(details, 1);
                String district = safe(details, 2);
                String phone = safe(details, 3);

                if (!district.equals("N/A"))
                    district = "District " + district;

                String imgUrl = li.findElement(By.tagName("img")).getAttribute("src");
                if (imgUrl.startsWith("//")) imgUrl = "https:" + imgUrl;

                Map<String, String> s = new HashMap<>();
                s.put("name", name);
                s.put("profileUrl", profileUrl);
                s.put("address", address);
                s.put("party", party);
                s.put("district", district);
                s.put("phone", phone);
                s.put("imgUrl", imgUrl);

                senatorsBrief.add(s);

            } catch (Exception ignored) {}
        }

        List<Map<String, String>> finalOutput = new ArrayList<>();

        for (Map<String, String> s : senatorsBrief) {
            try {
                driver.navigate().to(s.get("profileUrl"));
                wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

                String email = "N/A";
                try { email = driver.findElement(By.xpath("//strong[text()='Email:']/following-sibling::a")).getText().trim(); }
                catch (Exception ignored) {}

                String sessionAddress = "N/A";
                try {
                    WebElement block = driver.findElement(By.xpath("//strong[text()='Session Contact']/parent::div"));
                    sessionAddress = block.getText().replace("Session Contact", "").trim();
                } catch (Exception ignored) {}

                Map<String, String> json = new LinkedHashMap<>();
                json.put("name", s.get("name"));
                json.put("title", "Senator");
                json.put("party", s.get("party"));
                json.put("profile", "");
                json.put("dob", "");
                json.put("type", s.get("district"));
                json.put("country", "USA");
                json.put("url", s.get("profileUrl"));

                String others = "Email: " + email +
                        ", Phone: " + s.get("phone") +
                        ", Address: " + (sessionAddress.equals("N/A") ? s.get("address") : sessionAddress) +
                        ", Image: " + s.get("imgUrl");

                json.put("otherinfo", others);

                finalOutput.add(json);

            } catch (Exception ignored) {}
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter file = new FileWriter("output.json")) {
            gson.toJson(finalOutput, file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("✅ Done. Saved → output.json");
        driver.quit();
    }

    static String safe(List<WebElement> list, int i) {
        return (list.size() > i) ? list.get(i).getText() : "N/A";
    }
}
