package classes;

import classes.structure.Profile;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.list;

/**
 * Created by jaroslaw on 08.06.17.
 */
@SuppressWarnings("ALL")
public class JSONParser {

    private Gson gson = new Gson();
    private List<Profile> profiles = new ArrayList<Profile>();
    private String location;

    public JSONParser(String location) {
        this.location = location;
    }

    public boolean isJSONFile(String name) {

        String ext = "";
        int i = name.lastIndexOf('.');
        if (i >= 0) {
            ext = name.substring(i+1);
        }
            if(ext.equals("json"))
                return true;
        return false;
    }

    private int filesNumber() {

        File dir = new File(location + "/data");
        File[] files = dir.listFiles();
        int z = 0;

        for(File file : files) {
            if(isJSONFile(file.getName()))
                z++;
        }
        return z;
    }

    List<Profile> getProfilesList() {
        profiles = new ArrayList<Profile>();
        for (int i = 1; i <= filesNumber(); i++) {
            try {
                loadProfile(i);
            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
                e.printStackTrace();
            }
        }
        return profiles;
    }

    public void loadProfile(int i) throws FileNotFoundException{
        profiles.add(gson.fromJson(new FileReader(this.location + "/data/f" + i + ".json"), Profile.class));
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }
}
