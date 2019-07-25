public class Product{
    String name;
    ProductType type;
    ProductOrigin origin;
    Money price;
    
    public Product(String _name, ProductType _type, ProductOrigin _origin, Money _price){
        this.name = _name;
        this.type = _type;
        this.origin = _origin;
        this.price = _price;
    }
    
    public String getName(){
        return this.name;
    }

    public ProductType getType(){
        return this.type;
    }

    public ProductOrigin getOrigin(){
        return this.origin;
    }

}
