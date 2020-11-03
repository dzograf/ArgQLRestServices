package gr.forth.ics.argql.results;

import gr.forth.ics.utils.Enums.VariableType;

public class Variable {

	public VariableType type;
	public String name;


	public Variable(String name, VariableType type) {
		this.name = name;
		this.type = type;		
	}
}
