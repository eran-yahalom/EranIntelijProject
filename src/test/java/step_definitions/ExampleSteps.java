package step_definitions;

import configurations.db.QueryExecutor;

import io.cucumber.java.en.*;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class ExampleSteps {

    private List<Map<String, Object>> result;
    private int userId;

    @Given("I have a user ID")
    public void i_have_a_user_id() {
        userId = 1;
    }

    @When("I execute the query get_user_by_id")
    public void execute_query() {

        result = QueryExecutor.executeQueryAsTable(
                "get_album_title",
                userId
        );
    }

    @Then("I should see the user's details in the result")
    public void validate_result() {

        assertFalse(result.isEmpty(), "No data returned from DB");

        assertEquals(result.get(0).get("id"), userId);
    }
}