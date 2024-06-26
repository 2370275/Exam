package bean;

public class School {
    private String cd;
    private String name;

    public School(String cd, String name) {
        this.cd = cd;
        this.name = name;
    }

    public String getCd() {
        return cd;
    }

    public String getName() {
        return name;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public void setName(String name) {
        this.name = name;
    }
}

