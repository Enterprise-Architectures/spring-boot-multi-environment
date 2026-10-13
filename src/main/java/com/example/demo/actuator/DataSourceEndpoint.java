package com.example.demo.actuator;
import java.util.*;
import com.example.demo.data.*;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
@Component @Endpoint(id="dataSource") @Profile({"dev","prod"})
public class DataSourceEndpoint {
 private final DataFileReader reader; private final DataProperties properties;
 public DataSourceEndpoint(DataFileReader r,DataProperties p){reader=r;properties=p;}
 @ReadOperation public Map<String,Object> dataSource()throws Exception{
  var d=new LinkedHashMap<String,Object>();
  d.put("path",properties.getPath()); d.put("format",properties.getFormat()); d.put("delimiter",properties.getDelimiter());
  d.put("encoding",properties.getEncoding()); d.put("recordCount",reader.read().size()); return d;
 }
}
