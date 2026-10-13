package com.example.demo.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.core.io.ResourceLoader;

public class DelimitedDataFileReader implements DataFileReader {
    private final DataProperties properties;
    private final ResourceLoader resourceLoader;

    public DelimitedDataFileReader(DataProperties properties, ResourceLoader resourceLoader) {
        this.properties = properties;
        this.resourceLoader = resourceLoader;
    }

    public List<DataRecord> read() throws IOException {
        var records = new ArrayList<DataRecord>();
        var resource = resourceLoader.getResource(properties.getPath());
        try (var reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), Charset.forName(properties.getEncoding())))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] v = line.split(Pattern.quote(properties.getDelimiter()), -1);
                records.add(new DataRecord(Integer.parseInt(clean(v[0])), clean(v[1]), clean(v[2])));
            }
        }
        return records;
    }

    private String clean(String value) {
        return properties.isTrimValues() ? value.trim() : value;
    }
}
