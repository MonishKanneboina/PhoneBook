public class PhoneBook implements IMap 
{
   private class Entry
   {
      private Person person;
      private PhoneNumber phoneNumber;
      private Entry next;
   
      public Entry(Person p, PhoneNumber pN)
      {
         person = p;
         phoneNumber= pN;
      }
      
      public PhoneNumber getNumber()
      {
         return phoneNumber;
      }
   
      @Override
      public String toString()
      {
         return person.toString() + " " + phoneNumber.toString();
      }
      
      public void setNumber(PhoneNumber v)
      {
         phoneNumber = v;
      }
      
     
   }
   
   private Entry[] entryA;
   private int size;
   private int capacity;
   private double limit;
   
   public PhoneBook(int cap)
   {
      entryA = new Entry[cap];
      capacity = cap;
      size = 0;
      limit = .5;
   }
   
   public PhoneBook()
   {
      entryA = new Entry[11];
      capacity = 11;
      size = 0;
      limit = .5;
   }
 
   public PhoneNumber put(Person key, PhoneNumber value)
   {
      if(size > capacity*limit)
      {
         resizeAndRehash();
      }
      int suggested = key.hashCode() % capacity;
      Entry last = null;
      Entry current = entryA[suggested];
      while (current != null)
         {
            if (current.person.equals(key))
               {
                  PhoneNumber nP = current.phoneNumber;
                  current.setNumber(value);
                  return nP;
               }
            last = current;
            current = current.next;
         }
      Entry n = new Entry(key, value);
      if (last == null)
         {
            entryA[suggested] = n;
            size++;
            return null;
         }
      else
         {
            last.next = n; 
            size++;
            return null;
         }
              
   }
   public PhoneNumber get(Person key)
   {
      int suggested = key.hashCode() % capacity;
      if (entryA[suggested] != null)
      {
         Entry current = entryA[suggested];
         while(current.next != null)
         {
            if(current.person.equals(key))
               break;
            current = current.next;
         }
         return current.phoneNumber;
      }
      return null;
   }
    
   public PhoneNumber remove(Person key)
   {
      int suggested = key.hashCode() % capacity;
      if (entryA[suggested] != null)
      {
      if (entryA[suggested].person.equals(key))
         {
            Entry head = entryA[suggested];
            size--;
            PhoneNumber nP = head.phoneNumber;
            entryA[suggested] = head.next;
            return nP;
         }
      }
      if (entryA[suggested] != null)
            {
               Entry current = entryA[suggested];
               while(current.next != null)
                  {
                     if(current.next.person.equals(key))
                        {
                           PhoneNumber nP = current.next.phoneNumber;
                           size--;
                           current.next = current.next.next;
                           return nP;
                          }
                      current = current.next;
                  }
            }
      return null;
   }
   public int size()
   {
      return size;
   }

   public void printHashTable()
   {
      for(int i = 0; i < entryA.length; i++)
         {
            System.out.print("Index " + i + " >> "); 
            if (entryA[i] == null)
               {
                  System.out.print("Empty");
                  System.out.println();
                  continue;
               }
            Entry current = entryA[i];
            if(current.next == null)
               {
                  System.out.print(current.toString());
               }
            if (current.next != null)
               {
                  while(current.next != null)
                     {
                        System.out.print(current.toString() + " >> ");
                        current = current.next;
                     }
                   System.out.print(current.toString());
               }
             System.out.println();
         }
   }
   
   public void resizeAndRehash()
   {
      Entry[] newEA = entryA;
      capacity = capacity * 2;
      size = 0;
      entryA = new Entry[capacity];
      for(int i = 0; i < newEA.length; i++)
      {
         while(newEA[i] != null)
         {
            PhoneNumber nP = put(newEA[i].person, newEA[i].phoneNumber);
            newEA[i] = newEA[i].next;
         }
      }    
       
   }
   
   public void setLimit(double percentage)
      {
         limit = percentage;
      }

}