package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import models.Results;
import models.Root;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import rest.Get;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Characters {
    static String url = "https://rickandmortyapi.com/api/character";
    static List<String> listUrls = new ArrayList<>();
    static ObjectMapper om;

    @BeforeClass
    public static void startData() throws JsonProcessingException {
        listUrls.add(url);
        om = new ObjectMapper();
        while (true) {
            Root rootResults = om.readValue(Get.GetRequest(url), Root.class);
            if (!Objects.equals(rootResults.info.next, null)) {
                listUrls.add(rootResults.info.next);
                url = rootResults.info.next;
            } else {
                break;
            }
        }
        for (String l : listUrls) {
            System.out.println(l);
    }
}

    @Test
    public void test() throws JsonProcessingException {
        for (String l : listUrls) {
            Root model = om.readValue(Get.GetRequest(url), Root.class);
            for (Results r : model.results) {
                System.out.println("------------------------------");
                System.out.println(r);
            }
        }
    }
}

