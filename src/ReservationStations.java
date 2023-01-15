import java.util.HashMap;
import java.util.Formatter;

public class ReservationStations {

	ALUReservationStations A1;
	ALUReservationStations A2;
	ALUReservationStations A3;
	ALUReservationStations M1;
	ALUReservationStations M2;
	LoadBuffer L1;
	LoadBuffer L2;
	LoadBuffer L3;
	StoreBuffer S1;
	StoreBuffer S2;
	StoreBuffer S3;
	RegisterFile registers;
	HashMap<ALUReservationStations, Boolean> checkReadyStations;
	HashMap<LoadBuffer, Boolean> checkReadyStationsLoad;
	HashMap<StoreBuffer, Boolean> checkReadyStationsStore;
	
	public ReservationStations(RegisterFile registerfile) {
		A1= new ALUReservationStations("A1");
		A2= new ALUReservationStations("A2");
		A3= new ALUReservationStations("A3");
		M1= new ALUReservationStations("M1");
		M2= new ALUReservationStations("M2");
		L1= new LoadBuffer("L1");
		L2= new LoadBuffer("L2");
		L3= new LoadBuffer("L3");
		S1 = new StoreBuffer("S1");
		S2 = new StoreBuffer("S2");
		S3 = new StoreBuffer("S3");
		registers = registerfile;
		checkReadyStations = new HashMap<ALUReservationStations, Boolean>();
		checkReadyStationsLoad = new HashMap<LoadBuffer, Boolean>();
		checkReadyStationsStore = new HashMap<StoreBuffer, Boolean>();
	}
	public boolean addinstructions (Instructions instruction) {
		if (instruction.opCode.equals("ADD.D") || instruction.opCode.equals("SUB.D")) {
			//System.out.print(registers.getRegisterData("F2"));
			if(A1.busy==0) { 
				A1.addinstruction(instruction, registers);
				return true;
			}
			else if(A2.busy==0) {
				A2.addinstruction(instruction, registers);
				return true;
				}
			else if (A3.busy==0) {
				A3.addinstruction(instruction, registers);
				return true;
			}
			else {
				System.out.print("not enough place");
			    return false;
			}
		     
	}else if (instruction.opCode.equals("MUL.D") || instruction.opCode.equals("DIV.D")) {
		if(M1.busy==0) { 
			M1.addinstruction(instruction, registers);
		//	if(M1.destreg.equals(M1.firstOP) || M1.destreg.equals(M1.secondOP))
			//	registers.setQi(M1.destreg, M1.id);
			return true;
		}
		else if(M2.busy==0) {
			M2.addinstruction(instruction, registers);
			return true;
		}
		else {
			System.out.print("not enough place");
			return false;
		}
	     
}
	else if (instruction.opCode.equals("L.D")) {
		if(L1.busy==0) { 
			L1.addinstruction(instruction, registers);
			return true;
		}
		else if(L2.busy==0) {
			L2.addinstruction(instruction, registers);
			return true;
		}
		else if(L3.busy==0) {
			L3.addinstruction(instruction, registers);
			return true;
		}
		else {
			System.out.println("not enough place");
			return false;
		}
	}else if (instruction.opCode.equals("S.D")) {
		if(S1.busy==0) { 
			S1.addinstruction(instruction, registers);
			return true;
		}
		else if(S2.busy==0) {
			S2.addinstruction(instruction, registers);
			return true;
		}
		else if(S3.busy==0) {
			S3.addinstruction(instruction, registers);
			return true;
		}
		else {
			System.out.println("not enough place");
			return false;
		}
	}
		
		return false;
	}
	public void checkingforupdates() {
		if (A1.busy==1)
			A1.checkingforupdates(registers);
		if (A2.busy==1)
			A2.checkingforupdates(registers);
		if (A3.busy==1)
			A3.checkingforupdates(registers);
		if (M1.busy==1)
			M1.checkingforupdates(registers);
		if (M2.busy==1)
			M2.checkingforupdates(registers);
		if (S1.busy==1)
			S1.checkingforupdates(registers);
		if (S2.busy==1)
			S2.checkingforupdates(registers);
		if (S3.busy==1)
			S3.checkingforupdates(registers);
	}
	public void checkreadyStations() {
		checkReadyStations.put(A1, A1.Ready());
		checkReadyStations.put(A2, A2.Ready());
		checkReadyStations.put(A3, A3.Ready());
		checkReadyStations.put(M1, M1.Ready());
		checkReadyStations.put(M2, M2.Ready());
		checkReadyStationsLoad.put(L1, L1.Ready());
		checkReadyStationsLoad.put(L2, L2.Ready());
		checkReadyStationsLoad.put(L3, L3.Ready());
		checkReadyStationsStore.put(S1, S1.Ready());
		checkReadyStationsStore.put(S2, S2.Ready());
		checkReadyStationsStore.put(S3, S3.Ready());
		//System.out.print(checkReadyStations);
	}
//	public void printReservationStationsInfo(int clkCycle) {
//		String leftAlignFormat = "| %-2s | %-6s | %-14f | %-14f | %-4s | %-4s | %-4d |%n";
//
//		System.out.format("+----+--------+----------------+----------------+------+------+------+%n");
//		if (clkCycle / 10 == 0)
//			System.out.format("| " + clkCycle + "  | OpCode |        VJ      |        VK      |  QJ  |  QK  | BUSY |%n");
//		else
//			System.out.format("|" + clkCycle + "  | OpCode |        VJ      |        VK      |  QJ  |  QK  | BUSY |%n");
//		System.out.format("+----+--------+----------------+----------------+------+------+------+%n");
//		System.out.format(leftAlignFormat, "A1", A1.opcode, A1.vj, A1.vk, A1.qj, A1.qk, A1.busy);
//		System.out.format(leftAlignFormat, "A2", A2.opcode, A2.vj, A2.vk, A2.qj, A2.qk, A2.busy);
//		System.out.format(leftAlignFormat, "A3", A3.opcode, A3.vj, A3.vk, A3.qj, A3.qk, A3.busy);
//		System.out.format("+----+--------+----------------+----------------+------+------+------+%n");
//		System.out.format(leftAlignFormat, "M1", M1.opcode, M1.vj, M1.vk, M1.qj, M1.qk, M1.busy);
//		System.out.format(leftAlignFormat, "M2", M2.opcode, M2.vj, M2.vk, M2.qj, M2.qk, M2.busy);
//		//System.out.format(leftAlignFormat, "M3", M3.opCode, M3.vj, M3.vk, M3.qj, M3.qk, M3.busy);
//		System.out.format("+----+--------+----------------+----------------+------+------+------+%n");
//	}
//	public void printLoads(int clkCycle) {
////		String leftAlignFormat = "| %-2s | %-6s | %-14f | %-14f | %-4s | %-4s | %-4d |%n";
////
////		System.out.format("+----+--------+----------------+----------------+------+------+------+%n");
////		if (clkCycle / 10 == 0)
////			System.out.format("| " + clkCycle + "  | OpCode |        VJ      |        VK      |  QJ  |  QK  | BUSY |%n");
////		else
////			System.out.format("|" + clkCycle + "  | OpCode |        VJ      |        VK      |  QJ  |  QK  | BUSY |%n");
////		System.out.format("+----+--------+----------------+----------------+------+------+------+%n");
////		System.out.format(leftAlignFormat, "L1", L1.opCode, L1.destreg, L1.address, "0", "0", L1.busy);
////		System.out.format(leftAlignFormat, "L2", L2.opCode, L2.destreg, L2.address, "0", "0", L2.busy);
////		System.out.format(leftAlignFormat, "L3", L3.opCode, L3.destreg, L3.address, "0", "0", L3.busy);
////		System.out.format("+----+--------+----------------+----------------+------+------+------+%n");
//		System.out.print("L1 :"+L1.opCode +"  "+L1.destreg+"  "+L1.address+"  "+L1.busy);
////		
//	}
//	
	public void drawTable() {
	   Formatter t = new Formatter();
	   t.format("%12s %12s %12s %12s %12s %12s %12s\n", "ID", "Opcode","VJ", "VK","Qj","Qk","Busy");
	   t.format("%12s %12s %12s %12s %12s %12s %12s\n", "", "","", "","","","");
	   t.format("%12s %12s %12s %12s %12s %12s %12s\n", "A1", A1.opcode,A1.vj, A1.vk,A1.qj,A1.qk,A1.busy);
	   t.format("%12s %12s %12s %12s %12s %12s %12s\n", "A2", A2.opcode,A2.vj, A2.vk,A2.qj,A2.qk,A2.busy);
	   t.format("%12s %12s %12s %12s %12s %12s %12s\n", "A3", A3.opcode,A3.vj, A3.vk,A3.qj,A3.qk,A3.busy);
	   t.format("%12s %12s %12s %12s %12s %12s %12s\n", "", "","", "","","","");
	   t.format("%12s %12s %12s %12s %12s %12s %12s\n", "M1", M1.opcode,M1.vj, M1.vk,M1.qj,M1.qk,M1.busy);
	   t.format("%12s %12s %12s %12s %12s %12s %12s\n", "M2", M2.opcode,M2.vj, M2.vk,M2.qj,M2.qk,M2.busy);
	   t.format("%12s %12s %12s %12s %12s %12s %12s\n", "", "","", "","","","");
	   t.format("%12s %12s %12s %12s %12s\n", "ID", "Opcode","Destination", "address","Busy");
	   t.format("%12s %12s %12s %12s %12s %12s %12s\n", "", "","", "","","","");
	   t.format("%12s %12s %12s %12s %12s\n", "L1", L1.opCode,L1.destreg, L1.address,L1.busy);
	   t.format("%12s %12s %12s %12s %12s\n", "L2", L2.opCode,L2.destreg, L2.address,L2.busy);
	   t.format("%12s %12s %12s %12s %12s\n", "L3", L3.opCode,L3.destreg, L3.address,L3.busy);
	   t.format("%12s %12s %12s %12s %12s %12s %12s\n", "", "","", "","","","");
	   t.format("%12s %12s %12s %12s %12s %12s %12s\n", "ID", "Opcode","Destination", "address","V","Q","Busy");
	   t.format("%12s %12s %12s %12s %12s %12s %12s\n", "S1", S1.opCode,S1.reg, S1.address,S1.v,S1.q,S1.busy);
	   t.format("%12s %12s %12s %12s %12s %12s %12s\n", "S2", S2.opCode,S2.reg, S2.address,S2.v,S2.q,S2.busy);
	   t.format("%12s %12s %12s %12s %12s %12s %12s\n", "S3", S3.opCode,S3.reg, S3.address,S3.v,S3.q,S3.busy);
	   System.out.print(t);
	}
	
	public void prints() {
		System.out.print(registers.getRegisterData("F2"));
	}
}
