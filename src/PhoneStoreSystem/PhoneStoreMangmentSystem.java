package PhoneStoreSystem;
import MainClasses.*;
import DesignPatterns.*;
import Database.*;




public class PhoneStoreMangmentSystem {
    public static void main(String[] args) {
    
        
        Phone phone=new Phone( "S6" ,"Apple" ,1900,10 );
        PhoneStore store=PhoneStore.getInstance();
        store.addPhone(phone);  
        store.getOrderManager().moveOrderToNextStatus(25);
        
    }
 }
    

