package com.example.demo.data;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.data")
public class DataProperties {
    private String path;
    private String format;
    private String delimiter;
    private String encoding = "UTF-8";
    private boolean trimValues = true;
    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }
    public String getDelimiter() { return delimiter; }
    public void setDelimiter(String delimiter) { this.delimiter = delimiter; }
    public String getEncoding() { return encoding; }
    public void setEncoding(String encoding) { this.encoding = encoding; }
    public boolean isTrimValues() { return trimValues; }
    public void setTrimValues(boolean trimValues) { this.trimValues = trimValues; }
}
