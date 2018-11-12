package com.arbiva.apsfl.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arbiva.apfgc.tenant.bo.MsoRevenueShareBO;
import com.arbiva.apsfl.dto.FunctionsDTO;
import com.arbiva.apsfl.dto.RoleFunctionsDTO;
import com.arbiva.apsfl.dto.RolesDTO;
import com.arbiva.apsfl.dto.TenantsDTO;
import com.arbiva.apsfl.dto.UmsHelperDTO;
import com.arbiva.apsfl.dto.UserMenuItemsRestDTO;
import com.arbiva.apsfl.dto.UsersDTO;
import com.arbiva.apsfl.tms.serviceImpl.DemandNoteServiceImpl;
import com.arbiva.apsfl.util.ApsflHelper;
import com.arbiva.apsfl.util.IpAddressValues;

/**
 * @author Sai Suresh
 *
 */
@Controller
@SessionAttributes({ "loginID", "tenantcode", "domain", "pwd", "tenantname", "moduleNameMap", "userName", "roleName" })
public class UmsController {

	private static final Logger logger = Logger.getLogger(UmsController.class);

	RestTemplate restTemplate = new RestTemplate();

	@Autowired
	IpAddressValues ipAddressValues;
	
	@Autowired
	DemandNoteServiceImpl demandNoteServiceImpl;
	
	@RequestMapping(value = "/")
	public String home() {
		
		return "redirect:/home";
	}

	@RequestMapping(value = "/home")
	public String home(final RedirectAttributes attributes, Model model,HttpServletRequest request,HttpServletResponse res) throws IOException {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		UserMenuItemsRestDTO userMenuObj = null;
		ResponseEntity<UserMenuItemsRestDTO> response;
		HttpEntity<String> httpEntity;
		try {
			logger.info("UmsController :: home() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getUmsUserName(), ipAddressValues.getUmsPwd());

			String url = ipAddressValues.getUmsURL() + "getHeader?userName=" + userName;

			logger.info(url + "url");

			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity,UserMenuItemsRestDTO.class);

			logger.info(response + "response");

			userMenuObj = response.getBody();

			logger.info(userMenuObj.getMenuItemList().size() + "menuListgggg");
			model.addAttribute("domain", userMenuObj.getUsersDTOobj().getDomain());
			model.addAttribute("tenantcode", userMenuObj.getUsersDTOobj().getTenantCode());
			model.addAttribute("tenantname", userMenuObj.getUsersDTOobj().getTenantName());
			model.addAttribute("moduleNameMap", userMenuObj.getMenuItemList());
			model.addAttribute("userName", userMenuObj.getUsersDTOobj().getUserName());
			model.addAttribute("loginID", userMenuObj.getUsersDTOobj().getLoginID());
			model.addAttribute("roleName", userMenuObj.getUsersDTOobj().getRoleName());
			logger.info("UmsController :: home() :: End");
		} catch (Exception e) {
			logger.error("UmsController :: home() :: "+e);
			attributes.addFlashAttribute("loginValidationErrorMessage", "Failed to Login Please try again.");
		}finally {
			response = null;
			httpEntity = null;
			//res.sendRedirect("/apsfl/ViewTTList");
			if(userMenuObj.getUsersDTOobj().getLoginID().startsWith("LMO")) res.sendRedirect("/apsfl/Home");
			userMenuObj = null;
		}

		return "ums.home";

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/roleCreation")
	public ModelAndView roleCreation(@ModelAttribute(value = "rolesDTO") RolesDTO rolesDTO, BindingResult result,
			Model model, HttpServletRequest request) {
		String tenantCode = request.getSession().getAttribute("tenantcode").toString();
		ResponseEntity<ArrayList> response;
		HttpEntity<String> httpEntity;
		List<RolesDTO> rolesList;
		try {
			logger.info("UmsController :: roleCreation() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getUmsUserName(), ipAddressValues.getUmsPwd());
	
			String url = ipAddressValues.getUmsURL() + "roleCreation?tenantcode=" + tenantCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			rolesList = response.getBody();
			model.addAttribute("totalRecords", rolesList.size());
			model.addAttribute("rolesList", rolesList);
			logger.info("UmsController :: roleCreation() :: End");
			logger.info("::In Role Creation 123::");
		} catch (Exception e) {
			logger.error("UmsController :: roleCreation() :: "+e);
			e.printStackTrace();
		}finally {
			response = null;
			httpEntity = null;
			rolesList = null;
		}
		return new ModelAndView("ums.roleCreation");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/addRole", method = RequestMethod.POST)
	public ModelAndView addRole(@ModelAttribute(value = "rolesDTO") RolesDTO rolesDTO, BindingResult result,
			Model model, HttpServletRequest request) {
		
		List<RolesDTO> rolesList;
		HttpEntity<RolesDTO> httpEntity;
		ResponseEntity<String> response;
		HttpEntity<String> httpEntity1;
		ResponseEntity<ArrayList> response1;
		try {
			logger.info("UmsController :: addRole() :: Start");
			rolesDTO.setStatus(Byte.valueOf("1"));
			rolesDTO.setCreatedIPAddr(request.getRemoteAddr());
			rolesDTO.setCreatedBy(request.getSession().getAttribute("loginID").toString());
			rolesDTO.setCreatedOn(new Date());
			rolesDTO.setTenantCode(request.getSession().getAttribute("tenantcode").toString());
			rolesDTO.setModifiedOn(new Date());
			logger.info(rolesDTO + "before rolesDTO");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getUmsUserName(), ipAddressValues.getUmsPwd(), rolesDTO);
			String url = ipAddressValues.getUmsURL() + "/addRole";
			logger.info(url + "after rolesDTO");
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
			logger.info(url + "after exchange");
			String status = response.getBody();

			System.out.println("status" + status);

			if ("success".equalsIgnoreCase(status)) {
				model.addAttribute("successMsg", "Role Created Successfully");
			} else if ("failure".equalsIgnoreCase(status)) {
				model.addAttribute("errorMsg", "Role Failed to create");
			} else {
				model.addAttribute("errorMsg", "Role Already exists");
			}
			httpEntity1 = ApsflHelper.getHttpEntity(ipAddressValues.getUmsUserName(), ipAddressValues.getUmsPwd());
			String url1 = ipAddressValues.getUmsURL() + "roleCreation?tenantcode="
					+ request.getSession().getAttribute("tenantcode").toString();
			response1 = restTemplate.exchange(url1, HttpMethod.GET, httpEntity1,
					ArrayList.class);
			rolesList = response1.getBody();
			System.out.println(rolesList.size() + "sizeeeeggg");

			model.addAttribute("totalRecords", rolesList.size());
			model.addAttribute("rolesList", rolesList);
			logger.info("UmsController :: addRole() :: End");
			return new ModelAndView("ums.roleCreation");
			
		} catch (Exception e) {
			logger.error("UmsController :: addRole() :: "+e);
			e.printStackTrace();
		}finally {
			rolesList = null;
			httpEntity = null;
			response = null;
			httpEntity1 = null;
			response1 = null;
		}

		return new ModelAndView("ums.roleCreation");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/modifyRole", method = RequestMethod.POST)
	public ModelAndView modifyRole(@ModelAttribute(value = "rolesDTO") RolesDTO rolesDTO, BindingResult result,
			Model model, HttpServletRequest request) {
		
		HttpEntity<RolesDTO> httpEntity;
		ResponseEntity<String> response;
		List<RolesDTO> rolesList;
		try {
			logger.info("UmsController :: modifyRole() :: Start");
			logger.info(rolesDTO.getRowID() + "rowid" + rolesDTO.getRoleID());

			if (rolesDTO.getRowID() == null) {
				rolesDTO.setRowID("0");
			}
			rolesDTO.setModifiedIPAddr(request.getRemoteAddr());
			rolesDTO.setModifiedBy(request.getSession().getAttribute("loginID").toString());
			rolesDTO.setModifiedOn(new Date());
			rolesDTO.setTenantCode(request.getSession().getAttribute("tenantcode").toString());
			rolesDTO.setStatus(Byte.valueOf(rolesDTO.getRowID()));

			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getUmsUserName(), ipAddressValues.getUmsPwd(), rolesDTO);
			String url = ipAddressValues.getUmsURL() + "/modifyRole";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
			String status = response.getBody();

			logger.info("status" + status);
			HttpEntity<String> httpEntity1 = ApsflHelper.getHttpEntity(ipAddressValues.getUmsUserName(), ipAddressValues.getUmsPwd());
			String url1 = ipAddressValues.getUmsURL() + "roleCreation?tenantcode="+ request.getSession().getAttribute("tenantcode").toString();
			ResponseEntity<ArrayList> response1 = restTemplate.exchange(url1, HttpMethod.GET, httpEntity1, ArrayList.class);
			rolesList = response1.getBody();

			if (status.equalsIgnoreCase("success")) {

				model.addAttribute("successMsg", "Role Modified Successfully");
			} else if (status.equalsIgnoreCase("failure")) {
				model.addAttribute("errorMsg", "Role Failed to Modified");
			} else if (status.equalsIgnoreCase("NotPossible")) {
				// model.addAttribute("errorMsg", "Users Still Active in this
				// Role, So Role Cannot be Inactive ");
				model.addAttribute("errorMsg", "Users Still Mapped with this Role, So Role Cannot be Modified ");

			} else if (status.equalsIgnoreCase("Notok")) {
				// model.addAttribute("errorMsg", "Users Still Active in this
				// Role, So Role Cannot be Inactive ");
				model.addAttribute("errorMsg", "This Role is Still Mapped with some Functions, So Role Cannot be Modified ");

			} else if (status.equalsIgnoreCase("exists")) {
				model.addAttribute("errorMsg", "Role Already exists");
			}

			model.addAttribute("rolesList", rolesList);
			model.addAttribute("totalRecords", rolesList.size());
			logger.info("UmsController :: modifyRole() :: End");
		} catch (Exception e) {
			logger.error("UmsController :: modifyRole() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			rolesList = null;
		}

		return new ModelAndView("ums.roleCreation");
	}

	@RequestMapping(value = "/changePassword")
	public ModelAndView changePassword(@ModelAttribute(value = "usersDTO") UsersDTO usersDTO, BindingResult result,
			Model model, HttpServletRequest request) {
		logger.info("in change Password");
		try {
			logger.info("UmsController :: changePassword() :: Start");
		} catch (Exception e) {
			logger.error("UmsController :: changePassword() :: "+e);
			e.printStackTrace();
		}
		return new ModelAndView("ums.changePassword");
	}

	@RequestMapping(value = "/generatePassword", method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody String generatePassword() {
		System.out.println("in Generate>>>:::::");
		HttpEntity<String> httpEntity;
		ResponseEntity<String> response;
		String status = "";
		try {
			logger.info("UmsController :: generatePassword() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getUmsUserName(), ipAddressValues.getUmsPwd());
			String url = ipAddressValues.getUmsURL() + "/generatePassword";
			 response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			status = response.getBody();
			logger.info("UmsController :: generatePassword() :: End");
		} catch (Exception e) {
			logger.error("UmsController :: generatePassword() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
		return status;
	}

	@RequestMapping(value = "/forgotPwd", method = RequestMethod.POST)
	public ModelAndView forgotPwd(@ModelAttribute(value = "usersDTO") UsersDTO usersDTO, BindingResult result, Model model) {
		
		HttpEntity<String> httpEntity;
		ResponseEntity<UmsHelperDTO> response1;
		UmsHelperDTO umsHelperDTO;
		try {
			logger.info("UmsController :: forgotPwd() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getUmsUserName(), ipAddressValues.getUmsPwd());
			String url = ipAddressValues.getUmsURL() + "forgotPwd?loginID=" + usersDTO.getLoginID();
			response1 = restTemplate.exchange(url, HttpMethod.GET, httpEntity, UmsHelperDTO.class);
			umsHelperDTO = response1.getBody();
	
			String msg = umsHelperDTO.getMessage();

			logger.info(msg + "msggggg");
			if (msg == null) {
				msg = "Please Enter Correct Login-Id";
				model.addAttribute("errormsg", msg);
			} else if (msg.equalsIgnoreCase("success")) {
				msg = "Password Sent to Registered Email ID";
				model.addAttribute("msg", msg);
			} else {
				msg = "Password Failed to send to Email ID";
				model.addAttribute("errormsg", msg);
			}
			logger.info("UmsController :: forgotPwd() :: End");
		} catch (Exception e) {
			logger.error("UmsController :: forgotPwd() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response1 = null;
			umsHelperDTO = null;
		}
		return new ModelAndView("ums.forgotPassword");
	}

	@RequestMapping(value = "/updatePassword")
	public ModelAndView updatePassword(@ModelAttribute(value = "usersDTO") UsersDTO usersDTO, BindingResult result,
			Model model, HttpServletRequest request) {
		logger.info("::In Change Password::");
		HttpEntity<UsersDTO> httpEntity;
		ResponseEntity<String> response;
		try {
			logger.info("UmsController :: updatePassword() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getUmsUserName(), ipAddressValues.getUmsPwd(), usersDTO);
			String url = ipAddressValues.getUmsURL() + "/updatePassword";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
			String status = response.getBody();
	
			if ("success".equalsIgnoreCase(status)) {
				model.addAttribute("successMsg", "Password Changed Successfully");
			} else if ("success".equalsIgnoreCase(status)) {
				model.addAttribute("errorMsg", "Password Failed to Change");
			}
			logger.info("UmsController :: updatePassword() :: End");
		} catch (Exception e) {
			logger.error("UmsController :: updatePassword() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
		return new ModelAndView("ums.changePassword");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getTenants", method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody List<TenantsDTO> getTenants(@RequestParam("roleID") String roleID, HttpServletRequest request,
			Model model) {
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response1;
		List<TenantsDTO> tenantsList = null;
		try {
			logger.info("UmsController :: getTenants() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getUmsUserName(), ipAddressValues.getUmsPwd());
			String url = ipAddressValues.getUmsURL() + "getTenants?roleID=" + roleID;
			response1 = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			tenantsList = response1.getBody();
			logger.info("UmsController :: getTenants() :: End");
		} catch (Exception e) {
			logger.error("UmsController :: getTenants() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response1 = null;
		}
		return tenantsList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getRMUsers", method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody List<UsersDTO> checkOldPassword(HttpServletRequest request) {
		
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response1;
		List<UsersDTO> rmUsersList = null;
		try {
			logger.info("UmsController :: checkOldPassword() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getUmsUserName(), ipAddressValues.getUmsPwd());
			String url = ipAddressValues.getUmsURL() + "getRMUsers?tenantcode=" + request.getSession().getAttribute("tenantcode").toString();
			response1 = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			rmUsersList = response1.getBody();
			logger.info("UmsController :: checkOldPassword() :: End");
		} catch (Exception e) {
			logger.error("UmsController :: checkOldPassword() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response1 = null;
		}
		return rmUsersList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/userCreation")
	public ModelAndView userCreation(@ModelAttribute(value = "usersDTO") UsersDTO usersDTO, HttpServletRequest request,
			Model model) {

		HttpEntity<String> httpEntity;
		ResponseEntity<UmsHelperDTO> response1;
		UmsHelperDTO umsHelperDTO;
		List<RolesDTO> roleNameList;
		Iterator<RolesDTO> it;
		RolesDTO rolesDTOObj ;
		Map rolesListMap = new HashMap();
		try {
			logger.info("UmsController :: userCreation() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getUmsUserName(), ipAddressValues.getUmsPwd());
			String url = ipAddressValues.getUmsURL() + "userCreation?tenantcode=" + request.getSession().getAttribute("tenantcode").toString();
			response1 = restTemplate.exchange(url, HttpMethod.GET, httpEntity, UmsHelperDTO.class);
			umsHelperDTO = response1.getBody();

			roleNameList = (List<RolesDTO>) umsHelperDTO.getRolesList();
	
			it = roleNameList.iterator();
	
			while (it.hasNext()) {

			rolesDTOObj = (RolesDTO) it.next();

			rolesListMap.put(rolesDTOObj.getRoleName(), rolesDTOObj.getRoleName());

			logger.info(rolesDTOObj.getRoleName() + "rolenamegfgfg");
		    }
			model.addAttribute("rolesListMap", rolesListMap);
			model.addAttribute("totalRecords", umsHelperDTO.getUsersList().size());
			model.addAttribute("usersList", umsHelperDTO.getUsersList());
			logger.info("UmsController :: userCreation() :: End");
		}catch (Exception e) {
			logger.error("UmsController :: userCreation() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response1 = null;
			umsHelperDTO = null;
			roleNameList = null;
			it = null;
			rolesDTOObj = null;
			rolesListMap = null;
		}
		return new ModelAndView("ums.userCreation");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/saveUser")
	public ModelAndView saveUser(@ModelAttribute(value = "usersDTO") UsersDTO usersDTO, BindingResult result,
			Model model, HttpServletRequest request) {

		usersDTO.setCreatedBy(request.getSession().getAttribute("loginID").toString());
		usersDTO.setModifiedBy(request.getSession().getAttribute("loginID").toString());
		usersDTO.setLoginID(usersDTO.getLoginID());
		usersDTO.setPwdResetFlag(Byte.valueOf("1"));
		usersDTO.setUserName(usersDTO.getUserName());
		usersDTO.setStatus(usersDTO.getStatusHiddenID());
		usersDTO.setStatus(usersDTO.getStatus() == null ? 0 : usersDTO.getStatus());

		logger.info(usersDTO.getRoleName() + "rolenameeee");
		HttpEntity<UsersDTO> httpEntity;
		ResponseEntity<UmsHelperDTO> response1;
		UmsHelperDTO umsHelperDTO ;
		List<RolesDTO> roleNameList ;
		Map rolesListMap ;
		Iterator<RolesDTO> it ;
		RolesDTO rolesDTOObj;
		try {
			logger.info("UmsController :: saveUser() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getUmsUserName(), ipAddressValues.getUmsPwd(), usersDTO);
			String url = ipAddressValues.getUmsURL() + "saveUser";
			response1 = restTemplate.exchange(url, HttpMethod.POST, httpEntity, UmsHelperDTO.class);
			umsHelperDTO = response1.getBody();
	
			logger.info(umsHelperDTO.getMessage());
	
			if ("successmail".equalsIgnoreCase(umsHelperDTO.getMessage())) {
				model.addAttribute("successMsg", "User Created Successfully and Password Sent to Registered Email ID");
			}
	
			else if ("failuremail".equalsIgnoreCase(umsHelperDTO.getMessage())) {
				model.addAttribute("errorMsg", "User Created Successfully and Password Failed to Sent to Registered Email ID");
			}
	
			else if ("exists".equalsIgnoreCase(umsHelperDTO.getMessage())) {
	
				model.addAttribute("errorMsg", "User Already Exists with Login-ID ");
			}
	
			else if ("failure".equalsIgnoreCase(umsHelperDTO.getMessage())) {
				model.addAttribute("errorMsg", "User Failed to create");
			}
	
			roleNameList = (List<RolesDTO>) umsHelperDTO.getRolesList();
	
			rolesListMap = new HashMap();
	
			it = roleNameList.iterator();
	
			while (it.hasNext()) {
	
				rolesDTOObj = (RolesDTO) it.next();
	
				rolesListMap.put(rolesDTOObj.getRoleName(), rolesDTOObj.getRoleName());
	
				logger.info(rolesDTOObj.getRoleName() + "rolenamegfgfg");
			}
	
			model.addAttribute("rolesListMap", rolesListMap);
			model.addAttribute("updateMsg", null);
			model.addAttribute("updateErrMsg", null);
			// model.addAttribute("rolesListMap", umsHelperDTO.getRolesList());
			model.addAttribute("totalRecords", umsHelperDTO.getUsersList().size());
			model.addAttribute("usersList", umsHelperDTO.getUsersList());
			// model.addAttribute("errorMsg", umsHelperDTO.getMessage());
			logger.info("UmsController :: saveUser() :: End");
		}catch (Exception e) {
			logger.error("UmsController :: saveUser() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response1 = null;
			umsHelperDTO = null;
			roleNameList = null;
			it = null;
			rolesDTOObj = null;
			rolesListMap = null;
		}
		return new ModelAndView("ums.userCreation");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/updateUser")
	public ModelAndView updateUser(@ModelAttribute(value = "usersDTO") UsersDTO usersDTO, BindingResult result,
			Model model, HttpServletRequest request) {
		logger.info("::In Update User::>>>" + usersDTO.getRoleName());
		usersDTO.setLoginID(usersDTO.getLoginID());
		usersDTO.setModifiedBy(request.getSession().getAttribute("loginID").toString());
		usersDTO.setModifiedOn(new Date());
		usersDTO.setStatus(usersDTO.getStatus() == null ? 0 : usersDTO.getStatus());
		HttpEntity<UsersDTO> httpEntity;
		ResponseEntity<UmsHelperDTO> response1;
		UmsHelperDTO umsHelperDTO ;
		List<RolesDTO> roleNameList ;
		Map rolesListMap ;
		Iterator<RolesDTO> it ;
		RolesDTO rolesDTOObj;
		try {
			logger.info("UmsController :: updateUser() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getUmsUserName(), ipAddressValues.getUmsPwd(), usersDTO);
			String url = ipAddressValues.getUmsURL() + "updateUser";
			response1 = restTemplate.exchange(url, HttpMethod.POST, httpEntity, UmsHelperDTO.class);
			umsHelperDTO = response1.getBody();
	
			roleNameList = (List<RolesDTO>) umsHelperDTO.getRolesList();
	
			rolesListMap = new HashMap();
	
			it = roleNameList.iterator();
	
			while (it.hasNext()) {
	
				rolesDTOObj = (RolesDTO) it.next();
	
				rolesListMap.put(rolesDTOObj.getRoleName(), rolesDTOObj.getRoleName());
	
				logger.info(rolesDTOObj.getRoleName() + "rolenamegfgfg");
			}
	
			model.addAttribute("rolesListMap", rolesListMap);
	
			if (umsHelperDTO.getMessage().equalsIgnoreCase("success")) {
	
				model.addAttribute("successMsg", "User Updated Successfully");
			} else {
				model.addAttribute("errorMsg", "User Failed to Update");
			}
	
			// model.addAttribute("rolesListMap", umsHelperDTO.getRolesList());
			model.addAttribute("totalRecords", umsHelperDTO.getUsersList().size());
			model.addAttribute("usersList", umsHelperDTO.getUsersList());
			logger.info("UmsController :: updateUser() :: End");
		}catch (Exception e) {
			logger.error("UmsController :: updateUser() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response1 = null;
			umsHelperDTO = null;
			roleNameList = null;
			it = null;
			rolesDTOObj = null;
			rolesListMap = null;
		}
			return new ModelAndView("ums.userCreation");
	}

	@RequestMapping(value = "/searchUser")
	public ModelAndView searchUser(@ModelAttribute(value = "usersDTO") UsersDTO usersDTO, BindingResult result,
			Model model, HttpServletRequest request) {
		
		usersDTO.setStatus(usersDTO.getStatus() == null ? 0 : usersDTO.getStatus());
		usersDTO.setLoginID(request.getSession().getAttribute("loginID").toString());
		usersDTO.setTenantCode(request.getSession().getAttribute("tenantcode").toString());
		ResponseEntity<UmsHelperDTO> response1;
		HttpEntity<UsersDTO> httpEntity;
		UmsHelperDTO umsHelperDTO;
		try {
			logger.info("UmsController :: searchUser() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getUmsUserName(), ipAddressValues.getUmsPwd(), usersDTO);
			String url = ipAddressValues.getUmsURL() + "searchUser";
			response1 = restTemplate.exchange(url, HttpMethod.POST, httpEntity, UmsHelperDTO.class);
			umsHelperDTO = response1.getBody();
	
			model.addAttribute("rolesListMap", umsHelperDTO.getRolesList());
			model.addAttribute("totalRecords", umsHelperDTO.getUsersList().size());
			model.addAttribute("usersList", umsHelperDTO.getUsersList());
			usersDTO = null;
			logger.info("UmsController :: searchUser() :: End");
		}catch (Exception e) {
			logger.error("UmsController :: searchUser() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response1 = null;
			umsHelperDTO = null;
		}
		return new ModelAndView("ums.userCreation");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/roleServiceMapping")
	public ModelAndView roleServiceMappingAction(@ModelAttribute(value = "rolesDTO") RolesDTO rolesDTO,
			@ModelAttribute(value = "functionsDTO") FunctionsDTO functionsDTO, Model model,
			HttpServletRequest request) {
		String tenantCode = request.getSession().getAttribute("tenantcode").toString();
		String domain = request.getSession().getAttribute("domain").toString();
		ResponseEntity<UmsHelperDTO> response1;
		HttpEntity<String> httpEntity;
		UmsHelperDTO umsHelperDTO;
		List<RolesDTO> roleNameList;
		List rolesNameList;
		Iterator<RolesDTO> it;
		RolesDTO rolesDTOObj;
		try {
			logger.info("UmsController :: searchUser() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getUmsUserName(), ipAddressValues.getUmsPwd());
			String url = ipAddressValues.getUmsURL() + "roleServiceMapping?tenantCode=" + tenantCode + "&domain=" + domain;
			response1 = restTemplate.exchange(url, HttpMethod.GET, httpEntity, UmsHelperDTO.class);
			umsHelperDTO = response1.getBody();
	
			roleNameList = (List<RolesDTO>) umsHelperDTO.getRolesList();
			rolesNameList = new ArrayList();
	
			it = roleNameList.iterator();
	
			while (it.hasNext()) {
	
				rolesDTOObj = (RolesDTO) it.next();
	
				rolesNameList.add(rolesDTOObj.getRoleName());
	
			}
	
			model.addAttribute("rolesListMap", rolesNameList);
			model.addAttribute("functionsList", umsHelperDTO.getListObj());
		}catch (Exception e) {
			logger.error("UmsController :: searchUser() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response1 = null;
			umsHelperDTO = null;
			roleNameList = null;
			it = null;
			rolesDTOObj = null;
			rolesNameList = null;
		}
			return new ModelAndView("ums.roleServiceMapping");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/addRoleServices.do")
	public ModelAndView addRoleServices(@ModelAttribute(value = "rolesDTO") RolesDTO rolesDTO,
			@ModelAttribute(value = "functionsDTO") FunctionsDTO functionsDTO, BindingResult result, Model model,
			HttpServletRequest request) {
		functionsDTO.setTenantCode(request.getSession().getAttribute("tenantcode").toString());
		functionsDTO.setDomain(request.getSession().getAttribute("domain").toString());
		functionsDTO.setUserName(request.getSession().getAttribute("userName").toString());
		ResponseEntity<UmsHelperDTO> response1;
		HttpEntity<FunctionsDTO> httpEntity;
		UmsHelperDTO umsHelperDTO;
		List<RolesDTO> roleNameList;
		List rolesNameList;
		Iterator<RolesDTO> it;
		RolesDTO rolesDTOObj;
		try {
			logger.info("UmsController :: addRoleServices() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getUmsUserName(), ipAddressValues.getUmsPwd(), functionsDTO);
			String url = ipAddressValues.getUmsURL() + "addRoleServices";
			response1 = restTemplate.exchange(url, HttpMethod.POST, httpEntity, UmsHelperDTO.class);
			umsHelperDTO = response1.getBody();
	
			roleNameList = (List<RolesDTO>) umsHelperDTO.getRolesList();
	
			rolesNameList = new ArrayList();
	
			it = roleNameList.iterator();
	
			while (it.hasNext()) {
	
				rolesDTOObj = (RolesDTO) it.next();
	
				rolesNameList.add(rolesDTOObj.getRoleName());
			}
	
			model.addAttribute("rolesListMap", rolesNameList);
	
			if ("success".equalsIgnoreCase(umsHelperDTO.getMessage())) {
				model.addAttribute("successMsg", "Role Service Mapped Successfully");
			} else {
				model.addAttribute("errorMsg", "Role Service Mapped Failed");
			}
	
			model.addAttribute("functionsList", umsHelperDTO.getListObj());
			// model.addAttribute("rolesListMap", umsHelperDTO.getRolesList());
			logger.info("UmsController :: addRoleServices() :: End");
		}catch (Exception e) {
			logger.error("UmsController :: addRoleServices() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response1 = null;
			umsHelperDTO = null;
			roleNameList = null;
			it = null;
			rolesDTOObj = null;
			rolesNameList = null;
		}
		return new ModelAndView("ums.roleServiceMapping");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getRoleServicesList", method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody List<RoleFunctionsDTO> getRoleServicesList(@RequestParam("roleID") String roleID,
			HttpServletRequest request) {

		logger.info("In getRoleServicesList" + roleID);
		String roleName = request.getSession().getAttribute("roleName").toString();
		String tenantCode = request.getSession().getAttribute("tenantcode").toString();
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response1;
		List<RoleFunctionsDTO> roleServicesList = null;
		try {
			logger.info("UmsController :: getRoleServicesList() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getUmsUserName(), ipAddressValues.getUmsPwd());
			String url = ipAddressValues.getUmsURL() + "getRoleServicesList?roleID=" + roleID + "&roleName=" + roleName+ "&tenantcode=" +tenantCode ;
			response1 = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			roleServicesList = response1.getBody();
			logger.info(roleServicesList.size() + "size");
			
		}catch (Exception e) {
			logger.error("UmsController :: getRoleServicesList() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response1 = null;
		}
		return roleServicesList;
	}

	@RequestMapping(value = "/editUserDetails", method = RequestMethod.GET)
	public @ResponseBody UsersDTO editUserDetails(@RequestParam(value = "loginID", required = false) String loginId) {
		logger.info("Inside Edit USER::::");
		HttpEntity<String> httpEntity;
		ResponseEntity<UsersDTO> response1;
		UsersDTO usersDTO = null;
		try {
			logger.info("UmsController :: editUserDetails() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getUmsUserName(), ipAddressValues.getUmsPwd());
			String url = ipAddressValues.getUmsURL() + "editUserDetails?loginID=" + loginId;
			response1 = restTemplate.exchange(url, HttpMethod.GET, httpEntity, UsersDTO.class);
			usersDTO = response1.getBody();
			logger.info("UmsController :: editUserDetails() :: End");
		}catch (Exception e) {
			logger.error("UmsController :: editUserDetails() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response1 = null;
		}
		return usersDTO;
	}
	
	//Revenue of LoginMSO
	
		@RequestMapping(value = "/LoginLmoTenantRevenueShare", method = RequestMethod.GET)
		public String getRevenueShareDetailsOfLoginMso(@RequestParam(value = "year",required=false) String year, @RequestParam(value = "month",required=false) String month, HttpServletRequest request,
				Model model) {
			try{
			logger.info("ComsController :: getLMORevenueShareDetailsOfLoginTenant() :: START");
			String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			String tenantType = (String) request.getSession(false).getAttribute("domain");
			String totalPreviousBalance="";
			    		
			List<MsoRevenueShareBO> revnList = new ArrayList<>();
			revnList = demandNoteServiceImpl.getRevenueShareDetailsOfLoginMso(year, month, tenantCode);
			totalPreviousBalance=demandNoteServiceImpl.getTotalPreviousBalance(year, month, tenantCode);
			model.addAttribute("revnList", revnList);
			model.addAttribute("tenantCode", tenantCode);
			model.addAttribute("tenantType", tenantType);
			model.addAttribute("year1", year);
			model.addAttribute("month1", month);
			model.addAttribute("totalPreviousBalance", totalPreviousBalance);
			logger.info("ComsController :: getLMORevenueShareDetailsOfLoginTenant() :: END");
			
			} catch(Exception e){
				logger.info("ComsController :: getLmoRevenueShareDetailsOfLoginTenant() :: " + e);
				e.printStackTrace();
				
			}

			return "lmoRevenueShare";
		}
		
		// Revenue Share report
		@RequestMapping(value = "/downloadTenantRevenueDetails", method = RequestMethod.GET)
		public void downloadTenantRevenueShareDetails(@RequestParam(value = "download", required = false) boolean download,
				@RequestParam(value = "year") String year,
				@RequestParam(value = "month") String month,
				@RequestParam(value = "tenantCode") String tenantCode,
				HttpServletRequest request, HttpServletResponse response) {
			HSSFWorkbook workbook = null;
			try (ServletOutputStream out = response.getOutputStream()) {
				workbook = demandNoteServiceImpl.getTenantRevenueShareDetailsExcel(year, month, tenantCode);
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=MSO Wise LMO Report.xls");
				workbook.write(out);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				workbook = null;
			}
		}

}
