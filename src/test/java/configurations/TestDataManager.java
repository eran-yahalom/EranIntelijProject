package configurations;

import models.testdata.CustomerTestData;
import models.testdata.SearchTestData;
import utils.JsonUtils;

import java.util.List;

public class TestDataManager {

    private static List<SearchTestData> searchTestDataList;
    private static List<CustomerTestData> customerTestDataList;

    static {
        try {
            customerTestDataList = JsonUtils.loadListData("data/customer_data.json", CustomerTestData.class);
        } catch (Exception e) {
            System.err.println("Error loading customer_data.json:");
            e.printStackTrace();
        }

        try {
            searchTestDataList = JsonUtils.loadListData("data/search_items.json", SearchTestData.class);
        } catch (Exception e) {
            System.err.println("Error loading search_items.json:");
            e.printStackTrace();
        }
    }

    // --- Search Data Getters ---
    public static List<SearchTestData> getSearchTestData() {
        return searchTestDataList;
    }

    public static SearchTestData getSearchItem(int index) {
        return searchTestDataList.get(index);
    }

    // --- Customer Data Getters ---
    public static List<CustomerTestData> getCustomers() {
        return customerTestDataList;
    }

    public static CustomerTestData getCustomer(int index) {
        return customerTestDataList.get(index);
    }
}