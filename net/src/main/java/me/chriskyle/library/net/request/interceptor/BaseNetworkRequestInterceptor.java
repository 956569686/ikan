package me.chriskyle.library.net.request.interceptor;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import me.chriskyle.library.net.util.Util;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Description :
 * <p/>
 * Created by Chris Kyle on 2017/9/18.
 */
public abstract class BaseNetworkRequestInterceptor implements Interceptor {

    private static final String HTTP_METHOD_POST = "POST";

    private static final String MEDIA_TYPE_JSON = "application/json;charset=UTF-8";

    private static final String SUBTYPE_FORM = "x-www-form-urlencoded";
    private static final String SUBTYPE_JSON = "json";

    private Map<String, String> queryParamsMap = new HashMap<>();
    private Map<String, String> paramsMap = new HashMap<>();
    private Map<String, String> headerParamsMap = new HashMap<>();
    private List<String> headerLinesList = new ArrayList<>();

    private Gson gson;

    protected BaseNetworkRequestInterceptor() {
        gson = new Gson();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();

        Headers.Builder headerBuilder = request.headers().newBuilder();
        if (!headerParamsMap.isEmpty()) {
            Iterator iterator = headerParamsMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                headerBuilder.add((String) entry.getKey(), (String) entry.getValue());
            }
        }

        if (!headerLinesList.isEmpty()) {
            for (String line : headerLinesList) {
                headerBuilder.add(line);
            }
        }
        requestBuilder.headers(headerBuilder.build());

        if (!queryParamsMap.isEmpty()) {
            injectParamsIntoUrl(request, requestBuilder, queryParamsMap);
        }

        if (request.method().equals(HTTP_METHOD_POST)) {
            injectParamsIntoBody(request, requestBuilder);
        } else {
            injectParamsIntoUrl(request, requestBuilder, paramsMap);
        }

        request = requestBuilder.build();

        return chain.proceed(request);
    }

    private void injectParamsIntoBody(Request request, Request.Builder requestBuilder) {
        RequestBody requestBody = request.body();
        if (requestBody == null) {
            injectParamsIntoUrl(request, requestBuilder, paramsMap);
            return;
        }

        String subtype = requestBody.contentType().subtype();

        if (subtype.equals(SUBTYPE_FORM)) {
            processFormSubtypeParams(request, requestBuilder);
        } else if (subtype.equals(SUBTYPE_JSON)) {
            processJsonSubtypeParams(request, requestBuilder);
        } else {
            injectParamsIntoUrl(request, requestBuilder, paramsMap);
        }
    }

    private void processFormSubtypeParams(Request request, Request.Builder requestBuilder) {
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        if (paramsMap.size() > 0) {
            Iterator iterator = paramsMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                formBodyBuilder.add((String) entry.getKey(), (String) entry.getValue());
            }
        }

        String postBodyString = bodyToString(request.body());

        Map<String, String> bodyMap = getUrlParams(Util.urlDecode(postBodyString), gson);

        Map<String, String> wrapperParams = wrapperInjectParams(bodyMap);

        if (!wrapperParams.isEmpty()) {
            Iterator iterator = wrapperParams.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                formBodyBuilder.add((String) entry.getKey(), (String) entry.getValue());
            }
        }

        requestBuilder.post(formBodyBuilder.build());
    }

    private void processJsonSubtypeParams(Request request, Request.Builder requestBuilder) {
        String postBodyString = bodyToString(request.body());
        Map<String, String> bodyMap = gson.fromJson(postBodyString, new TypeToken<Map<String, String>>() {
        }.getType());
        bodyMap.putAll(paramsMap);

        Map<String, String> wrapperParams = wrapperInjectParams(bodyMap);

        String newRequestBodyString = gson.toJson(wrapperParams);

        requestBuilder.post(RequestBody.create(MediaType.parse(MEDIA_TYPE_JSON), newRequestBodyString));
    }

    private void injectParamsIntoUrl(Request request, Request.Builder requestBuilder, Map<String, String> paramsMap) {
        HttpUrl.Builder httpUrlBuilder = request.url().newBuilder();
        if (!paramsMap.isEmpty()) {
            Iterator iterator = paramsMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                httpUrlBuilder.addQueryParameter((String) entry.getKey(), (String) entry.getValue());
            }
        }

        HttpUrl httpUrl = httpUrlBuilder.build();
        Map<String, String> urlParamsMap = getUrlParams(httpUrl.query(), gson);
        Map<String, String> wrapperUrlParams = wrapperInjectParams(urlParamsMap);

        requestBuilder.url(signHttpUrlBuilder(httpUrl.newBuilder(), wrapperUrlParams).build());
    }

    protected Map<String, String> wrapperInjectParams(Map<String, String> urlParamsMap) {
        return urlParamsMap;
    }

    protected abstract HttpUrl.Builder signHttpUrlBuilder(HttpUrl.Builder signHttpUrlBuilder,
                                                          Map<String, String> urlParamsMap);

    private static String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

    private static Map<String, String> getUrlParams(String param, Gson gson) {
        Map<String, String> map = new HashMap<>();
        if (TextUtils.isEmpty(param)) {
            return map;
        }
        String[] params = param.split("&");
        for (int i = 0; i < params.length; i++) {
            String[] p = params[i].split("=");
            if (p.length == 2) {
                map.put(p[0], p[1]);
            }
        }
        if (map.isEmpty()) {
            map = gson.fromJson(param, HashMap.class);
        }
        return map;
    }

    private static String getTimestamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    public void addParam(String key, String value) {
        paramsMap.put(key, value);
    }

    public void addParamsMap(Map<String, String> paramsMap) {
        paramsMap.putAll(paramsMap);
    }

    public void addHeaderParam(String key, String value) {
        headerParamsMap.put(key, value);
    }

    public void addHeaderParamsMap(Map<String, String> headerParamsMap) {
        headerParamsMap.putAll(headerParamsMap);
    }

    public void addHeaderLine(String headerLine) {
        int index = headerLine.indexOf(":");
        if (index == -1) {
            throw new IllegalArgumentException("Unexpected header: " + headerLine);
        }
        headerLinesList.add(headerLine);
    }

    public void addHeaderLinesList(List<String> headerLinesList) {
        for (String headerLine : headerLinesList) {
            int index = headerLine.indexOf(":");
            if (index == -1) {
                throw new IllegalArgumentException("Unexpected header: " + headerLine);
            }
            headerLinesList.add(headerLine);
        }
    }

    public void addQueryParam(String key, String value) {
        queryParamsMap.put(key, value);
    }

    public void addQueryParamsMap(Map<String, String> queryParamsMap) {
        queryParamsMap.putAll(queryParamsMap);
    }
}
