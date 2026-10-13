package com.example.demo.data;

import java.io.IOException;
import java.util.List;

public interface DataFileReader {
    List<DataRecord> read() throws IOException;
}
