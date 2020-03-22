package com.json.rw;

//public class JsonRW {
//
//}
//
//package com.json.test;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonRW
{
    public static void main(String[] args) throws ParseException, IOException
    {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("D:\\Testing_Software\\Assignment\\Person.json"))
        {
            Object obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;

            System.out.println(employeeList);

            List<Employee> employees = new ArrayList<>();

            employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp, employees ) );

            write2Csv(employees);

        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

    }

    private static void parseEmployeeObject(JSONObject jsonObject, List<Employee> employees)
    {
        Employee employee = new Employee();

        String name = (String) jsonObject.get("Name");

        String[] separated = name.split(" ");

        for (int i = 0; i < separated.length; i++) {
            if (i == 0) {
                employee.setFirstName(separated[0].trim());
            } else if (i == 1) {
                employee.setMiddleName(separated[1].trim());
            } else if (i == 2) {
                employee.setLastName(separated[2].trim());
            }
        }

        //Get  DOB
        String DOB = (String) jsonObject.get("DOB");
        employee.setDob(DOB);

        //Get  Gender
        String Gender = (String) jsonObject.get("Gender");
        employee.setGender(Gender);

        //Get CTC_Annual_INR
        String CTC_Annual_INR = (String) jsonObject.get("CTC_Annual_INR");
        employee.setCtc(CTC_Annual_INR);

        employees.add(employee);
    }

    private static void write2Csv(List<Employee> employees){

        try (FileWriter writer = new
                FileWriter("D:\\Testing_Software\\Assignment\\employees1.csv");){

            writer.append("First Name, Middle Name, Last Name, DOB, Gender, Annual CTC\n");

            StatefulBeanToCsv<Employee> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();

            beanToCsv.write(employees);

        }catch (Exception e){
            e.printStackTrace();

        }

    }
}