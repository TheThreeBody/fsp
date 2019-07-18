package com.npsex.fsp.commons.utils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * @author dell
 *
 */
public class VcreditUtils{
	
	@SuppressWarnings({ "unchecked" })
	public static Map<String, Object> json2Map(String json) {
		Map<String, Object> result = null;
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		try {
			result = mapper.readValue(json, Map.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static Map<String, Object> jsonObj2Map(String json) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> result = new HashMap<String, Object>();
		JsonNode jsonNode;
		try {
			jsonNode = mapper.readTree(json);
			Iterator<Map.Entry<String,JsonNode>> it = jsonNode.fields();
			while (it.hasNext()) {
				 Entry<String, JsonNode> entry = it.next();
				 result.put(entry.getKey(), entry.getValue());
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static <T> T json2Obj(String json, TypeReference<T> tr) {
		T obj = null;
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		mapper.setSerializationInclusion(Include.NON_NULL);  
		try {
			obj = mapper.readValue(json, tr);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * @param obj
	 * @return
	 */
	public static String obj2Json(Object obj) {
		try {
			if(obj!=null){
			ObjectMapper objectMapper = new ObjectMapper();
			
			String json = objectMapper.writeValueAsString(obj);

			return json;
			}
			return "";
		} catch (IOException e) {
			e.printStackTrace();

			return "";
		}
	}
	public static String getMD5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密 
            return buf.toString();
            // 16位的加密
            //return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
	public static <T> T json2ObjTypeReference(String json, TypeReference<T> tr)
			throws JsonParseException, JsonMappingException, IOException {
		T obj = null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		mapper.setSerializationInclusion(Include.NON_NULL);  
		obj = mapper.readValue(json, tr);
		return obj;
	}
}
