package net.esc20.txeis.sample.controllers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.esc20.txeis.sample.domains.Student;
import net.esc20.txeis.sample.domains.User;
import net.esc20.txeis.sample.models.RestModel;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

@Controller
@RequestMapping(value="/rest-ws-java")
@SessionAttributes("RestModel")
public class RestJavaController {

	
	//Initial page request
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {
		RestModel restModel = new RestModel();
		
		model.addAttribute("RestModel", restModel);
		return "rest-ws-java";
	}
	
	@RequestMapping(method = RequestMethod.POST, params = "GetXml")
	public String getXml(@ModelAttribute("RestModel") RestModel restModel) {
		try {
			String url = "https://" + restModel.getServer() + "/ws/v1/UserService/users";
			
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	
			// optional default is GET
			con.setRequestMethod("GET");
	
			//add request header
			//con.setRequestProperty("User-Agent", USER_AGENT);
	
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
	
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	
			//print result
			System.out.println(response.toString());
			
			restModel.setUsers(parseXML(response.toString()));
			
			restModel.setOutputBox("Success");
			
		} catch (Exception e) {
			e.printStackTrace();
			restModel.setOutputBox("Failure");
			restModel.setUsers(null);
		}

		return "rest-ws-java";
	}
	
	@RequestMapping(method = RequestMethod.POST, params = "GetJson")
	public String getJson(@ModelAttribute("RestModel") RestModel restModel) {
	
		try {
			String url = "https://" + restModel.getServer() + "/ws/v1/UserService/usersjson";
			
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
				
			// optional default is GET
			con.setRequestMethod("GET");
	
			//add request header
			//con.setRequestProperty("User-Agent", USER_AGENT);
	
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
	
			InputStream inputStream = con.getInputStream();
				
			ObjectMapper objectMapper = new ObjectMapper();
			List<User> users = objectMapper.readValue(inputStream, new TypeReference<List<User>>(){});
						
			restModel.setUsers(users);
			
			restModel.setOutputBox("Success");
			
		} catch (Exception e) {
			e.printStackTrace();
			restModel.setOutputBox("Failure");
			restModel.setUsers(null);
		}		
		
		return "rest-ws-java";
	}
	
	@RequestMapping(method = RequestMethod.POST, params = "GetStudents")
	public String getStudents(@ModelAttribute("RestModel") RestModel restModel) {
	
		try {
			String url = "https://" + restModel.getServer() + "/ws/v1/students/demographics/D218999";
			//String url = "https://localhost:8443/ws/v1/students/demographics";
			
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
				
			// optional default is GET
			con.setRequestMethod("GET");
	
			//add request header
			//con.setRequestProperty("User-Agent", USER_AGENT);
	
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
	
			if (responseCode > 300) {
				String line = "";
				InputStream error = con.getErrorStream();
				BufferedReader input = new BufferedReader(new InputStreamReader(error));
				  while ((line = input.readLine()) != null) {
				    System.out.println(line);
				  }
				restModel.setOutputBox("Failure");
			} else {
			
				InputStream inputStream = con.getInputStream();
				
				ObjectMapper objectMapper = new ObjectMapper();
				List<Student> students = objectMapper.readValue(inputStream, new TypeReference<List<Student>>(){});
						
				restModel.setStudents(students);
			
				restModel.setOutputBox("Success");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			restModel.setOutputBox("Failure");
			restModel.setUsers(null);
		}		
		
		return "rest-ws-java";
	}	
	
	@RequestMapping(method = RequestMethod.POST, params = "GetXmlUser")
	public String getXmlUser(@ModelAttribute("RestModel") RestModel restModel) {
		try {
			String url = "https://" + restModel.getServer() + "/ws/v1/UserService/users/" + restModel.getId1();
			
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	
			// optional default is GET
			con.setRequestMethod("GET");
	
			//add request header
			//con.setRequestProperty("User-Agent", USER_AGENT);
	
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
	
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	
			//print result
			System.out.println(response.toString());
			
			restModel.setUsers(parseXML(response.toString()));
			
			restModel.setOutputBox("Success");
			
		} catch (Exception e) {
			e.printStackTrace();
			restModel.setOutputBox("Failure");
			restModel.setUsers(null);
		}

		return "rest-ws-java";
	}
	
	@RequestMapping(method = RequestMethod.POST, params = "PostUser")
	public String postUser(@ModelAttribute("RestModel") RestModel restModel) {
		try {
			String url = "https://" + restModel.getServer() + "/ws/v1/UserService/users";
			
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			
			StringBuilder postData = new StringBuilder();
			postData.append(URLEncoder.encode("id", "UTF-8"));
			postData.append("=");
            postData.append(URLEncoder.encode(restModel.getId3(), "UTF-8"));
            postData.append("&");
			postData.append(URLEncoder.encode("name", "UTF-8"));
			postData.append("=");
            postData.append(URLEncoder.encode(restModel.getName2(), "UTF-8"));
            postData.append("&");
			postData.append(URLEncoder.encode("profession", "UTF-8"));
			postData.append("=");
            postData.append(URLEncoder.encode(restModel.getProfession2(), "UTF-8"));
       
			byte[] postDataBytes = postData.toString().getBytes("UTF-8");

			con.setRequestMethod("POST");
			con.setDoOutput(true); 
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		    con.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		    con.getOutputStream().write(postDataBytes);
		    
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
	
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	
			//print result
			System.out.println(response.toString());
			
			restModel.setUsers(null);
			
			restModel.setOutputBox("Success");
			
		} catch (Exception e) {
			e.printStackTrace();
			restModel.setOutputBox("Failure");
			restModel.setUsers(null);
		}

		return "rest-ws-java";
	}
		
	@RequestMapping(method = RequestMethod.POST, params = "PutUser")
	public String putUser(@ModelAttribute("RestModel") RestModel restModel) {
		try {
			String url = "https://" + restModel.getServer() + "/ws/v1/UserService/users";
			
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			
			StringBuilder postData = new StringBuilder();
			postData.append(URLEncoder.encode("id", "UTF-8"));
			postData.append("=");
            postData.append(URLEncoder.encode(restModel.getId2(), "UTF-8"));
            postData.append("&");
			postData.append(URLEncoder.encode("name", "UTF-8"));
			postData.append("=");
            postData.append(URLEncoder.encode(restModel.getName1(), "UTF-8"));
            postData.append("&");
			postData.append(URLEncoder.encode("profession", "UTF-8"));
			postData.append("=");
            postData.append(URLEncoder.encode(restModel.getProfession1(), "UTF-8"));
       
			byte[] postDataBytes = postData.toString().getBytes("UTF-8");

			con.setRequestMethod("PUT");
			con.setDoOutput(true); 
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		    con.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		    con.getOutputStream().write(postDataBytes);
		    
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'PUT' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
	
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	
			//print result
			System.out.println(response.toString());
			
			restModel.setUsers(null);
			
			restModel.setOutputBox("Success");
			
		} catch (Exception e) {
			e.printStackTrace();
			restModel.setOutputBox("Failure");
			restModel.setUsers(null);
		}

		return "rest-ws-java";
	}
	
	@RequestMapping(method = RequestMethod.POST, params = "DeleteUser")
	public String deleteUser(@ModelAttribute("RestModel") RestModel restModel) {
		try {
			String url = "https://" + restModel.getServer() + "/ws/v1/UserService/users/" + restModel.getId4();
			
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	
			con.setRequestMethod("DELETE");
	
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'DELETE' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
	
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	
			//print result
			System.out.println(response.toString());
			
			restModel.setUsers(null);
			
			restModel.setOutputBox("Success");
			
		} catch (Exception e) {
			e.printStackTrace();
			restModel.setOutputBox("Failure");
			restModel.setUsers(null);
		}

		return "rest-ws-java";
	}
	
	private List<User> parseXML(String stringDoc) {
		List<User> users = new ArrayList<User>();
	    try {	
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        InputSource is = new InputSource();
	        is.setCharacterStream(new StringReader(stringDoc));
	        Document doc = dBuilder.parse(is);
	        doc.getDocumentElement().normalize();
	        NodeList nList = doc.getElementsByTagName("user");
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	        	User user = new User();
	        	Node nNode = nList.item(temp);
	            Element element = (Element) nNode;
	            NodeList id = element.getElementsByTagName("id");
	            user.setId(id.item(0).getTextContent());
	            NodeList name = element.getElementsByTagName("name");
	            user.setName(name.item(0).getTextContent());
	            NodeList prof = element.getElementsByTagName("profession");
	            user.setProfession(prof.item(0).getTextContent());
	            users.add(user);
	         }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }	
		
		return users;
	}
	

}
