
public class StoreBuffer {

	String id ;
	int busy;
	String opCode;
	int address;
	String reg;
	Double v;
	String q;
	int latencyLoad = 3;
	double result;
	int finishcycle=0;
	boolean started=false;
	public StoreBuffer(String id) {
		this.id=id;
		busy=0;
		this.q=null;
		this.v=null;
	}
	public void addinstruction(Instructions instruction , RegisterFile registers) {
		System.out.println(id +" "+"is fetched");
		this.busy=1;
		this.opCode=instruction.opCode;
		this.address=instruction.effectiveaddress;
		this.reg=instruction.destregMem;
		if (registers.getQi(reg).equals("0"))
		    this.v=registers.getRegisterData(reg);
		else 
			this.q=registers.getQi(reg);
		//registers.setQi(reg, id);
		
	}
	public void removeinstruction() {
		this.address=0;
		this.opCode="";
		this.busy=0;
		this.reg="";
	}
	public void startexecuting(int cycle , Memory m) {
		finishcycle = cycle +latencyLoad;
		started=true;
		  result = m.memory[address];
		  
	}
	public void checkingforupdates (RegisterFile registers) {
		
		  if (registers.getQi(reg)=="0") {
			 this.v=registers.getRegisterData(reg);
			 this.q=null;
		  }else 
			 this.q=registers.getQi(reg);
	}
	public RegisterFile checkiffinishexecuting(int cycle , RegisterFile registers ,Memory m) {
		if (cycle==finishcycle) {
			if(opCode.equals("S.D"))
			  m.memory[address]=registers.getRegisterData(reg);	
			registers.setQi(reg, "0");
			this.opCode=null;
			this.busy=0;
			this.address=0;
			this.reg=null;
			this.q=null;
			this.v=null;
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
		
		return this.busy==1 && this.started==false && this.q==null;
	}
}
