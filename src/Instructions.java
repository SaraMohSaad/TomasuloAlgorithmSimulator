
public class Instructions {

	String opCode;
	String destreg;
	String destregMem;
	String reg1;
	String reg2;
	String memreg;
	String memaddress;
	int effectiveaddress;
	public Instructions(String[] instruction , RegisterFile registers) {
		this.opCode = instruction[0];
		if(opCode.equals("ADD.D") || opCode.equals("MUL.D") || opCode.equals("SUB.D") || opCode.equals("DIV.D")) {
			String [] variables = instruction[1].split(",");
			destreg=variables[0];
			reg1= variables[1];
			reg2=variables[2];
		}
		else if (opCode.equals("L.D") || opCode.equals("S.D")) {
			String [] variables = instruction[1].split(",");
			destregMem=variables[0];
			effectiveaddress=Integer.parseInt(variables[1]);
			//memaddress(variables[1]);
			//finaladdress=(int)(Integer.parseInt(memaddress)+registers.getRegisterData(memreg));
		}
	}
	public void memaddress (String s) {
		String temp = "";
		for(int i=0;i<s.length();i++) {
			if (s.charAt(i)=='(')
				break;
			else temp+=s.charAt(i);
		}
		memaddress=temp;
		String temp2 = "";
		boolean regfound=false;
		for(int i=0;i<s.length();i++) {
			 if (s.charAt(i)==')')
			  	regfound=false;
			if(regfound)
		    	temp2+=s.charAt(i);
		    if (s.charAt(i)=='(')
				regfound=true;
		   
		}
		memreg=temp2;
	}
	/*public static void main(String[]args) {
		String[] s = new String[8];
		s[0]="L.D";
		s[1]="F1,0(F1)";
		Instructions i = new Instructions(s); 
		i.memaddress("98(F43)");
		System.out.println(i.memaddress);
		System.out.print(i.memreg);
		   
		}*/
	public void printIns() {
		System.out.print(this.destreg);
	}
}
