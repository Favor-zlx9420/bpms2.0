package com.zlx.bpms.utils;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.util.Map;

/**
 * @Package: com.zlx.bpms.utils
 * @Author: LQW
 * @Date: 2020/3/17
 * @Description:模板读取工具
 */
public class VelocityTools {

    public static final String IPO_APPLICATION_EMAIL_TEMPLATE = "templates/applicationEmail.vm";

    public static final String IPO_SIGNED_EMAIL_TEMPLATE = "templates/signedEmail.vm";


    static String fillTemplate(String templatePath, Map<String, Object> model) {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
        Template template = ve.getTemplate(templatePath, "utf-8");
        VelocityContext ctx = new VelocityContext();
        for (String key : model.keySet()) {
            ctx.put(key, model.get(key));
        }
        StringWriter sw = new StringWriter();
        template.merge(ctx, sw);
        return sw.toString();
    }


}
