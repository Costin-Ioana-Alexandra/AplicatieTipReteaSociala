package com.example.back.registration;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmailValidator implements Predicate<String> {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    @Override
    public boolean test(String s) { // Test if the email is valid
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }
}
