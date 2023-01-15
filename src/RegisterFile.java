import java.util.HashMap;


public class RegisterFile {

	   HashMap <String,Double> regData;
	   HashMap <String,String> Qi;
	   public RegisterFile() {
		 regData = new HashMap<String,Double>();
		 Qi= new HashMap<String,String>();
		 for(int i=0,j=0;i<6;i++,j+=2) {
			 regData.put("F"+j , 0.0);
			 Qi.put("F"+j, "0");
		 }
	   }
	   
	 public Double getRegisterData(String r) {
			return regData.get(r);
		}

	public void setRegisterData(String r , Double value) {
	     regData.put(r, value);	
	}
	public String getQi(String r) {
		return Qi.get(r);
	}

public void setQi(String r , String value) {
     Qi.put(r, value);	
}
	public void printregfile() {
		for (int i=0;i<regData.size();i++) {
			System.out.print(regData);
		}
	}
	
	
	
	
/*public static void main(String[]args) {
   RegisterFile r = new RegisterFile();
   System.out.print(r.regData);
}	*/
}