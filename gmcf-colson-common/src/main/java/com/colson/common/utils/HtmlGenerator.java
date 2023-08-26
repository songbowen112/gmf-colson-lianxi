package com.colson.common.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public class HtmlGenerator {

    /**
     * Generate html string.
     *
     * @param template
     *            the name of freemarker teamlate.
     * @param map
     *            the data of teamlate.
     * @return htmlStr
     * @throws IOException
     * @throws TemplateException
     * @throws Exception
     */
    public String generate(String template, Map<String, Object> map)
            throws IOException, TemplateException {
        BufferedWriter writer = null;
        String htmlContent = "";
        try {

            String templateName = template.substring(template
                    .lastIndexOf(File.separator) + 1);
            String templatePath = template.substring(0,
                    template.lastIndexOf(File.separator));

            Configuration config = new Configuration();
            config.setDefaultEncoding("UTF-8");
            config.setDirectoryForTemplateLoading(new File(templatePath));
            Template tp = config.getTemplate(templateName);

            StringWriter stringWriter = new StringWriter();
            writer = new BufferedWriter(stringWriter);

//            tp.setEncoding("UTF-8");
            tp.process(map, writer);
            htmlContent = stringWriter.toString();
            writer.flush();

        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        return htmlContent;
    }
}