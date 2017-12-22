package com.min.home;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		return home(model);
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {
		List<String> cssList;
		cssList=new ArrayList<String>();
		cssList.add("header.css");
		cssList.add("style.css");
		cssList.add("body/home.css");
		
		model.addAttribute("title","home");
		model.addAttribute("cssList",cssList);
		model.addAttribute("headerhtml", readHtml("header/header.html"));
		model.addAttribute("bodyhtml", readHtml("body/home.html"));
		
		return "index";
	}
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Model model) {
		List<String> cssList;
		cssList=new ArrayList<String>();
		cssList.add("header.css");
		cssList.add("style.css");
		cssList.add("body/profile.css");
		
		model.addAttribute("title","profile");
		model.addAttribute("cssList",cssList);
		model.addAttribute("headerhtml", readHtml("header/header.html"));
		model.addAttribute("bodyhtml", readHtml("body/profile.html"));
		return "index";
	}
	@RequestMapping(value = "/project", method = RequestMethod.GET)
	public String project(Model model) {
		List<String> cssList;
		cssList=new ArrayList<String>();
		cssList.add("header.css");
		cssList.add("style.css");
		cssList.add("body/project.css");
		cssList.add("body/sidebar.css");
		
		model.addAttribute("title","project");
		model.addAttribute("cssList",cssList);
		model.addAttribute("headerhtml", readHtml("header/header.html"));
		
		List<Map<String, String>> devList=new ArrayList<Map<String, String>>();
		
		JSONObject obj = readJSON("json/project.json");
		JSONArray devs=(JSONArray) obj.get("projects");
		for(int i=0;i<devs.size();i++) {
			obj=(JSONObject) devs.get(i);
			devList.add(getMapFromJsonObject(obj));
		}
		model.addAttribute("projectList", devList);
		return "project";
	}
	
	String readHtml(String html) {
		Resource resource = new ClassPathResource(html);
		try {
			InputStream is = resource.getInputStream();
			InputStreamReader isr = new InputStreamReader(is,"UTF-8");
			return IOUtils.toString(isr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	JSONObject readJSON(String json) {
		JSONParser parser = new JSONParser();
		Resource resource = new ClassPathResource(json);
		try {
			InputStream is = resource.getInputStream();
			InputStreamReader isr = new InputStreamReader(is,"UTF-8");
			try {
				return (JSONObject) parser.parse(IOUtils.toString(isr));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, String> getMapFromJsonObject( JSONObject jsonObj ) {
		Map<String, String> map = new HashMap<String, String>();
		Iterator<String> keys = (Iterator<String>) jsonObj.keySet().iterator();
		while(  keys.hasNext() ) {
		    String key = (String)keys.next();
		    map.put(key, (String) jsonObj.get(key));
		}
        return map;
	}
}
