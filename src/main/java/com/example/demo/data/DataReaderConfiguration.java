package com.example.demo.data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ResourceLoader;
@Configuration(proxyBeanMethods=false)
public class DataReaderConfiguration {
 @Bean @Profile("dev") @ConditionalOnExpression("'${app.data.format:}' == 'csv'")
 DataFileReader developmentCsvReader(DataProperties p,ResourceLoader r){return new DelimitedDataFileReader(p,r);}
 @Bean @Profile("prod") @ConditionalOnExpression("'${app.data.format:}' == 'pipe'")
 DataFileReader productionPipeReader(DataProperties p,ResourceLoader r){return new DelimitedDataFileReader(p,r);}
}
