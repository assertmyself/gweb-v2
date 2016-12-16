package com.gbcom.system.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
/**
 * 发送Http request
 * 
 * @author SunYanzheng
 * @date 2014-10-15
 * @version v1.0.0
 * @see com.gbcom.protocol.http.HttpClientUtil
 */
@SuppressWarnings("deprecation")
public class HttpClientUtil {
	private static Logger log = Logger.getLogger(HttpClientUtil.class);

	/**
	 * 单文件上传。
	 * 
	 * @param url
	 *            String
	 * @param file
	 *            String
	 * @param filepath
	 *            String
	 * @return String
	 */
	public static String postFile(String url, String file, String filepath) {
		String body = "HTTP 404";
		if (file == null || filepath == null) {
			return body;
		}
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			HttpPost httppost = new HttpPost(url);
			MultipartEntity reqEntity = new MultipartEntity();
			FileBody fileBody = new FileBody(new File(filepath + File.separator
					+ file));
			reqEntity.addPart("file", fileBody);// file1为请求后台的File upload;属性
			httppost.setEntity(reqEntity);
			body = invoke(httpclient, httppost);
			httpclient.getConnectionManager().shutdown();
		} catch (Exception e) {
			log.error("invoke is error", e);
		}
		return body;
	}

	/**
	 * @param url
	 *            String
	 * @param params
	 *            Map<String, String>
	 * @return String
	 */
	public static String post(String url, Map<String, String> params) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;
		log.info("create httppost:" + url);
		HttpPost post = postForm(url, params);
		body = invoke(httpclient, post);

		httpclient.getConnectionManager().shutdown();

		return body;
	}

	/**
	 * 允许指定http头消息
	 * 
	 * @param url
	 *            String
	 * @param params
	 *            Map<String, String>
	 * 
	 * @param headers
	 *            Map<String,String> 指定头
	 * @return String
	 */
	public static String post(String url, Map<String, String> params,
			Map<String, String> headers) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;

		log.info("create httppost:" + url);
		HttpPost post = postForm(url, params);
		setHeader(post, headers);
		body = invoke(httpclient, post);
		httpclient.getConnectionManager().shutdown();

		return body;
	}

	/**
	 * @param url
	 *            String
	 * @param obj
	 *            Object
	 * @return String
	 */
	public static String post2(String url, Object obj) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;

		log.info("create httppost  json  :" + url);
		HttpPost post = postJSON(url, obj);
		body = invoke(httpclient, post);

		httpclient.getConnectionManager().shutdown();

		return body;
	}

	/**
	 * 向贝联 URL 发送POST方法的请求
	 * 
	 * @author qinyeju
	 * 
	 * @param params
	 *            请求参数，请求参数是 JSON格式。
	 * @return 所代表远程资源的响应结果
	 */
	// off checkstyle
	public static String bblinkPost(Map<String, Object> params) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";

		String url = params.get("url").toString();
		String param = params.get("param").toString();
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept",
					"application/vnd.bblink.remote-access.v1+json");
			conn.setRequestProperty("Content-Type",
					"application/vnd.bblink.remote-access.v1+json");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			log.error("send post request exception:" + e);
		} finally {// 使用finally块来关闭输出流、输入流
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				log.error("close exception:" + ex);
			}
		}
		return result;
	}

	public static String get(String url, Object obj) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;

		log.info("create httppost  json  :" + url);
		HttpGet get = getJSON(url, obj);
		body = invoke(httpclient, get);
		httpclient.getConnectionManager().shutdown();
		return body;

	}

	private static String invoke(DefaultHttpClient httpclient,
			HttpUriRequest httpost) {
		HttpResponse response = sendRequest(httpclient, httpost);
		String body = paseResponse(response);
		return body;
	}

	private static String paseResponse(HttpResponse response) {
		log.info("get response from http server..");
		HttpEntity entity = response.getEntity();

		String charset = EntityUtils.getContentCharSet(entity);
		log.info("response status: " + response.getStatusLine()
				+ " ;; charset=" + charset);

		String body = null;
		try {
			body = EntityUtils.toString(entity);
			if (log.isDebugEnabled()) {
				log.info(body);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception", e);
		}

		return body;
	}

	private static HttpResponse sendRequest(DefaultHttpClient httpclient,
			HttpUriRequest httpost) {
		log.info("execute post...");
		HttpResponse response = null;

		try {
			response = httpclient.execute(httpost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	private static HttpPost postForm(String url, Map<String, String> params) {

		HttpPost httpost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}

		try {
			log.info("set utf-8 form entity to httppost");
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return httpost;
	}

	// 填充头
	private static void setHeader(HttpRequestBase req,
			Map<String, String> params) {
		@SuppressWarnings("unused")
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			req.setHeader(key, params.get(key));
		}
	}

	private static HttpPost postJSON(String url, Object obj) {

		HttpPost httpost = new HttpPost(url);

		try {
			log.info("set utf-8 form entity to httppost");
			httpost.setEntity(new StringEntity(JsonUtil.beanToJSON(obj),
					HTTP.UTF_8));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return httpost;
	}

	public static String get(String url) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;

		log.info("create httppost:" + url);
		HttpGet get = new HttpGet(url);
		body = invoke(httpclient, get);

		httpclient.getConnectionManager().shutdown();

		return body;
	}

	public static String get(String url, Map<String, String> headers) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;

		log.info("create httppost:" + url);
		HttpGet get = new HttpGet(url);
		setHeader(get, headers);

		// get.addHeader("Cookie",
		// "MSNADS=UM=8238 name=admin domain=10.50.120.45 language=0 expires=Mon 21-Sep-2015 04:52:06 GMT");
		body = invoke(httpclient, get);

		httpclient.getConnectionManager().shutdown();

		return body;
	}

	private static HttpGet getJSON(String url, Object obj) {

		String params = JsonUtil.beanToJSON(obj).replace("\"", "%22").replace(
				"{", "%7b").replace("}", "%7d");

		HttpGet httpGet = new HttpGet(url + "?" + params);

		return httpGet;

		// 方法二
		// HttpGet httpGet = new HttpGet(url);
		// try {
		// log.info("set utf-8 form entity to httppost");
		// ((HttpResponse) httpGet).setEntity( new
		// StringEntity(JsonUtils.beanToJSON(obj),HTTP.UTF_8));
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		// 方法三
		// HttpGet httpGet = new HttpGet(url+"?"+"params:{phoneId:'phones'}");
		// httpGet.addHeader("Accept",
		// "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		// httpGet.addHeader("Connection", "Keep-Alive");
		// httpGet.addHeader("User-Agent",
		// "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");
		// httpGet.addHeader("Cookie", "");
		// try {
		// HttpResponse response = HttpClients.createDefault().execute(httpGet);
		//	
		// if(response.getStatusLine().getStatusCode()==200){
		// EntityUtils.toString(response.getEntity());
		// }
		// } catch (ClientProtocolException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

	}

	/**
	 * 处理一些不能够通过字符串的形式写到浏览器中的数据，比如：图片
	 * 
	 * @param url
	 *            图片地址
	 * @param headers
	 *            请求头
	 * @param resp
	 *            HttpServletResponse
	 * @throws IOException
	 *             IOException
	 */
	public static void getAsStream(String url, Map<String, String> headers,
			HttpServletResponse resp) throws IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		setHeader(get, headers);
		HttpResponse response = HttpClientUtil.sendRequest(httpclient, get);
		InputStream is = response.getEntity().getContent();
		OutputStream out = resp.getOutputStream();
		try {
			int count = 0;
			byte[] buffer = new byte[1024 * 1024];
			while ((count = is.read(buffer)) != -1)
				out.write(buffer, 0, count);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
			if (is != null)
				is.close();
		}
	}

	/**
	 * 获取文输入流
	 * 
	 * @param url
	 *            路径
	 * @return 流
	 */
	public static InputStream getAsStream(String url) {
		InputStream inputStream = null;
		try {
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpGet get = new HttpGet(url);
			HttpResponse response = HttpClientUtil.sendRequest(httpclient, get);
			inputStream = response.getEntity().getContent();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return inputStream;
	}

	
    
	/**
	 * 
	 * curl curl.exe -F file=@d:\SysInfo.java
	 * 
	 * 
	 * D:\get-Next\web2.0\restful\curl-7.40.0-ssl-sspi-zlib-static-bin-w32>curl.
	 * exe -F "file=@d:\SysInfo.java"
	 * http://10.50.120.45:18080/ccsv3/rest/bblink/logs
	 * 
	 * @param args
	 */

	public static void main(String args[]) {
		for (int i = 0; i < 15; i++) {
			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {

					for (int i = 0; i < 200; i++) {
						String rst = postFile(
								"http://localhost:8080/ccsv3/rest/bblink/logs",
								"aa.txt", "C:/");
						System.out.println("rst=" + rst);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				}

			});
			t.start();
		}
	}

}
