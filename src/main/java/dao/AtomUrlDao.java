package dao;

import models.AtomUrl;

import java.util.List;

public interface AtomUrlDao {
    //create a new AtomUrl #Create Operation
    void createAtomUrl(AtomUrl atomurl);

    //get a specific AtomUrl #Read Operation
    AtomUrl findAtomUrlById(int id);
    AtomUrl findAtomUrlByAlias(String alias);
    //List all AtomUrls
    List<AtomUrl> getAllAtomUrls();

    //Update an AtomUl #Update Operation
    void updateAtomUrl(int id, String alias, String long_url, String title);

    //Delete a hero #Delete Operation
    void deleteAtomUrlById(int id);
    void deleteAtomUrlByAlias(String alias);
    //Delete all AtomUrls
    void deleteAllAtomUrls();
}
