package shenry.passwordvalidator;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PasswordValidatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void construct_withDefaultValues() {
        PasswordValidator validator = createDefaultValidator();

        Assert.assertEquals(6, validator.getMinLength());
        Assert.assertEquals(20, validator.getMaxLength());
        Assert.assertEquals(true, validator.isAllowDigits());
        Assert.assertEquals(true, validator.isAllowLowercaseEnglishLetters());
        Assert.assertEquals(true, validator.isAllowLowercaseEnglishLetters());
    }

    @Test
    public void construct_withDefaultBuilder() {
        PasswordValidator validator = PasswordValidator.getBuilder().build();

        Assert.assertEquals(0, validator.getMinLength());
        Assert.assertEquals(Integer.MAX_VALUE, validator.getMaxLength());
        Assert.assertEquals(false, validator.isAllowDigits());
        Assert.assertEquals(false, validator.isAllowLowercaseEnglishLetters());
        Assert.assertEquals(false, validator.isAllowLowercaseEnglishLetters());
    }

    @Test
    public void validate_nullPassword() {
        PasswordValidator validator = createDefaultValidator();
        String nullPassword = null;
        expectedException.expect(IllegalArgumentException.class);

        ValidateResult result = validator.validate(nullPassword);
    }

    @Test
    public void validate_tooShortPassword() {
        PasswordValidator validator = createDefaultValidator();
        String tooShortPassword = "12345";

        ValidateResult result = validator.validate(tooShortPassword);

        Assert.assertEquals(ValidateResult.ErrorShortPassword, result);
    }

    @Test
    public void validate_tooLongPassword() {
        PasswordValidator validator = createDefaultValidator();
        String tooLongPassword = "111111111111111111111";

        ValidateResult result = validator.validate(tooLongPassword);

        Assert.assertEquals(ValidateResult.ErrorLongPassword, result);
    }

    @Test
    public void validate_digits_allowed() {
        PasswordValidator validator = createDefaultValidator();
        String digitsPassword = "12345678";

        ValidateResult result = validator.validate(digitsPassword);

        Assert.assertEquals(ValidateResult.Success, result);
    }

    @Test
    public void validate_digits_notAllowed() {
        PasswordValidator validator = PasswordValidator.getBuilder()
                                                       .setAllowDigits(false)
                                                       .build();
        String digitsPassword = "12345678";
        ValidateResult expected = ValidateResult.ErrorDigitsNotAllowed;

        ValidateResult actual = validator.validate(digitsPassword);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void validate_englishLowercase_allowed() {
        PasswordValidator validator = createDefaultValidator();
        String englishLowercasePassword = "qwerty";
        ValidateResult expected = ValidateResult.Success;

        ValidateResult actual = validator.validate(englishLowercasePassword);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void validate_englishLowercase_notAllowed() {
        PasswordValidator validator = PasswordValidator.getBuilder()
                                                       .setAllowLowercaseEnglishLetters(false)
                                                       .build();
        String englishLowercasePassword = "qwerty";
        ValidateResult expected = ValidateResult.ErrorLowercaseEnglishNotAllowed;

        ValidateResult actual = validator.validate(englishLowercasePassword);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void validate_englishUppercase_allowed() {
        PasswordValidator validator = createDefaultValidator();
        String englishUppercasePassword = "QWERTY";
        ValidateResult expected = ValidateResult.Success;

        ValidateResult actual = validator.validate(englishUppercasePassword);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void validate_englishUppercase_notAllowed() {
        PasswordValidator validator = PasswordValidator.getBuilder()
                                                       .setAllowUppercaseEnglishLetters(false)
                                                       .build();
        String englishUppercasePassword = "QWERTY";
        ValidateResult expected = ValidateResult.ErrorUppercaseEnglishNotAllowed;

        ValidateResult actual = validator.validate(englishUppercasePassword);

        Assert.assertEquals(expected, actual);
    }

    private PasswordValidator createDefaultValidator() {
        return new PasswordValidator();
    }
}
