
public class ALUReservationStations {
	String id ;
	int busy;
	String opcode;
	Double vj ;
	Double vk ;
	String qj=null;
	String qk=null;
	String firstOP;
	String secondOP;
	String address;
	String destreg;
	int latencyAddSub = 2;
	int latencyMulDiv=10;
	int latencyMulDiv2=40;
	double result;
	int finishcycle;
	boolean started=false;
	public ALUReservationStations(String id) {
		this.id=id;
		this.busy=0;
		this.qj=null;
		this.qk=null;
		this.vk=null;
		this.vj=null;
	}
	public void addinstruction(Instructions i , RegisterFile registers) {
		System.out.println(id +" "+"is fetched");
		busy=1;
		this.opcode=i.opCode;
		this.firstOP=i.reg1;
		this.secondOP=i.reg2;
		this.destreg=i.destreg;
		
		if(registers.getQi(firstOP)=="0") {
			this.vj=registers.getRegisterData(firstOP);
		}else 
			this.qj=registers.getQi(firstOP);
		if(registers.getQi(secondOP)=="0") {
			this.vk=registers.getRegisterData(secondOP);
		}
		else 
			this.qk=registers.getQi(secondOP);
//		if(destreg.equals(firstOP) || destreg.equals(secondOP))
//			;
//		else	
		     registers.setQi(destreg, id);
//		     if(destreg.equals(firstOP))
//		    	 this.qj=null;
//		     if(destreg.equals(secondOP))
//		    	 this.qk=null;
	}

	public void startexecuting (int cycle) {
		if(opcode.equals("ADD.D")) {
			result=vj+vk;
			finishcycle=cycle+latencyAddSub;
		}
		if(opcode.equals("SUB.D")) {
			result=vj-vk;
			finishcycle=cycle+latencyAddSub;
		}
		if(opcode.equals("MUL.D")) {
			result=vj*vk;
			finishcycle=cycle+latencyMulDiv;
		}
		if(opcode.equals("DIV.D")) {
			result=vj/vk;
			finishcycle=cycle+latencyMulDiv2;
		}
		this.started=true;
	}
	public RegisterFile checkiffinishexec(int cycle, RegisterFile registers) {
		if(finishcycle== cycle) {
			  registers.setQi(destreg, "0");
			registers.setRegisterData(destreg, result);
			this.vj = null;
			this.vk = null;
			this.qj = null;
			this.qk = null;
			this.firstOP = null;
			this.secondOP = null;
			this.busy = 0;
			this.opcode = null;
			this.destreg=null;
			this.started=false;
			System.out.println(id+" "+"finished executing");
		}
		return registers;
			
	}
//	public void removeinstruction () {
//		this.vj = null;
//		this.vk = null;
//		this.qj = null;
//		this.qk = null;
//		this.firstOP = null;
//		this.secondOP = null;
//		this.busy = 0;
//		this.opcode = null;
//		this.destreg=null;
//		this.started=false;
//	}
	public void checkingforupdates (RegisterFile registers) {
		if (registers.getQi(firstOP)=="0") {
			this.vj=registers.getRegisterData(firstOP);
			this.qj="";
		}else if(vj==null)
			     if(qj==null)
			this.qj=registers.getQi(firstOP);
		if (registers.getQi(secondOP)=="0") {
			this.vk=registers.getRegisterData(secondOP);
			this.qk="";
		}else if(vk==null)
			     if(qk==null)
			        this.qk=registers.getQi(secondOP);
			
			
	}
	public boolean Ready() {
		return (this.qj == null || this.qj=="") && (this.qk == null || this.qk=="") && this.busy==1 && started==false;
	}
	public void printall() {
		System.out.print(vj);
	}
}
