package shenry.passwordvalidator;

public class PasswordValidator {
    private final int minLength;
    private final int maxLength;

    private final boolean allowDigits;
    private final boolean allowLowercaseEnglishLetters;
    private final boolean allowUppercaseEnglishLetters;

    //private final Set<Character> availableSymbols;
    //private final List<ICustomValidator> customValidators;

    public PasswordValidator() {
        minLength = 6;
        maxLength = 20;

        allowDigits = true;
        allowLowercaseEnglishLetters = true;
        allowUppercaseEnglishLetters = true;

        //availableSymbols = Utils.createSet("\\/.,[]");
        //customValidators = new ArrayList<ICustomValidator>();
    }

    protected PasswordValidator(int minLength, int maxLength,
                                boolean allowDigits, boolean allowLowercaseEnglishLetters, boolean allowUppercaseEnglishLetters) {
        this.minLength = minLength;
        this.maxLength = maxLength;

        this.allowDigits = allowDigits;
        this.allowLowercaseEnglishLetters = allowLowercaseEnglishLetters;
        this.allowUppercaseEnglishLetters = allowUppercaseEnglishLetters;
    }

    public int getMinLength() {
        return minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public boolean isAllowDigits() {
        return allowDigits;
    }

    public boolean isAllowLowercaseEnglishLetters() {
        return allowLowercaseEnglishLetters;
    }

    public boolean isAllowUppercaseEnglishLetters() {
        return allowUppercaseEnglishLetters;
    }

    public ValidateResult validate(String password) {

        if (password == null) {
            throw new IllegalArgumentException();
        }

        if (password.length() < minLength) {
            return ValidateResult.ErrorShortPassword;
        }

        if (password.length() > maxLength) {
            return ValidateResult.ErrorLongPassword;
        }

        for (int i = 0; i < password.length(); i++) {
            Character ch = password.charAt(i);

            if (Character.isDigit(ch) && !allowDigits) {
                return ValidateResult.ErrorDigitsNotAllowed;
            }

            if (isLowercaseEnglishLetter(ch) && !allowLowercaseEnglishLetters) {
                return ValidateResult.ErrorLowercaseEnglishNotAllowed;
            }

            if (isUppercaseEnglishLetter(ch) && !allowUppercaseEnglishLetters) {
                return ValidateResult.ErrorUppercaseEnglishNotAllowed;
            }
        }

        return ValidateResult.Success;
    }

    private boolean isLowercaseEnglishLetter(Character ch) {
        return (ch >= 'a' && ch <= 'z');
    }

    private boolean isUppercaseEnglishLetter(Character ch) {
        return (ch >= 'A' && ch <= 'Z');
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {

        private int minLength = 0;
        private int maxLength = Integer.MAX_VALUE;

        private boolean allowDigits = false;
        private boolean allowLowercaseEnglishLetters = false;
        private boolean allowUppercaseEnglishLetters = false;

        public Builder setMinLength(int minLength) {
            if (minLength < 0) {
                throw new IllegalArgumentException("minLength should be >= 0");
            }

            this.minLength = minLength;
            return this;
        }

        public Builder setMaxLength(int maxLength) {
            if (maxLength < 0) {
                throw new IllegalArgumentException("maxLength should be >= 0");
            }

            this.maxLength = maxLength;
            return this;
        }

        public Builder setAllowDigits(boolean allowDigits) {
            this.allowDigits = allowDigits;
            return this;
        }

        public Builder setAllowLowercaseEnglishLetters(boolean allowLowercaseEnglishLetters) {
            this.allowLowercaseEnglishLetters = allowLowercaseEnglishLetters;
            return this;
        }

        public Builder setAllowUppercaseEnglishLetters(boolean allowUppercaseEnglishLetters) {
            this.allowUppercaseEnglishLetters = allowUppercaseEnglishLetters;
            return this;
        }

        public PasswordValidator build() {
            return new PasswordValidator(minLength, maxLength,
                    allowDigits,
                    allowLowercaseEnglishLetters, allowUppercaseEnglishLetters);
        }
    }
}
