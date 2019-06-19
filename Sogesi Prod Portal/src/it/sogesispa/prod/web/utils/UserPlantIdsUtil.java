package it.sogesispa.prod.web.utils;

import it.sogesispa.prod.web.models.jpa.TUser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List; 

public class UserPlantIdsUtil {
	

	public static void main(String[] aa){
		TUser user = new TUser();
		user.setPlantAccess(new BigDecimal( (8 + 16 + 1 ) ));
		int plantAccess = user.getPlantAccess().intValue();
		List<Long> lista = getPlantIds(plantAccess);
		
		for (Long integer : lista) {
			System.out.println( integer );
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public static List<Long> getPlantIds(int plantAccess){
		List<Long> result = new ArrayList<Long>();
		
		
		
		for (int i = 0; true; i++) {
			int val  = (int) (plantAccess%2 * (  Math.pow(2, i) ));
			
			if (val > 0) {
				result.add(new Long(val));
			}
			
			plantAccess = plantAccess/2;
			
			if (plantAccess==0) {
				break;
			}
		}
		
		
		return result;
	}
}
