package be.iccbxl.pid.reservationsSpringBoot;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        List<ArtistType> artistTypes = List.of(
                new ArtistType(new Artist("Bruce", "Willis"), new Type("Acteur")),
                new ArtistType(new Artist("Tom", "Cruise"), new Type("Acteur")),
                new ArtistType(new Artist("Bruce", "Willis"), new Type("Scenariste")),
                new ArtistType(new Artist("Tom", "Cruise"), new Type("Scenariste")),
                new ArtistType(new Artist("Will", "Smtih"), new Type("Slapper"))
        );

        Map<Type, List<ArtistType>> map = new HashMap<>();
        for (ArtistType artistType : artistTypes) {
            Type key = artistType.getType();
            if (map.get(key) == null) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(artistType);
        }

        System.out.println(map);
    }
}

@AllArgsConstructor
@Data
class Artist {
    private String firstname, lastname;
}

@AllArgsConstructor
@Data
class Type {
    private String name;
}

@AllArgsConstructor
@Data
class ArtistType {
    private Artist artist;
    private Type type;
}
