import com.xignite.sdk.api.webservices.XigniteGlobalMetals.Models.MetalQuote;
import com.xignite.sdk.api.webservices.XigniteGlobalMetals.XigniteGlobalMetals;

/**
 * Created by xiaoxia on 16/3/11.
 */
public class XigniteTest {

    public static void main (String [] args) throws Exception {
        String token = "";
        XigniteGlobalMetals metals_request = new XigniteGlobalMetals (token);
        MetalQuote quote = metals_request.getRealTimeMetalQuote("XAU", "USD");
        System.out.println (quote.Bid);
    }
}
