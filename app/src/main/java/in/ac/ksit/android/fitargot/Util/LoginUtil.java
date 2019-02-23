package in.ac.ksit.android.fitargot.Util;


public class LoginUtil {

    public String name;
    public String id;

    public LoginUtil(String name, String id) {
        this.id = id;
        this.name = name;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
