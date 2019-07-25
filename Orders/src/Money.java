import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {
    public BigDecimal amount;

    public Money(BigDecimal _amount) {
    	this.amount = _amount;
    }

    public Money(String _amount)
    {
        amount = new BigDecimal(_amount);
    }
    
    public Money add(Money _value) {
    	return new Money(this.amount.add(_value.amount));    	
    }
    
    public Money calculateTax(String _percentage) {
    	BigDecimal calculatePercentage = new BigDecimal(_percentage.replaceAll("%", "")).movePointLeft(2); //divide by 100
    	BigDecimal _percentagePrice = this.amount.multiply(calculatePercentage); 
    	return new Money(_percentagePrice);
    }
    
    public Money addTax(String _percentage) {
    	BigDecimal calculatePercentage = new BigDecimal(_percentage.replaceAll("%", "")).movePointLeft(2); //divide by 100
    	BigDecimal _percentagePrice = this.amount.multiply(calculatePercentage);
    	BigDecimal _percentagePriceBG = Money.roundToNearest5Cents(this.amount.add(_percentagePrice));
    	return new Money(_percentagePriceBG);    	
    }
    
    public static BigDecimal roundToNearest5Cents(BigDecimal value) {
        return value.setScale(2, RoundingMode.FLOOR).multiply(new BigDecimal(20)).add(new BigDecimal("0.5"))
                .setScale(0, RoundingMode.FLOOR).divide(new BigDecimal(20)).setScale(2, RoundingMode.FLOOR);
    }    
    
    public static BigDecimal roundUpToNearest5Cents(BigDecimal value) {
        	
    	BigDecimal b1 = value.divide(new BigDecimal(0.05));
    	BigDecimal b2 = b1.setScale(2, RoundingMode.CEILING);
    	BigDecimal b3 = b2.multiply(new BigDecimal(0.05));
    	
    	return b3;
    	
    	
//    	return value.divide(new BigDecimal(0.05)).setScale(2, RoundingMode.CEILING).multiply(new BigDecimal(0.05));
    	
    	
    }    


}
