
public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Simulator s = new Simulator();
		s.registerfile.setRegisterData("F2", 11.2);
		s.registerfile.setRegisterData("F4", 11.2);
		s.registerfile.setRegisterData("F6", 22.8);
		s.registerfile.setRegisterData("F8", 33.0);
		s.registerfile.setRegisterData("F10", 44.0);
		s.registerfile.setRegisterData("F12", 22.2);
       // s.registerfile.printregfile();

		for (int i=1;i<=19;i++) {			
			System.out.println("Clock Cycle: " + i);
			s.getnextinstruction();
			if (i!=1)
				s.startexeucting(i);
			s.reservationstations.checkingforupdates();
			s.reservationstations.drawTable();
			
			//s.reservationstations.prints();
			//s.reservationstations.printReservationStationsInfo(i);
			//s.reservationstations.printLoads(i);
			
			s.readyInstructions();
			System.out.println("RegisterFile : "+s.registerfile.Qi);
			//System.out.println(s.memory.memory[10]);
			//System.out.println(s.reservationstations.A1.qk);
			System.out.println("---------------------------------------------------------------------------------------------------------");
			}
		
	}

}
