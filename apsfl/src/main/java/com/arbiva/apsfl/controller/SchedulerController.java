package com.arbiva.apsfl.controller;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.arbiva.apsfl.dto.MailDTO;
import com.arbiva.apsfl.tms.model.EmailMaster;
import com.arbiva.apsfl.tms.model.EmailSendDetails;
import com.arbiva.apsfl.tms.serviceImpl.DemandNoteServiceImpl;
import com.arbiva.apsfl.util.ApsflHelper;
import com.arbiva.apsfl.util.IpAddressValues;


@RestController
@RequestMapping(value="/scheduler")
public class SchedulerController {

	private static final Logger LOGGER = Logger.getLogger(SchedulerController.class);
	
	@Autowired
	DemandNoteServiceImpl demandNoteServiceImpl;
	
	@Autowired
	IpAddressValues ipAddressValues;
	
	@Value("${CAF_REPORT_NAME}")
	private String cafReportName;
	
	@Value("${DISTRICT_REPORT_NAME}")
	private String districtReportName;
	
	@Value("${MSO_REPORT_NAME}")
	private String msoReportName;

	@Value("${district_Wise_Caf_ReportName}")
	private String districtWiseCafReportName;
	
	@Value("${PON_WISE_REPORT}")
	private String ponWiseReport;

	RestTemplate restTemplate = new RestTemplate();
	
	@RequestMapping(value="/cafWiseDemandReport")
	public String cafWiseDemand() {
		HttpEntity<MailDTO> httpEntity = null;;
		ResponseEntity<String> response = null;
		List<EmailMaster> emailList = null;
		MailDTO mailDto = null;
		HSSFWorkbook workbook = null;
		try(ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			workbook = demandNoteServiceImpl.getCafWiseExcelFile();
			emailList = demandNoteServiceImpl.getEamilOfCafWiseReport(cafReportName);
			workbook.write(bos);
			for(EmailMaster emailMast : emailList){
				mailDto = new MailDTO();
				mailDto.setTo(emailMast.getEmailid());
				mailDto.setFile(bos.toByteArray());
				mailDto.setMsg(emailMast.getMsg());
				mailDto.setSubject(emailMast.getSubj());
				mailDto.setFileName("CAF REPORT.xls");
				httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getUmsUserName(), ipAddressValues.getUmsPwd(),mailDto);
				String url = ipAddressValues.getUmsURL()+"sendMailWithAttachment";
				response = restTemplate.exchange(url, HttpMethod.POST, httpEntity,String.class);
				
				EmailSendDetails emailSendDetails = new EmailSendDetails();
				emailSendDetails.setEmailid(emailMast.getEmailid());
				emailSendDetails.setRptname(cafReportName);
				emailSendDetails.setSenttime(Calendar.getInstance());
				emailSendDetails.setStatus(response.getBody().substring(0, 1));
				demandNoteServiceImpl.save(emailSendDetails);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			LOGGER.error(e);
		}
		
		return "Success";
	}
	
	@RequestMapping(value="/ttEscalation")
	public void caf() {
		System.out.println("Raghavendra");
	}
	
	@RequestMapping(value="/districtWiseDemandReport")
	public String districtWiseDemand() {
		HttpEntity<MailDTO> httpEntity = null;;
		ResponseEntity<String> response = null;
		List<EmailMaster> emailList = null;
		MailDTO mailDto = null;
		HSSFWorkbook workbook = null;
		try(ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			workbook = demandNoteServiceImpl.getDistrictWiseExcelFile();
			emailList = demandNoteServiceImpl.getEamilOfCafWiseReport(districtReportName);
			workbook.write(bos);
			for(EmailMaster emailMast : emailList){
				mailDto = new MailDTO();
				mailDto.setTo(emailMast.getEmailid());
				mailDto.setFile(bos.toByteArray());
				mailDto.setMsg(emailMast.getMsg());
				mailDto.setSubject(emailMast.getSubj());
				mailDto.setFileName("District REPORT.xls");
				httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getUmsUserName(), ipAddressValues.getUmsPwd(),mailDto);
				String url = ipAddressValues.getUmsURL()+"sendMailWithAttachment";
				response = restTemplate.exchange(url, HttpMethod.POST, httpEntity,String.class);
				
				EmailSendDetails emailSendDetails = new EmailSendDetails();
				emailSendDetails.setEmailid(emailMast.getEmailid());
				emailSendDetails.setRptname(districtReportName);
				emailSendDetails.setSenttime(Calendar.getInstance());
				emailSendDetails.setStatus(response.getBody().substring(0, 1));
				demandNoteServiceImpl.save(emailSendDetails);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			LOGGER.error(e);
		}
		return "Success";
	}
	
	@RequestMapping(value="/mSOWiseDemandReport")
	public String  MSOWiseDemand() {
		HttpEntity<MailDTO> httpEntity = null;;
		ResponseEntity<String> response = null;
		List<EmailMaster> emailList = null;
		MailDTO mailDto = null;
		HSSFWorkbook workbook = null;
		try(ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			workbook = demandNoteServiceImpl.getMsoWiseExcelFile();
			emailList = demandNoteServiceImpl.getEamilOfCafWiseReport(msoReportName);
			workbook.write(bos);
			for(EmailMaster emailMast : emailList){
				mailDto = new MailDTO();
				mailDto.setTo(emailMast.getEmailid());
				mailDto.setFile(bos.toByteArray());
				mailDto.setMsg(emailMast.getMsg());
				mailDto.setSubject(emailMast.getSubj());
				mailDto.setFileName("MSO REPORT.xls");
				httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getUmsUserName(), ipAddressValues.getUmsPwd(),mailDto);
				String url = ipAddressValues.getUmsURL()+"sendMailWithAttachment";
				response = restTemplate.exchange(url, HttpMethod.POST, httpEntity,String.class);
				
				EmailSendDetails emailSendDetails = new EmailSendDetails();
				emailSendDetails.setEmailid(emailMast.getEmailid());
				emailSendDetails.setRptname(msoReportName);
				emailSendDetails.setSenttime(Calendar.getInstance());
				emailSendDetails.setStatus(response.getBody().substring(0, 1));
				demandNoteServiceImpl.save(emailSendDetails);
			}
		} catch (Exception e) {
			LOGGER.error("The Exception is SchedulerController :: MSOWiseDemand" +e);
			e.printStackTrace();
		}
		return "Success";
	}

	@RequestMapping(value = "/districtWiseCafListEmail")
	public String districtWiseCafListEmail() {
		HttpEntity<MailDTO> httpEntity = null;
		ResponseEntity<String> response = null;
		List<EmailMaster> emailList = null;
		MailDTO mailDto = null;
		HSSFWorkbook workbook = null;
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			workbook = demandNoteServiceImpl.getDistrictWiseCafExcelFile();
			emailList = demandNoteServiceImpl.getEamilOfCafWiseReport(districtWiseCafReportName);
			workbook.write(bos);
			for (EmailMaster emailMast : emailList) {
				mailDto = new MailDTO();
				mailDto.setTo(emailMast.getEmailid());
				mailDto.setFile(bos.toByteArray());
				mailDto.setMsg(emailMast.getMsg());
				mailDto.setSubject(emailMast.getSubj());
				mailDto.setFileName("DISTRICT WISE CAF REPORT.xls");
				httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getUmsUserName(), ipAddressValues.getUmsPwd(), mailDto);
				String url = ipAddressValues.getUmsURL() + "sendMailWithAttachment";
				response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);

				EmailSendDetails emailSendDetails = new EmailSendDetails();
				emailSendDetails.setEmailid(emailMast.getEmailid());
				emailSendDetails.setRptname(districtWiseCafReportName);
				emailSendDetails.setSenttime(Calendar.getInstance());
				emailSendDetails.setStatus(response.getBody().substring(0, 1));
				demandNoteServiceImpl.save(emailSendDetails);
			}
		} catch (Exception e) {
			LOGGER.error("The Exception is SchedulerController :: DistrictWiseCafListEmail" +e);
			e.printStackTrace();
			LOGGER.error(e);
		}
		return "Success";
	}
	
	@RequestMapping(value="/emailPonReport")
	public String  emailPONReport() {
		HttpEntity<MailDTO> httpEntity = null;;
		ResponseEntity<String> response = null;
		List<EmailMaster> emailList = null;
		MailDTO mailDto = null;
		HSSFWorkbook workbook = null;
		try(ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			workbook = demandNoteServiceImpl.ponReportExcelFile();
			emailList = demandNoteServiceImpl.getEamilOfCafWiseReport(ponWiseReport);
			workbook.write(bos);
			for(EmailMaster emailMast : emailList){
				mailDto = new MailDTO();
				mailDto.setTo(emailMast.getEmailid());
				mailDto.setFile(bos.toByteArray());
				mailDto.setMsg(emailMast.getMsg());
				mailDto.setSubject(emailMast.getSubj());
				mailDto.setFileName("PON REPORT.xls");
				
				httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getUmsUserName(), ipAddressValues.getUmsPwd(),mailDto);
				String url = ipAddressValues.getUmsURL()+"sendMailWithAttachment";
				response = restTemplate.exchange(url, HttpMethod.POST, httpEntity,String.class);
				
				EmailSendDetails emailSendDetails = new EmailSendDetails();
				emailSendDetails.setEmailid(emailMast.getEmailid());
				emailSendDetails.setRptname(ponWiseReport);
				emailSendDetails.setSenttime(Calendar.getInstance());
				emailSendDetails.setStatus(response.getBody().substring(0, 1));
				demandNoteServiceImpl.save(emailSendDetails);
			}
		} catch (Exception e) {
			LOGGER.error("The Exception is SchedulerController :: PON Wise Report" +e);
			e.printStackTrace();
		}
		return "Success";
	}
}
