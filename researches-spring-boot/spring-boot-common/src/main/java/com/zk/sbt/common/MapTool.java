package com.zk.sbt.common;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by qcon on 2017/3/27.
 */
public class MapTool {

    public static MapTool.MapBuilder builder() {
        return new MapTool.MapBuilder();
    }

    public static class MapBuilder {

        private Map<String, Object> map;

        MapBuilder() {
            map = new TreeMap<>();
        }

        public MapTool.MapBuilder put(String k, Object v) {
            map.put(k, v);
            return this;
        }

        public MapTool.MapBuilder put(String k, Object v, Object defaultValue) {
            if (null == v) {
                map.put(k, defaultValue);
            } else {
                map.put(k, v);
            }
            return this;
        }

        public MapTool.MapBuilder putIf(boolean flag, String k, Object v) {
            return flag ? put(k, v) : this;
        }

        public MapTool.MapBuilder putIf(boolean flag, String k, Object v, Object defaultValue) {
            return flag ? put(k, v, defaultValue) : this;
        }

        public Map<String, Object> buildMap() {
            return map;
        }

    }

    // ============================================= tools =============================================================

    public static Number getNumber(final Map map, final Object key) {
        if (map != null) {
            Object answer = map.get(key);
            if (answer != null) {
                if (answer instanceof Number) {
                    return (Number) answer;

                } else if (answer instanceof String) {
                    try {
                        String text = (String) answer;
                        return NumberFormat.getInstance().parse(text);

                    } catch (ParseException e) {
                        return null;
                    }
                }
            }
        }
        return null;
    }

    public static Long getLong(final Map map, final Object key) {
        Number answer = getNumber(map, key);
        if (answer == null) {
            return null;
        } else if (answer instanceof Long) {
            return (Long) answer;
        }
        return answer.longValue();
    }

    public static Integer getInteger(final Map map, final Object key) {
        Number answer = getNumber(map, key);
        if (answer == null) {
            return null;
        } else if (answer instanceof Integer) {
            return (Integer) answer;
        }
        return answer.intValue();
    }

    public static Double getDouble(final Map map, final Object key) {
        Number answer = getNumber(map, key);
        if (answer == null) {
            return null;
        } else if (answer instanceof Double) {
            return (Double) answer;
        }
        return answer.doubleValue();
    }

    public static String getString(final Map map, final Object key) {
        if (map != null) {
            Object answer = map.get(key);
            if (answer != null) {
                return answer.toString();
            }
        }
        return null;
    }

}
