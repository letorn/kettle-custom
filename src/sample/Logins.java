package sample;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

import util.Carte;
import util.Carte.JobStatus;

public class Logins {

	@Test
	public void loginToHB() throws Exception {
		String url = "http://www.hbjob.gov.cn/login/loginForThree.do";
		String account = "letorn";
		String password = "2011472";

		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		getMethod.setQueryString(new NameValuePair[] { new NameValuePair("account", account), new NameValuePair("password", password) });
		httpClient.executeMethod(getMethod);
		String response = getMethod.getResponseBodyAsString();// aac001: ff8080814ed785d7014edd6cd82f02e7
		System.out.println("hb response: " + response);

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
		String success = (String) jsonObject.get("success");
		String jobhunter = (String) jsonObject.get("aac001");

		if ("0".equals(success)) {
			System.out.println("init sync ...");
			JobStatus initStatus = Carte.initSync();
			System.out.println("init status: " + initStatus.getStatusCode() + ", error: " + initStatus.getError());

			System.out.println("sync jobhunter and jobresume ...");
			JobStatus syncStatus = Carte.syncJobhunterAndJobresumeFromHBToWHDH(jobhunter);
			System.out.println("sync jobhunter: " + syncStatus.getStatusCode() + ", error: " + syncStatus.getError());
		}
	}

	/**
	 * 使用湖北公共招聘网的求职者账号登录我们的App
	 * 如果不存在些求职者账号, 会同步
	 * @param account
	 * @param password
	 */
	public void loginToHB(String account, String password) {
		if (account != null && !"".equals(account.trim()) && password != null && !"".equals(password.trim())) {
			/*
			 * 调用 http://www.hbjob.gov.cn/login/loginForThree.do?account=account&password=password
			 * 返回 {"success":"3"} 提示：请输入用户名、密码
			 * 返回 {"success":"1"} 提示：用户名或密码不正确
			 * 返回 {"success":"2"} 提示：用户账户已被锁定，暂不能登录
			 * 返回 {"aac001":"8a84f6e94e0a6a39014e0a704d820000","success":"0"} 提示：登录成功
			 * 登录成功之后，获取userid
			 */
			String success = "...";
			String userid = "...";
			if ("3".equals(success)) {
				// 提示
			} else if ("1".equals(success)) {
				// 提示
			} else if ("2".equals(success)) {
				// 提示
			} else if ("0".equals(success)) {
				String dataSrc = "hb";
				String dataKey = userid;
				/*
				 * dataSrc, dataKey对应zcdh_jobhunte_user的data_src, data_key, 查询是否存在
				 * 如果不存在，刚同步
				 * 如果存在，返回app所需要的数据
				 */
				boolean exist = false;
				if (exist) {
					// 返回app所需要的数据
				} else {
					JobStatus jobStatus = Carte.syncJobhunterAndJobresumeFromHBToWHDH(dataKey);// 用于导入湖北公共招聘网的求职者信息
					if (jobStatus.isFinished() && !jobStatus.hasError()) {
						// 同步成功
						/*
						 * 重新根据dataSrc, dataKey对应zcdh_jobhunte_user的data_src, data_key, 查询是否存在
						 * 如果仍然不存在，提示服务器异常
						 * 如果存在，返回app所需要的数据
						 */
						exist = false;
						if (exist) {
							// 返回app所需要的数据
						} else {
							// 提示服务器异常
						}
					}
				}
			} else {
				// 提示服务器异常
			}
		} else {
			// 提示
		}
	}

	/**
	 * 提供给湖北公共招聘网使用的
	 * @param account
	 * @param password
	 */
	public void loginForHB(String account, String password) {
		String success = "";
		if (account == null || "".equals(account.trim()) || password == null || "".equals(password.trim())) {
			success = "3";
			// 返回 {"success":"3"}
		} else {
			/*
			 * 根据account, password查询我们系统是否存在这个用户
			 */
			boolean exist = false;
			if (!exist) {// 不存在
				success = "1";
				// 返回 {"success":"1"}
			} else {// 存在
				/*
				 * 获取此账号的求职者主键
				 */
				String userid = "...";// 求职者主键
				JobStatus jobStatus = Carte.syncJobhunterAndJobresumeFromWHDHToHB(userid);// 用于导出求职者信息到湖北公共招聘网
				if (jobStatus.isFinished() && !jobStatus.hasError()) {
					// 同步成功
					success = "0";
					// 返回 {"success":"0", "userid": "..."}
				} else {
					success = "4";// 服务器异常，同步失败
					// 返回 {"success":"4"}
				}
			}
		}
	}

}
