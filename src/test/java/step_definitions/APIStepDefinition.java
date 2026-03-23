package step_definitions;

import api.client.PetStoreSwaggerAPI;
import api.client.ReqresAPI;
import api.pojo.*;
import api.utils.APIUtils;
import api.utils.ApiResponse;
import configurations.db.QueryExecutor;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import utils.GeneratorUtils;
import utils.ScenarioContext;

import java.util.List;
import java.util.Map;

public class APIStepDefinition {

    private ReqresAPI reqresApi;
    private PetStoreSwaggerAPI petStoreSwaggerAPI;
    private ApiResponse<ShipResponsePOJO> response;
    private ApiResponse<SingleUserResponsePOJO> singleUserResponse;
    private ApiResponse<LoginResponsePOJO> loginResponse;
    private ApiResponse<UpdateUserResponsePOJO> updateUserResponse;
    private ApiResponse<Void> deleteUserResponse;
    private ApiResponse<GetAllUsersDataPOJO> getAllUsersResponse;

    private List<Map<String, Object>> result;
    private Map<String, String> swaggerResult;

    @When("API request all users details")
    public void APIRequestAllUsersDetails() {
        reqresApi = new ReqresAPI();
        getAllUsersResponse = reqresApi.getUsersDetails(APIUtils.getPostmanHeaders());
        Assert.assertNotNull(getAllUsersResponse.getBody(), "Response body should not be null");
    }

    @When("API creates a random user")
    public void APICreatesARandomUser() {
        petStoreSwaggerAPI = new PetStoreSwaggerAPI();
        swaggerResult = petStoreSwaggerAPI.createNewUserWithDTO(APIUtils.perStoreSwaggerHeaders(),
                GeneratorUtils.generateUserName(), GeneratorUtils.generateFirstName(),
                GeneratorUtils.generateLastName(), GeneratorUtils.generateEmail(), GeneratorUtils.generatePassword(),
                GeneratorUtils.phoneNumber());

        ScenarioContext.save("statusCode", swaggerResult.get("statusCode"));
        ScenarioContext.save("type", swaggerResult.get("type"));

        Assert.assertNotNull(swaggerResult);
    }

    @When("API updates user details for user")
    public void APIUpdatesUserDetails() {
        String userNameFromDB = QueryExecutor.executeQueryAsTable(
                "get_user_data_by_user_name").getFirst().get("user_bame").toString();

        petStoreSwaggerAPI = new PetStoreSwaggerAPI();
        swaggerResult = petStoreSwaggerAPI.updateUserWithDTO(APIUtils.perStoreSwaggerHeaders(),
                userNameFromDB, GeneratorUtils.generateFirstName(), GeneratorUtils.generateLastName(),
                GeneratorUtils.generateEmail(), GeneratorUtils.generatePassword(), GeneratorUtils.phoneNumber());
        ScenarioContext.save("statusCode", swaggerResult.get("statusCode"));
        ScenarioContext.save("type", swaggerResult.get("type"));

        Assert.assertNotNull(swaggerResult);
    }

    @When("API deletes user with user name")
    public void APIDeletesUser() {
        String userNameFromDB = QueryExecutor.executeQueryAsTable(
                "get_user_data_by_user_name").getFirst().get("user_bame").toString();

        petStoreSwaggerAPI = new PetStoreSwaggerAPI();
        swaggerResult = petStoreSwaggerAPI.deleteUser(APIUtils.perStoreSwaggerHeaders(),
                userNameFromDB);
        ScenarioContext.save("statusCode", swaggerResult.get("statusCode"));

        Assert.assertNotNull(swaggerResult);
    }


    @Then("API response should be successful with status code {string} and type {string}")
    public void APIResponseShouldBeSuccessfulWithStatusCodeAndType(String expectedStatusCode, String expectedType) {
        Assert.assertEquals(ScenarioContext.get("statusCode", String.class), expectedStatusCode, "Expected status code does not match actual");
        Assert.assertEquals(ScenarioContext.get("type", String.class), expectedType, "Expected type does not match actual");
    }

    @Then("API response should be successful with status code {int}")
    public void APIResponseShouldBeSuccessfulWithStatusCode(int expectedStatusCode) {
        Assert.assertEquals(getAllUsersResponse.getStatusCode(), expectedStatusCode, "Expected status code does not match actual");
    }

    @Then("number of users in the response should be {int}")
    public void numberOfUsersInTheResponseShouldBe(int expectedUserCount) {
        Assert.assertEquals(getAllUsersResponse.getBody().getTotal(), expectedUserCount, "Expected user count does not match actual");
    }

    @When("API request user details with id {string}")
    public void APIRequestUserDetailsWithId(String userId) {
        ScenarioContext.save("userId", userId);
        reqresApi = new ReqresAPI();
        singleUserResponse = reqresApi.getSingleUserDetails(APIUtils.getPostmanHeaders(), userId);
        Assert.assertNotNull(singleUserResponse.getBody(), "Response body should not be null");
    }

    @And("API response user mail should be {string}")
    public void APIResponseUserMailShouldBe(String expectedEmail) {
        Assert.assertEquals(singleUserResponse.getBody().getData().getEmail(), expectedEmail, "Expected email does not match actual");
    }

    @And("API response user first name should be {string} and last name should be {string}")
    public void APIResponseUserFirstNameShouldBeAndLastNameShouldBe(String expectedFirstName, String expectedLastName) {
        result = QueryExecutor.executeQueryAsTable(
                "get_customer_data",
                ScenarioContext.get("userId", String.class));

        Assert.assertEquals(singleUserResponse.getBody().getData().getEmail(), result.getFirst().get("Email").toString(), "Expected first name does not match actual");
        Assert.assertEquals(singleUserResponse.getBody().getData().getFirstName(), expectedFirstName, "Expected first name does not match actual");
        Assert.assertEquals(singleUserResponse.getBody().getData().getLastName(), expectedLastName, "Expected last name does not match actual");
    }

    @When("API request user login with username {string} and password {string}")
    public void APIRequestUserLogin(String username, String password) {
        reqresApi = new ReqresAPI();
        loginResponse = reqresApi.userLogin(APIUtils.geUTF8PostmanHeaders(), username, password);
        Assert.assertNotNull(loginResponse.getBody(), "Response body should not be null");
    }

    @Then("API LOGIN response should be successful")
    public void APILOGINResponseShouldBeSuccessful() {
        Assert.assertEquals(loginResponse.getStatusCode(), 200, "Expected status code does not match actual");
        Assert.assertNotNull(loginResponse.getBody().getToken(), "Login token should not be null");
    }

    @When("API updated user details with name {string} and job {string} for user with id {string}")
    public void APIUpdatedUserDetailsWithIdAndNameAndJob(String name, String job, String id) {
        reqresApi = new ReqresAPI();
        updateUserResponse = reqresApi.updateUser(APIUtils.geUTF8PostmanHeaders(), name, job, id);
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
        reqresApi = new ReqresAPI();
        deleteUserResponse = reqresApi.deleteUser(APIUtils.getPostmanHeaders(), userId);
        Assert.assertNotNull(deleteUserResponse, "Delete user response should not be null");
    }

    @Then("API DELETE response should be successful with status code {int}")
    public void APIDELETEResponseShouldBeSuccessfulWithStatusCode(int expectedStatusCode) {
        Assert.assertEquals(deleteUserResponse.getStatusCode(), expectedStatusCode, "Expected status code does not match actual");
    }

    @When("API request user details with user name")
    public void APIRequestUserDetailsWithUserName() {
        petStoreSwaggerAPI = new PetStoreSwaggerAPI();

        List<Map<String, Object>> queryResults = QueryExecutor.executeQueryAsTable(
                "get_user_data_by_user_name");

        String userName = queryResults.getFirst().get("user_bame").toString();
        String email = queryResults.getFirst().get("email").toString();

        ScenarioContext.save("userNameFromDB", userName);
        ScenarioContext.save("emailFromDB", email);

        swaggerResult = petStoreSwaggerAPI.getUserDetails(APIUtils.perStoreSwaggerHeaders(), userName);
        ScenarioContext.save("statusCode", swaggerResult.get("statusCode"));
        ScenarioContext.save("userName", swaggerResult.get("userName"));
        ScenarioContext.save("email", swaggerResult.get("email"));
        ScenarioContext.save("password", swaggerResult.get("password"));
    }

    @Then("API get user details response should be successful with status code {string}")
    public void APIGetUserDetailsResponseShouldBeSuccessfulWithStatusCode(String expectedStatusCode) {

        Assert.assertEquals(ScenarioContext.get("statusCode", String.class), expectedStatusCode, "Expected status code does not match actual");
        Assert.assertEquals(ScenarioContext.get("userName", String.class), ScenarioContext.get("userNameFromDB", String.class), "Expected user name does not match actual");
        Assert.assertEquals(ScenarioContext.get("email", String.class), ScenarioContext.get("emailFromDB", String.class), "Expected email does not match actual");
    }

    @Then("API update user details response should be successful with status code {string}")
    public void APIUpdateUserDetailsResponseShouldBeSuccessfulWithStatusCode(String expectedStatusCode) {
        Assert.assertEquals(ScenarioContext.get("statusCode", String.class), expectedStatusCode, "Expected status code does not match actual");
    }

    @Then("API delete user response should be successful with status code {string}")
    public void APIDeleteUserResponseShouldBeSuccessfulWithStatusCode(String expectedStatusCode) {
        Assert.assertEquals(ScenarioContext.get("statusCode", String.class), expectedStatusCode, "Expected status code does not match actual");
    }
}