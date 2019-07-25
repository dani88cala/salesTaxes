import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;

public class Tax {

    private String name;
    private String rate;
    private ArrayList<ProductType> exemptionTypes = null;
    private ProductOrigin origin = null;

    public static Tax create(String _name, String _rate, ArrayList<ProductType> _exemptionType){
        return new Tax(_name, _rate, _exemptionType);
    }

    public static Tax create(String _name, String _rate, ProductOrigin _origin){
        return new Tax(_name, _rate, _origin);
    }

    private Tax(String _name, String _rate, ArrayList<ProductType> _exemptionType){
        this.name = _name;
        this.rate =_rate;
        this.exemptionTypes = _exemptionType;
    }

    private Tax(String _name, String _rate, ProductOrigin _origin){
        this.name = _name;
        this.rate =_rate;
        this.origin = _origin;
    }
    
    public boolean freeTax(ProductType _productType) {
    	boolean _freeTax = false;
    	if (this.exemptionTypes != null) {
    		Iterator<ProductType> iter = this.exemptionTypes.iterator();
    		while (!_freeTax && iter.hasNext()) {
    			ProductType p = iter.next();
    			if (_productType.toString() == p.toString()) {
    				_freeTax = true;
    			}    		
    		}
    	}
    	return _freeTax;    	
    }

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
        
       
    

}