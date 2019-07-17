import com.zhengtong.fsp.commons.core.exception.AjaxException;
import com.zhengtong.fsp.commons.utils.HttpClientUtil;
import com.zhengtong.fsp.commons.utils.JsonUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * Created by dongwen on 2017/8/10.
 */
public class TestApi {
    @Test
    public void testApi() {
        String result = HttpClientUtil.doPostJson("http://10.1.12.51:10001/V3SBaseService/PostLoan/QueryBusinessStatusAddXYH", "{\"BusinessIDList\":[" + 50005 + "]}");
        System.out.println(result);
//        try {
//            Map resultMap = JsonUtils.Json2Map(result);
//            result = resultMap.get("Content").toString();
//            result = result.replace("[", "");
//            result = result.replace("]", "");
//            Map conMap = JsonUtils.Json2Map(result);
//            result = conMap.get("IsClearLoan").toString();
//        } catch (IOException ioe) {
//            throw new AjaxException("the After Loan Api is broken！");
//        }
    }
}
