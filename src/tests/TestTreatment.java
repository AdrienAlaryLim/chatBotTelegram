package tests;

import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import core.Treatment;

@RunWith(MockitoJUnitRunner.class)
public class TestTreatment {
	
	@Mock
	private Treatment treatment;
	
	@Test
	public void test_TreatmentFindQuestionByWholeWords()
	{
		String questionByWholeWords = TestConstantsValues.QUESTION_FOUND_BY_WORDS;
		
		
		
		String responseTreatment = treatment.returnMessage(questionByWholeWords);
		
	}
}
