package com.revature.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.http.HttpRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.ReceiptRequest;
import com.revature.repository.ERSReimbursementsDao;
import com.revature.repository.ERSReimbursementsDaoImpl;

public class ReceiptsController {
	
	ObjectMapper om = new ObjectMapper(); 
	ERSReimbursementsDao ersReimbursementDao = new ERSReimbursementsDaoImpl();
	byte[] receiptArray;
	
	public void handleReceiptRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		int role_id;
		
		if(req.getSession()==null) {
			res.sendError(401);
		}
		
		else if((int)req.getSession().getAttribute("role_id")==2) {
			 role_id = (int)req.getSession().getAttribute("role_id");
			 ReceiptRequest receiptRequest = om.readValue(req.getInputStream(), ReceiptRequest.class);
			 if(receiptRequest.getReimb_owner_id()!=(int)req.getSession().getAttribute("userid")) {
				 res.sendError(403);
			 }
			 else {
				receiptArray = ersReimbursementDao.getReceipt(receiptRequest.getReimb_id());
			
				InputStream is = new ByteArrayInputStream(receiptArray);
				Tika tika = new Tika();
				MediaType mediaType = tika.getDetector().detect(is, new Metadata());
				TikaConfig config = TikaConfig.getDefaultConfig();
				MimeType mimeType = null;
				String extension = "";
				try {
					mimeType = config.getMimeRepository().forName(mediaType.toString());
				}
				catch(MimeTypeException e) {
					e.printStackTrace();
				}
				if(mimeType!=null) {
					extension = mimeType.getExtension();
				}
					
			
				ServletOutputStream os = res.getOutputStream();
					
				res.setContentType(mediaType.toString());
				res.setHeader("Content-Disposition", "attachment; filename=receipt_download"+extension);
				res.getOutputStream().write(receiptArray);
				System.out.println(receiptRequest.getReimb_id());
				System.out.println(receiptRequest.getReimb_owner_id());
				res.setStatus(200);
			 }
			 
		}
		else if((int)req.getSession().getAttribute("role_id")==1) {
			ReceiptRequest receiptRequest = om.readValue(req.getInputStream(), ReceiptRequest.class);
			receiptArray = ersReimbursementDao.getReceipt(receiptRequest.getReimb_id());
		
			
			InputStream is = new ByteArrayInputStream(receiptArray);
			Tika tika = new Tika();
			MediaType mediaType = tika.getDetector().detect(is, new Metadata());
			TikaConfig config = TikaConfig.getDefaultConfig();
			MimeType mimeType = null;
			String extension = "";
			try {
				mimeType = config.getMimeRepository().forName(mediaType.toString());
			}
			catch(MimeTypeException e) {
				e.printStackTrace();
			}
			if(mimeType!=null) {
				extension = mimeType.getExtension();
			}
			
	
			ServletOutputStream os = res.getOutputStream();
			
			res.setContentType(mediaType.toString());
			res.setHeader("Content-Disposition", "attachment; filename=receipt_download"+extension);
			res.getOutputStream().write(receiptArray);
			res.setStatus(200);
			
		}
		else {
			res.sendError(403);
		}
		
		
		
	}
	
	
	
}
