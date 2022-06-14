package tutorial;

import tutorial.person.Person;

public class Arrays
{
    public static void main(String[] args)
    {
        Person one_person = new Person("Fred", 21);
        System.out.println(one_person.name);
        
        // Arrays keep more than one thing of a type
        Person[] the_smiths = new Person[4];
        // Creating a new Whatever[count] array creates just the array,
        // need to then set the [elements]:
        the_smiths[0] = new Person("Dad", 47);
        the_smiths[1] = new Person("Mum", 48);
        the_smiths[2] = new Person("Daughter", 17);
        the_smiths[3] = new Person("Son", 14);

        // May also combine creating the array and setting the elements:
        Person[] the_millers = new Person[]
        {
            new Person("Grandpa", 78),
            new Person("Grandma", 75),
            new Person("Husband", 52),
            new Person("Wife", 50),
            new Person("Son", 21)
        };
        
        // Access specfic element
        System.out.println(the_millers[1].name);

        // Loop over all elements by index
        for (int i=0; i<the_smiths.length; ++i)
            System.out.println("Element " + i + " is " + the_smiths[i].name);

        // If index doesn't matter, this is shorter
        for (Person person : the_millers)
            System.out.println(person.name);
    }
}
