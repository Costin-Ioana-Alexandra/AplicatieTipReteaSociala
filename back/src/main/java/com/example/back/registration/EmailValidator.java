package com.example.back.registration;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

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
  private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

  /**
   * Tests if the given string is a valid email address.
   *
   * @return true if the email address is valid, false otherwise
   */
  @Override
  public boolean test(final String s) {
    Matcher matcher = EMAIL_PATTERN.matcher(s);
    return matcher.matches();
  }
}
