/**
 * 
 */
package com.arbiva.apsfl.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class DownloadFile extends HttpServlet {

	/**
	 * @author Arbiva
	 *
	 */
	private static final Logger logger = Logger.getLogger(DownloadFile.class);

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
     
		String fileName = request.getParameter("path");
		if (!fileName.equalsIgnoreCase("")) {
			File file = new File(fileName);
			ServletContext ctx = getServletContext();
			String mimeType = null;
			byte[] bufferData = null;
			try(InputStream fis = new FileInputStream(file);
					ServletOutputStream os = response.getOutputStream()) {
				
				logger.info("DownloadFile :: doGet() :: Start");
				
				mimeType = ctx.getMimeType(file.getAbsolutePath());
				response.setContentType(mimeType != null ? mimeType : "application/octet-stream");
				response.setContentLength((int) file.length());
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			

				bufferData = new byte[4096];
				int read = 0;
				while ((read = fis.read(bufferData)) != -1) {
					os.write(bufferData, 0, read);
				}
				logger.info("DownloadFile :: doGet() :: End");
			} catch (Exception e) {
				logger.error("DownloadFile :: doGet() :: " + e);
			} finally {
				file = null;
				ctx = null;
				mimeType = null;
				bufferData = null;
			}
		}
	}
}
