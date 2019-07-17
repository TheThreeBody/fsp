package com.zhengtong.fsp.commons.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义响应结构
 */
public class JsonUtils {

    public static <T> T Json2Obj(String json, TypeReference<T> tr)
            throws JsonParseException, JsonMappingException, IOException {
        T obj = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        obj = mapper.readValue(json, tr);
        return obj;
    }

    @SuppressWarnings({ "unchecked" })
    public static Map<String, Object> Json2Map(String json)
            throws JsonParseException, JsonMappingException, IOException {
        Map<String, Object> result = null;
        // write your code here
        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        result = mapper.readValue(json, Map.class);
        return result;
    }

    /**
     * Json 转 String , String Map
     * @param json
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static ConcurrentHashMap<String, String> JsontoConcurrentMap(String json)
            throws JsonParseException, JsonMappingException, IOException {
        ConcurrentHashMap<String, String> result = null;
        // write your code here
        // can reuse, share globally
        ObjectMapper mapper = new ObjectMapper();
        result = mapper.readValue(json, ConcurrentHashMap.class);
        return result;
    }
    /**
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public static String Obj2Json(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(obj);

        return json;
    }

    /**
     * @param
     * @return
     * @throws JsonProcessingException
     */
    public static String keyValToJson(Object... objs) throws JsonProcessingException {
        if (objs.length % 2 == 0) {
            ObjectMapper objectMapper = new ObjectMapper();
            Map map = new ConcurrentHashMap();
            for (int i = 0; i < objs.length; i = i + 2) {
                map.put(objs[i], objs[i + 1]);
            }
            String json = objectMapper.writeValueAsString(map);
            return json;
        }
        return null;
    }

    /**
     *
     * @param
     * @return
     * @throws JsonProcessingException
     */
    public static String arrayToJson(String... params) throws JsonProcessingException {
        int size = params.length;
        if (size % 2 == 0) {
            Map<String, Object> map = new ConcurrentHashMap<>(size / 2);
            for (int i = 0; i < size; i++) {
                if (i % 2 == 0)
                    map.put(params[i], params[i + 1]);
            }
            return Obj2Json(map);
        }
        return null;
    }

}
