package com.kekemon.mywallet.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.kekemon.mywallet.UserManager;
import com.kekemon.mywallet.model.UserProfile;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		System.out.println("doPost");
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
			JSONObject json = (JSONObject)new JSONParser().parse(jb.toString());
			String mobile = (String)json.get("mobile");
			String password = (String)json.get("password");
			
			UserProfile userProfile = UserManager.getUserBy(mobile, password);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			
			if(userProfile != null){
				PrintWriter out = response.getWriter();
				JSONObject jsonObject = userProfile.toJSON();
				jsonObject.put("msg", "Success to Login");
				out.println(jsonObject);
				return;
			}
			
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
		PrintWriter out = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "Failed to Login");
		out.println(jsonObject);
	}	

}