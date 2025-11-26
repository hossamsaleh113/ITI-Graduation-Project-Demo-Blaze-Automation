package coreLibraries.dataproviders;

import org.testng.annotations.DataProvider;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;
import java.util.Map;

public class JSONDataProvider {
    @DataProvider(name = "jsonDataTestsOrders")
    public Object[][] getTestDataForOrders() {
        return fillDataFromJson("src/test/resources/test-data/orders.json");
    }


    private Object[][] fillDataFromJson(String jsonPath) {
        try {
            //Put the data from json file into list of maps <Key , Value>
            ObjectMapper mapper = new ObjectMapper();
            List<Map<String, String>> dataList = mapper.readValue(new File(jsonPath), new TypeReference<List<Map<String , String>>>() {
            });

            //loop through the list and fill data into multi_dimensional array of objects for data providers
            Object[][] data = new Object[dataList.size()][];
            for (int i = 0; i < dataList.size(); i++) {
                data[i] = new Object[]{dataList.get(i)};
            }

            return data;
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }
}
