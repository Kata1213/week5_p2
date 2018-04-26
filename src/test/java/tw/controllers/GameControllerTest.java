package tw.controllers;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.core.generator.RandomIntGenerator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {
    private RandomIntGenerator randomIntGenerator;
    private AnswerGenerator answerGenerator;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        randomIntGenerator = mock(RandomIntGenerator.class);
        answerGenerator = new AnswerGenerator(randomIntGenerator);
    }

    @Test
    public void test_should_return_an_given_random_answer() throws OutOfRangeAnswerException {
        //Given
        String expectAnswer = "1 2 3 4";

        //When
        when(randomIntGenerator.generateNums(10, 4)).thenReturn(expectAnswer);

        //Then
        assertThat(answerGenerator.generate().toString(), is(expectAnswer));

    }

    @Test
    public void test_should_return_an_NumberFormatException_when_give_wrong_digit() throws OutOfRangeAnswerException {
        //Given
        String wrongDigit = "a";
        String expectAnswer = "1 2 3 a";

        //When
        when(randomIntGenerator.generateNums(10, 4)).thenReturn(expectAnswer);

        //Then
        expectedException.expect(NumberFormatException.class);
        expectedException.expectMessage("For input string: \"" + wrongDigit + "\"");
        answerGenerator.generate();

    }

    @Test
    public void test_should_return_an_OutOfRangeAnswerException_when_give_repeat_digit() throws OutOfRangeAnswerException {
        //Given
        String expectAnswer = "1 2 3 3";

        //When
        when(randomIntGenerator.generateNums(10, 4)).thenReturn(expectAnswer);

        //Then
        expectedException.expect(OutOfRangeAnswerException.class);
        expectedException.expectMessage("Answer format is incorrect");
        answerGenerator.generate();

    }

    @Test
    public void test_should_return_an_OutOfRangeAnswerException_when_give_digit_greater_than_9() throws OutOfRangeAnswerException {
        //Given
        String expectAnswer = "1 2 3 10";

        //When
        when(randomIntGenerator.generateNums(10, 4)).thenReturn(expectAnswer);

        //Then
        expectedException.expect(OutOfRangeAnswerException.class);
        expectedException.expectMessage("Answer format is incorrect");
        answerGenerator.generate();

    }

    @Test
    public void test_should_return_an_OutOfRangeAnswerException_when_give_digit_without_trim() throws OutOfRangeAnswerException {
        //Given
        String expectAnswer = "1 2 34";

        //When
        when(randomIntGenerator.generateNums(10, 4)).thenReturn(expectAnswer);

        //Then
        expectedException.expect(OutOfRangeAnswerException.class);
        expectedException.expectMessage("Answer format is incorrect");
        answerGenerator.generate();
    }
}