package utils;


public class Assert {

	public static void assertTrue(ValidationType vType, String message, boolean condition){
		try{
			org.testng.Assert.assertTrue(condition, message);
			report(vType, message, null);//executed when above step is passed
		}catch(AssertionError e){
			report(vType, message, e);
		}
	}
	
	public static void assertEquals(ValidationType vType, String expected, String actual, String message){
		try{
			org.testng.Assert.assertEquals(actual, expected);
			report(vType, message, null);
		}catch(AssertionError e){
			report(vType, message, e);
		}
	}

	private static void report(ValidationType vType, String message, AssertionError exp){
		if(exp == null){
			switch(vType){
			case ASSERT:
			case VERIFY:
				Report.log(Status.PASS, message);
				break;
			case Assert:
			case Verify:
				Report.log(Status.Pass, message);
				break;
			}
		}else{
			switch(vType){
			case ASSERT:
			case Assert:
				Report.log(Status.Fail, message);
				throw exp;
			case VERIFY:
			case Verify:
				Report.log(Status.Fail, message);
				break;
			}
		}
	}
}
