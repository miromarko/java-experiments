package ro.springsoft.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "http://localhost:8080/converter2/FFConverter";
		String infile = "/home/miromarko/Documents/docs/manuals/en/install.odt";
		String outfile = "/home/miromarko/Documents/docs/manuals/en/install.pdf";
		System.out.println((new TestMain()).convertPDF(url, infile, outfile));
	}

	public  boolean convertPDF(String url, String infile, String outfile) {
		Logger log = Logger.getLogger(getClass().getName());

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		
		boolean convertOK = false;

		try {
			List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>(
					2);
			nameValuePairs.add(new BasicNameValuePair("infile", infile));
			nameValuePairs.add(new BasicNameValuePair("outfile", outfile));
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = "";
			
			while ((line = rd.readLine()) != null) {
				log.info(line);
				if (line.contains(" OK ")) {
					convertOK = true;
					break;
				}
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
		return convertOK;

	}

}
