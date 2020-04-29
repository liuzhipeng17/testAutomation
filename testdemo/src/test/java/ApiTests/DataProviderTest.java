package ApiTests;

import com.api.common.entity.Request;
import io.qameta.allure.junit4.DisplayName;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;  //类似testNG的
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@DisplayName("Data provider test")
@RunWith(DataProviderRunner.class)
public class DataProviderTest {

    @DataProvider
    public static Object[][] dataProviderAdd() {
        //String s = "sdf";
        return new Object[][]{
                {0, 0, 0},
                {1, 1, 2},
        };
    }

    @Test
    @UseDataProvider("dataProviderAdd")  // 加了这个后，testadd可以有参数，否则不能
    public void testAdd(int first, int second, long result) {
        assertEquals(first + second, result);
    }


    @DataProvider
    public static Request dataMessage7019(){
        // 返回的是一个二维数组，每一行显示一条测试用例
        return new Request();
    }

    @Test
    @UseDataProvider("dataMessage7019")
    public void testMessage7019(Request reqInfo){


    }

}