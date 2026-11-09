package aadd.beans.conversorvalidador;

import java.util.HashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class NobelApp{
	
	private HashMap<Integer, PremioNobelLit> nobelesLiteratura;
	
	
	public NobelApp() {
		nobelesLiteratura = new HashMap<Integer, PremioNobelLit>();
		
		nobelesLiteratura.put(2025, new PremioNobelLit("László Krasznahorkai",2025));
		nobelesLiteratura.put(2024, new PremioNobelLit("Han Kang",2024));
		nobelesLiteratura.put(2023, new PremioNobelLit("Jon Fosse", 2023));
		nobelesLiteratura.put(2022, new PremioNobelLit("Annie Ernaux", 2022));
		nobelesLiteratura.put(2021, new PremioNobelLit("Abdulrazak Gurnah", 2021));
		nobelesLiteratura.put(2020, new PremioNobelLit("Louise Glück", 2020));
		nobelesLiteratura.put(2019, new PremioNobelLit("Peter Handke", 2019));
		nobelesLiteratura.put(2018, new PremioNobelLit("Olga Tokarczuk", 2018));
		nobelesLiteratura.put(2017, new PremioNobelLit("Kazuo Ishiguro", 2017));
		nobelesLiteratura.put(2016, new PremioNobelLit("Bob Dylan", 2016));
		nobelesLiteratura.put(2015, new PremioNobelLit("Svetlana Aleksiévich", 2015));
	}


	public HashMap<Integer, PremioNobelLit> getNobelesLiteratura() {
		return nobelesLiteratura;
	}
	
	public void addNewNobel(Integer year, String nombre) {
		nobelesLiteratura.put(year, new PremioNobelLit(nombre, year));
	}

	
}
