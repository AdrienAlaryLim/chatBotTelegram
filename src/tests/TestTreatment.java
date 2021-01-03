package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import core.RequestTreatment;
import core.Treatment;

@RunWith(MockitoJUnitRunner.class)
public class TestTreatment {
	
	@InjectMocks
	Treatment treatment;
	
	@InjectMocks
	RequestTreatment requestTreament;
	
	@SuppressWarnings("static-access")
	@Test
	public void test_TreatmentFindQuestionByWholeWords()
	{
		String questionByWholeWords = TestConstantsValues.QUESTION_FOUND_BY_WORDS;
		
		Mockito.when(requestTreament.returnQueryStringResponse(Mockito.any(), Mockito.anyString())).thenReturn(TestConstantsValues.QUESTION_ID_STRING);
		
		Mockito.when(requestTreament.returnQueryStringResponse(Mockito.any(), Mockito.anyString())).thenReturn(TestConstantsValues.RESPONSE_FOUND_BY_WORDS);
		
		String responseTreatment = treatment.returnMessage(questionByWholeWords);
		
		assertEquals(responseTreatment, TestConstantsValues.RESPONSE_FOUND_BY_WORDS);
	}
}
