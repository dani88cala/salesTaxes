import java.util.ArrayList;

public class StartUpClass {
	
	public static void main(String [] args) {
		
		Product book1 = new Product("harryPotter", ProductType.BOOK, ProductOrigin.NOTIMPORTED, new Money("12.49"));
		ArrayList<ProductType> exemptionTypes = new ArrayList<ProductType>();
		exemptionTypes.add(ProductType.BOOK);
		exemptionTypes.add(ProductType.FOOD);
		exemptionTypes.add(ProductType.MEDICAL);
		
		//Tax basicSalesTax = Tax.create("basicSalesTax", "10%", exemptionTypes);
		//Tax importedTax = Tax.create("importedTax", "5%", ProductOrigin.IMPORTED);

		ArrayList<Tax> taxToApplied = new ArrayList<Tax>();
		//taxToApplied.add(basicSalesTax);
		//taxToApplied.add(importedTax);

 
		Order order1 = new Order(taxToApplied);
		order1.add(book1, new int[] {1});
		order1.printBill();
		/*


        //Tax basicSalesTax = new Tax("basicSalesTax", new BigDecimal("0.10"), exemption(ProductType.BOOK, ProductType.FOOD, ProductType.MEDICAL));
 		 */
		
		
		
	}
	

}
