package enums;

public enum Areas {
	DESENVOLVIMENTO(1), REDES(2), ARQUITETURA(3), NUCLEO_COMUM(4);
	
	private int areaNumero;
	
	private Areas(int i) {
		areaNumero = i;
	}

	public int getAreaNumero() {
		return areaNumero;
	}
	
}
