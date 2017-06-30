package org.trialdocs.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.trialdocs.model.Documents;
import org.trialdocs.model.DocumentsDao;
import org.trialdocs.model.Logs;
import org.trialdocs.model.LogsDao;
import org.trialdocs.model.Users;
import org.trialdocs.model.UsersDao;

@Controller
public class HomeController extends HttpServlet {

	private NamedParameterJdbcTemplate jdbc;
	private static final int BUFFER_SIZE = 4096;

	@Autowired
	private UsersDao userdao;

	@Autowired
	private ServletContext context;

	@Autowired
	private DocumentsDao documentDao;
	
	@Autowired
	private LogsDao logsDao;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	@RequestMapping("/")
	public String showHome() {
		return "login";
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public ModelAndView showLogin(@RequestParam("adminid") String adminid, @RequestParam("password") String password, @RequestParam(value = "action", required=false) String action) {

		if (adminid.equals("AmpelAdmin") && password.equals("ClinOps2017")) {
			if(action.equals("register")){
				ModelAndView mv = new ModelAndView("home");
				return mv;
			}else{
				ModelAndView mv = new ModelAndView("logs");
				return mv;
			}
		
		} else {

			ModelAndView mv = new ModelAndView("admin");
			Map<String,Object> model = mv.getModel();
			model.put("admincred", "Invalid admin credentials !");
			return mv;
		}

	}

	// @RequestParam("name") String name,@RequestParam("email") String
	// email,@RequestParam("password") String password,@RequestParam("role")
	// String role

	@RequestMapping(value = "/usercreated", method = RequestMethod.POST)
	public String createUser(HttpServletRequest request, Users users, @RequestParam("password") String password,
			@RequestParam("password_confirm") String pass_confirm) {

		if (password.equals(pass_confirm)) {
			System.out.println(users);
			userdao.createUser(users);
			return "login";
		} else {
			request.setAttribute("nopass", "The password and confirm password are not the same");
			return "home";
		}
		


	}

	@RequestMapping(value = "/upload")
	public ModelAndView authorizeUser(@RequestParam("email") String email, @RequestParam("role") String role) {

		ModelAndView mv = new ModelAndView("upload");
		Map<String, Object> mod = mv.getModel();
		mod.put("email", email);
		mod.put("role", role);
		return mv;

	}

	@RequestMapping(value = "/mainpage", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView postMainPage(Model model, HttpSession session, HttpServletRequest request,
			@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("role") String role) {

		Users user = userdao.getUser(email, password);
		if (user != null) {
			// Log the date and time when the user sign in and sign out.
			DateFormat dateTimeFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date date = new Date();
			String logMessage = "The user "+user.getUsername()+" logged in at :"+ dateTimeFormat.format(date);
			Logs logs = new Logs(email,user.getRole(),logMessage,dateFormat.format(date));
			
			//Creating logs in MySql database.
			logsDao.createLogs(logs);
						
			ModelAndView mv = new ModelAndView("mainpage");
			Map<String, Object> mod = mv.getModel();
			mod.put("email", user.getEmail());
			mod.put("role", user.getRole());	
			return mv;
		} else if (userdao.getBackuser(email, role) != null) {
			Users gBuser = userdao.getBackuser(email, role);
			ModelAndView mv = new ModelAndView("mainpage");
			Map<String, Object> mod = mv.getModel();
			mod.put("email", gBuser.getEmail());
			mod.put("role", gBuser.getRole());
			return mv;
		} else {

			request.setAttribute("usercred", "Invalid UserName and/or Password");
			// session.setAttribute("usercred", "Invalid UserName and/or
			// Password");
			ModelAndView mv = new ModelAndView("login");
			return mv;
		}

	}

	@RequestMapping(value = "/connect", method = RequestMethod.POST)
	public ModelAndView uploadDocument(HttpServletRequest request, HttpSession session,
			@RequestParam("name") String name, @RequestParam("surname") String surname,
			@RequestParam("sitenumber") String sitenumber, @RequestParam("date") String date,
			@RequestParam("version") String version, @RequestParam("email") String email,
			@RequestParam("role") String role) throws IOException, ServletException {

		InputStream inputStream = null;
		String[] emailPart = email.split("\\.");
		String emailPart1 = emailPart[0];
		String filename = name + surname + sitenumber + date + version + emailPart1 ;
		Part filePart = request.getPart("file");
		if (filePart != null) {
			System.out.println(filePart.getName());
			System.out.println(filePart.getSize());
			System.out.println(filePart.getContentType());
			inputStream = filePart.getInputStream();

		}

		Documents docs = new Documents(email, inputStream, filename,filePart.getContentType());
		documentDao.createDocument(docs);
		ModelAndView mv = new ModelAndView("success");
		Map<String, Object> model = mv.getModel();
		model.put("success", "Succesfully uploaded the file !");
		model.put("email", email);
		model.put("role", role);
		// request.setAttribute("success", "Succesfully uploaded the file !");
		return mv;
	}

	@RequestMapping("/remove")
	public ModelAndView deleteDocument(@RequestParam("filename") String filename, @RequestParam("email") String email,
			@RequestParam("role") String role) {
		if (documentDao.deleteDocument(filename)) {
			ModelAndView mv = new ModelAndView("success");
			Map<String, Object> model = mv.getModel();
			model.put("success", "Succesfully deleted the file!");
			model.put("email", email);
			model.put("role", role);
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("success");
			Map<String, Object> model = mv.getModel();
			model.put("success", "Unable to delete the file, please try again after sometime.");
			model.put("email", email);
			model.put("role", role);
			return mv;
		}

	}
	
	@RequestMapping("/downloadLogs")
	public void downloadLogs(HttpServletRequest request, HttpServletResponse response){
		
		String fileName = "logs.txt";
		String fileType = "application/octet-stream";	
		response.setContentType(fileType);
		response.setHeader("Content-disposition", "attachment; filename="+fileName);
		File myFile = new File(fileName);
		
		try {
			OutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(myFile);
			byte[] buffer = new byte[4096];
			int length;
			
			while((length = in.read(buffer)) != -1){
				out.write(buffer, 0, length);
			}
			
			in.close();
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/download")
	public void downloadDocument(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("filename") String filename) throws IOException, SQLException {

		try {
			Documents document = documentDao.getSingleDocument(filename);
			System.out.println("The file length is : " + document.getInputStream().available()+document.getContenttype());
			// ServletContext context = getServletContext();
			String mimeType = context.getMimeType(filename);
			if (mimeType == null) {
				//mimeType = "application/octet-stream";
				mimeType = document.getContenttype();
				
			}

			if(mimeType.contains("pdf")){
				filename = filename + ".pdf";
				
			}else if(mimeType.contains("word")){
				filename = filename + ".docx";
				
			}
			response.setContentType(mimeType);
			response.setContentLength(document.getInputStream().available());
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", filename);
			response.setHeader(headerKey, headerValue);

			InputStream inputStream = document.getInputStream();
			OutputStream outPutStream = response.getOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outPutStream.write(buffer, 0, bytesRead);
			}

			outPutStream.close();
			inputStream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			response.getWriter().print("IO Error: " + ex.getMessage());
		}

	}

	@RequestMapping("/admin")
	public ModelAndView adminPage(@RequestParam(value = "action", required=true) String action) {
		ModelAndView mv = new ModelAndView("admin");
		Map<String, Object> model = mv.getModel();
		model.put("action", action);
		return mv;
	}
}
