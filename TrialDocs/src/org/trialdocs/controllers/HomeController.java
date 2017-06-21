package org.trialdocs.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

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
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.trialdocs.model.Documents;
import org.trialdocs.model.DocumentsDao;
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
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	@RequestMapping("/")
	public String showHome() {
		return "login";
	}

	@RequestMapping("/home")
	public String showLogin() {
		return "home";
	}

	// @RequestParam("name") String name,@RequestParam("email") String
	// email,@RequestParam("password") String password,@RequestParam("role")
	// String role

	@RequestMapping(value = "/usercreated", method = RequestMethod.POST)
	public String createUser(Model model, Users users) {
		System.out.println(users);
		userdao.createUser(users);
		return "login";
	}

	@RequestMapping(value = "/upload")
	public String authorizeUser() {

		return "upload";

	}

	@RequestMapping(value = "/mainpage", method = RequestMethod.POST)
	public String postMainPage(Model model, @RequestParam("email") String email,
			@RequestParam("password") String password) {

		if (userdao.getUser(email, password) != null) {
			return "mainpage";
		} else {
			return "login";
		}

	}

	@RequestMapping(value = "/connect", method = RequestMethod.POST)
	public String uploadDocument(HttpServletRequest request, HttpSession session) throws IOException, ServletException {

		InputStream inputStream = null;
		Part filePart = request.getPart("file");
		if (filePart != null) {
			System.out.println(filePart.getName());
			System.out.println(filePart.getSize());
			System.out.println(filePart.getContentType());
			inputStream = filePart.getInputStream();

		}

		Documents docs = new Documents("k@k.com", inputStream, "filename");
		documentDao.createDocument(docs);
		session.setAttribute("success", "Succesfully uploaded the data in to MySql");
		return "mainpage";
	}

	@RequestMapping("/download")
	public void downloadDocument(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("filename") String filename) throws IOException, SQLException {

		try {
			Documents document = documentDao.getSingleDocument(filename);
			System.out.println("The file length is : " + document.getInputStream().available());
			//ServletContext context = getServletContext();
			String mimeType = context.getMimeType(filename);
			if (mimeType == null) {
				mimeType = "application/octet-stream";
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
}
