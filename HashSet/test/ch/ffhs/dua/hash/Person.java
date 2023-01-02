package ch.ffhs.dua.hash;

public class Person {
	private String surname;
	private String name;
	
	public Person(String surname, String name) {
		this.surname = surname;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Person(" + this.surname + "," + this.name + ")";
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Person)) {
			return false;
		}
		Person other = (Person) obj;
		return this.surname.equals(other.surname) && this.name.equals(other.name);
	}
	
	@Override
	public int hashCode() {
		return 31 * this.surname.hashCode() + 17 * this.name.hashCode();
	}
}