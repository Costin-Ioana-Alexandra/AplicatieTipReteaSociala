package com.example.back.registration;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents the class component for validating email addresses.
 */
@Component
public class EmailValidator implements Predicate<String> {

  /**
   * Regular expression for validating email addresses.
   */
  private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

  /**
   * Pattern for the regular expression.
   */
  private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

  /**
   * Tests if the given string is a valid email address.
   *
   * @return true if the email address is valid, false otherwise
   */
  @Override
  public boolean test(String s) {
    Matcher matcher = pattern.matcher(s);
    return matcher.matches();
  }
}
