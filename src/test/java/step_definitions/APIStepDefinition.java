package step_definitions;

import api.client.ReqresAPI;
import api.pojo.LoginResponsePOJO;
import api.pojo.ShipResponsePOJO;
import api.pojo.SingleUserResponsePOJO;
import api.pojo.UpdateUserResponsePOJO;
import api.utils.ApiResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class APIStepDefinition {

    private ReqresAPI api;
    private ApiResponse<ShipResponsePOJO> response;
    private ApiResponse<SingleUserResponsePOJO> singleUserResponse;
    private ApiResponse<LoginResponsePOJO> loginResponse;
    private ApiResponse<UpdateUserResponsePOJO> updateUserResponse;
    private ApiResponse<Void> deleteUserResponse;

    @When("API request all users details")
    public void APIRequestAllUsersDetails() {
        api = new ReqresAPI();
        response = api.getUsersDetails();
        Assert.assertNotNull(response.getBody(), "Response body should not be null");
    }

    @Then("API response should be successful with status code {int}")
    public void APIResponseShouldBeSuccessfulWithStatusCode(int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Expected status code does not match actual");
    }

    @Then("number of users in the response should be {int}")
    public void numberOfUsersInTheResponseShouldBe(int expectedUserCount) {
        Assert.assertEquals(response.getBody().getTotal(), expectedUserCount, "Expected user count does not match actual");
    }

    @When("API request user details with id {string}")
    public void APIRequestUserDetailsWithId(String userId) {
        api = new ReqresAPI();
        singleUserResponse = api.getSingleUserDetails(userId);
        Assert.assertNotNull(singleUserResponse.getBody(), "Response body should not be null");
    }

    @And("API response user mail should be {string}")
    public void APIResponseUserMailShouldBe(String expectedEmail) {
        Assert.assertEquals(singleUserResponse.getBody().getData().getEmail(), expectedEmail, "Expected email does not match actual");
    }

    @And("API response user first name should be {string} and last name should be {string}")
    public void APIResponseUserFirstNameShouldBeAndLastNameShouldBe(String expectedFirstName, String expectedLastName) {
        Assert.assertEquals(singleUserResponse.getBody().getData().getFirstName(), expectedFirstName, "Expected first name does not match actual");
        Assert.assertEquals(singleUserResponse.getBody().getData().getLastName(), expectedLastName, "Expected last name does not match actual");
    }

    @When("API request user login with username {string} and password {string}")
    public void APIRequestUserLogin(String username, String password) {
        api = new ReqresAPI();
        loginResponse = api.userLogin(username, password);
        Assert.assertNotNull(loginResponse.getBody(), "Response body should not be null");
    }

    @Then("API LOGIN response should be successful")
    public void APILOGINResponseShouldBeSuccessful() {
        Assert.assertEquals(loginResponse.getStatusCode(), 200, "Expected status code does not match actual");
        Assert.assertNotNull(loginResponse.getBody().getToken(), "Login token should not be null");
    }

    @When("API updated user details with name {string} and job {string} for user with id {string}")
    public void APIUpdatedUserDetailsWithIdAndNameAndJob(String name, String job, String id) {
        api = new ReqresAPI();
        updateUserResponse = api.updateUser(name, job, id);
        Assert.assertNotNull(updateUserResponse.getBody(), "Response body should not be null");
    }

    @Then("API UPDATE response should be successful with updated name {string} and job {string}")
    public void APIUPDATEResponseShouldBeSuccessfulWithUpdatedNameAndJob(String expectedName, String expectedJob) {
        Assert.assertEquals(updateUserResponse.getStatusCode(), 200, "Expected status code does not match actual");
        Assert.assertEquals(updateUserResponse.getBody().getName(), expectedName, "Expected name does not match actual");
        Assert.assertEquals(updateUserResponse.getBody().getJob(), expectedJob, "Expected job does not match actual");
    }

    @When("API deletes user details with id {string}")
    public void APIRDeletesUserWithId(String userId) {
        api = new ReqresAPI();
        deleteUserResponse = api.deleteUser(userId);
        Assert.assertNotNull(deleteUserResponse, "Delete user response should not be null");
    }

    @Then("API DELETE response should be successful with status code {int}")
    public void APIDELETEResponseShouldBeSuccessfulWithStatusCode(int expectedStatusCode) {
        Assert.assertEquals(deleteUserResponse.getStatusCode(), expectedStatusCode, "Expected status code does not match actual");
    }
}