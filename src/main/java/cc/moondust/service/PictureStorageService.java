package cc.moondust.service;

import cc.moondust.exception.ParamsException;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by jiang on 2017/4/9.
 */
@Service
public class PictureStorageService {

    @Autowired
    GridFsTemplate gridFsTemplate;

    /**
     * 存储图片/文件
     * @param dbObject 数据源
     * @param filePath 文件路径
     * @param fileName 文件名
     * @return 存储数据库的文件id
     * @throws ParamsException
     */
    public Object storePicture(DBObject dbObject, String filePath, String fileName) throws ParamsException {
        try {
            InputStream inputStream = new FileInputStream(filePath);
            GridFSFile store = gridFsTemplate.store(inputStream, fileName, dbObject);
            return store.getId();
        } catch (Exception e) {
            throw new ParamsException(500, "文件不存在");
        }
    }

    /**
     * 获取图片/文件
     * @param id
     * @return
     */
    public GridFSDBFile findPictureById(String id){
       return gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
    }



    public void writeFileToMongo(){

    }


}
