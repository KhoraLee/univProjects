package konkuk.sylee.assignment5.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class GoogleTranslateTTS {
	private String encoding = "UTF-8", language, client = "tw-ob";

	/**
	 * 
	 * @param language The language to use in IETF format (en-US, fr-FR, etc.)
	 */
	public GoogleTranslateTTS(String language) {
		this.language = language;
	}

	/**
	 * Save the MP3 audio file for the specified text.
	 * 
	 * @param text
	 * @param file
	 * @throws IOException
	 */
	public void saveToFile(String text, String file) throws IOException {
		saveToFile(text, new File(file));
	}

	/**
	 * Save the MP3 audio file for the specified text.
	 * 
	 * @param text
	 * @param file
	 * @throws IOException
	 */
	public void saveToFile(String text, File file) throws IOException {
		byte[] data = getData(text);
		FileOutputStream out = new FileOutputStream(file);
		out.write(data);
		out.close();
	}

	/**
	 * Get the raw data from the server
	 * 
	 * @param text
	 * @return the audio raw data
	 * @throws IOException
	 */
	public byte[] getData(String text) throws IOException {
		InputStream in = getStream(text);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int n;
		byte[] buffer = new byte[1024];
		while ((n = in.read(buffer)) > -1) {
			out.write(buffer, 0, n);
		}
		in.close();
		out.close();
		return out.toByteArray();
	}

	/**
	 * Make the HTTPS URL that needs to be called to get the audio file
	 * 
	 * @param text
	 * @return the url
	 * @throws MalformedURLException
	 * @throws UnsupportedEncodingException
	 */
	public URL getUrl(String text) throws MalformedURLException, UnsupportedEncodingException {
		return new URL("https://translate.google.com/translate_tts?ie=" + encoding + "&q="
				+ URLEncoder.encode(text, encoding) + "&tl=" + language + "&client=" + client);
	}

	public InputStream getStream(String text) throws IOException {
		HttpsURLConnection con = (HttpsURLConnection) getUrl(text).openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:25.0) Gecko/20100101 Firefox/25.0");
		con.setReadTimeout(5000);
		con.connect();
		return con.getInputStream();
	}

	/**
	 * 
	 * @return the encoding
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * Set the encoding to use. (Default UTF-8)
	 * 
	 * @param encoding
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * 
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Set the language to use.
	 * 
	 * @param language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * 
	 * @return the 'client'
	 */
	public String getClient() {
		return client;
	}

	/**
	 * The 'client' to set. I actually don't know what this is, but Google requires
	 * it to be set to 'tw-ob' in order to work. Default: "tw-ob"
	 * 
	 * @param client
	 */
	public void setClient(String client) {
		this.client = client;
	}

}
