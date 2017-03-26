package cc.moondust.entity.mongo;


import org.springframework.data.annotation.Id;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiang on 2017/3/26.
 */
public class Paper {

    @Id
    private String paperId;

    private String paperName;

    private Map<String,List<String>> paperQuestions = new HashMap<>();

    public String getPaperId() {

        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public Map<String, List<String>> getPaperQuestions() {
        return paperQuestions;
    }

    public Paper addPaperQuestions(String key, List<String> value){
        this.paperQuestions.put(key, value);
        return this;
    }
}
