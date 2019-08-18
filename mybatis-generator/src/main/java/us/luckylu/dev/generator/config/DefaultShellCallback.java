package us.luckylu.dev.generator.config;

import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.util.messages.Messages;

import java.io.File;
import java.util.StringTokenizer;

/**
 * mapper文件分出base、common
 * @author lu
 * @create 2019-03-22 16:40
 */
public class DefaultShellCallback implements ShellCallback {

    private boolean overwrite;

    public DefaultShellCallback(boolean overwrite) {
        this.overwrite = overwrite;
    }

    @Override
    public File getDirectory(String targetProject, String targetPackage) throws ShellException {
        File project = new File(targetProject);
        if (!project.isDirectory()) {
            throw new ShellException(Messages.getString("Warning.9", targetProject));
        } else {
            StringBuilder sb = new StringBuilder();
            StringTokenizer st = new StringTokenizer(targetPackage, ".");

            while(st.hasMoreTokens()) {
                sb.append(st.nextToken());
                sb.append(File.separatorChar);
            }
            // 将生成mapper文件作为基类继承
            File directory = new File(project, sb.toString());
            if (directory.getAbsolutePath().contains("resources")) {
                String baseDirectoryPath = directory.getAbsolutePath() + File.separator + "base" + File.separator;
                File baseDirectory = new File(baseDirectoryPath);
                if (!baseDirectory.exists()) {
                    baseDirectory.mkdirs();
                }

                String autoDirectoryPath = directory.getAbsolutePath() + File.separator + "common" + File.separator;
                File autoDirectory = new File(autoDirectoryPath);
                if (!autoDirectory.exists()) {
                    autoDirectory.mkdirs();
                }
            }

            boolean rc;
            if (!directory.isDirectory()) {
                rc = directory.mkdirs();
                if (!rc) {
                    throw new ShellException(Messages.getString("Warning.10", directory.getAbsolutePath()));
                }
            }

            directory = new File(project, sb.toString());
            if (!directory.isDirectory()) {
                rc = directory.mkdirs();
                if (!rc) {
                    throw new ShellException(Messages.getString("Warning.10", directory.getAbsolutePath()));
                }
            }

            return directory;
        }
    }

    @Override
    public void refreshProject(String project) {
    }

    @Override
    public boolean isMergeSupported() {
        return false;
    }

    @Override
    public boolean isOverwriteEnabled() {
        return this.overwrite;
    }

    @Override
    public String mergeJavaFile(String newFileSource, File existingFile, String[] javadocTags, String fileEncoding) throws ShellException {
        throw new UnsupportedOperationException();
    }
}
