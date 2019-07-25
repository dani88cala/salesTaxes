import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class testUnitMoney {

	@Test
	void testAddTax() {
		Money m1 = new Money("100");
		
		//add 5% of tax
		Money expected = m1.addTax("5%");		
		assertEquals(expected.amount.toString(), new Money("105.00").amount.toString());		
		assertEquals(expected.amount, new Money("105.00").amount);		
	}
	
	@Test
	//check the round up to 0.05
	void testUnitRound() {
		Money m2 = new Money("10.01");

		Money m3 = new Money(Money.roundToNearest5Cents(m2.amount));
		assertEquals(m3.amount, new Money("10.00").amount);
		
		
	}
}
