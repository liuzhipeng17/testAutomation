import io.qameta.allure.Step;
import org.hamcrest.core.IsEqual;
import org.junit.rules.ErrorCollector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class CustomAssertion {

    @Step("soft validate")
    public static void softValidate(Map<String, Object> response, List<HashMap<String, Object>> validators, ErrorCollector co)
    {
        for(int i=0; i<validators.size();i++){
            String checkPoint = (String)validators.get(i).get("check");
            String expect = (String) validators.get(i).get("expected");
            String[] s = checkPoint.split(Pattern.quote("."));
            String actual = "";
            if (s.length == 1){
                actual = (String)response.get(checkPoint);
                co.checkThat(actual, IsEqual.equalTo(expect));
            }
            else{
                int j = 1;
                Map<String, Object> tempVar = (Map)response.get(s[0]);
                while(j<s.length-1){
                    tempVar = (Map)tempVar.get(s[j]);
                    j = j+1;
                }

                try{
                    actual = (String)tempVar.get(s[j]);
                }
                catch (ClassCastException e)
                {
                    actual = tempVar.get(s[j]).toString();
                }

            }

            co.checkThat(actual, IsEqual.equalTo(expect));

        }


    }


}

