package org.vadim.genetics;

public class Gene {
	private boolean value;

	public Gene(boolean value) {
		this.value = value;
	}

	public boolean getValue() {
		return value;
	}
	
	public void setValue(boolean value) {
		this.value = value;
	}
	
	public Gene copy() {
		return new Gene(value);
	}
}
