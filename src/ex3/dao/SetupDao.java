package ex3.dao;


import java.io.File;
import java.net.URL;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;

public class SetupDao extends AbstractDao {

    public void createSchema() {
        executeSqlFromFile(getClassPathFile("schema.sql"));
    }

    private String getClassPathFile(String fileName) {
        URL url = getClass().getClassLoader().getResource(fileName);
        if (url == null) {
            throw new IllegalStateException("can't find " + fileName);
        }

        return getClass().getClassLoader().getResource(fileName).getFile();
    }

    private void executeSqlFromFile(String sqlFilePath) {

        Project project = new Project();
        project.init();

        SQLExec e = new SQLExec();
        e.setProject(project);
        e.setTaskType("sql");
        e.setTaskName("sql");
        e.setSrc(new File(sqlFilePath));
        e.setDriver("org.hsqldb.jdbcDriver");
        e.setUserid("sa");
        e.setPassword("");
        e.setUrl(DB_URL);
        e.execute();
    }
}
