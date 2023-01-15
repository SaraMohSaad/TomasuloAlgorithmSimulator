
public class LoadBuffer {

	String id ;
	int busy;
	String opCode;
	int address;
	String destreg;
	int latencyLoad = 2;
	double result;
	int finishcycle=0;
	boolean started=false;
	public LoadBuffer(String id) {
		this.id=id;
		busy=0;
	}
	public void addinstruction(Instructions instruction , RegisterFile registers) {
		System.out.println(id +" "+"is fetched");
		this.busy=1;
		this.opCode=instruction.opCode;
		this.address=instruction.effectiveaddress;
		this.destreg=instruction.destregMem;
		registers.setQi(destreg, id);
		
	}
	public void removeinstruction() {
		this.address=0;
		this.opCode="";
		this.busy=0;
		this.destreg="";
	}
	public void startexecuting(int cycle , Memory m) {
		finishcycle = cycle +latencyLoad;
		started=true;
		  result = m.memory[address];
	}
	public RegisterFile checkiffinishexecuting(int cycle , RegisterFile registers ,Memory m) {
		if (cycle==finishcycle) {
			if(opCode.equals("L.D"))
			     registers.setRegisterData(destreg, result);
//			if(opCode.equals("S.D"))
//			  m.memory[address]=registers.getRegisterData(destreg);	
			registers.setQi(destreg, "0");
			this.opCode=null;
			this.busy=0;
			this.address=0;
			this.destreg=null;
			this.started=false;
			this.result=0.0;
			System.out.println(id+" "+"finished executing");
//			if (id.equals(registers.getQi(destreg)))
           	
//			}
//		if (cycle>finishcycle && finishcycle!=0) {
//			if (id.equals(registers.getQi(destreg)))
//				registers.setQi(destreg, "0");
		}
		return registers;
	}
	public boolean Ready() {
		
		return this.busy==1 && this.started==false;
	}
}
