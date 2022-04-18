import dao.Sql2oAtomUrlDao;
import models.AtomUrl;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        String connectionString = "jdbc:postgresql://ec2-44-194-4-127.compute-1.amazonaws.com:5432/dah5gta1vfs0on?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
        Sql2o sql2o = new Sql2o(connectionString,"yfqndepxbxcwmf","ce6192728995d2f69df9629b86d6e8e8560c2f954e20f0cb9d77559a0bfdd3de");

//        String connectionString = "jdbc:postgresql://localhost:5432/atomurl";
//        Sql2o sql2o = new Sql2o(connectionString,"ftm","sparkpass");
        Sql2oAtomUrlDao atomUrlDao = new Sql2oAtomUrlDao(sql2o);

        //index page route and controller
        //display a form to create a new atomUrl
        get("/", (request, response)->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //create new atomUrl
        post("/new",(request,response)->{
            Map<String, Object>model = new HashMap<>();
            String error;
            String long_url = request.queryParams("longUrl");
            String alias = request.queryParams("alias");
            if(long_url.isEmpty()){
                error = "Enter a URL to shorten";
                model.put("message", error);
            }
            if(alias.isEmpty()){
                alias = UUID.randomUUID().toString().substring(0,6); //generate a random alphanumeric alias
                System.out.println(String.format("%s, %s",long_url,alias));
                AtomUrl newAtomUrl = new AtomUrl(long_url, alias);
                atomUrlDao.createAtomUrl(newAtomUrl);
                model.put("message", "AtomURL Created Successfully!");
                AtomUrl createdAtomUrl = atomUrlDao.findAtomUrlByAlias(alias);
                model.put("atomurl",createdAtomUrl);
                return new ModelAndView(model, "index.hbs");
            }else{
                System.out.println(String.format("%s, %s",long_url,alias));
                AtomUrl newAtomUrl = new AtomUrl(long_url, alias);
                atomUrlDao.createAtomUrl(newAtomUrl);
                model.put("message", "AtomURL Created Successfully!");
                AtomUrl createdAtomUrl = atomUrlDao.findAtomUrlByAlias(alias);
                model.put("atomurl",createdAtomUrl);
                return new ModelAndView(model, "index.hbs");
            }
        },new HandlebarsTemplateEngine());

        //update atomUrl
        post("/update", (request, response)->{
            Map<String, Object> model = new HashMap<>();
            String msg;
            String title = request.queryParams("title");
            String long_url = request.queryParams("longUrl");
            String alias = request.queryParams("alias");
            int id = Integer.parseInt(request.queryParams("id"));
            System.out.println(String.format("%s, %s", "%s" , "%d",long_url,alias, title, id));
            if(title.isEmpty() && long_url.isEmpty() && alias.isEmpty()){
                msg = "No Changes made";
            }else{
                atomUrlDao.updateAtomUrl(id, alias, long_url, title);
                msg = "AtomURL Updated Successfully!";
                AtomUrl updatedAtomUrl = atomUrlDao.findAtomUrlById(id);
                model.put("atomurl", updatedAtomUrl);
            }
            model.put("message", msg);
            return new ModelAndView(model, "index.hbs");
        },new HandlebarsTemplateEngine());

        //delete atomUrl
        post("/delete", (request, response)->{
            Map<String, Object> model = new HashMap<>();
            String msg;
            Integer id = Integer.parseInt(request.queryParams("id"));
            String alias = request.queryParams("alias");
            if(id == null && alias.isEmpty()){
                msg = "Please enter an alias or id to delete";
            }else if(id != null){
                atomUrlDao.deleteAtomUrlById(id);
                msg = "AtomURL Deleted Successfully!";
            }else if(!alias.isEmpty()){
                atomUrlDao.deleteAtomUrlByAlias(alias);
                msg = "AtomURL Deleted Successfully!";
            }else {
                msg = "Something went wrong";
            }
            model.put("message", msg);

            return new ModelAndView(model, "index.hbs");
        },new HandlebarsTemplateEngine());

        get("/:alias", (request, response)->{
            Map<String, Object> model = new HashMap<>();
            String alias = request.params("alias");
            if(alias.isEmpty()){
                response.redirect("/");
            }else{
                AtomUrl foundAtomUrl = atomUrlDao.findAtomUrlByAlias(alias);
                if(foundAtomUrl == null){
                    return new ModelAndView(model, "404.hbs");
                }else{
                    String long_url = foundAtomUrl.getLong_url();
                    response.redirect(long_url);
                }
            }
            return null;
        }, new HandlebarsTemplateEngine());

        get("/expand/:alias", (request, response)->{
            Map<String, Object> model = new HashMap<>();
            String page = "expand";
            model.put("page",page);
            String alias = request.params("alias");
            if(alias.isEmpty()){
                response.redirect("/");
            }else{
                AtomUrl foundAtomUrl = atomUrlDao.findAtomUrlByAlias(alias);
                if(foundAtomUrl == null){
                    return new ModelAndView(model, "404.hbs");
                }else{
                    model.put("atomurl",foundAtomUrl);
                    return new ModelAndView(model, "index.hbs");
                }
            }
            return null;
        }, new HandlebarsTemplateEngine());



    }
}
