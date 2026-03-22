package api.client;

import api.pojo.*;
import api.utils.APIRequestUtility;
import api.utils.ApiResponse;
import api.utils.GenericAPIRequestBuilder;

import java.util.HashMap;
import java.util.Map;


public class ReqresAPI {

    private final String baseUrl = "https://reqres.in/api/";
    private final String GETUsers = "users?page=2";
    private final String SingleUser = "users/";
    private final String userLogin = "login";


    Map<String, String> headers;
    Map<String, Object> pathParams = new HashMap<>();

    public ApiResponse<GetAllUsersDataPOJO> getUsersDetails() {

        headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("x-api-key", "reqres_19e403e39f404de8b734a4acd6f8b1d0");

        return APIRequestUtility.makeApiRequest(
                baseUrl + GETUsers,
                headers,
                null,
                GetAllUsersDataPOJO.class,
                GenericAPIRequestBuilder.RequestMethod.GET,
                null,
                null
        );
    }//ShipResponsePOJO

    public ApiResponse<SingleUserResponsePOJO> getSingleUserDetails(String id) {

        headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("x-api-key", "reqres_19e403e39f404de8b734a4acd6f8b1d0");

        return APIRequestUtility.makeApiRequest(
                baseUrl + SingleUser + id,
                headers,
                null,
                SingleUserResponsePOJO.class,
                GenericAPIRequestBuilder.RequestMethod.GET,
                null,
                null
        );
    }

    public ApiResponse<LoginResponsePOJO> userLogin(String email, String password) {

        headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json; charset=UTF-8");
        headers.put("x-api-key", "reqres_19e403e39f404de8b734a4acd6f8b1d0");

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", email);
        requestBody.put("password", password);

        return APIRequestUtility.makeApiRequest(
                baseUrl + userLogin,
                headers,
                requestBody,
                LoginResponsePOJO.class,
                GenericAPIRequestBuilder.RequestMethod.POST,
                null,
                null
        );
    }

    public ApiResponse<UpdateUserResponsePOJO> updateUser(String userName, String job, String id) {

        headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json; charset=UTF-8");
        headers.put("x-api-key", "reqres_19e403e39f404de8b734a4acd6f8b1d0");

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", userName);
        requestBody.put("job", job);

        return APIRequestUtility.makeApiRequest(
                baseUrl + SingleUser + id,
                headers,
                requestBody,
                UpdateUserResponsePOJO.class,
                GenericAPIRequestBuilder.RequestMethod.PUT,
                null,
                null
        );
    }

    public ApiResponse<Void> deleteUser(String id) {

        headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("x-api-key", "reqres_19e403e39f404de8b734a4acd6f8b1d0");

        return APIRequestUtility.makeApiRequest(
                baseUrl + SingleUser + id,
                headers,
                null,
                Void.class,   // ✅ correct
                GenericAPIRequestBuilder.RequestMethod.DELETE,
                null,
                null
        );
    }
}