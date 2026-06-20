package Contentful;

import com.contentful.java.cma.*;
public class ApiClient {
    private static final String SPACE_ID = "i2qqvp4vv9gi"; 
    private static final String ACCESS_TOKEN = "9-oTDHCXeAsEbPoa2G8GDlbjPejOFKMPMi8jgImJ1Eg"; 
    private static ApiClient instance = null;
    private CMAClient cMaclient;

    public ApiClient() {
    	cMaclient =
    		    new CMAClient
    		        .Builder()
    		        .setAccessToken(ACCESS_TOKEN)
    		        .setSpaceId(SPACE_ID)
    		        .setEnvironmentId("master")
    		        .build();
    }

    public static ApiClient getInstance() {
        if (instance == null) {
            synchronized (ApiClient.class) {
                if (instance == null) {
                    instance = new ApiClient();
                }
            }
        }
        return instance;
    }
    
    public CMAClient getClient() {
    	return cMaclient;
    }
}