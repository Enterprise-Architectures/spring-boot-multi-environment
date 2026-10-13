package com.example.demo.data;
import java.util.List;
import org.slf4j.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
@Component @Profile({"dev","prod"})
public class DataStartupRunner implements CommandLineRunner {
 private static final Logger log=LoggerFactory.getLogger(DataStartupRunner.class);
 private final DataFileReader reader; private final DataProperties properties; private final Environment environment;
 public DataStartupRunner(DataFileReader r,DataProperties p,Environment e){reader=r;properties=p;environment=e;}
 public void run(String... args)throws Exception{
  List<DataRecord> records=reader.read();
  log.info("Data source: path={}, format={}, records={}",properties.getPath(),properties.getFormat(),records.size());
  records.stream().limit(3).forEach(x->log.info("Loaded record: {}",x));
  log.info("Precedence demo value: {}",environment.getProperty("app.demo.precedence-message"));
 }
}
