package com.jaredfwaya.onlineshop.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.jaredfwaya.onlineshop.dao.ProductDao;
import com.jaredfwaya.onlineshop.service.ImageService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
    ProductDao productDao;
	
	@Override
	public byte[] convert2Byte(MultipartFile image) {
		byte[] byteArr = null;
		
		try {
			byteArr = image.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byteArr;
	}
	
	@Override
	public String convert2String(Integer id) {
		String base64Encoded = null;
		
		try {
			byte[] encodeBase64 = Base64.encodeBase64(productDao.findImage(id));
			if(encodeBase64 == null){
				return "";
			}
			base64Encoded = new String(encodeBase64, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return base64Encoded;
	}

}
