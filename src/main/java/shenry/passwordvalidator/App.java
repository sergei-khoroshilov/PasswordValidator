package shenry.passwordvalidator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        PasswordValidator validator = PasswordValidator.getBuilder()
                                                       .setMinLength(2)
                                                       .setAllowDigits(true)
                                                       .build();

        ValidateResult result = validator.validate("196");

        System.out.println("result = " + result);
    }
}
