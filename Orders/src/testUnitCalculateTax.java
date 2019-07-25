import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

class testUnitCalculateTax {
	
	@BeforeClass
	public void initClass() {
		
		//imported and non-exempt product
		//not-importend and non exempt product
		//not-imported and exempt product
		//imported and exempt product		
		
	}

	@Test // check total of imported product
	public void testCalulateImportedTax() {

		Product book1 = new Product("harryPotter", ProductType.BOOK, ProductOrigin.IMPORTED, new Money("10.00"));
		
		Tax importedTax = Tax.create("importedTax", "5%", ProductOrigin.IMPORTED);
		ArrayList<Tax> taxToApplied = new ArrayList<Tax>();
		taxToApplied.add(importedTax);
		
		Order order1 = new Order(taxToApplied);
		order1.add(book1, new int[] {1});
		
		assertEquals(order1.getTotal().amount, new Money("10.50").amount.setScale(2));
	}

	
	@Test //check total of exempt tax and not imported product
	void testCheckExemptionGood() {
		
		Product med1 = new Product("Aspirine", ProductType.MEDICAL, ProductOrigin.NOTIMPORTED, new Money("10.00"));
		
		ArrayList<ProductType> exemptionTypes = new ArrayList<ProductType>();
		exemptionTypes.add(ProductType.BOOK);
		exemptionTypes.add(ProductType.FOOD);
		exemptionTypes.add(ProductType.MEDICAL);	
		
		Tax basicSalesTax = Tax.create("basicSalesTax", "10%", exemptionTypes);
		ArrayList<Tax> taxToApplied = new ArrayList<Tax>();
		taxToApplied.add(basicSalesTax);
		
		Order order1 = new Order(taxToApplied);
		order1.add(med1, new int[] {1});
		assertEquals(order1.getTotal().amount, new Money("10.00").amount.setScale(2));
		
	}
	
	@Test //check total of exempt tax and not imported product
	void testImportBasicTaxes() {
		Product mus1 = new Product("Mozart", ProductType.MUSIC, ProductOrigin.IMPORTED, new Money("10.00"));
		ArrayList<ProductType> exemptionTypes = new ArrayList<ProductType>();
		exemptionTypes.add(ProductType.BOOK);
		exemptionTypes.add(ProductType.FOOD);
		exemptionTypes.add(ProductType.MEDICAL);
		
		Tax basicSalesTax = Tax.create("basicSalesTax", "10%", exemptionTypes);
		Tax importedTax = Tax.create("importedTax", "5%", ProductOrigin.IMPORTED);
		ArrayList<Tax> taxToApplied = new ArrayList<Tax>();
		taxToApplied.add(basicSalesTax);
		taxToApplied.add(importedTax);
		
		Order order1 = new Order(taxToApplied);
		order1.add(mus1, new int[] {1});
		assertEquals(order1.getTotal().amount, new Money("10.00").amount.setScale(2));
	}
	
	
	
}
