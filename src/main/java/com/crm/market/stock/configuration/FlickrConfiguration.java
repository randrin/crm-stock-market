package com.crm.market.stock.configuration;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlickrConfiguration {

    @Value("${flickr.apiKey}")
    private String apiKey;

    @Value("${flickr.apiSecret}")
    private String apiSecret;

    @Value("${flickr.appKey}")
    private String appKey;

    @Value("${flickr.appSecret}")
    private String appSecret;

//    @Bean
//    public Flickr getFlickr() throws InterruptedException, ExecutionException, IOException, FlickrException {
//        Flickr flickr = new Flickr(apiKey, apiSecret, new REST());
//
//        OAuth10aService oAuth10aService = new ServiceBuilder(apiKey)
//                .apiSecret(apiSecret).build(FlickrApi.instance(FlickrApi.FlickrPerm.DELETE));
//
//        final Scanner scanner = new Scanner(System.in);
//
//        final OAuth1RequestToken oAuth1RequestToken = oAuth10aService.getRequestToken();
//
//        final String authUrl = oAuth10aService.getAuthorizationUrl(oAuth1RequestToken);
//
//        System.out.println(authUrl);
//        System.out.println("Paste it here >>");
//
//        final String authVerifier = scanner.nextLine();
//
//        OAuth1AccessToken oAuth1AccessToken = oAuth10aService.getAccessToken(oAuth1RequestToken, authVerifier);
//
//        System.out.println(oAuth1AccessToken.getToken());
//        System.out.println(oAuth1AccessToken.getTokenSecret());
//
//        Auth auth = flickr.getAuthInterface().checkToken(oAuth1AccessToken);
//
//        System.out.println("---------------------------------");
//        System.out.println(auth.getToken());
//        System.out.println(auth.getTokenSecret());
//
//        return flickr;
//    }

    @Bean
    public Flickr getFlickr() {
        Flickr flickr = new Flickr(apiKey, apiSecret, new REST());

        Auth auth = new Auth();

        auth.setPermission(Permission.DELETE);

        auth.setToken(appKey);
        auth.setTokenSecret(appSecret);

        RequestContext requestContext = RequestContext.getRequestContext();
        requestContext.setAuth(auth);

        flickr.setAuth(auth);

        return flickr;
    }
}
