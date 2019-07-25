import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class testUnitOrder {

	@Test
	void testPrintOrder() {

		Product book1 = new Product("harryPotter", ProductType.BOOK, ProductOrigin.NOTIMPORTED, new Money("12.49"));

		
		ArrayList<Tax> taxToApplied = new ArrayList<Tax>();
		Order order1 = new Order(taxToApplied);

		order1.add(book1, new int[] {1});
		order1.printBill();

	}

}
