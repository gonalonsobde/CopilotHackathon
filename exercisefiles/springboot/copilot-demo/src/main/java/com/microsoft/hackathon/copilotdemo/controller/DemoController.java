package com.microsoft.hackathon.copilotdemo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.json.JSONException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.hackathon.entities.ListElement;


@RestController
public class DemoController {

    //private final static String FILE_NAME = "classpath:colors.json";
    private final static String FILE_NAME = "./colors.json";

    ObjectMapper mapper = new ObjectMapper();


    @GetMapping("/hello")
    public String hello(@RequestParam(name = "key", required = false) String key) {
        return (key == null) ? "key not passed" : "hello " + key;
    }

    // New operation under /diffdates that calculates the difference between two dates. The operation should receive two dates as parameter in format dd-MM-yyyy and return the difference in days
    @GetMapping("/diffdates")
    public String diffdates(
        @RequestParam(name = "date1", required = false) /*@DateTimeFormat(pattern = "dd-MM-yyyy")*/ String date1,
        @RequestParam(name = "date2", required = false) /*@DateTimeFormat(pattern = "dd-MM-yyyy")*/ String date2) {
        return (date1 == null || date2 == null) ? "date not passed" : "diffdates " + date1 + " " + date2;
    }

    // Validate the format of a spanish phone number (+34 prefix, then 9 digits, starting with 6, 7 or 9). The operation should receive a phone number as parameter and return true if the format is correct, false otherwise.
    @GetMapping("/validatephone")
    public String validatephone(@RequestParam(name = "phone", required = false) String phone) {
        if (phone == null) {
            return "phone not passed";
        } else if (phone.matches("^\\+34[679]\\d{8}$")) {
            return "validatephone " + phone;
        } else {
            return "invalid phone number";
        }
    }


    // Validate the format of a spanish DNI (8 digits and 1 letter). The operation should receive a DNI as parameter and return true if the format is correct, false otherwise.
    @GetMapping("/validatedni")
    public String validatedni(@RequestParam(name = "dni", required = false) String dni) {
        if (dni == null) {
            return "dni not passed";
        } else if (dni.matches("^\\d{8}[A-Z]$")) {
            return "validatedni " + dni;
        } else {
            return "invalid dni";
        }
    }

    // Based on existing colors.json file under resources folder, given the name of the color as path parameter, return the hexadecimal code. If the color is not found, return 404
    @GetMapping("/color")
    public String color(@RequestParam(name = "color", required = false) String color) {
        if (color == null) 
            return "color not valid";

        TypeReference<List<ListElement>> typeReference = new TypeReference<List<ListElement>>(){};

        // open colors.json file as read-only
        try {
            InputStream resource = new ClassPathResource(FILE_NAME).getInputStream();

            List<ListElement> list = mapper.readValue(resource, typeReference);

            // get the color from the json object
            String hexCode = list.stream()
                .filter(obj -> obj.getColor().equalsIgnoreCase(color))
                .findFirst()
                .map(obj -> obj.getCode().getHex())
                //.map(hex -> String.format("#%06X", (0xFFFFFF & hex)))
                .orElse(null);

            // return the hex code or 404 if not found
            if (hexCode == null) {
                return "404 Not Found";
            } else {
                return hexCode;
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return "Error reading colors.json";
        }
    }
}
