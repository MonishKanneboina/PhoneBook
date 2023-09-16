public class Person
{
   private String name;
   
   public Person(String name)
   {
      this.name = name;
   }
   
   @Override
   public int hashCode()
   {
      int total = 0;
      for (int i = 0; i < name.length(); i++) 
      {
            total += (int) name.charAt(i);
      }
      return total;
   }
   
   @Override
   public boolean equals(Object person)
   {
      Person p = (Person) person;
      if(this.toString().equals(p.toString()))
      {
         return true;
      } 
      return false;
   }
   
   @Override
   public String toString()
   {
      return name;
   }  
}