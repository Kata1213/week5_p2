package tw.core;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.validator.InputValidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 * 在InputValidatorTest文件中完成InputValidator中对应的单元测试
 */
public class InputValidatorTest {
    private InputValidator inputValidator;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        inputValidator = new InputValidator();
    }

    @Test
    public void test_should_return_false_when_give_5_digit() {
        //Given
        String expectAnswer = "1 2 3 4 5";

        //Then
        assertFalse(inputValidator.validate(expectAnswer));
    }

    @Test
    public void test_should_return_true_when_give_4_digit() {
        //Given
        String expectAnswer = "1 2 3 4";

        //Then
        assertTrue(inputValidator.validate(expectAnswer));
    }

    @Test
    public void test_should_return_an_NumberFormatException_when_give_wrong_digit() {
        //Given
        String wrongDigit = "a";
        String expectAnswer = "1 2 3 a";

        //Then
        expectedException.expect(NumberFormatException.class);
        expectedException.expectMessage("For input string: \"" + wrongDigit + "\"");
        inputValidator.validate(expectAnswer);

    }

    @Test
    public void test_should_return_an_OutOfRangeAnswerException_when_give_repeat_digit() {
        //Given
        String expectAnswer = "1 2 3 3";

        //Then
        assertFalse(inputValidator.validate(expectAnswer));

    }

    @Test
    public void test_should_return_an_OutOfRangeAnswerException_when_give_digit_greater_than_9() {
        //Given
        String expectAnswer = "1 2 3 10";

        //Then
        assertFalse(inputValidator.validate(expectAnswer));

    }

    @Test
    public void test_should_return_an_OutOfRangeAnswerException_when_give_digit_without_trim() {
        //Given
        String expectAnswer = "1 2 34";

        //Then
        assertFalse(inputValidator.validate(expectAnswer));
    }
}
