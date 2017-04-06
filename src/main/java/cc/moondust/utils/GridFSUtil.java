package cc.moondust.utils;

import cc.moondust.exception.UnKnowException;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

import java.io.InputStream;

/**
 * Created by huang.yufeng on 2017/4/6.
 */
public class GridFSUtil {

    private static String dbName = "homework";
    private static MongoClient mongoClient = new MongoClient("localhost", 27017);
    private static DB db = mongoClient.getDB(dbName);
    private static GridFS gridFS = new GridFS(db);

    public static void save(InputStream in, Object id, String fileName) throws UnKnowException {

        DBObject query  = new BasicDBObject("_id", id);
        GridFSDBFile gridFSDBFile = gridFS.findOne(query);
        if(gridFSDBFile == null){
            GridFSInputFile gridFSInputFile = gridFS.createFile(in);
            gridFSInputFile.setId(id);
            gridFSInputFile.setFilename(fileName);
//            gridFSInputFile.setChunkSize();
            //gridFSInputFile.setContentType();
            //gridFSInputFile.setMetaData();
            gridFSInputFile.save();
        }else {
            throw new UnKnowException(500, "文件名已存在");
        }
    }

    /**
     * 据id返回文件
     * @param id
     * @return
     */
    public static GridFSDBFile getById(Object id){
        DBObject query  = new BasicDBObject("_id", id);
        GridFSDBFile gridFSDBFile = gridFS.findOne(query);
        return gridFSDBFile;
    }

    /**
     * 据文件名返回文件，只返回第一个
     * @param fileName
     * @return
     */
    public static GridFSDBFile getByFileName(String fileName){
        DBObject query  = new BasicDBObject("filename", fileName);
        GridFSDBFile gridFSDBFile = gridFS.findOne(query);
        return gridFSDBFile;
    }

}
