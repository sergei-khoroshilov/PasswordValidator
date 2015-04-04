# PasswordValidator
Simple password validator for java

## Build

To build with maven:

```
$ mvn clean package
```

## Usage

``` java

PasswordValidator validator = PasswordValidator
                                .getBuilder()
                                .setMinLength(6)
                                .setMaxLength(20)
                                .setAllowDigits(true)
                                .setAllowUppercaseEnglishLetters(true)
                                .setAllowLowercaseEnglishLetters(true)
                                .build();

ValidateResult result = validator.validate("16abcaA");

```
