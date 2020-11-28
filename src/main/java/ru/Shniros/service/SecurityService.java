package ru.Shniros.service;

import ru.Shniros.DBase.DAO.DaoFactory;
import ru.Shniros.DBase.DAO.PersonDao;
import ru.Shniros.DBase.domain.Person;
import ru.Shniros.exception.CommonServiceException;

import java.sql.Connection;

public class SecurityService {
    private final PersonDao personDao;
    private final  DigestService digestService;
    public SecurityService(PersonDao personDao, DigestService digestService) {
        this.personDao = personDao;
        this.digestService = digestService;
    }

    public Person login(String email, String password){

        Person curPerson = personDao.findByEmail(email);
        try {
            if (curPerson != null) {
                if (digestService.getHash(password).equals(
                        curPerson.getPassword())) {
                    return curPerson;
                } else {
                    throw new CommonServiceException(SecurityService.class.getName(),"Wrong password: " + password);
                }
            } else {
                throw new CommonServiceException(SecurityService.class.getName(),"Wrong email: " + email);
            }
        }catch (Exception ignored){}
        return null;
    }

    public Person registration(Person person){

        try (Connection connection =  DaoFactory.getDataSource().getConnection()){
            if(personDao.findByEmail(person.getEmail()) == null){
                return personDao.insert(person,connection);

            }else{
               throw new CommonServiceException(SecurityService.class.getName(),
                       "Email already exists: " + person.getEmail());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}