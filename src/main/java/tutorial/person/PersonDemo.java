package tutorial.person;

// Demo of persons with name and age
public class PersonDemo
{
    public static void main(String[] args)
    {
        Person guy = new Person("Freddy", 21);
        Person gal = new Person("Jenny", 17);

        // System.out.println(guy.name + " is " + guy.getAge() + " years old");
        show(guy);
        show(gal);

        System.out.println("Happy Birthday to " + gal.name + " and " + gal.name + "!");
        gal.celebrateBirthday();
        guy.celebrateBirthday();

        show(guy);
        show(gal);
    }

    private static void show(Person person)
    {
        System.out.println(person.name + " is " + person.getAge() + " years old");
    }
}
