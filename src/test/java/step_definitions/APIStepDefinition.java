package step_definitions;

import api.client.PetStoreSwaggerAPI;
import api.client.ReqresAPI;
import api.pojo.*;
import api.utils.APIUtils;
import api.utils.ApiResponse;
import com.google.inject.Inject;
import com.google.inject.Provider;
import configurations.db.QueryExecutor;
import context.ScenarioState;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import utils.GeneratorUtils;
import utils.ScenarioContext;

import java.util.List;
import java.util.Map;

public class APIStepDefinition {

    @Inject
    private Provider<ReqresAPI> reqresAPIProvider;
    @Inject
    private Provider<PetStoreSwaggerAPI> petStoreSwaggerAPIProvider;


    @When("API request all users details")
    public void APIRequestAllUsersDetails() {
        ReqresAPI reqresApi = reqresAPIProvider.get();
        ApiResponse<GetAllUsersDataPOJO> response =
                reqresApi.getUsersDetails(APIUtils.getPostmanHeaders());

        ScenarioState.save("getAllUserDetails", response);
        Assert.assertNotNull(response.getBody(), "Response body should not be null");
    }

    @When("API creates a random user")
    public void APICreatesARandomUser() {
        PetStoreSwaggerAPI petStoreSwaggerAPI = petStoreSwaggerAPIProvider.get();
        Map<String, String> swaggerResult = petStoreSwaggerAPI.swaggerAPICreateNewUserWithDTO(APIUtils.perStoreSwaggerHeaders(),
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

        PetStoreSwaggerAPI petStoreSwaggerAPI = petStoreSwaggerAPIProvider.get();

        Map<String, String> swaggerResult = petStoreSwaggerAPI.swaggerAPIUpdateUserWithDTO(APIUtils.perStoreSwaggerHeaders(),
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

        PetStoreSwaggerAPI petStoreSwaggerAPI = petStoreSwaggerAPIProvider.get();
        Map<String, String> swaggerResult = petStoreSwaggerAPI.swaggerAPIDeleteUser(APIUtils.perStoreSwaggerHeaders(),
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
        ApiResponse<GetAllUsersDataPOJO> response =
                ScenarioState.get("getAllUserDetails", ApiResponse.class);

        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Expected status code does not match actual");
    }

    @Then("number of users in the response should be {int}")
    public void numberOfUsersInTheResponseShouldBe(int expectedUserCount) {
        ApiResponse<GetAllUsersDataPOJO> response =
                ScenarioState.get("getAllUserDetails", ApiResponse.class);
        Assert.assertEquals(response.getBody().getTotal(), expectedUserCount, "Expected user count does not match actual");
    }

    @When("API request user details with id {string}")
    public void APIRequestUserDetailsWithId(String userId) {
        ScenarioContext.save("userId", userId);
        ReqresAPI reqresApi = reqresAPIProvider.get();
        ApiResponse<SingleUserResponsePOJO> response = reqresApi.getSingleUserDetails(APIUtils.getPostmanHeaders(), userId);

        ScenarioState.save("singleUserResponse", response);

        Assert.assertNotNull(response.getBody(), "Response body should not be null");
    }

    @And("API response user mail should be {string}")
    public void APIResponseUserMailShouldBe(String expectedEmail) {
        ApiResponse<SingleUserResponsePOJO> response =
                ScenarioState.get("singleUserResponse", ApiResponse.class);

        Assert.assertEquals(response.getBody().getData().getEmail(), expectedEmail, "Expected email does not match actual");
    }

    @And("API response user first name should be {string} and last name should be {string}")
    public void APIResponseUserFirstNameShouldBeAndLastNameShouldBe(String expectedFirstName, String expectedLastName) {
        List<Map<String, Object>> result = QueryExecutor.executeQueryAsTable(
                "get_customer_data",
                ScenarioContext.get("userId", String.class));

        ApiResponse<SingleUserResponsePOJO> response =
                ScenarioState.get("singleUserResponse", ApiResponse.class);

        Assert.assertEquals(response.getBody().getData().getEmail(), result.getFirst().get("Email").toString(), "Expected first name does not match actual");
        Assert.assertEquals(response.getBody().getData().getFirstName(), expectedFirstName, "Expected first name does not match actual");
        Assert.assertEquals(response.getBody().getData().getLastName(), expectedLastName, "Expected last name does not match actual");
    }

    @When("API request user login with username {string} and password {string}")
    public void APIRequestUserLogin(String username, String password) {
        ReqresAPI reqresApi = reqresAPIProvider.get();
        ApiResponse<LoginResponsePOJO> request = reqresApi.userLogin(APIUtils.geUTF8PostmanHeaders(), username, password);

        ScenarioState.save("loginResponse", request);
        Assert.assertNotNull(request.getBody(), "Response body should not be null");
    }

    @Then("API LOGIN response should be successful")
    public void APILOGINResponseShouldBeSuccessful() {
        ApiResponse<LoginResponsePOJO> loginResponse =
                ScenarioState.get("loginResponse", ApiResponse.class);

        Assert.assertEquals(loginResponse.getStatusCode(), 200, "Expected status code does not match actual");
        Assert.assertNotNull(loginResponse.getBody().getToken(), "Login token should not be null");
    }

    @When("API updated user details with name {string} and job {string} for user with id {string}")
    public void APIUpdatedUserDetailsWithIdAndNameAndJob(String name, String job, String id) {
        ReqresAPI reqresApi = reqresAPIProvider.get();


        ApiResponse<UpdateUserResponsePOJO> request = reqresApi.updateUser(APIUtils.geUTF8PostmanHeaders(), name, job, id);

        ScenarioState.save("updateUserResponse", request);

        Assert.assertNotNull(request.getBody(), "Response body should not be null");
    }

    @Then("API UPDATE response should be successful with updated name {string} and job {string}")
    public void APIUPDATEResponseShouldBeSuccessfulWithUpdatedNameAndJob(String expectedName, String expectedJob) {

        ApiResponse<UpdateUserResponsePOJO> updateUserResponse =
                ScenarioState.get("updateUserResponse", ApiResponse.class);

        Assert.assertEquals(updateUserResponse.getStatusCode(), 200, "Expected status code does not match actual");
        Assert.assertEquals(updateUserResponse.getBody().getName(), expectedName, "Expected name does not match actual");
        Assert.assertEquals(updateUserResponse.getBody().getJob(), expectedJob, "Expected job does not match actual");
    }

    @When("API deletes user details with id {string}")
    public void APIRDeletesUserWithId(String userId) {
        ReqresAPI reqresApi = reqresAPIProvider.get();
        ApiResponse<Void> request = reqresApi.deleteUser(APIUtils.getPostmanHeaders(), userId);

        ScenarioState.save("deleteUserResponse", request);

        Assert.assertNotNull(request, "Delete user response should not be null");
    }

    @Then("API DELETE response should be successful with status code {int}")
    public void APIDELETEResponseShouldBeSuccessfulWithStatusCode(int expectedStatusCode) {
        ApiResponse<Void> deleteUserResponse = ScenarioState.get("deleteUserResponse", ApiResponse.class);
        Assert.assertEquals(deleteUserResponse.getStatusCode(), expectedStatusCode, "Expected status code does not match actual");
    }

    @When("API request user details with user name")
    public void APIRequestUserDetailsWithUserName() {
        PetStoreSwaggerAPI petStoreSwaggerAPI = petStoreSwaggerAPIProvider.get();

        List<Map<String, Object>> queryResults = QueryExecutor.executeQueryAsTable(
                "get_user_data_by_user_name");

        String userName = queryResults.getFirst().get("user_bame").toString();
        String email = queryResults.getFirst().get("email").toString();

        ScenarioContext.save("userNameFromDB", userName);
        ScenarioContext.save("emailFromDB", email);

        Map<String, String> swaggerResult = petStoreSwaggerAPI.swaggerAPIGetUserDetails(APIUtils.perStoreSwaggerHeaders(), userName);
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

    @When("API creates a list of random users")
    public void APICreatesAListOfRandomUsers() {
        PetStoreSwaggerAPI petStoreSwaggerAPI = petStoreSwaggerAPIProvider.get();
        Map<String, String> swaggerResult = petStoreSwaggerAPI.swaggerAPICreateNewUserListWithDTO(APIUtils.perStoreSwaggerHeaders(),
                GeneratorUtils.generateUserName(), GeneratorUtils.generateFirstName(),
                GeneratorUtils.generateLastName(), GeneratorUtils.generateEmail(), GeneratorUtils.generatePassword(),
                GeneratorUtils.phoneNumber());

        ScenarioContext.save("statusCode", swaggerResult.get("statusCode"));
        ScenarioContext.save("type", swaggerResult.get("type"));

        Assert.assertNotNull(swaggerResult);
    }
}