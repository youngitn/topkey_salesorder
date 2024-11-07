package com.topkey.salesorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		try {
			App.testAddNewsInWebClient();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testAddNewsInWebClient() {
		// 創建 WebClient 實例，並設置基本 URL
		WebClient webClient = WebClient.builder().baseUrl("https://ekptest.keentech-xm.com") // 設定基本的網域
				.build();

		String url = "/api/km-review/kmReviewRestService/addReview"; // 設定相對路徑

		// 把 SysNewsParamterForm 轉換成 MultiValueMap
		MultiValueMap<String, Object> wholeForm = new LinkedMultiValueMap<>();
		wholeForm.add("docSubject", "鄭仰廷的單子"); // 新聞主題
		wholeForm.add("docCreator", "{\"PersonNo\":\"046484\"}"); // 文件創建者的 JSON 字串
		wholeForm.add("docContent", "現在是星期五"); // 文件內容
		wholeForm.add("docStatus", 10); // 文件狀態
		wholeForm.add("fdTemplateId", "18cf1fee10387873b9e52614a03a4e3b"); // 模板 ID
		wholeForm.add("formValues", "{\"fd_txtRemark\":\"你好~~\"}"); // 表單值的 JSON 字串

		LinkedMultiValueMap<String, Object> innerMap = new LinkedMultiValueMap<>();
		// 注意附件列表的 key 是一樣的
		// wholeForm.add("attachmentForms[0].fdKey", "fd_3731bf4852e8fe"); // 第一个附件的 key
		// wholeForm.add("attachmentForms[0].fdFileName", new
		// String("测试文档.txt".getBytes("UTF-8"), "ISO-8859-1")); // 附件文件名
		// wholeForm.add("attachmentForms[0].fdAttachment", new FileSystemResource(new
		// File("E:/测试文档.txt"))); // 附件檔案

		// 設置 POST 請求的請求體規範
		WebClient.RequestBodySpec requestBodySpec = webClient.post().uri(url) // 設定請求的 URI
				.contentType(MediaType.MULTIPART_FORM_DATA); // 設定請求的內容類型為多部分表單數據

		// 發送請求並獲取響應
		Mono<ResponseEntity<String>> response = requestBodySpec.body(BodyInserters.fromMultipartData(wholeForm)) // 設置請求體
				.retrieve() // 發送請求
				.toEntity(String.class); // 將響應轉換為 ResponseEntity<String>

		// 訂閱響應，處理成功和錯誤情況
		response.subscribe(resp -> {
			System.out.println("Response status code: " + resp.getStatusCode()); // 打印響應狀態碼
			System.out.println("Response body: " + resp.getBody()); // 打印響應體
		}, error -> {
			System.err.println("Request failed: " + error.getMessage()); // 打印錯誤訊息
		});
	}

	/**
	 * 多层级的VO对象，且包含上传功能的样例 注意key的书写格式,类似EL表达式的方式，属性关系用'.', 列表和数组关系用[]，Map关系用["xxx"]
	 */
	public static void testAddNewsInRestTemplate() throws Exception {
		RestTemplate yourRestTemplate = new RestTemplate();

		String url = "https://ekptest.keentech-xm.com/api/km-review/kmReviewRestService/addReview"; // 指向EKP的接口url
		// 把SysNewsParamterForm转换成MultiValueMap
		MultiValueMap<String, Object> wholeForm = new LinkedMultiValueMap<>();
		wholeForm.add("docSubject", "鄭仰廷的單子");
		wholeForm.add("docCreator", "{\"PersonNo\":\"046484\"}");
		wholeForm.add("docContent", "現在是星期五");
		wholeForm.add("docStatus", 10);
		wholeForm.add("fdTemplateId", "18cf1fee10387873b9e52614a03a4e3b");
		wholeForm.add("formValues", "{\"fd_txtRemark\":\"你好\"}");

		LinkedMultiValueMap<String, Object> innerMap = new LinkedMultiValueMap<>();
		// 注意附件列表的key是一样的
		// wholeForm.add("attachmentForms[0].fdKey", "fd_3731bf4852e8fe");// 第一个附件
		// wholeForm.add("attachmentForms[0].fdFileName", new
		// String("测试文档.txt".getBytes("UTF-8"), "ISO-8859-1"));
		// wholeForm.add("attachmentForms[0].fdAttachment", new FileSystemResource(new
		// File("E:/测试文档.txt")));

		HttpHeaders headers = new HttpHeaders();
		// 如果EKP对该接口启用了Basic认证，那么客户端需要加入
		// addAuth(headers,"yourAccount"+":"+"yourPassword");是VO，则使用APPLICATION_JSON
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		// 必须设置上传类型，如果入参是字符串，使用MediaType.TEXT_PLAIN；如果
		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String, Object>>(wholeForm,
				headers);

		// 有返回值的情况 VO可以替换成具体的JavaBean
		ResponseEntity<String> obj = yourRestTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		String body = obj.getBody();
		System.out.println(obj);
	}

	/**
	 * 创建附件列表
	 */
//	public static List<AttachmentForm> createAllAtts() throws Exception {
//
//		List<AttachmentForm> attForms = new ArrayList<AttachmentForm>();
//
//		String fileName = "测试文档.txt";
//		AttachmentForm attForm01 = createAtt(fileName);
//
//		attForms.add(attForm01);
//
//		return attForms;
//	}

	/**
	 * 创建附件对象
	 */
//	public static AttachmentForm createAtt(String fileName) throws Exception {
//		AttachmentForm attForm = new AttachmentForm();
//		attForm.setFdFileName(fileName);
//		// 设置附件关键字，表单模式下为附件控件的id
//		attForm.setFdKey("fd_3731bf4852e8fe");
//
//		File file = new File("E:\\" + fileName);
//		DataSource dataSource = new FileDataSource(file);
//		DataHandler dataHandler = new DataHandler(dataSource);
//
//		attForm.setFdAttachment(dataHandler);
//
//		return attForm;
//	}

}
