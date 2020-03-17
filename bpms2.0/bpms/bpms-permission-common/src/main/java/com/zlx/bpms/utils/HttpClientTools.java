package com.zlx.bpms.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Package: com.bpms.auth.util
 * @Author: LQW
 * @Date: 2020/3/17
 * @Description: http连接工具
 */
public class HttpClientTools {
    private static final Logger logger = Logger.getLogger(HttpClientUtils.class);
    private static final ThreadLocal<Map<String, QNHttpClient>> threadLocal = new ThreadLocal<Map<String, QNHttpClient>>();

    /**
     * @param url
     * @param releaseConnnection 是否关闭连接
     * @return
     */
    public static final String get(String url, Charset charset, boolean... releaseConnnection) {
        String res = get(url, null, null, charset, releaseConnnection);
        return res;
    }

    /**
     * @param proxy
     * @param url
     * @param releaseConnnection 是否关闭连接
     * @return
     */
    public static final String get(HttpHost proxy, String url, Charset charset, boolean... releaseConnnection) {
        String res = get(proxy, url, null, null, charset, releaseConnnection);
        return res;
    }

    /**
     * @param url
     * @param params
     * @param releaseConnnection 是否关闭连接
     * @return
     */
    public static final String get(String url, Map<String, String> params, Charset charset, boolean... releaseConnnection) {
        String res = get(url, params, null, charset, releaseConnnection);
        return res;
    }

    /**
     * @param proxy
     * @param url
     * @param params
     * @param releaseConnnection 是否关闭连接
     * @return
     */
    public static final String get(HttpHost proxy, String url, Map<String, String> params, Charset charset, boolean... releaseConnnection) {
        String res = get(proxy, url, params, null, charset, releaseConnnection);
        return res;
    }

    /**
     * @param url
     * @param params
     * @param headers
     * @param releaseConnnection 是否关闭连接
     * @return
     */
    public static final String get(String url, Map<String, String> params, Map<String, String> headers, Charset charset, boolean... releaseConnnection) {
        HttpResponse response = null;
        String content = null;
        try {
            response = get4Response(url, params, headers, charset);
            content = EntityUtils.toString(response.getEntity(), charset);
        } catch (IOException e) {
            closeHttpClient(url);
            throw handlerException("http get for html(url=" + url + ",params=" + params + ",headers=" + headers + ")", e);
        } finally {
            closeResponse(response);
            if (releaseConnnection.length == 0 || releaseConnnection[0]) {
                closeHttpClient(url);
            }
        }
        if (logger.isDebugEnabled()) {
            logger.debug("url=" + url + ",res=" + content);
        }
        return content;
    }

    /**
     * @param proxy
     * @param url
     * @param params
     * @param headers
     * @param releaseConnnection 是否关闭连接
     * @return
     */
    public static final String get(HttpHost proxy, String url, Map<String, String> params, Map<String, String> headers, Charset charset, boolean... releaseConnnection) {
        HttpResponse response = null;
        String content = null;
        try {
            response = get4Response(proxy, url, params, headers, charset);
            content = EntityUtils.toString(response.getEntity(), charset);
        } catch (IOException e) {
            closeHttpClient(url);
            throw handlerException("http get for html(proxy=" + proxy + ",url=" + url + ",params=" + params + ",headers=" + headers + ")", e);
        } finally {
            closeResponse(response);
            if (releaseConnnection.length == 0 || releaseConnnection[0]) {
                closeHttpClient(url);
            }
        }
        if (logger.isDebugEnabled()) {
            logger.debug("url=" + url + ",res=" + content);
        }
        return content;
    }

    private static final boolean isEmptyCollection(Map<?, ?> collection) {
        return (collection == null || collection.isEmpty());
    }

    public static final HttpResponse get4Response(HttpHost proxy, String url, Map<String, String> params, Map<String, String> headers, Charset charset) throws IOException {
        HttpResponse response = null;
        HttpGet get = null;
        try {
            HttpClient client = getHttpClient(url);

            if (proxy != null) {
                client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
            }
            //使用抢先认证
            if (!isEmptyCollection(params)) {
                String paramsStr = "?" + URLEncodedUtils.format(getNameValuePairsFromMap(params), charset);
                get = new HttpGet(url + paramsStr);
                if (logger.isDebugEnabled()) {
                    logger.debug(url + paramsStr);
                }
            } else {
                get = new HttpGet(url);
            }

            if (headers != null) {
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    get.addHeader(header.getKey(), header.getValue());
                }
            }

            response = client.execute(get);
        } catch (Exception e) {
            closeResponse(response);
            closeHttpClient(url);
            if (get != null) {
                get.releaseConnection();
            }
            throw new RuntimeException("close response.", e);
        } finally {
        }

        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            logger.error("http get status: " + response.getStatusLine().getStatusCode() + ",reason:" + response.getStatusLine().getReasonPhrase());
        }
        if (logger.isDebugEnabled()) {
            logger.debug("url=" + url + ",response=" + response);
        }

        return response;
    }

    public static final HttpResponse get4Response(String url, Map<String, String> params, Map<String, String> headers, Charset charset) throws IOException {
        return get4Response(null, url, params, headers, charset);
    }

    public static final HttpResponse get4Response(String url, Map<String, String> params, Map<String, String> headers) throws IOException {
        return get4Response(null, url, params, headers, Charset.forName("UTF-8"));
    }

    public static final String post(String url, Charset charset, boolean... releaseConnnection) {
        String res = post(url, null, null, charset, releaseConnnection);
        return res;
    }

    public static final String post(String url, boolean... releaseConnnection) {
        String res = post(url, null, null, Charset.forName("UTF-8"), releaseConnnection);
        return res;
    }

    /**
     * @param url
     * @param params
     * @param releaseConnnection 是否关闭连接
     * @return
     */
    public static final String post(String url, Map<String, String> params, Charset charset, boolean... releaseConnnection) {
        String res = post(url, params, null, charset, releaseConnnection);
        return res;
    }

    public static final String post(String url, Map<String, String> params, boolean... releaseConnnection) {
        String res = post(url, params, null, Charset.forName("UTF-8"), releaseConnnection);
        return res;
    }

    public static final String postJson(String url, String json, Charset charset, boolean... releaseConnnection) {
        String res = postJson(url, json, null, charset, releaseConnnection);
        return res;
    }

    public static final String postXml(String url, String xml, Charset charset, boolean... releaseConnnection) {
        String res = postXml(url, xml, null, charset, releaseConnnection);
        return res;
    }

    public static final String postJson(String url, String json, boolean... releaseConnnection) {
        String res = postJson(url, json, null, Charset.forName("UTF-8"), releaseConnnection);
        return res;
    }

    public static final String postXml(String url, String xml, boolean... releaseConnnection) {
        String res = postXml(url, xml, null, Charset.forName("UTF-8"), releaseConnnection);
        return res;
    }

    public static final String post(String url, Map<String, String> params, Map<String, String> headers, boolean... releaseConnnection) {
        return post(url, params, Charset.forName("UTF-8"), releaseConnnection);
    }

    public static final String post(String url, Map<String, String> params, Map<String, String> headers, Charset charset, boolean... releaseConnnection) {
        String res = post(null, url, params, headers, charset, releaseConnnection);
        return res;
    }

    public static final String postJson(String url, String json, Map<String, String> headers, Charset charset, boolean... releaseConnnection) {
        String res = postJson(null, url, json, headers, charset, releaseConnnection);
        return res;
    }

    public static final String postXml(String url, String xml, Map<String, String> headers, Charset charset, boolean... releaseConnnection) {
        String res = postXml(null, url, xml, headers, charset, releaseConnnection);
        return res;
    }

    public static final String postJson(String url, String json, Map<String, String> headers, boolean... releaseConnnection) {
        String res = postJson(null, url, json, headers, Charset.forName("UTF-8"), releaseConnnection);
        return res;
    }

    public static final String postXml(String url, String xml, Map<String, String> headers, boolean... releaseConnnection) {
        String res = postXml(null, url, xml, headers, Charset.forName("UTF-8"), releaseConnnection);
        return res;
    }

    public static final String post(HttpHost proxy, String url, Map<String, String> params, Map<String, String> headers, Charset charset, boolean... releaseConnnection) {
        HttpResponse response = null;
        String content = null;
        try {
            response = post4Response(proxy, url, params, headers, charset);
            content = EntityUtils.toString(response.getEntity(), String.valueOf(charset));
        } catch (IOException e) {
            closeHttpClient(url);
            throw handlerException("http post for html(proxy=" + proxy + ",url=" + url + ",params=" + params + ",headers=" + headers + ")", e);
        } finally {
            closeResponse(response);
            if (releaseConnnection.length == 0 || releaseConnnection[0]) {
                closeHttpClient(url);
            }
        }
        if (logger.isDebugEnabled()) {
            logger.debug("url=" + url + ",res=" + content);
        }
        return content;
    }

    public static final String postJson(HttpHost proxy, String url, String json, Map<String, String> headers, Charset charset, boolean... releaseConnnection) {
        HttpResponse response = null;
        try {
            response = postJsonResponse(proxy, url, json, headers, charset);
            String content = EntityUtils.toString(response.getEntity(), String.valueOf(charset));
            if (logger.isDebugEnabled()) {
                logger.debug("url=" + url + ",res=" + content);
            }
            return content;
        } catch (Exception e) {
            closeHttpClient(url);
            throw handlerException("post json(proxy=" + proxy + ",url=" + url + ",params=" + json + ",headers=" + headers + ")", e);
        } finally {
            closeResponse(response);
            if (releaseConnnection.length == 0 || releaseConnnection[0]) {
                closeHttpClient(url);
            }
        }
    }

    public static final String postXml(HttpHost proxy, String url, String xml, Map<String, String> headers, Charset charset, boolean... releaseConnnection) {
        HttpResponse response = null;
        String content = null;
        try {
            response = postXmlResponse(proxy, url, xml, headers, charset);
            content = EntityUtils.toString(response.getEntity(), String.valueOf(charset));
        } catch (IOException e) {
            closeHttpClient(url);
            throw handlerException("post xml(proxy=" + proxy + ",url=" + url + ",params=" + xml + ",headers=" + headers + ")", e);
        } finally {
            closeResponse(response);
            if (releaseConnnection.length == 0 || releaseConnnection[0]) {
                closeHttpClient(url);
            }
        }
        if (logger.isDebugEnabled()) {
            logger.debug("url=" + url + ",res=" + content);
        }
        return content;
    }

    public static final HttpResponse post4Response(HttpHost proxy, String url, Map<String, String> params, Map<String, String> headers, Charset charset) {
        HttpResponse response = null;
        try {
            HttpClient client = getHttpClient(url);
            if (proxy != null) {
                client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
            }
            HttpPost post = new HttpPost(url);
            if (!isEmptyCollection(params)) {
                post.setEntity(new UrlEncodedFormEntity(getNameValuePairsFromMap(params), charset));
            }

            if (headers != null) {
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    post.setHeader(header.getKey(), header.getValue());
                }
            }
            response = client.execute(post);
            if (logger.isDebugEnabled()) {
                logger.debug("url=" + url + ",response=" + response);
            }
            return response;
        } catch (IOException e) {
            closeHttpClient(url);
            closeResponse(response);
            throw handlerException("post to response(proxy=" + proxy + ",url=" + url + ",params=" + params + ",headers=" + headers + ")", e);
        }
    }

    public static final HttpResponse postJsonResponse(HttpHost proxy, String url, String json, Map<String, String> headers, Charset charset) {
        HttpResponse response = null;
        try {
            DefaultHttpClient client = getHttpClient(url);


            if (proxy != null) {
                client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
            }

            HttpPost post = new HttpPost(url);
            if (StringUtils.isNotEmpty(json)) {
                StringEntity se = new StringEntity(json, String.valueOf(charset));
                se.setContentType(ContentType.APPLICATION_JSON.getMimeType());
                post.setEntity(se);
            }

            if (headers != null) {
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    post.setHeader(header.getKey(), header.getValue());
                }
            }
            response = client.execute(post);
            if (logger.isDebugEnabled()) {
                logger.debug("url=" + url + ",params=" + json + ",response=" + response);
            }
            return response;
        } catch (IOException e) {
            closeHttpClient(url);
            closeResponse(response);
            throw handlerException("post json response(proxy=" + proxy + ",url=" + url + ",params=" + json + ",headers=" + headers + ")", e);
        }
    }

    public static final HttpResponse postXmlResponse(HttpHost proxy, String url, String json, Map<String, String> headers, Charset charset) {
        HttpResponse response = null;
        try {
            HttpClient client = getHttpClient(url);

            if (proxy != null) {
                client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
            }
            HttpPost post = new HttpPost(url);
            if (StringUtils.isNotEmpty(json)) {
                StringEntity se = new StringEntity(json, charset);
                se.setContentType(ContentType.TEXT_XML.getMimeType());
                post.setEntity(se);
            }

            if (headers != null) {
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    post.setHeader(header.getKey(), header.getValue());
                }
            }
            response = client.execute(post);
            if (logger.isDebugEnabled()) {
                logger.debug("url=" + url + ",params=" + json + ",response=" + response);
            }
            return response;
        } catch (IOException e) {
            closeHttpClient(url);
            closeResponse(response);
            throw handlerException("post xml response(proxy=" + proxy + ",url=" + url + ",params=" + json + ",headers=" + headers + ")", e);
        }
    }

    public static final HttpResponse post4Response(String url, Map<String, String> params, Map<String, String> headers, Charset charset) {
        return post4Response(null, url, params, headers, charset);
    }

    /**
     * 设置参数
     *
     * @param params
     * @return
     */
    private static final List<NameValuePair> getNameValuePairsFromMap(Map<String, String> params) {
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        if (!isEmptyCollection(params)) {
            for (Map.Entry<String, String> e : params.entrySet()) {
                pairs.add(new BasicNameValuePair(e.getKey(), e.getValue()));
            }
        }
        return pairs;
    }

    /**
     * 获取host
     *
     * @param url
     * @return
     */
    public static final String getHostAndPort(String url) {
        String host = url;
        if (url.startsWith("http://")) {
            host = url.substring(7);
        }
        if (host.contains("/")) {
            host = host.substring(0, host.indexOf("/"));
        }
        if (logger.isDebugEnabled()) {
            logger.debug(url + ">>>host>>>" + host);
        }
        return host;
    }

    private static final long expiredTime = 900000l;

    private static final boolean isExpired(QNHttpClient qnHttpClient) {
        return System.currentTimeMillis() - qnHttpClient.getAccessTime() > expiredTime;
    }

    private static long lastCloseTime = 0;
    private static long interval = 1 * 60 * 1000;

    private final static void checkAndProcessConnections() {
        final Map<String, QNHttpClient> clients = threadLocal.get();
        if (clients != null && System.currentTimeMillis() - lastCloseTime > interval) {
            //启动线程，关闭过期的连接
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lastCloseTime = System.currentTimeMillis();
                    for (Map.Entry<String, QNHttpClient> en : clients.entrySet()) {
                        QNHttpClient c = en.getValue();
                        c.getHttpClient().getConnectionManager().closeExpiredConnections();
                        if (logger.isDebugEnabled()) {
                            logger.debug("close expired connections.");
                        }
                    }
                }
            }).start();
        }
    }

    private static final RuntimeException handlerException(String msg, Exception exception) {
        return new RuntimeException(msg, exception);
    }

    /**
     * use BasicClientConnectionManager to get connections
     *
     * @param url
     * @return
     */
    private static final DefaultHttpClient getHttpClient(String url) {
        checkAndProcessConnections();
        String host = getHostAndPort(url);
        Map<String, QNHttpClient> clients = threadLocal.get();
        if (clients == null) {
            clients = new ConcurrentHashMap<String, QNHttpClient>();
            threadLocal.set(clients);
        }
        QNHttpClient qnClient = clients.get(host);
        if (qnClient == null) {
            qnClient = new QNHttpClient();
        }
        boolean fromCache = true;
        DefaultHttpClient client = qnClient.getHttpClient();

        if (client == null || isExpired(qnClient)) {
            client = new DefaultHttpClient();
            fromCache = false;
            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
            client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 50000);
            client.getParams().setParameter(CoreConnectionPNames.SO_KEEPALIVE, true);
            client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);

            qnClient.setHttpClient(client);
            clients.put(host, qnClient);
        } else {
            client = qnClient.getHttpClient();
        }

        client.addResponseInterceptor(new HttpResponseInterceptor() {
            public void process(final HttpResponse response, final HttpContext context) throws HttpException, IOException {
                HttpEntity entity = response.getEntity();
                Header ceheader = entity.getContentEncoding();
                if (ceheader != null) {
                    HeaderElement[] codecs = ceheader.getElements();
                    for (int i = 0; i < codecs.length; i++) {
                        if (codecs[i].getName().equalsIgnoreCase("gzip")) {
                            response.setEntity(new GzipDecompressingEntity(response.getEntity()));
                            return;
                        }
                    }
                }
            }
        });

        qnClient.setAccessTime(System.currentTimeMillis());
        if (fromCache) {
            if (logger.isDebugEnabled()) {
                logger.debug("get httpClient[" + client + "] from cache.===current thread cache route's size=" + clients.size());
            }
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("new httpClient[" + client + "].===current thread cache route's size=" + clients.size());
            }
        }
        return client;
    }

    /**
     * 关闭连接
     *
     * @param url
     */
    public static final void closeHttpClient(String url) {
        String host = getHostAndPort(url);
        Map<String, QNHttpClient> httpClients = threadLocal.get();
        HttpClient client = null;
        if (httpClients != null) {
            QNHttpClient qnHttpClient = httpClients.get(host);
            if (qnHttpClient != null) {
                client = qnHttpClient.getHttpClient();
                client.getConnectionManager().shutdown();
            }
            httpClients.remove(host);
            if (logger.isDebugEnabled()) {
                logger.debug("shutdown httpClient[" + client + "].===current thread cache route's size=" + httpClients.size());
            }
        }
    }

    public static final void closeResponse(HttpResponse response) {
        if (response != null && response.getEntity() != null) {
            try {
                response.getEntity().getContent().close();
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }

    static class QNHttpClient {
        private DefaultHttpClient httpClient;
        private long accessTime;

        public DefaultHttpClient getHttpClient() {
            return httpClient;
        }

        public void setHttpClient(DefaultHttpClient httpClient) {
            this.httpClient = httpClient;
        }

        public long getAccessTime() {
            return accessTime;
        }

        public void setAccessTime(long accessTime) {
            this.accessTime = accessTime;
        }
    }
}
