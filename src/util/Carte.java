package util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

public class Carte {

	private static String url = "http://localhost:8088/kettle/execJob";
	private static String username = "admin";
	private static String password = "123456";
	private static int timeout = 30000;

	private static HttpClient httpClient = new HttpClient();
	private static GetMethod getMethod = new GetMethod(url);
	private static Credentials credentials = new UsernamePasswordCredentials(username, password);

	static {
		httpClient.getState().setCredentials(AuthScope.ANY, credentials);
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeout);
	}

	public static JobStatus init() {
		return execJob("init", "level", "info");
	}

	public static JobStatus syncEnterpriseToZCDH(String enterpriseId) {
		return execJob("hb-enterprise-entpost", "enterpriseId", enterpriseId);
	}

	public static JobStatus syncEnterpriseToHB(String enterpriseId) {
		return execJob("zcdh-enterprise-entpost", "enterpriseId", enterpriseId);
	}

	public static JobStatus syncJobhunterToZCDH(String jobhunterId) {
		return execJob("hb-jobhunter-jobresume", "jobhunterId", jobhunterId);
	}

	public static JobStatus syncJobhunterToHB(String jobhunterId) {
		return execJob("zcdh-jobhunter-jobresume", "jobhunterId", jobhunterId);
	}

	private static JobStatus execJob(String job, String... keyvals) {
		if (job != null && keyvals.length % 2 == 0) {
			List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
			nameValuePairList.add(new NameValuePair("job", job));
			for (int i = 0; i < keyvals.length; i += 2)
				if (keyvals[i] != null && keyvals[i + 1] != null)
					nameValuePairList.add(new NameValuePair(keyvals[i], keyvals[i + 1]));
			getMethod.setQueryString(nameValuePairList.toArray(new NameValuePair[0]));
			try {
				httpClient.executeMethod(getMethod);
				JobStatus jobStatus = new JobStatus();
				jobStatus.setStatusCode(getMethod.getStatusCode());
				if (getMethod.getStatusCode() != HttpServletResponse.SC_OK) {
					jobStatus.setFinished(false);
					jobStatus.setError(getMethod.getStatusText());
				} else {
					jobStatus.setFinished(true);
					Document document = DocumentHelper.parseText(getMethod.getResponseBodyAsString());
					Node resultNode = document.selectSingleNode("webresult/result");
					Node messageNode = document.selectSingleNode("webresult/message");
					if (resultNode == null || messageNode == null)
						jobStatus.setError("Unknown");
					else if (!"OK".equalsIgnoreCase(resultNode.getText()))
						jobStatus.setError(messageNode.getText());
				}
				return jobStatus;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	public static class JobStatus {

		private int statusCode = -1;
		private boolean finished = false;
		private String error;

		public int getStatusCode() {
			return statusCode;
		}

		public void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}

		public boolean isFinished() {
			return finished;
		}

		public void setFinished(boolean finished) {
			this.finished = finished;
		}

		public boolean hasError() {
			return error != null ? true : false;
		}

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}

	}

}
