package edu.pucmm.eict.util;

import com.pusher.pushnotifications.PushNotifications;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PushNotification {

    public static void sendPushNotification(String title,String body) throws IOException, URISyntaxException, InterruptedException {
        String instanceId = "76dc4a0c-919e-4985-b637-93963f82cfaf";
        String secretKey = "741C7694AE640549F4D6052A8843EE93669E46C2E54EC72FF022EBBBAD0EF151";

        PushNotifications beamsClient = new PushNotifications(instanceId, secretKey);

        List<String> interests = Arrays.asList("hello");

        Map<String, Map> publishRequest = new HashMap();
        Map<String, String> webNotification = new HashMap();
        webNotification.put("title", title);
        webNotification.put("body", body);
        webNotification.put("icon", "https://image.flaticon.com/icons/png/512/25/25332.png");
        webNotification.put("deep_link", "http://localhost:7000/");
        Map<String, Map> web = new HashMap();
        web.put("notification", webNotification);
        publishRequest.put("web", web);

        beamsClient.publishToInterests(interests, publishRequest);
    }
}
