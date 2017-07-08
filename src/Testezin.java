
// Algoritmo de inserção no horário.	
public class Testezin {

	public static void teste(int horaini, int horafim) {
		
		int[] horasini = { 690, 720, 845 };
		int[] horasfim = { 700, 839, 960 };
		int FINAL = 2;
		int curr = 0;
		System.out.printf("Caso %d %d",horaini,horafim );
		while (curr <= FINAL) {
			System.out.println("ROdada !");
			if ((horasini[curr] > horafim) && (horasini[curr] > horaini)) {
				System.out.println("Aqui!");
				// index = curr;
				// MOVEPARADIREITA(HORAS INI, curr) && MOVEPARADIREITA(HORAS FIM, curr);
				// HOrasINI[curr] = HOrasININOVO;
				// HorasFIM[curr] = HORASFIMNOVO;
				// return index;
				break;
			} else if (horasini[curr] <= horaini && horaini <= horasfim[curr]) {
				System.out.println("inicio intersec");
				break;
//689, 701  .  (688, 889)
			} else if ((horasini[curr] <= horafim) && (horafim <= horasfim[curr])) {
				System.out.println("FIM INTERSEC");
				break;
			} else if (horaini < horasini[curr] && horafim > horasfim[curr]) {
				System.out.println("ENGLOBA");
				break;
			} else {
				System.out.println("FRENTE!");
			}
			curr++;
		}
		
		System.out.println("\n");
	}

	public static void main(String[] args) {
	
	}
}