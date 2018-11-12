package com.arbiva.apsfl.tms.dto;

import java.io.IOException;

public class TenantValidation {
	
	static String status = "false";
	
	public static String tenantPageValidation(TenantVO tenantVO) throws IOException {

		if ((!tenantVO.getName().isEmpty()  && !tenantVO.getTenantTypeLov().isEmpty() 
				&& !tenantVO.getTenantCode().isEmpty() 
				&& tenantVO.getName().length()<=100
				&& tenantVO.getTenantCode().length()<=30
				)) {
			status = "true";
			return status;

		} else {
			return status;
		}

	}
	
	

}
