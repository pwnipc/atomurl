package dao;

import models.AtomUrl;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oAtomUrlDao implements AtomUrlDao{
    private final Sql2o sql2o;

    //constructor to initialize all sql2oAtomUrlDao instances with sql2o
    public Sql2oAtomUrlDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    //Get postgresql jdbc drivers
    public void getDrivers(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createAtomUrl(AtomUrl atomurl) {
        getDrivers();
        String sql = "INSERT INTO atomurls (title,long_url,alias) VALUES (:title,:long_url,:alias)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(atomurl)
                    .executeUpdate()
                    .getKey();
            atomurl.setId(id);
        }catch(Sql2oException e){
            System.out.println(e);
        }
    }

    @Override
    public AtomUrl findAtomUrlById(int id) {
        getDrivers();
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM atomurls WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(AtomUrl.class);
        }
    }

    @Override
    public AtomUrl findAtomUrlByAlias(String alias) {
        getDrivers();
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM atomurls WHERE alias = :alias")
                    .addParameter("alias", alias)
                    .executeAndFetchFirst(AtomUrl.class);
        }
    }

    @Override
    public List<AtomUrl> getAllAtomUrls() {
        getDrivers();
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM atomurls")
                    .executeAndFetch(AtomUrl.class);
        }
    }

    @Override
    public void updateAtomUrl(int id, String alias, String long_url, String title) {
        getDrivers();
        String sql = "UPDATE atomurls SET (alias, long_url, title) = (:alias, :long_url, :title) WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("alias", alias)
                    .addParameter("long_url", long_url)
                    .addParameter("title", title)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteAtomUrlById(int id) {
        getDrivers();
        String sql = "DELETE FROM atomurls WHERE id =:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }catch (Sql2oException e){
            System.out.println(e);
        }
    }

    @Override
    public void deleteAtomUrlByAlias(String alias) {
        getDrivers();
        String sql = "DELETE FROM atomurls WHERE alias =:alias";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("alias", alias)
                    .executeUpdate();
        }catch (Sql2oException e){
            System.out.println(e);
        }
    }

    @Override
    public void deleteAllAtomUrls() {
        getDrivers();
        String sql = "DELETE from atomurls";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .executeUpdate();
        }catch(Sql2oException e){
            System.out.println(e);
        }
    }
}
