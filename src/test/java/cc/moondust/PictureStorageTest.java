package cc.moondust;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

/**
 * Created by jiang on 2017/4/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PictureStorageTest {

    @Autowired
    GridFsOperations gridFsOperations;

    @Autowired
    GridFsTemplate gridFsTemplate;

    @Test
    public void storePictureToGirdFs(){

        try {
            InputStream inputStream = new FileInputStream("D:/1.jpg");
            DBObject metaData = new BasicDBObject();
            metaData.put("createBy","jiang");
            GridFSFile lol = gridFsTemplate.store(inputStream, "lol", metaData);
            System.out.println(lol.getId());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findPictureById() throws IOException {
        String id = "58e644dd104d642c24d2454c";
        GridFSDBFile gridFsdbFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        InputStream in = gridFsdbFile.getInputStream();
        String filename = gridFsdbFile.getFilename();
        FileOutputStream out = new FileOutputStream("F:/" + filename);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
            out.write(buffer, 0, bytesRead);
        }

        in.close();
        out.close();
    }
}
