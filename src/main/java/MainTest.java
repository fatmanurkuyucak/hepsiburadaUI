import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;

public class MainTest {

    @Test
    public void compareSearchResults()
    {
        WebDriver driver = Configuration.getDriver(Constants.SEARCH_ENGINE_1_URL);
        driver.manage().deleteAllCookies();

        List<SearchResult> googleSearchResult = getFirstSearchEngineResult(driver);
        System.out.println("googleSearchResult : " + googleSearchResult);

        driver = Configuration.getDriver(Constants.SEARCH_ENGINE_2_URL);
        driver.manage().deleteAllCookies();

        List<SearchResult> yahooSearchResult = getSecondSearchEngineResult(driver);
        System.out.println("yahooSearchResult : " + yahooSearchResult);

        listSameResult(googleSearchResult, yahooSearchResult);
    }

    private void listSameResult(List<SearchResult> googleSearchResult, List<SearchResult> yahooSearchResult)
    {
        for(SearchResult googleResult : googleSearchResult)
        {
            for(SearchResult yahooResult : yahooSearchResult)
            {
                if(googleResult.getHeader().equalsIgnoreCase(yahooResult.getHeader())
                        && googleResult.getLink().equalsIgnoreCase(yahooResult.getLink()))
                {
                    System.out.println("same result : " + yahooResult);
                }
            }
        }
    }

    private List<SearchResult> getFirstSearchEngineResult(WebDriver driver)
    {
        WebElement searchText = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input"));
        searchText.sendKeys("gram altın");

        WebElement searchBtn = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[2]/div[2]/div[5]/center/input[1]"));
        searchBtn.submit();

        List<SearchResult> googleSearchResult = new ArrayList<>();

        SearchResult searchResult;
        List<WebElement> results = driver.findElements(By.xpath("//*[@id=\"rso\"]/div"));
        for(WebElement result : results)
        {
            WebElement header = result.findElement(By.xpath("//div/div/div[1]/div/a/h3"));
            WebElement link = result.findElement(By.xpath("//div/div/div[1]/div/a/div/cite"));
            searchResult = new SearchResult(header.getText(), link.getText());
            googleSearchResult.add(searchResult);
            System.out.println("added new result to google search results");
        }

        return googleSearchResult;
    }

    private List<SearchResult> getSecondSearchEngineResult(WebDriver driver)
    {
        WebElement searchText2 = driver.findElement(By.xpath("//*[@id=\"ybar-sbq\"]"));
        searchText2.sendKeys("gram altın");

        WebElement searchBtn2 = driver.findElement(By.xpath("//*[@id=\"ybar-search\"]"));
        searchBtn2.submit();

        List<SearchResult> yahooSearchResult = new ArrayList<>();

        SearchResult searchResult;
        List<WebElement> results = driver.findElements(By.xpath("//*[@id=\"web\"]/ol/li"));
        for(WebElement result : results)
        {
            String[] siteData = result.findElement(By.xpath("//div/div[1]/h3/a")).getText().split(Constants.NEW_LINE_SEPERATOR);
            searchResult = new SearchResult(siteData[1], siteData[0]);
            yahooSearchResult.add(searchResult);
            System.out.println("added new result to yahoo search results");
        }

        return yahooSearchResult;
    }
}
