import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;


public class Order {
    
    private ArrayList<Tax> taxToApply;
    private ArrayList<Product> productsInOrder;
    private ArrayList<int[]> quantity;
    private Money salesTaxes; 
    private Money total; //price with taxes
    private Money totalBeforeTaxes;

    public Order(ArrayList<Tax> _taxToApply){
        this.taxToApply  = new ArrayList<Tax>(_taxToApply);
        this.productsInOrder = new ArrayList<Product>();
        this.quantity = new ArrayList<int[]>();
        this.salesTaxes = new Money("0");
        this.total = new Money("0");
        this.totalBeforeTaxes = new Money("0");
    }
    
    //in case I want to create an order without taxes
    public Order(){
        this.taxToApply  = new ArrayList<Tax>();
        this.productsInOrder = new ArrayList<Product>();
        this.quantity = new ArrayList<int[]>();
        this.salesTaxes = new Money("0");
        this.total = new Money("0");
        this.totalBeforeTaxes = new Money("0");
    }

    public void add(Product _productToAdd, int[] _quantity){    	
        this.productsInOrder.add(_productToAdd);

        
        Money quantityPrice = new Money (_productToAdd.price.amount);
        this.totalBeforeTaxes = this.totalBeforeTaxes.add(quantityPrice);
                
        calulateBasicTaxes(_productToAdd);                
        calculateImportedTaxes(_productToAdd);
        
         //calculateTotalWithoutTaxes(_productToAdd.price);
        //calculateTotalWithTaxes(_productToAdd);
        //calculateTaxes();
        
                
        this.totalBeforeTaxes.amount.setScale(2, RoundingMode.CEILING);
        this.salesTaxes.amount.setScale(2, RoundingMode.CEILING);
        this.total.amount.setScale(2, RoundingMode.CEILING);
    }
    
    private void calulateBasicTaxes(Product _product) {
    	ProductType _productType = _product.getType();
    	Money basicTax = new Money("0");
    	for (Tax tax : this.taxToApply) {
    		if (!tax.freeTax(_productType)) {
       			this.salesTaxes = this.salesTaxes.add(basicTax.calculateTax("10%"));       			
    			//this.total.addTax("10%");
    		}
    		else {

    		}
    			
    	}    	
    }

    private void calculateTotalWithoutTaxes(Money _price){
    	this.total.add(_price);
    }
    /*
    private void calculateTotalWithTaxes(Product _product){
    	Money firstTax = null, secondTax = null;
        for (Tax tax : taxToApply){
            if (!tax.freeTax(_product.type)){                
            	firstTax = _product.price.addTax(tax.getRate()); //apply 10%
            }
            if (_product.getOrigin().toString() == "IMPORTED"){
            	secondTax = _product.price.addTax(tax.getRate());// apply 5%
            }
            total = firstTax.add(secondTax).amount;
        }
    }*/
    
    private void calculateImportedTaxes(Product _product){
    	Money importedTax = new Money("0");
    	if (_product.origin.toString() == "IMPORTED") {
    		this.salesTaxes = this.salesTaxes.add(importedTax).calculateTax("5%");
    		//this.total = _product.price.addTax("5%");
    	}
    }
    
    
    public Money getTotal() {
    	return total;
    }
    
    public Money getSalesTaxes() {
    	return salesTaxes;
    }
    
    
    public void printBill(){
    	System.out.print("Your order is:\n");
    	for (int i = 0; i < productsInOrder.size(); i++) {
    		//System.out.printf("%d \t %s \n", quantity.get(i), productsInOrder.get(i).getName());
    		System.out.println( Arrays.toString(quantity.get(i)) + "\t" + productsInOrder.get(i).getName());
    		System.out.printf("stampa il prezzo con le tasse");
    	}
    	System.out.printf("\t TOTAL WITH TAXES:%s", total.amount.toString());
    	System.out.printf("\t TOTAL BEFORE TAXES:%s", totalBeforeTaxes.amount.toString());
    }

}