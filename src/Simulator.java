import java.util.ArrayList;

public class Simulator {

	ReadFile readfile;
	RegisterFile registerfile;
	ReservationStations reservationstations;
	ArrayList<Instructions> instructions;
	ArrayList<Instructions> instructionsToBeFetched;
	Memory memory;
	int nextins;
	public Simulator() {
		readfile = new ReadFile("Instructions.txt");
		registerfile = new RegisterFile();
		reservationstations = new ReservationStations(registerfile);
		instructions = new ArrayList<Instructions>();
	     memory=new Memory();
		for (int i=0;i<readfile.Instructions.size();i++) {
			String s = readfile.Instructions.get(i);
			Instructions ins = new Instructions(s.split(" "), registerfile);
			instructions.add(ins);
		}
		nextins=0;
		instructionsToBeFetched = new ArrayList<Instructions>();
		
	}
	public void getnextinstruction() {
		if (nextins < instructions.size()) {
			if (reservationstations.addinstructions(instructions.get(nextins))==true) {
				instructionsToBeFetched.add(instructions.get(nextins));
				nextins++;
				//System.out.print(registerfile.getRegisterData("F2"));
			}
		}
		//else System.out.print("Intructions finished");
	}
	public void readyInstructions() {
		this.reservationstations.checkreadyStations();
	}
	public void startexeucting(int cycle) {
		if(reservationstations.checkReadyStations.get(reservationstations.A1)) {
			reservationstations.A1.startexecuting(cycle);
			System.out.println(" A1 Started Executing ");
		}
		if(reservationstations.checkReadyStations.get(reservationstations.A2)) {
			reservationstations.A2.startexecuting(cycle);
			System.out.println(" A2 Started Executing ");
		}
		if(reservationstations.checkReadyStations.get(reservationstations.A3)) {
			reservationstations.A3.startexecuting(cycle);
			System.out.println(" A3 Started Executing ");
		}
		if(reservationstations.checkReadyStations.get(reservationstations.M1)) {
			reservationstations.M1.startexecuting(cycle);
			System.out.println(" M1 Started Executing ");
		}
		if(reservationstations.checkReadyStations.get(reservationstations.M2)) {
			reservationstations.M2.startexecuting(cycle);
			System.out.println(" M2 Started Executing ");
		}
		if (reservationstations.checkReadyStationsLoad.get(reservationstations.L1)) {
			reservationstations.L1.startexecuting(cycle, memory);
			System.out.println(" L1 Started Executing ");
		}
		if (reservationstations.checkReadyStationsLoad.get(reservationstations.L2)) {
			reservationstations.L2.startexecuting(cycle, memory);
			System.out.println(" L2 Started Executing ");
		}
		if (reservationstations.checkReadyStationsLoad.get(reservationstations.L3)) {
			reservationstations.L3.startexecuting(cycle, memory);
			System.out.println(" L3 Started Executing ");
		}
		if (reservationstations.checkReadyStationsStore.get(reservationstations.S1)) {
			reservationstations.S1.startexecuting(cycle, memory);
			System.out.println(" S1 Started Executing ");
		}
		if (reservationstations.checkReadyStationsStore.get(reservationstations.S2)) {
			reservationstations.S2.startexecuting(cycle, memory);
			System.out.println(" S2 Started Executing ");
		}
		if (reservationstations.checkReadyStationsStore.get(reservationstations.S3)) {
			reservationstations.S3.startexecuting(cycle, memory);
			System.out.println(" S3 Started Executing ");
		}
		
		registerfile=reservationstations.A1.checkiffinishexec(cycle, registerfile);
		registerfile=reservationstations.A2.checkiffinishexec(cycle, registerfile);
		registerfile=reservationstations.A3.checkiffinishexec(cycle, registerfile);
		registerfile=reservationstations.M1.checkiffinishexec(cycle, registerfile);
		registerfile=reservationstations.M2.checkiffinishexec(cycle, registerfile);
		registerfile=reservationstations.L1.checkiffinishexecuting(cycle, registerfile,memory);
		registerfile=reservationstations.L2.checkiffinishexecuting(cycle, registerfile,memory);
		registerfile=reservationstations.L3.checkiffinishexecuting(cycle, registerfile,memory);
		registerfile=reservationstations.S1.checkiffinishexecuting(cycle, registerfile,memory);
		registerfile=reservationstations.S2.checkiffinishexecuting(cycle, registerfile,memory);
		registerfile=reservationstations.S3.checkiffinishexecuting(cycle, registerfile,memory);

	}
}
