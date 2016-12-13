package me.duanyong.blog.action;

import com.google.gson.Gson;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by PP on 10/25/16.
 */
public class BaseRestAction {
    protected Map<String, Object> json = new HashMap<String, Object>();


    protected String gson() {
        return gson(0, null);
    }


    protected String gson(Integer code, String message) {
        if (code == null) {
            code = 0;
        }

        if (code != 0 && message != null) {
            json.put("errmsg", message);
        }

        return gson("error", code);

    }


    protected String gson(String key, Object message) {
        if (StringUtils.isEmpty(key)) {
            key = "autoKey";
        }

        json.put(key, message);

        if (json.containsKey("error") == false) {
            json.put("error", 0);
        }

        return toString();
    }

    protected String gson(Map<String, Object> map) {
        for (String key : map.keySet()) {
            json.put(key, map.get(key));
        }

        return toString();
    }

    public String toString() {
        return new Gson().toJson(json);
    }
}
