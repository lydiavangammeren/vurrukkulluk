package com.lydia.vurrukkulluk.util;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Getter
@Component
public class VulgarityFilter {
    private List<String> blackList = new ArrayList<>();

    public VulgarityFilter() {
        readBlackList();
    }

    public String doFilter(String filterText){

        for (String word : blackList) {
            filterText = filterText.replace(" "+ word + " ", " *** ");
            filterText = filterText.replace(" "+ word + ".", " *** ");
            filterText = filterText.replace(" "+ word + ",", " *** ");
            filterText = filterText.replace(" "+ word + "!", " *** ");
            filterText = filterText.replace(" "+ word + "?", " *** ");
        }
        return filterText;
    }


    private void readBlackList(){
        String pathNl = "src\\main\\resources\\nl.txt";
        String pathEn = "src\\main\\resources\\en.txt";

        readBlacklistFile(pathNl);
        readBlacklistFile(pathEn);

    }

    private void readBlacklistFile(String path){

        try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
            stream.forEach(blackList::add);
        } catch (IOException | NullPointerException e) {
            blackList = null;
        }

    }


}
