package com.javadeveloperzone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import java.io.IOException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

/**
 * Created by JavaDeveloperZone on 19-07-2017.
 */
@Controller
public class EmployeeController {


    @GetMapping("getForm")
    public String getForm() {
        return "employeeFrom";
    }

    @PostMapping("/saveDetails")                     // it only support port method
    public String saveDetails(@RequestParam("employeeName") String employeeName,
                              @RequestParam("employeeEmail") String employeeEmail,
                              ModelMap modelMap) {

        // write your code to save details
        modelMap.put("employeeName", employeeName);
        modelMap.put("employeeEmail", employeeEmail);
	//save the employee to database
        String baseUrl = "http://events-data-service:8082/user/save?email="+employeeEmail+"&name="+employeeName;
        System.out.println(baseUrl);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response=null;
		try{
		response=restTemplate.exchange(baseUrl,	HttpMethod.GET, getHeaders(),String.class);
        System.out.println(response);
		}catch (Exception ex)
		{
			System.out.println(ex);
		}
        return "viewDetails";           // welcome is view name. It will call employeeFrom.jsp
    }
    private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
}
