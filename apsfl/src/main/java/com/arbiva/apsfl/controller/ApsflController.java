package com.arbiva.apsfl.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.arbiva.apsfl.dto.UsersDTO;
import com.arbiva.apsfl.util.IpAddressValues;

/**
 * @author Sai Suresh reddy
 *
 */
@Controller
public class ApsflController {
	
	@Autowired
	IpAddressValues ipAddressValues;

	private static final Logger logger = Logger.getLogger(ApsflController.class);

	@RequestMapping(value = "/preLogin", method = RequestMethod.GET)
	public String preLogin(@ModelAttribute(value = "usersDTO") UsersDTO usersDTO,
			@RequestParam(value = "error", required = false) String errorMessage, Model uiModel) {

		try {
			logger.info("ApsflController :: preLogin :: Start");
			if (errorMessage != null)
				uiModel.addAttribute("error", "Failed to Login Please try again");
			logger.info("ApsflController :: preLogin :: End");
		} catch (Exception e) {
			logger.error("ApsflController :: preLogin :: " + e);
			e.printStackTrace();
		}
		return "ums.login";
	}

	@RequestMapping(value = "/changePasswordLogin", method = RequestMethod.POST)
	public String changePasswordLogin(@ModelAttribute(value = "usersDTO") UsersDTO usersDTO,
			@RequestParam(value = "error", required = false) String errorMessage, Model uiModel) {

		try {
			logger.info("ApsflController  :: changePasswordLogin :: Start");
		} catch (Exception e) {
			logger.error("ApsflController  :: changePasswordLogin :: " + e);
			e.printStackTrace();
		}
		return "ums.login";
	}

	@RequestMapping(value = "/forgotPasswordPage", method = RequestMethod.GET)
	public ModelAndView forgotPassword() {

		try {
			logger.info("ApsflController  :: forgotPassword :: Start");
		} catch (Exception e) {
			logger.error("ApsflController  :: forgotPassword :: " + e);
			e.printStackTrace();
		}
		return new ModelAndView("ums.forgotPassword");
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {

		try {
			logger.info("ApsflController  :: logout :: Start");
			HttpSession session = request.getSession(false);
			session.invalidate();
		} catch (Exception e) {
			logger.error("ApsflController  :: logout :: " + e);
			e.printStackTrace();
		}
		return "redirect:./j_spring_security_logout";
	}

	@RequestMapping(value = "/downLoadApk", method = RequestMethod.GET)
	public void downLoadApk(HttpServletRequest request, HttpServletResponse response){

		File file = null;
		file = new File(ipAddressValues.getApkPath());
		try(InputStream inputStream = new FileInputStream(file);
				BufferedInputStream bIs = new BufferedInputStream(inputStream);
				ServletOutputStream os = response.getOutputStream()){
			
			if (file != null) {
				String mimeType = "application/vnd.android.package-archive";
				response.setContentType(mimeType);
				response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
				response.setContentLength((int) file.length());
				FileCopyUtils.copy(bIs, os);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			file = null;
		}

	}
}
