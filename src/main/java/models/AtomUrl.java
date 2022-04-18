package models;

import java.util.Objects;

public class AtomUrl {
    private String title;
    private String long_url;
    private int id;
    private String alias;

    public AtomUrl(String long_url, String alias){
        this.long_url = long_url;
        this.alias = alias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AtomUrl)) return false;
        AtomUrl atomUrl = (AtomUrl) o;
        return id == atomUrl.id && Objects.equals(title, atomUrl.title) && long_url.equals(atomUrl.long_url) && alias.equals(atomUrl.alias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, long_url, id, alias);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLong_url() {
        return long_url;
    }

    public void setLong_url(String long_url) {
        this.long_url = long_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
