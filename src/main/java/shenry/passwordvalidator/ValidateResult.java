package shenry.passwordvalidator;

public enum ValidateResult {
    Success,
    ErrorShortPassword,
    ErrorLongPassword,
    ErrorDigitsNotAllowed,
    ErrorLowercaseEnglishNotAllowed,
    ErrorUppercaseEnglishNotAllowed,
    ErrorWrongSymbol
}
