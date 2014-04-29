package com.bionic.iakovenko.department.dao.interfaces;

import com.bionic.iakovenko.department.dao.entity.Person;
import java.util.List;

/**
 * The interface provides for working with a table Person
 *
 * @autor Alex Iakovenko
 * Date: 3/31/14
 * Time: 9:42 AM
 */
public interface IPerson {
    /**
     * Selects from table Person all notes.
     * @return list of Person.
     */
    public List<Person> findAll();

    /**
     * Selects from table Person one note by id.
     * @param personId person id number;
     * @return Person object.
     */
    public Person findPerson(String personId);

    /**
     * Selects from table Person one note by name.
     * @param familyName        person's familyName;
     * @param givenName         person's name;
     * @param additionalName    person's additional name;
     * @return                  Person object.
     */
    public List<Person> findPerson(String familyName, String givenName, String additionalName);

    /**
     * Selects from table Person one note by name.
     * @param login             person's login;
     * @return                  Person object.
     */
    public Person findPersonByLogin(String login);

    /**
     * Inserts note about Person to Person table.
     * @param person
     * @return true     if note about person has been inserted.
     *         false    if the exception has thrown.
     */
    public boolean insertPerson(Person person);

    /**
     * Removes note about person from Person table.
     * @param person
     * @return true     if note about person has been removed.
     *         false    if the exception has thrown.
     */
    public boolean deletePerson(Person person);

}
