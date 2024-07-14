package org.example;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GridFsTemplateUnitTest {
    @Resource
    private GridFsTemplate gridFsTemplate;

    @Before
    public void setUp() {
        Query query = Query.query(Criteria.where("filename").is("testFile"));
        gridFsTemplate.delete(query);
        InputStream inputStream = new ByteArrayInputStream("hello".getBytes());
        ObjectId objectId = gridFsTemplate.store(inputStream, "testFile", "txt");
    }


    @Test
    public void downloadTest() throws IOException {
        Query query = Query.query(Criteria.where("filename").is("testFile"));
        GridFSFile gridFSFile = gridFsTemplate.findOne(query);
        assert gridFSFile != null;
        GridFsResource resource = gridFsTemplate.getResource(gridFSFile);
        String filename = resource.getFilename();
        String ext = resource.getContentType();
        InputStream in = resource.getInputStream();

        FileOutputStream out = new FileOutputStream(filename + "." +ext);
        IOUtils.copy(in, out);
    }

    @Test
    public void uploadTest() {
        InputStream inputStream = new ByteArrayInputStream("hello".getBytes());
        ObjectId objectId = gridFsTemplate.store(inputStream, "testFile", "txt");
        Assert.assertNotNull(objectId);
    }

}
