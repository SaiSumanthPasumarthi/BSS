package com.arbiva.apsfl.tms.dto;

import java.io.Serializable;

public class OltSearch implements Serializable{
	
	 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private String district;
		
		private String mandal;

		public String getDistrict() {
			return district;
		}

		public void setDistrict(String district) {
			this.district = district;
		}

		public String getMandal() {
			return mandal;
		}

		public void setMandal(String mandal) {
			this.mandal = mandal;
		}

}
