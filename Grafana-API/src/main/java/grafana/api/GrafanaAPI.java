package grafana.api;

import java.io.IOException;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GrafanaAPI {
	
	static String url = "http://localhost:3000/api";
	String key = "Bearer eyJrIjoiUExvWlprb2drYkJTZDMzM0h4azNpdWZpdjd1dE5kajQiLCJuIjoib2xhIiwiaWQiOjF9";
	static CloseableHttpClient httpclient = HttpClients.createDefault();
	
	public static void main (String arg []) throws ClientProtocolException, IOException {
		printKeys();
    }
	
	
	
	public static void printDashBoards() throws ClientProtocolException, IOException {
		HttpGet httpget = new HttpGet(url + "/search?folderIds=0&query=&starred=false");
		CloseableHttpResponse response = httpclient.execute(httpget);
		
		printResponseContent(response);
	}
	
	public static void printHomeDashBoard () throws ClientProtocolException, IOException {
		HttpGet httpget = new HttpGet(url + "/dashboards/home");	
		CloseableHttpResponse response = httpclient.execute(httpget);
		
		printResponseContent(response);
	}		
	
	public static void printDataSources() throws ClientProtocolException, IOException {
		HttpGet httpget = new HttpGet(url + "/datasources");	
		CloseableHttpResponse response = httpclient.execute(httpget);
		
		printResponseContent(response);
	}	
	
	public static void printKeys() throws ClientProtocolException, IOException {
		HttpGet httpget = new HttpGet(url + "/auth/keys");	
		CloseableHttpResponse response = httpclient.execute(httpget);
		
		printResponseContent(response);
	}	
	
	public static void printResponseContent(CloseableHttpResponse response) throws ParseException, IOException {
		try {
		    HttpEntity entity = response.getEntity();
		    if (entity != null) {
		        long len = entity.getContentLength();
		        if (len != -1 && len < 2048) {
		            System.out.println(EntityUtils.toString(entity));
		        } else {
		            // Stream content out
		        }
		    }
		} finally {
		    response.close();
		}			
	}
	
}
