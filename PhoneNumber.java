public class PhoneNumber
{
   private String number;
   
   public PhoneNumber(String number)
   {
      this.number = number;
   }
   
   @Override 
   public String toString()
   {
      return "" + number;
   }
  
}