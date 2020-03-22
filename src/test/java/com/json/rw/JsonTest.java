package com.json.rw;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

public class JsonTest {
	
	public static void main(String[] args) throws Exception {
        Writer writer = new FileWriter("D:\\Testing_Software\\Assignment\\employees-new.csv");

        StatefulBeanToCsvBuilder<Product> builder = new StatefulBeanToCsvBuilder<>(writer);
        StatefulBeanToCsv<Product> beanWriter = builder.build();

        List<Product> products = new ArrayList<>();
        products.add(new Product("1", "11", "111"));
        products.add(new Product("2", "22", "222"));
        products.add(new Product("3", "33", "333"));
        beanWriter.write(products);
        writer.close();
    }

    public static class Product {
        @CsvBindByName(column = "productCode")
        String id;
        @CsvBindByName(column = "MFD")
        String member2;
        @CsvBindByName(column = "EXD")
        String member3;

        Product(String id, String member2, String member3) {
            this.id = id;
            this.member2 = member2;
            this.member3 = member3;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMember2() {
            return member2;
        }

        public void setMember2(String member2) {
            this.member2 = member2;
        }

        public String getMember3() {
            return member3;
        }

        public void setMember3(String member3) {
            this.member3 = member3;
        }
    }
}
