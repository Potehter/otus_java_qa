package cases;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.*;
import utils.BaseHooks;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GithubPageObjectTest extends BaseHooks {

    @Test
    public void githubPageObjectTest() {
        login();
        search();
        openIssues();
        findIssue();
        getIssueAndCheck();
    }


    private void getIssueAndCheck() {
        IssuePage issuePage = new IssuePage(driver);
        String title = issuePage.getIssueTitle();

        assertThat(title, is("Status: Open"));
    }

    private void findIssue() {
        ListOfIssuesPage listOfIssuesPage = new ListOfIssuesPage(driver);
        listOfIssuesPage.clickIssuesByNumber(0);
    }


    private void openIssues() {
        RepositoryTopBarElement repositoryTopBarElement = new RepositoryTopBarElement(driver);
        repositoryTopBarElement.openIssues();
    }


    private void search() {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.clickFirstRepository();
    }


    public void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open()
                .search("Selenide");
    }

}